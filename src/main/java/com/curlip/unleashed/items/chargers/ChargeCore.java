package com.curlip.unleashed.items.chargers;

import static net.minecraft.util.EnumChatFormatting.DARK_RED;
import static net.minecraft.util.EnumChatFormatting.GOLD;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.curlip.unleashed.CraftingHandler;
import com.curlip.unleashed.UnleashedInfo;
import com.curlip.unleashed.framework.CraftingRecipe;
import com.curlip.unleashed.framework.SimpleItem;
import com.curlip.unleashed.framework.interfaces.UnleashedMetaItem;

public class ChargeCore extends SimpleItem implements UnleashedMetaItem {

	static int[] itemColors = new int[]{0xFF0000, 0xFFFF00};
	static String[] itemTypes = new String[]{DARK_RED + "Redstone", GOLD + "Glowstone"};
	static ItemStack[] items = new ItemStack[]{new ItemStack(Items.redstone), new ItemStack(Items.glowstone_dust)};
	static int[] increment = new int[]{1, 2};
	static int[] decrement = new int[]{1, 1};
	
	public ChargeCore(String itemid) {
		super(itemid, false);
		
		setHasSubtypes(true);
        setMaxDamage(0);
	}

	@Override
	public int getColorFromItemStack(ItemStack itemstack, int renderpass){
		return renderpass == 0 ? itemColors[itemstack.getMetadata()] : 0xFFFFFF;
	}
	
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
	    subItems.add(new ItemStack(itemIn, 1, 0));
	    subItems.add(new ItemStack(itemIn, 1, 1));
	}

	@Override
	public String getModelNameForIndex(int index) {
		return getID();
	}

	@Override
	public String getUnlocalizedNameForIndex(int index) {
		return getUnlocalizedName(new ItemStack(this, 1, index));
	}
	
	public void addInformation(ItemStack itemStack, EntityPlayer player,
            List list, boolean par4) {
		
		super.addInformation(itemStack, player, list, par4);
		
        list.add(itemTypes[itemStack.getMetadata()]);
        
	}

	@Override
	public void registerRender(){
		List<ItemStack> subs = new ArrayList<ItemStack>();

		this.getSubItems(this.getMinecraftItem(), this.getMinecraftItem().getCreativeTab(), subs);
		
		for(ItemStack sub : subs){
			if(sub != null){
				UnleashedMetaItem item = (UnleashedMetaItem) sub.getItem();
				
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item.getMinecraftItem(), sub.getMetadata(), new ModelResourceLocation(UnleashedInfo.MODID + ":" + item.getModelNameForIndex(sub.getMetadata()), "inventory"));
			}
		}
	}
	
	@Override
	public void registerRecipes(){
		int inum = 0;
		
		for(ItemStack stack : items){
			Item i = Items.iron_ingot;
			
			CraftingRecipe recipe =	
			new CraftingRecipe(new Item[][]{
					new Item[]{i, i, i},
					new Item[]{i, stack.getItem(), i},
					new Item[]{i, i, i}
				}, new ItemStack(this, 1, inum));
			
			CraftingHandler.add(recipe);
			
			inum++;
		}
	}
}
