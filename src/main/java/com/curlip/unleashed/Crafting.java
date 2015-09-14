package com.curlip.unleashed;

import java.util.ArrayList;
import java.util.List;

import com.curlip.unleashed.framework.interfaces.UnleashedItem;
import com.curlip.unleashed.framework.registers.Register;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Crafting {

	static Register<UnleashedItem> itemRegister = UnleashedMod.instance.itemRegister;

	public static void register() {
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
    	});
    	
		Item[] centers = new Item[] { Items.redstone, Items.glowstone_dust,
				Items.emerald };

		Item iitem = itemRegister.getByID("chargecore").getMinecraftItem();
		Item citem = itemRegister.getByID("energycrystal").getMinecraftItem();

		List isubs = new ArrayList();
		ItemStack[] isubsArray = new ItemStack[16];

		iitem.getSubItems(iitem, iitem.getCreativeTab(), isubs);
		isubs.toArray(isubsArray);

		List csubs = new ArrayList();
		ItemStack[] csubsArray = new ItemStack[16];

		citem.getSubItems(citem, citem.getCreativeTab(), csubs);
		csubs.toArray(csubsArray);

		for (int i = 0; i < isubsArray.length; i++) {
			if (isubsArray[i] != null) {
				System.out.println(i);
				GameRegistry.addRecipe(
						new ItemStack(itemRegister.getByID("chargecore").getMinecraftItem(), 1, i), new Object[] {

						"III", 
						"IVI", 
						"III",

						'I', Items.iron_ingot, 'V', centers[i] 
				});
			}
		}

		for (int i = 0; i < csubsArray.length; i++) {
			if (csubsArray[i] != null) {
				GameRegistry.addRecipe(
						new ItemStack(itemRegister.getByID("energycrystal").getMinecraftItem(), 1, i), new Object[] {

						"GGG", 
						"GVG", 
						"GGG",

						'G', Blocks.glowstone, 'V', centers[i] 
				});
			}
		}
	}
}
