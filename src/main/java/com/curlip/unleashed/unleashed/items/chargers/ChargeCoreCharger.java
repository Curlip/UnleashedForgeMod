package com.curlip.unleashed.items.chargers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.framework.interfaces.UnleashedCharger;

public class ChargeCoreCharger implements UnleashedCharger {
	private int charge = 0;
	
	private int meta = 0;
	
	public ChargeCoreCharger(){};
	
	public ChargeCoreCharger(int meta) {
		this.meta = meta;
	}
	
	@Override
	public String getID() {
		return "chargeCoreCharger";
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(UnleashedMod.instance.itemRegister.getByID("chargecore").getMinecraftItem(), 1, meta);
	}

	@Override
	public boolean use(InventoryPlayer inv) {
		if((charge - ChargeCore.decrement[meta]) > 0){
			charge -= ChargeCore.decrement[meta];
			return true;
		}
		
		return false;
	}

	@Override
	public void charge(InventoryPlayer inv) {
		if((charge + ChargeCore.increment[meta]) < getMaxCharge()){
			if(consumeItems(ChargeCore.items[meta], inv)){
				charge += ChargeCore.increment[meta];
			}
		}
	}

	@Override
	public int getCharge() {
		return charge;
	}

	@Override
	public int getMaxCharge() {
		return (meta + 1) * 64;
	}

	@Override
	public NBTTagCompound save() {
		NBTTagCompound compound = new NBTTagCompound();
		
		compound.setString("ID", this.getID());
		
		compound.setInteger("meta", meta);
		compound.setInteger("charge", charge);
		
		return compound;
	}

	@Override
	public void load(NBTTagCompound compound) {
		if(ownsNBT(compound)){
			meta = compound.getInteger("meta");
			charge = compound.getInteger("charge");
		}
	}

	@Override
	public boolean ownsNBT(NBTTagCompound compound) {
		boolean valid = true;
		
		valid = compound.getString("ID").equals(this.getID());
		
		return valid;
	}

	@Override
	public int getChargeColor() {
		return 0;
	}

	private boolean consumeItems(ItemStack stack, InventoryPlayer inv){
		boolean hasItems = true;

		for (int i = 0; i < stack.stackSize; i++) {
			if(!inv.hasItem(stack.getItem())){
				hasItems = false;
				break;
			}
		}

		if(hasItems){
			for (int i = 0; i < stack.stackSize; i++) {
				inv.consumeInventoryItem(stack.getItem());
			}
		}

		return hasItems;
	}
}
