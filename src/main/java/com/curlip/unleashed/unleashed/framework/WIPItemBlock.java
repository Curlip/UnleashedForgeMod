package com.curlip.unleashed.framework;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class WIPItemBlock extends ItemBlock {

    private UnleashedGenericBlock itemblock;

	public WIPItemBlock(Block block) {
        super(block);
        
        this.itemblock = (UnleashedGenericBlock) block;
    }
    
    @Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		if(itemblock.wip){
			tooltip.add(EnumChatFormatting.RED + "This Item is a Work In Progress (WIP)");
			tooltip.add(EnumChatFormatting.DARK_RED.toString() + EnumChatFormatting.BOLD.toString() + "It May Not Work!");
		}
	}
}
