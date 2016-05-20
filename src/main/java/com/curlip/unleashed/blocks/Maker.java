package com.curlip.unleashed.blocks;

import java.util.Random;

import com.curlip.unleashed.framework.SimpleBlock;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Maker extends SimpleBlock {

	public Maker(String id) {
		super(Material.rock, id, true);
	}

	@Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn){
		ItemStack item = playerIn.getHeldItem();
		
		System.out.println("qctive");
		
    	if(item.hasTagCompound()){
    		System.out.println("Has Tag");
    		
    		int amount = item.getTagCompound().getInteger("mass");
    		
    		if(amount != 0){
    			System.out.println(">0");
    			
    			for(int i = 0; i <= amount; i++){
    				EntityItem drop = new EntityItem(worldIn, pos.getX(), pos.getY() + 2, pos.getX(), new ItemStack(Item.getItemById(new Random().nextInt(100))));
    				worldIn.spawnEntityInWorld(drop);
    				
    				System.out.println("Out");
    			}
    		}
    	}
    }
}
