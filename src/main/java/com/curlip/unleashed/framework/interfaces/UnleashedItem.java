package com.curlip.unleashed.framework.interfaces;

import net.minecraft.item.Item;

public interface UnleashedItem extends UnleashedRegisterable {

	public Item getMinecraftItem();
	
	public void registerRender();
}
