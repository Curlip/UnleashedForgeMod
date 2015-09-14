package com.curlip.unleashed.handlers;

import com.curlip.unleashed.wip.BackpackContainer;
import com.curlip.unleashed.wip.BackpackGuiContainer;
import com.curlip.unleashed.wip.BackpackInventory;
import com.curlip.unleashed.wip.BackpackItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import static com.curlip.unleashed.UnleashedMod.GUI;

public class GuiHandler implements IGuiHandler {

	private BackpackContainer backpackContainer;
	private BackpackGuiContainer backpackGuiContainer;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		createVars(player);
		System.out.println("Server");
		switch(ID){
			case 0:
				System.out.println("Backpack Server");
				return backpackContainer;
			default:	
				return null;
		}
	}

	private void createVars(EntityPlayer player) {
		backpackContainer = new BackpackContainer(player, player.inventory, new BackpackInventory(new ItemStack(player.inventory.armorInventory[2].getItem())));
		backpackGuiContainer = new BackpackGuiContainer(backpackContainer);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		createVars(player);
		System.out.println("Client");
		switch(ID){
			case 0:
				System.out.println("Backpack Client");
				return backpackGuiContainer;
			default:	
				return null;
		}
	}

}
