package com.curlip.unleashed.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGradient extends ItemBlock {

    public ItemBlockGradient(Block block) {
        super(block);
        
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage){
        return damage;
    }

    @Override
	public int getColorFromItemStack(ItemStack itemstack, int renderpass){
    	EnumDyeColor color = EnumDyeColor.byMetadata(itemstack.getItemDamage());
    	
		return color.getMapColor().colorValue;
	}
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "_" + stack.getItemDamage();
    }
}
