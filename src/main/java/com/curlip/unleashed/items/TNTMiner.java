package com.curlip.unleashed.items;

import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.curlip.unleashed.framework.UnleashedChargable;
import com.curlip.unleashed.items.chargers.ChargeCoreCharger;

public class TNTMiner extends UnleashedChargable {

	public TNTMiner(String itemid) {
		super(itemid, new ChargeCoreCharger(0), true);

		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			charge(stack, playerIn.inventory);
		}
		
		return stack;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player){
		if(player.inventory.hasItem(Blocks.tnt.getItem(player.worldObj, pos))){
			if(use(stack, player.inventory)){
				player.inventory.consumeInventoryItem(Blocks.tnt.getItem(player.worldObj, pos));
				player.worldObj.createExplosion(new EntityTNTPrimed(player.worldObj), (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), 2, true);

				return true;
			}
		}
	
		return false;
	}
	
	@Override
	public int getColorFromItemStack(ItemStack itemstack, int renderpass){
		if(itemstack.getTagCompound()!=null){
			return renderpass == 0 ? 0x370000 + (0x20000 * (itemstack.getTagCompound().getInteger(Items.redstone.getUnlocalizedName()))) : 0xFFFFFF;
		}else{
			return renderpass == 0 ? 0x370000 : 0xFFFFFF;
		}
	}
}
