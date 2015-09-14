package com.curlip.unleashed.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.curlip.unleashed.UnleashedInfo;
import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.framework.SimpleBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedMetaBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedMetaItem;

public class Gradient extends Block implements UnleashedMetaBlock {

	public static final PropertyEnum COLOR = PropertyEnum.create("color", EnumDyeColor.class);
	
	private String id;

	public Gradient(String id, Class<? extends ItemBlock> iblock) {
		super(Material.rock);
		
		this.id = id;
		
		GameRegistry.registerBlock(this, iblock, id);

		setUnlocalizedName(id);
		setCreativeTab(UnleashedMod.tabUnleashed);
		setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
	}

	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();

        ret.add(new ItemStack(this, 1, state.getBlock().getMetaFromState(state)));
        
        return ret;
    }
	
	@Override
	protected BlockState createBlockState() {
	    return new BlockState(this, new IProperty[] { COLOR });
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
	    return getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumDyeColor type = (EnumDyeColor) state.getValue(COLOR);
	    return type.getMetadata();
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for(EnumDyeColor color : EnumDyeColor.values()){
			list.add(new ItemStack(itemIn, 1, color.getMetadata()));
		}
	}

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass){
    	EnumDyeColor color = (EnumDyeColor) Minecraft.getMinecraft().theWorld.getBlockState(pos).getProperties().get(COLOR);
    	
        return color.getMapColor().colorValue;
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
	public String getUnlocalizedNameForIndex(int index) {
		return getUnlocalizedName().substring(5) + "_" + index;
	}

	@Override
	public String getModelNameForIndex(int index) {
		return getUnlocalizedName().substring(5);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos){
        return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumDyeColor) world.getBlockState(pos).getProperties().get(COLOR)).getMetadata());
    }
	
	@Override
	public void registerRender() {
		for(int i = 0; i <= 16; i++){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), i, new ModelResourceLocation(UnleashedInfo.MODID + ":" + getModelNameForIndex(i), "inventory"));
		}
	}
}
