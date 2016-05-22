package com.curlip.unleashed.wip;

import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.framework.CraftingRecipe;
import com.curlip.unleashed.framework.interfaces.UnleashedBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BackpackBlock extends Block implements UnleashedBlock {

	private String id;

	public BackpackBlock(Material materialIn, String pid) {
		super(materialIn);
		
		id = pid;
		
		setUnlocalizedName(id);
		GameRegistry.registerBlock(getMinecraftBlock(), id);
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public Block getMinecraftBlock() {
		return this;
	}

	@Override
	public void registerRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerRecipes() {
		// TODO Auto-generated method stub
		
	}
}
