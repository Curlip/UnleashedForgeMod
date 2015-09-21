package com.curlip.unleashed;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.ZombieEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DamageMessageEvents {

	HashMap<EntityPlayer, String> messageMap = new HashMap<EntityPlayer, String>();
	
	@SubscribeEvent
	public void entityHurtArrow(LivingHurtEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entity;
			
			if(event.source instanceof EntityDamageSourceIndirect && event.source.getDamageType().equals("arrow")){
				EntityDamageSourceIndirect damage = (EntityDamageSourceIndirect) event.source;
				
				Entity attacker;
				String end = "ERROR";
				
				if(damage.getEntity() instanceof EntityPlayer){
					attacker = damage.getEntity();
					end = attacker.getName() + "'s Arrow ";
				}else if(damage.getEntity() instanceof EntityArrow){
					attacker = damage.getEntity();
					end = "an Arrow ";
				}else{
					attacker = damage.getEntity();
					end = (isVowel(attacker.getName().charAt(0)) ? "an " + attacker.getName(): "a " + attacker.getName()) + "'s Arrow ";
				}	

				String message = EnumChatFormatting.RED + "You were shot by " + end;
				
				if(!(message.equals(messageMap.get(player)))){
					BlockPos playpos = player.getPosition();
					BlockPos attackpos = attacker.getPosition();
					
					float x = playpos.getX() - attackpos.getX();
					float y = playpos.getY() - attackpos.getY();
					float z = playpos.getZ() - attackpos.getZ();
					
					int dist = (int) Math.round((Math.sqrt(x*x + y*y + z*z)));
					
					player.addChatMessage(new ChatComponentText(message + "from " + dist + " Blocks Away."));
					
					messageMap.put(player, message);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void entityHurtGeneral(LivingHurtEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entity;
			
			if(event.source instanceof EntityDamageSource && !event.source.getDamageType().equals("arrow")){
				EntityDamageSource damage = (EntityDamageSource) event.source;
				
				Entity attacker;
				String end = "ERROR";
				
				if(damage.getEntity() instanceof EntityPlayer){
					attacker = damage.getEntity();
					end = attacker.getName();
				}else{
					attacker = damage.getEntity();
					end = isVowel(attacker.getName().charAt(0)) ? "an " + attacker.getName(): "a " + attacker.getName();
				}	

				String message = EnumChatFormatting.RED + "You were attacked by " + end;
				
				if(!(message.equals(messageMap.get(player)))){
					player.addChatMessage(new ChatComponentText(message));
					
					messageMap.put(player, message);
				}
			}
		}
	}
	
	private static boolean isVowel(char v){
		v = Character.toUpperCase(v);
		
		if(v == 'A' || v == 'E' || v == 'I' || v == 'O' || v == 'U'){
			return true;
		}
		
		return false;
	}
}
