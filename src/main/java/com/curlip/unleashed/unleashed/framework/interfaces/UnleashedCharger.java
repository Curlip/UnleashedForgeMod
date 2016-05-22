package com.curlip.unleashed.framework.interfaces;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface UnleashedCharger extends UnleashedRegisterable {

	public ItemStack getItem();
	
	/**
	 * This method should decrement charge if possible.
	 * 
	 * Return true if decrement succeeded 
	 * 
	 * @param inv Players Inventory
	 * 
	 * @return If decrement succeeded 
	 */
	public boolean use(InventoryPlayer inv);
	
	/**
	 * This method should increment charge.
	 * 
	 * Return true if increment succeeded 
	 * 
	 * @param inv Players Inventory, Use to consume items
	 */
	public void charge(InventoryPlayer inv);
	
	/**
	 * @return Current charge
	 */
	public int getCharge();
	
	/**
	 * @return Max charge
	 */
	public int getMaxCharge();
	
	/**
	 * Get the color for charge. (Used for Charging Texture)
	 * 
	 * @return Color
	 */
	public int getChargeColor();
	
	/**
	 * Save this charger to NBTTagCompound
	 * 
	 * @param compound, NBTTagCompound containing save data.
	 */
	public NBTTagCompound save();
	
	/**
	 * Transform this charger into the charger denoted by the NBTTagCompound, may not be valid Tag.
	 * 
	 * @param compound NBTTagCompound to read from.
	 */
	public void load(NBTTagCompound compound);
	
	/**
	 * Test if this class owns this NBTTagCompound. (Is NBT Valid)
	 * 
	 * @param compound Compound to test.
	 * 
	 * @return Does this class own this Tag
	 */
	public boolean ownsNBT(NBTTagCompound compound);
}
