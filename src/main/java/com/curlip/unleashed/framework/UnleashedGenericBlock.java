package com.curlip.unleashed.framework;

import com.curlip.unleashed.UnleashedInfo;
import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.framework.interfaces.UnleashedBlock;
import com.sun.org.apache.xpath.internal.axes.SelfIteratorNoPredicate;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class UnleashedGenericBlock extends Block implements UnleashedBlock {
	
	private String id;

	protected UnleashedGenericBlock(Material material, String blockid) {
		super(material);
		
		id = blockid;
		
		setUnlocalizedName(id);
		setCreativeTab(UnleashedMod.tabUnleashed);
		
		GameRegistry.registerBlock(getMinecraftBlock(), id);
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public Block getMinecraftBlock() {
		return this;
	}
	
	@Override
	public void registerRender(){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(UnleashedInfo.MODID + ":" + getID(), "inventory"));
	}
}
