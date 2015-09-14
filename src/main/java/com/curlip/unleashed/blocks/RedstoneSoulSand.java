package com.curlip.unleashed.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.curlip.unleashed.framework.SimpleBlock;

public class RedstoneSoulSand extends SimpleBlock {

	public RedstoneSoulSand(String id) {
		super(Material.sand, id);
    }

	@Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state){
    	if(worldIn.isBlockPowered(pos)){
    		float f = 0.125F;
    		return new AxisAlignedBB((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), (double)(pos.getX() + 1), (double)((float)(pos.getY() + 1) - f), (double)(pos.getZ() + 1));
    	}
    	
    	return new AxisAlignedBB((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 1), (double)(pos.getZ() + 1));
    }

	@Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){
    	if(worldIn.isBlockPowered(pos)){
    		entityIn.motionX *= 0.4D;
        	entityIn.motionZ *= 0.4D;
    	}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass){
    	if(Minecraft.getMinecraft().theWorld.isBlockPowered(pos)){
    		return 0xFF0000;
    	}else{
    		return 0xFFFFFF;
    	}
    }
}
