package com.curlip.unleashed;

import java.util.ArrayList;
import java.util.List;

import com.curlip.unleashed.framework.CraftingRecipe;
import com.curlip.unleashed.framework.interfaces.UnleashedBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedItem;
import com.curlip.unleashed.framework.registers.Register;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingHandler {

	private static List<CraftingRecipe> recipes = new ArrayList<CraftingRecipe>();
	
	public static void register() {

		for(UnleashedBlock ublock : UnleashedMod.instance.blockRegister.getAll()){
			ublock.registerRecipes();
		}
		
		for(UnleashedItem uitem : UnleashedMod.instance.itemRegister.getAll()) {
			uitem.registerRecipes();
		}
		
		for(CraftingRecipe recipe : recipes){
			if(recipe != null){
				GameRegistry.addRecipe(recipe.getResult(), recipe.getMinecraftRecipe());
			}
		}
		
		/*if(UnleashedMod.instance.wipEnabled){
			GameRegistry.addRecipe(new ItemStack(itemRegister.getByID("tntminer").getMinecraftItem()), 
					new Object[]{

				"I..",
				"PT.",
				"CPI",

				'I', itemRegister.getByID("elementblockfunnel").getMinecraftItem(),
				'T', Blocks.tnt,
				'P', itemRegister.getByID("elementpipe").getMinecraftItem(),
				'C', new ItemStack(itemRegister.getByID("chargecore").getMinecraftItem(), 1, 0),
			});

			GameRegistry.addRecipe(new ItemStack(itemRegister.getByID("tntminer").getMinecraftItem()), 
					new Object[]{

				"I..",
				"CPO",
				"I..",

				'O', itemRegister.getByID("elementblockfunnel").getMinecraftItem(),
				'I', Items.iron_ingot,
				'P', itemRegister.getByID("elementpipe").getMinecraftItem(),
				'C', new ItemStack(itemRegister.getByID("chargecore").getMinecraftItem(), 1, 0),
			});
		}
		
    	GameRegistry.addRecipe(new ItemStack(itemRegister.getByID("elementpipe").getMinecraftItem(), 6, 0), 
    			new Object[]{
    		
    		"I.I",
    		"I.I",
    		"I.I",
    		
    		'I', Items.iron_ingot
    	});
    	
    	GameRegistry.addRecipe(new ItemStack(itemRegister.getByID("elementblockfunnel").getMinecraftItem(), 2, 0), 
    			new Object[]{
    		
    		"C.C",
    		"CCC",
    		".P.",
    		
    		'P', itemRegister.getByID("elementpipe").getMinecraftItem(),
    		'C', Blocks.cobblestone
    	});*/
	}
	
	public static void add(CraftingRecipe recipe){
		recipes.add(recipe);
	}
}
