package com.curlip.unleashed.framework;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.curlip.unleashed.ChargerRegistry;
import com.curlip.unleashed.framework.interfaces.UnleashedCharger;

public class UnleashedChargable extends SimpleItem {
	
	private Item charge;

	public UnleashedChargable(String itemid, boolean wip, Item charge) {
		super(itemid, wip);
		
		this.charge = charge;
		
		setMaxStackSize(1);
		setMaxDamage(128);
		setNoRepair();
	}
	
	@Override
	public int getDamage(ItemStack stack){
		if(!stack.hasTagCompound()){
			NBTTagCompound compound = new NBTTagCompound();
			compound.setInteger("charge", 0);
			
			stack.setTagCompound(compound);
		}
		
		return 128 - stack.getTagCompound().getInteger("charge");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player){
		if(player.isSneaking()){
			if(player.inventory.hasItem(charge)){
				int charge = stack.getTagCompound().getInteger("charge");
				
				if(charge != 128){
					stack.getTagCompound().setInteger("charge", charge+1);
					player.inventory.consumeInventoryItem(this.charge);
				}
			}
		}
		
		return stack;
	}
	
	@Override    
	public boolean showDurabilityBar(ItemStack stack){ return stack.getItemDamage() != 128; }
}
