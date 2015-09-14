package com.curlip.unleashed.wip;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCounter extends TileEntity {

	public int number = 0;

	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);

		compound.setInteger("number", number);
	}
	
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		
		this.number = compound.getInteger("number");
	}
}
