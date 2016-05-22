package com.curlip.unleashed.framework;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;

import com.curlip.unleashed.framework.interfaces.UnleashedEnchantment;

public abstract class UnleashedGenericEnchantment extends Enchantment implements UnleashedEnchantment {

	private String id;

	protected UnleashedGenericEnchantment(int enchID, String enchStrID, int enchWeight, EnumEnchantmentType enchType) {
		super(enchID, new ResourceLocation(enchStrID), enchWeight, enchType);
		
		id = enchStrID;
		
		this.setName(enchStrID);

	    addToBookList(this);
	}

	@Override
	public String getID(){
		return id;
	}
}
