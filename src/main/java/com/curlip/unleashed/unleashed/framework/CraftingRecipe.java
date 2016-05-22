package com.curlip.unleashed.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingRecipe {

	private ItemStack[][] recipe = new ItemStack[3][3];
	
	private ItemStack result;
	
	public CraftingRecipe(Item[][] precipe, ItemStack result){
		this(new ItemStack[][]{
				new ItemStack[]{new ItemStack(precipe[0][0]), new ItemStack(precipe[0][1]), new ItemStack(precipe[0][2])},
				new ItemStack[]{new ItemStack(precipe[1][0]), new ItemStack(precipe[1][1]), new ItemStack(precipe[1][2])},
				new ItemStack[]{new ItemStack(precipe[2][0]), new ItemStack(precipe[2][1]), new ItemStack(precipe[2][2])}
		}, result);
	}
	
	public CraftingRecipe(ItemStack[][] precipe, ItemStack result){
		if(precipe.length == recipe.length){
			recipe = precipe;
		}
		
		this.result = result;
	}
	
	public Object[] getMinecraftRecipe(){
		List<Object> minecraft = new ArrayList<Object>();
		
		HashMap<ItemStack, Character> keyMap = new HashMap<ItemStack, Character>();
		
		int i = 0;
		
		for(ItemStack[] itemA : recipe){
			for(ItemStack item : itemA){
				keyMap.put(null, '.');
				
				if((!keyMap.containsKey(item)) && item!=null){
					keyMap.put(item, Character.forDigit(i, 10));
					
					i++;
				}
			}		
		}

		for(ItemStack[] itemA : recipe){
			String str = "";
			
			for(ItemStack item : itemA){
				str = str + keyMap.get(item);
			}
			
			minecraft.add(str);
		}
		
		for(Entry<ItemStack, Character> pair : keyMap.entrySet() ){
			minecraft.add(pair.getValue());
			minecraft.add(pair.getKey());
		}
		
		return minecraft.toArray();
	}
	
	public ItemStack getResult(){
		return result;
	}
}
