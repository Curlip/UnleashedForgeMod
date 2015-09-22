package com.curlip.unleashed.framework;

import java.util.List;

import com.curlip.unleashed.UnleashedInfo;
import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.framework.interfaces.UnleashedItem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;

public abstract class UnleashedGenericItem extends Item implements UnleashedItem {
	
	private String id;
	private boolean wip;
	public boolean enabled = true;

	protected UnleashedGenericItem(String itemid, boolean wip){
		this(itemid, wip, true);
	}
	
	protected UnleashedGenericItem(String itemid, boolean wip, boolean autoRegister) {
		super();
		
		this.id = itemid;
		this.wip = wip;
		
		setUnlocalizedName(id);
		
		if(!wip){
			if(!id.startsWith("element")){
				setCreativeTab(UnleashedMod.tabUnleashed);
			}else{
				setCreativeTab(UnleashedMod.tabCraftingUnleashed);
			}
		}else if(UnleashedMod.instance.wipEnabled){
			setCreativeTab(UnleashedMod.tabWipUnleashed);
		}
		
		if(UnleashedMod.instance.wipEnabled && wip){
			GameRegistry.registerItem(getMinecraftItem(), id);
		}else if(!UnleashedMod.instance.wipEnabled && wip){
			enabled = false;
			UnleashedMod.instance.getLog().info(LanguageRegistry.instance().getStringLocalization(getUnlocalizedName() + ".name") + " was Disabled because WIP features are turned off.");
		}else{
			if(autoRegister){
				GameRegistry.registerItem(getMinecraftItem(), id);
			}
		}
	}

	@Override
	public String getID(){
		return id;
	}
	
	@Override
	public Item getMinecraftItem() {
		return this;
	}
	
	@Override
	public void registerRender(){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, 0, new ModelResourceLocation(UnleashedInfo.MODID + ":" + getID(), "inventory"));
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		if(wip){
			tooltip.add(EnumChatFormatting.RED + "This Item is a Work In Progress (WIP)");
			tooltip.add(EnumChatFormatting.DARK_RED.toString() + EnumChatFormatting.BOLD.toString() + "It May Not Work!");
		}
	}
	
	@Override
	public void registerRecipes() {}
}
