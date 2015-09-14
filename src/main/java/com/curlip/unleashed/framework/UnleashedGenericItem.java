package com.curlip.unleashed.framework;

import com.curlip.unleashed.UnleashedInfo;
import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.framework.interfaces.UnleashedItem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class UnleashedGenericItem extends Item implements UnleashedItem {
	
	private String id;

	protected UnleashedGenericItem(String itemid) {
		super();
		
		id = itemid;
		
		setUnlocalizedName(id);
		GameRegistry.registerItem(getMinecraftItem(), id);
		
		if(!id.startsWith("element")){
			setCreativeTab(UnleashedMod.tabUnleashed);
		}else{
			setCreativeTab(UnleashedMod.tabCraftingUnleashed);
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
}
