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

import com.curlip.unleashed.framework.UnleashedChargable;
import com.curlip.unleashed.items.chargers.EnergyCrystalCharger;

public class ConsumeMiner extends UnleashedChargable {

	public ConsumeMiner(String itemid) {
		super(itemid, new EnergyCrystalCharger(0));

		setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			charge(stack, playerIn.inventory);
		}else{
				if(use(stack, playerIn.inventory)){
					IBlockState state = worldIn.getBlockState(pos);

					List<ItemStack> drops = state.getBlock().getDrops(worldIn, pos, state, -1);

					worldIn.setBlockToAir(pos);

					for(ItemStack dropStack : drops){
						Minecraft.getMinecraft().thePlayer.inventory.addItemStackToInventory(dropStack);
					}

				}
			}

		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().getCompoundTag("Charger").setTag("Charger", this.getCharger(stack).save());
		
		return true;
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
