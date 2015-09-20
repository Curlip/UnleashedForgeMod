package com.curlip.unleashed.framework;

import java.util.List;

import com.curlip.unleashed.UnleashedInfo;
import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.framework.interfaces.UnleashedBlock;
import com.sun.org.apache.xpath.internal.axes.SelfIteratorNoPredicate;

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

public abstract class UnleashedGenericBlock extends Block implements UnleashedBlock {
	
	private String id;
	public boolean wip;

	public boolean enabled = true;
	
	protected UnleashedGenericBlock(Material material, String blockid, boolean wip) {
		super(material);
		
		this.id = blockid;
		this.wip = wip;
		
		setUnlocalizedName(id);
		
		if(!wip){
			setCreativeTab(UnleashedMod.tabUnleashed);
		}else if(UnleashedMod.instance.wipEnabled){
			setCreativeTab(UnleashedMod.tabWipUnleashed);
		}
		
		if(UnleashedMod.instance.wipEnabled && wip){
			GameRegistry.registerBlock(getMinecraftBlock(), WIPItemBlock.class, id);
		}else if(!UnleashedMod.instance.wipEnabled && wip){
			enabled = false;
			UnleashedMod.instance.getLog().info(getLocalizedName() + " was Disabled because WIP features are turned off.");
		}else{
			GameRegistry.registerBlock(getMinecraftBlock(), id);
		}
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
