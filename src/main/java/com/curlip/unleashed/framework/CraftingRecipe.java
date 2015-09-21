package com.curlip.unleashed.framework;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingRecipe {

	private Item[][] recipe = new Item[3][3];
	private ItemStack result;
	
	public CraftingRecipe(Item[][] precipe, ItemStack result){
		int i = 0;
		int i2 = 0;
		
		if(precipe.length == recipe.length){
				recipe = precipe;
		}
		
		this.result = result;
	}
	
	public Object[] getMinecraftRecipe(){
		Object[] minecraft = new Object[21];
		
		HashMap<Item, Character> keyMap = new HashMap<Item, Character>();
		
		int i = 0;
		
		for(Item[] itemA : recipe){
			for(Item item : itemA){
				keyMap.put(null, '.');
				
				if((!keyMap.containsKey(item)) && item!=null){
					keyMap.put(item, Character.forDigit(i, 10));
					
					i++;
				}
			}		
		}
		
		int strc = 0;
		
		for(Item[] itemA : recipe){
			String str = "";
			
			for(Item item : itemA){
				str = str + keyMap.get(item);
			}
			
			minecraft[strc] = str;
			strc++;
		}
		
		int pairc = 3;
		
		for(Entry<Item, Character> pair : keyMap.entrySet() ){
			minecraft[pairc] = pair.getValue();
			pairc++;
			minecraft[pairc] = pair.getKey();
			pairc++;
		}
		
		return minecraft;
	}
	
	public ItemStack getResult(){
		return result;
	}
}
