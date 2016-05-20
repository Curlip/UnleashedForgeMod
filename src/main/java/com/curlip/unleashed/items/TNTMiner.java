package com.curlip.unleashed.items;

import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.curlip.unleashed.framework.UnleashedChargable;
import com.curlip.unleashed.items.chargers.ChargeCoreCharger;

public class TNTMiner extends UnleashedChargable {

	public TNTMiner(String itemid) {
		super(itemid, true, Items.redstone);

		setMaxStackSize(1);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player){
		if(!stack.hasTagCompound()) return false;
		
		Item tntItem = Blocks.tnt.getItem(player.worldObj, pos);
		int charge = stack.getTagCompound().getInteger("charge");
		
		if(player.inventory.hasItem(tntItem) && charge > 0){
			player.inventory.consumeInventoryItem(Blocks.tnt.getItem(player.worldObj, pos));
			if(!player.worldObj.isRemote){	
				player.worldObj.createExplosion(new EntityTNTPrimed(player.worldObj), (double) pos.getX() - 0.5, (double) pos.getY() - 0.5, (double) pos.getZ() - 0.5, 3, true);
			}
			stack.getTagCompound().setInteger("charge", charge-1);
			
			return true;
		}
	
		return false;
	}
	
	@Override
	public int getColorFromItemStack(ItemStack itemstack, int renderpass){
		if(itemstack.getTagCompound()!=null){
			return renderpass == 0 ? 0x370000 + (0x010000 * (itemstack.getTagCompound().getInteger("charge"))) : 0xFFFFFF;
		}else{
			return renderpass == 0 ? 0x370000 : 0xFFFFFF;
		}
	}
}
