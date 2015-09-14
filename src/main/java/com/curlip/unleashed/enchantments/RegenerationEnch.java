package com.curlip.unleashed.enchantments;

import java.util.Random;

import com.curlip.unleashed.framework.SimpleEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class RegenerationEnch extends SimpleEnchantment {

	public RegenerationEnch(int enchID, String enchIDStr) {
		super(enchID, enchIDStr, 2, EnumEnchantmentType.ARMOR_TORSO);
	}

	@Override 
	public int getMaxLevel(){
		return 3;
	}
	
	@Override
	public int getMaxEnchantability(int level){
		return (level + 1) * 8;
	}
	
	@Override
	public int getMinEnchantability(int level){
		return level * 8;
	}
	
	@Override
	public void onUserHurt(EntityLivingBase user, Entity attacker, int level) {
		Random rand = new Random();
		
		if(user instanceof EntityPlayer){
			if(rand.nextInt(3) < level){
				EntityPlayer player = (EntityPlayer) user;
				
				player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), (int) Math.ceil(level * 1.5), 1, true, true));
			}
		}
	}
}
