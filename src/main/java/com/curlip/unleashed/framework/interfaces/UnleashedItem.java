package com.curlip.unleashed.framework.interfaces;

import com.curlip.unleashed.framework.CraftingRecipe;

import net.minecraft.item.Item;

public interface UnleashedItem extends UnleashedRegisterable {

	public Item getMinecraftItem();
	
	public void registerRender();
	
	public void registerRecipes();
}
