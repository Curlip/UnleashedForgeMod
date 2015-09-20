package com.curlip.unleashed.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.curlip.unleashed.framework.SimpleBlock;

public class RedstoneSlimeBlock extends SimpleBlock {

	public RedstoneSlimeBlock(String id) {
		super(Material.clay, id, false);

		this.slipperiness = 0.8F;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        return block == this ? false : super.shouldSideBeRendered(worldIn, pos, side);
    }
	
	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance){
			if (entityIn.isSneaking() || worldIn.isBlockPowered(pos)){
				super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
			}else{
            	entityIn.fall(fallDistance, 0.0F);
        	}
    }

    /**
     * Called when an Entity lands on this Block. This method *must* update motionY because the entity will not do that
     * on its own
     */
	@Override
    public void onLanded(World worldIn, Entity entityIn)
    {
        if (entityIn.isSneaking() || !worldIn.isBlockPowered(entityIn.getPosition().down()))
        {
            super.onLanded(worldIn, entityIn);
        }
        else if (entityIn.motionY < 0.0D)
        {
            entityIn.motionY = -entityIn.motionY;
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block)
     */
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (Math.abs(entityIn.motionY) < 0.1D && (!entityIn.isSneaking() && !worldIn.isBlockPowered(pos)))
        {
            double d0 = 0.4D + Math.abs(entityIn.motionY) * 0.2D;
            entityIn.motionX *= d0;
            entityIn.motionZ *= d0;
        }

        super.onEntityCollidedWithBlock(worldIn, pos, entityIn);
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
