package com.curlip.unleashed.framework.interfaces;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Deprecated
public interface OldUnleashedCharger extends UnleashedItem {

	public int getMaxCharge(ItemStack stack);
	
	public boolean canIncrement(ItemStack stack);
	
	public int getUseDecrement(ItemStack stack);
	
	public String getNBTName(ItemStack stack);
}
