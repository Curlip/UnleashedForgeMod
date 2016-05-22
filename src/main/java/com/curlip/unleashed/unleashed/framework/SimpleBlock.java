package com.curlip.unleashed.framework;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SimpleBlock extends UnleashedGenericBlock {
	
	public SimpleBlock(Material material, String id, boolean wip) {
		super(material, id, wip);
		
		setHardness(1.5F);
	}
}
