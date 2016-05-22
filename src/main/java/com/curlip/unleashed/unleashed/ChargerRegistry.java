package com.curlip.unleashed;

import net.minecraft.nbt.NBTTagCompound;

import com.curlip.unleashed.framework.interfaces.UnleashedCharger;
import com.curlip.unleashed.framework.registers.Register;

public class ChargerRegistry extends Register<UnleashedCharger> {

	public static final ChargerRegistry instance = new ChargerRegistry();
	
	private ChargerRegistry() {};
	
	public Class<? extends UnleashedCharger> getNBTOwner(NBTTagCompound compound){
		for(UnleashedCharger charger : this.getAll()){
			if(charger.ownsNBT(compound)){
				return charger.getClass();
			}
		}
		
		return null;	
	}
}
