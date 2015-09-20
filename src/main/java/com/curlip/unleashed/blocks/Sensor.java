package com.curlip.unleashed.blocks;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.curlip.unleashed.framework.UnleashedGenericBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedMetaBlock;

public class Sensor extends UnleashedGenericBlock implements UnleashedMetaBlock {

	public enum FlatAxis implements IStringSerializable {
		X, 
		Z, 
		ALL,
		NONE;

		@Override
		public String getName() {
			return this.toString();
		}
	}
	
	public static final PropertyEnum AXIS = PropertyEnum.create("axis", FlatAxis.class);
	
	public Sensor(String blockid) {
		super(Material.rock, blockid, true);
		
		setDefaultState(this.blockState.getBaseState().withProperty(AXIS, FlatAxis.ALL));
	}

	@Override
	protected BlockState createBlockState() {
	    return new BlockState(this, new IProperty[] { AXIS });
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
	    return getDefaultState().withProperty(AXIS, FlatAxis.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((FlatAxis) state.getValue(AXIS)).ordinal();
	}
	
	@Override
	public String getUnlocalizedNameForIndex(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getModelNameForIndex(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public boolean canProvidePower()
    {
        return true;
    }

	@Override
    public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side){
		int dist = 8;
		
		boolean flag = false;
		
		if(((FlatAxis) worldIn.getBlockState(pos).getValue(AXIS)) == FlatAxis.X || ((FlatAxis) worldIn.getBlockState(pos).getValue(AXIS)) == FlatAxis.ALL){
			BlockPos player = Minecraft.getMinecraft().thePlayer.getPosition();
			
			Iterator<BlockPos> posses = (Iterator<BlockPos>) pos.getAllInBox(pos, pos.add(dist, dist, 0));
		}
		
        return dist-1;
    }
	
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		String control = "XZ";
		
		if(!(worldIn.getBlockState(pos.south()).getBlock() instanceof BlockAir && 
				worldIn.getBlockState(pos.north()).getBlock() instanceof BlockAir))
		{
			control = control.replace('X', ' ');
		}
		
		if(!(worldIn.getBlockState(pos.east()).getBlock() instanceof BlockAir && 
				worldIn.getBlockState(pos.west()).getBlock() instanceof BlockAir))
		{
			control = control.replace('Z', ' ');
		}
		
		control = control.trim();
		
		if(control.length() == 1){
			worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(AXIS, FlatAxis.valueOf(control)));
		}
		
		if(control.length() == 0){
			worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(AXIS, FlatAxis.NONE));
		}
		
		if(control.length() == 2){
			worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(AXIS, FlatAxis.ALL));
		}
	}
}
