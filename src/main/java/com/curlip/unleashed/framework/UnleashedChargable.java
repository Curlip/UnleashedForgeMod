package com.curlip.unleashed.framework;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.curlip.unleashed.ChargerRegistry;
import com.curlip.unleashed.framework.interfaces.UnleashedCharger;

public class UnleashedChargable extends SimpleItem {
	
	private UnleashedCharger defaultCharger;
	
	private boolean hasChargerNbt = false;

	public UnleashedChargable(String itemid, UnleashedCharger charger, boolean wip) {
		super(itemid, wip);
		
		this.defaultCharger = charger;
		
		this.setMaxStackSize(1);
		
		setMaxDamage(charger.getMaxCharge());
		setNoRepair();
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected){
		if(!compoundsReady(stack)){
			NBTTagCompound compound = new NBTTagCompound();

			stack.setTagCompound(compound);
			
			stack.getTagCompound().setTag("Charger", defaultCharger.save());
		}
		
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setTag("Charger", this.getCharger(stack).save());
	}
	
	@Override
	public int getDamage(ItemStack pstack){
		this.setMaxDamage(getCharger(pstack).getMaxCharge());
		
		if(pstack.hasTagCompound()){
			return getCharger(pstack).getMaxCharge() - getCharger(pstack).getCharge();
		}
		return getMaxDamage();
	}
	
	@Override    public boolean showDurabilityBar(ItemStack stack){  return compoundsReady(stack);  }

	
	/* UTILITIES */
	
	protected final boolean compoundsReady(ItemStack stack){
		return stack.hasTagCompound() && !(stack.getSubCompound("Charger", true).hasNoTags());
	}
	
	protected final UnleashedCharger getCharger(ItemStack stack){
		Class<? extends UnleashedCharger> charger;

		try {
			charger = ChargerRegistry.instance.getNBTOwner(stack.getSubCompound("Charger", true));
			
			if(charger != null){
				UnleashedCharger chargerObj = (UnleashedCharger) charger.newInstance();
				
				chargerObj.load(stack.getSubCompound("Charger", true));
				
				return chargerObj;
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		return defaultCharger;
	}
	
	protected final void charge(ItemStack stack, InventoryPlayer inv){
		getCharger(stack).charge(inv);
	}
	
	protected final boolean use(ItemStack stack, InventoryPlayer inv){
		return getCharger(stack).use(inv);
	}
}
