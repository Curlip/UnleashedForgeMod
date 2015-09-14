package com.curlip.unleashed.framework.interfaces;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface UnleashedBlock extends UnleashedRegisterable {
	
	public Block getMinecraftBlock();
	
	public void registerRender();
}
