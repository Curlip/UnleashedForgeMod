package com.curlip.unleashed.wip;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class BackpackInventory implements IInventory {

	private ItemStack[] inv;
	public static final int INV_SIZE = 27;

	public BackpackInventory(ItemStack itemstack){
		inv = new ItemStack[27];
		
		if (!itemstack.hasTagCompound()) {

			itemstack.setTagCompound(new NBTTagCompound());

		}

		readFromNBT(itemstack.getTagCompound());
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}               
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return INV_SIZE;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	public void readFromNBT(NBTTagCompound tagCompound) {
		NBTTagList tagList = tagCompound.getTagList("Inventory", tagCompound.getId());
		
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	public void writeToNBT(NBTTagCompound tagCompound) {
		NBTTagList itemList = new NBTTagList();
		
		for (int i = 0; i < inv.length; i++) {
			ItemStack stack = inv[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		
		tagCompound.setTag("Inventory", itemList);
	}

	@Override
	public String getName() {
		return "tco.tileentitytiny";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		return new ChatComponentText(getName());
	}

	@Override
	public void markDirty() {

	}

	@Override
	public void openInventory(EntityPlayer player) {
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		((BackpackContainer) player.openContainer).writeToNBT();
		((BackpackContainer) player.openContainer).needsUpdate = false;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return stack.stackSize > getInventoryStackLimit();
	}

	@Override
	public int getField(int id) {	return 0;	}

	@Override
	public void setField(int id, int value) {	}

	@Override
	public int getFieldCount() {	return 0;	}

	@Override
	public void clear() {	}
}

