package com.curlip.unleashed.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RandomItem {
	
	public static List<Item> common = new ArrayList<Item>();
	public static List<Item> mid = new ArrayList<Item>();
	public static List<Item> rare = new ArrayList<Item>();
	public static List<Item> vrare = new ArrayList<Item>();
	
	static{
		for(int i = 0; i <= 36; i++) common.add(Item.getItemFromBlock(Blocks.cobblestone));
		for(int i = 0; i <= 4; i++) common.add(Item.getItemFromBlock(Blocks.stone));
		for(int i = 0; i <= 40; i++) common.add(Item.getItemFromBlock(Blocks.dirt));
		for(int i = 0; i <= 30; i++) common.add(Item.getItemFromBlock(Blocks.sand));
		for(int i = 0; i <= 10; i++) common.add(Item.getItemFromBlock(Blocks.sandstone));
		
		for(int i = 0; i <= 5; i++) mid.add(Items.glowstone_dust);
		for(int i = 0; i <= 4; i++) mid.add(Items.apple);
		for(int i = 0; i <= 6; i++) mid.add(Items.clay_ball);
		for(int i = 0; i <= 7; i++) mid.add(Item.getItemFromBlock(Blocks.log));
		for(int i = 0; i <= 3; i++) mid.add(Item.getItemFromBlock(Blocks.sapling));
		
		for(int i = 0; i <= 7; i++) rare.add(Items.iron_ingot);
		for(int i = 0; i <= 3; i++) rare.add(Items.gold_ingot);
		for(int i = 0; i <= 5; i++) rare.add(Item.getItemFromBlock(Blocks.obsidian));
		for(int i = 0; i <= 7; i++) rare.add(Items.ender_pearl);
		for(int i = 0; i <= 3; i++) rare.add(Items.diamond);
		
		for(int i = 0; i <= 11; i++) vrare.add(Items.quartz);
		for(int i = 0; i <= 5; i++) vrare.add(Items.golden_apple);
		for(int i = 0; i <= 5; i++) vrare.add(Item.getItemFromBlock(Blocks.bedrock));
		for(int i = 0; i <= 2; i++) vrare.add(Item.getItemFromBlock(Blocks.emerald_block));
		for(int i = 0; i <= 2; i++) vrare.add(Item.getItemFromBlock(Blocks.diamond_block));
	}
	
	
	public static ItemStack getRandomItem(){
		int selector = new Random().nextInt(1000);
		
		if(selector <= 800){
			int numb = new Random().nextInt(common.size());
			return new ItemStack(common.get(numb), 1, 0);
		}else if(selector <= 900){
			int numb = new Random().nextInt(mid.size());
			return new ItemStack(mid.get(numb), 1, 0);
		}else if(selector <= 975){
			int numb = new Random().nextInt(rare.size());
			return new ItemStack(rare.get(numb), 1, 0);
		}else if(selector <= 1000){
			int numb = new Random().nextInt(vrare.size());
			return new ItemStack(vrare.get(numb), 1, 0);
		}
		
		return null;
	}
}
