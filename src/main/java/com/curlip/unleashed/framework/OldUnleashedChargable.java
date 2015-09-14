package com.curlip.unleashed.framework;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.curlip.unleashed.UnleashedInfo;
import com.curlip.unleashed.framework.interfaces.OldUnleashedCharger;
import com.curlip.unleashed.framework.interfaces.UnleashedMetaItem;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

@Deprecated
public abstract class OldUnleashedChargable extends SimpleItem {

	private OldUnleashedCharger charger;
	private ItemStack stack;

	public OldUnleashedChargable(String itemid, ItemStack charger) {
		super(itemid);

		this.charger = (OldUnleashedCharger) charger.getItem();
		this.stack = charger;

		setMaxDamage(this.charger.getMaxCharge(stack));
		setNoRepair();
	}

	@Override
	public int getDamage(ItemStack pstack){
		if(pstack.getTagCompound() != null){
			int amount = pstack.getTagCompound().getInteger(charger.getNBTName(pstack));

			return getMaxDamage() - amount;
		}
		return getMaxDamage();
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack){  return true;  }

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(stack.getTagCompound() == null){
			stack.setTagCompound(new NBTTagCompound());
		}
	}

	//** Utilities **//
	
	public void charge(ItemStack pstack, EntityPlayer player){
		NBTTagCompound compound = pstack.getTagCompound();
		
		if(!(compound.getInteger(charger.getNBTName(pstack)) >= charger.getMaxCharge(pstack)) && player.worldObj.isRemote){
			
			if(consumeItems(charger.getIncrement(pstack), player.inventory)){
				
				String nbt = charger.getNBTName(pstack);
				
				compound.setInteger(nbt, (compound.getInteger(nbt)) + charger.getIncrement(pstack).stackSize);
				player.inventory.inventoryChanged = true;
			}
		}
	}
	
	public boolean isStackUsable(ItemStack pstack){
		if(pstack.getTagCompound().getInteger(charger.getNBTName(pstack)) > charger.getDecrement(stack)){
			return true;
		}
		return false;
	}

	public boolean consumeUse(ItemStack pstack, World world){
		String nbt = charger.getNBTName(pstack);
		
		if((pstack.getTagCompound().getInteger(nbt) > charger.getDecrement(stack)) && world.isRemote){

			pstack.getTagCompound().setInteger(nbt, (pstack.getTagCompound().getInteger(nbt) - charger.getDecrement(stack)));
			return true;
		}
		return false;
	}

	public boolean consumeItems(ItemStack stack, InventoryPlayer inv){
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

	public void setCharger(ItemStack charger){
		this.charger = (OldUnleashedCharger) charger.getItem();
		this.stack = charger;

		setMaxDamage(this.charger.getMaxCharge(stack));
		setNoRepair();
	}
}
