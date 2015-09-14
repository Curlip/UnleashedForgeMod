package com.curlip.unleashed.handlers;

import org.lwjgl.input.Keyboard;

import com.curlip.unleashed.UnleashedInfo;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings {

    public static KeyBinding helm;
    public static KeyBinding chest;
    public static KeyBinding leg;
    public static KeyBinding boot;

    public static void init() {
        helm  = new KeyBinding("key.helmet",     Keyboard.KEY_H, "key.categories." + UnleashedInfo.MODID + ".armor");
        chest = new KeyBinding("key.chestplate", Keyboard.KEY_C, "key.categories." + UnleashedInfo.MODID + ".armor");
        leg   = new KeyBinding("key.leggings",   Keyboard.KEY_L, "key.categories." + UnleashedInfo.MODID + ".armor");
        boot  = new KeyBinding("key.boots",      Keyboard.KEY_B, "key.categories." + UnleashedInfo.MODID + ".armor");

    
        ClientRegistry.registerKeyBinding(helm);
        ClientRegistry.registerKeyBinding(chest);
        ClientRegistry.registerKeyBinding(leg);
        ClientRegistry.registerKeyBinding(boot);
    }

}
