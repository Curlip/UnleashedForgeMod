package com.curlip.unleashed.blocks;

import java.util.Random;

import com.curlip.unleashed.CraftingHandler;
import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.framework.CraftingRecipe;
import com.curlip.unleashed.framework.RandomItem;
import com.curlip.unleashed.framework.SimpleBlock;

import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class Maker extends SimpleBlock implements ITileEntityProvider {

	final IBehaviorDispenseItem dropBehavior = new BehaviorDefaultDispenseItem();
	
	public Maker(String id) {
		super(Material.rock, id, true);

		GameRegistry.registerTileEntity(MakerTile.class, "Maker");
	}
	
	public int tickRate(World worldIn){
        return 2;
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ){
		MakerTile entity = ((MakerTile) worldIn.getTileEntity(pos));
		int amount = entity.charge;
		
		ItemStack stack = playerIn.getCurrentEquippedItem();
		
		if(stack == null) 			return true;
		if(!stack.hasTagCompound()) return true;
		
    	entity.charge += stack.getTagCompound().getInteger("mass");
    	stack.getTagCompound().setInteger("mass", 0);

    	worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    	
    	return true;
    }
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand){
		MakerTile entity = ((MakerTile) worldIn.getTileEntity(pos));
		int amount = entity.charge;
		
		if(worldIn.isRemote) return;
		if(amount <= 0) return;
		
		BlockSourceImpl source = new BlockSourceImpl(worldIn, pos.up());
		dropBehavior.dispense(source, RandomItem.getRandomItem());
		
		entity.charge--;
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new MakerTile();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
	   super.breakBlock(worldIn, pos, state);
	   worldIn.removeTileEntity(pos);
	}
	
	@Override
	public void registerRecipes(){
		ItemStack i = new ItemStack(Items.iron_ingot);
		ItemStack g = new ItemStack(Item.getItemFromBlock(Blocks.glass));
		ItemStack e = new ItemStack(Items.ender_pearl);
		
		CraftingHandler.add(new CraftingRecipe(new ItemStack[][]{
				new ItemStack[]{i, i, i},
				new ItemStack[]{g, e, g},
				new ItemStack[]{i, i, i}
		}, new ItemStack(this, 1, 0)));
	}

	public static class MakerTile extends TileEntity {
		
		public int charge = 0;
		
		public void writeToNBT(NBTTagCompound var1){
		    var1.setInteger("charge", this.charge);
		    super.writeToNBT(var1);
		}
		
		public void readFromNBT(NBTTagCompound var1){
		    this.charge = var1.getInteger("charge");
		    super.readFromNBT(var1);
		}
	}
}
