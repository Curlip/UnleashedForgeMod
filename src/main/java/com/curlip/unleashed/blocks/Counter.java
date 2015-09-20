package com.curlip.unleashed.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.curlip.unleashed.UnleashedInfo;
import com.curlip.unleashed.framework.SimpleBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedMetaBlock;

public class Counter extends SimpleBlock implements UnleashedMetaBlock {

	public static final PropertyInteger NUMBER = PropertyInteger.create("numb", 0, 9);
	
    public Counter(String id) {
        super(Material.rock, id, false);
        
        setDefaultState(this.blockState.getBaseState().withProperty(NUMBER, 0));
    } 
    
    @Override
	protected BlockState createBlockState() {
	    return new BlockState(this, new IProperty[] { NUMBER });
	}
    
	@Override
	public IBlockState getStateFromMeta(int meta) {
	    return getDefaultState().withProperty(NUMBER, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
	    return (Integer) state.getValue(NUMBER);
	}
	
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ){
    	if(!worldIn.isBlockPowered(pos)){
    	    worldIn.setBlockState(pos, worldIn.getBlockState(pos).cycleProperty(NUMBER));
    	    
    	    return true;
    	}
    	
        return false;
    }

    @Override
    public int getComparatorInputOverride(World worldIn, BlockPos pos){
        return ((Integer) worldIn.getBlockState(pos).getValue(NUMBER)) + 6;
    }
    
    @Override public boolean hasComparatorInputOverride(){  return true;  }
    
    @Override
	public String getUnlocalizedNameForIndex(int index) {
		return getUnlocalizedName().substring(5) + "_" + index;
	}

	@Override
	public String getModelNameForIndex(int index) {
		return getUnlocalizedName().substring(5) + index;
	}
	
	@Override
	public void registerRender() {
		for(int i = 0; i <= 16; i++){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), i, new ModelResourceLocation(UnleashedInfo.MODID + ":" +  getUnlocalizedName().substring(5), "inventory"));
		}
	}
}
