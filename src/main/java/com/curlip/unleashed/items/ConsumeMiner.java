package com.curlip.unleashed.items;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.blocks.Maker;
import com.curlip.unleashed.framework.UnleashedChargable;
import com.curlip.unleashed.items.chargers.EnergyCrystalCharger;

public class ConsumeMiner extends UnleashedChargable {

	public ConsumeMiner(String itemid) {
		super(itemid, true, Items.glowstone_dust);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
		
		if(!stack.hasTagCompound()) return false;
		if(playerIn.isSneaking()) return false;
		if(worldIn.getBlockState(pos).getBlock() == UnleashedMod.instance.blockRegister.getByID("maker")) return false;
		
		int charge = stack.getTagCompound().getInteger("charge");
		int mass = stack.getTagCompound().getInteger("charge");
		
		if(charge > 0){
			IBlockState state = worldIn.getBlockState(pos);
			worldIn.setBlockToAir(pos);

			stack.getTagCompound().setInteger("charge", charge-1);
			stack.getTagCompound().setInteger("mass", mass+1);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public int getColorFromItemStack(ItemStack itemstack, int renderpass){
		if(itemstack.getTagCompound()!=null){
			return renderpass == 0 ? 0x373700 + (0x010100 * (itemstack.getTagCompound().getInteger(Items.glowstone_dust.getUnlocalizedName()))) : 0xFFFFFF;
		}else{
			return renderpass == 0 ? 0x373700 : 0xFFFFFF;
		}
	}
}
