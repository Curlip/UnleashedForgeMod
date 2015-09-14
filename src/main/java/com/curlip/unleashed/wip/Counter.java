package com.curlip.unleashed.wip;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.curlip.unleashed.framework.SimpleBlock;

public class Counter extends SimpleBlock {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
    public Counter(String id) {
        super(Material.rock, id);
    }
    
    public TileEntity createTileEntity(World world, IBlockState state){
		return new TileEntityCounter();
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ){
    	TileEntityCounter entity = (TileEntityCounter) worldIn.getTileEntity(pos);
    	
		if(entity.number <= 16){
			entity.number++;

    		return true;
		}
			
		return false;
    }
    
}
