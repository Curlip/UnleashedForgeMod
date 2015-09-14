package com.curlip.unleashed.handlers;

import com.curlip.unleashed.framework.interfaces.UnleashedWearable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyInputHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(KeyBindings.helm.isPressed()){
        	trySignalArmor(3);
        }
        
        if(KeyBindings.chest.isPressed()){
        	trySignalArmor(2);
        }
        
        if(KeyBindings.leg.isPressed()){
        	trySignalArmor(1);
        }
        
        if(KeyBindings.boot.isPressed()){
        	trySignalArmor(0);
        } 
    }
    
    private static void trySignalArmor(int index){
    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    	
    	Item armor = player.inventory.armorInventory[index].getItem();
    	
    	if(armor instanceof UnleashedWearable){
    		((UnleashedWearable) armor).wearableTriggered();
    	}
    }
}
