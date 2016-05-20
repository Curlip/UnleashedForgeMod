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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import com.curlip.unleashed.CraftingHandler;
import com.curlip.unleashed.UnleashedInfo;
import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.framework.CraftingRecipe;
import com.curlip.unleashed.framework.SimpleBlock;
import com.curlip.unleashed.framework.UnleashedGenericBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedMetaBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedMetaItem;

public class Gradient extends UnleashedGenericBlock implements UnleashedMetaBlock {

	public static final PropertyEnum COLOR = PropertyEnum.create("color", EnumDyeColor.class);

	public Gradient(String id) {
		super(Material.rock, id, false, false);

		GameRegistry.registerBlock(getMinecraftBlock(), ItemBlockGradient.class, id);
		setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
		this.setHardness(1);
	}

	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        int meta = 15 - state.getBlock().getMetaFromState(state);
        
        				ret.add(new ItemStack(this, 1, 0));
        if(meta != 15) 	ret.add(new ItemStack(Items.dye, 1, meta));
        
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
		list.add(new ItemStack(itemIn, 1, 0));
	}

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass){
    	EnumDyeColor color = (EnumDyeColor) Minecraft.getMinecraft().theWorld.getBlockState(pos).getProperties().get(COLOR);
    	
    	if(color != null){
    		return color.getMapColor().colorValue;
    	}
    	
        return 0xFFFFFF;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ){
    	ItemStack stack = playerIn.inventory.getCurrentItem();
    	
    	if(stack == null) return false;
    	if(!(stack.getItem() instanceof ItemDye)) return false;
    	
		if((15 - stack.getMetadata()) != ((EnumDyeColor) worldIn.getBlockState(pos).getValue(COLOR)).getMetadata()){
			worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(COLOR, EnumDyeColor.byMetadata(15 - stack.getMetadata())));
			
			playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, 1);
				
			return true;
		}
			
    	return false;
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
	
	@Override
	public void registerRecipes(){
		if(this==UnleashedMod.instance.blockRegister.getByID("simplegradient")){
			ItemStack w = new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0);
			ItemStack g = new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 7);
			ItemStack b = new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 15);
			
			CraftingHandler.add(new CraftingRecipe(new ItemStack[][]{
					new ItemStack[]{w, w, w},
					new ItemStack[]{g, g, g},
					new ItemStack[]{b, b, b}
			}, new ItemStack(this, 9, 0)));
		}else if(this==UnleashedMod.instance.blockRegister.getByID("solidblock")){
			ItemStack w = new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0);
			
			CraftingHandler.add(new CraftingRecipe(new ItemStack[][]{
					new ItemStack[]{w, w, w},
					new ItemStack[]{w, w, w},
					new ItemStack[]{w, w, w}
			}, new ItemStack(this, 9, 0)));
		}
		
		for(int i=0; i <= 15; i++){
			GameRegistry.addShapelessRecipe(new ItemStack(this, 1, i), new ItemStack(Items.dye, 1, 15 - i), new ItemStack(Item.getItemFromBlock(this), 1, OreDictionary.WILDCARD_VALUE));
		}
	}
}
