package com.curlip.unleashed.wip;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.curlip.unleashed.UnleashedInfo;
import com.curlip.unleashed.UnleashedMod;
import com.curlip.unleashed.framework.interfaces.UnleashedItem;
import com.curlip.unleashed.framework.interfaces.UnleashedWearable;

public class BackpackItem extends ItemArmor implements UnleashedWearable, UnleashedItem {

	private String id;

	public BackpackItem(ArmorMaterial material, int armorType, String itemid) {
		super(material, 4, armorType);
		
		id = itemid;
		
		setUnlocalizedName(id);
		setCreativeTab(UnleashedMod.tabUnleashed);
		setMaxStackSize(1);
		
		GameRegistry.registerItem(getMinecraftItem(), id);
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		/*if(world.isRemote){ System.out.println("WearableTriggeredServer"); player.openGui(UnleashedMod.instance, UnleashedMod.BACKPACK.ordinal(), world, (int) player.posX, (int) player.posY, (int) player.posZ); }
		
		System.out.println("WearableTriggered");

		player.openGui(UnleashedMod.instance, UnleashedMod.BACKPACK.ordinal(), world, (int) player.posX, (int) player.posY, (int) player.posZ);
		
		return true;*/
		
        boolean flag = world.getBlockState(pos).getBlock().isReplaceable(world, pos);
        BlockPos blockpos1 = flag ? pos : pos.offset(side);

        if (!player.canPlayerEdit(blockpos1, side, stack))
        {
            return false;
        }
        else
        {
            Block block = world.getBlockState(blockpos1).getBlock();

            if (!world.canBlockBePlaced(block, blockpos1, false, side, (Entity)null, stack))
            {
                return false;
            }
            else if (UnleashedMod.instance.blockRegister.getByID("backpackblock").getMinecraftBlock().canPlaceBlockAt(world, blockpos1))
            {
                world.setBlockState(blockpos1, UnleashedMod.instance.blockRegister.getByID("backpackblock").getMinecraftBlock().getDefaultState());
                return true;
            }
            else
            {
                return false;
            }
        }
    }
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return UnleashedInfo.MODID + ":textures/models/armor/backpack_layer_1.png";
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
	public int getWearableType() {
		return 2;
	}
	
	@Override
	public void wearableTriggered() {
		
	}

	@Override
	public void registerRender() {
		// TODO Auto-generated method stub
		
	}
}
