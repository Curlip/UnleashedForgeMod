package com.curlip.unleashed;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.logging.log4j.Logger;

import com.curlip.unleashed.blocks.Counter;
import com.curlip.unleashed.blocks.Gradient;
import com.curlip.unleashed.blocks.RedstoneSlimeBlock;
import com.curlip.unleashed.blocks.RedstoneSoulSand;
import com.curlip.unleashed.blocks.Sensor;
import com.curlip.unleashed.enchantments.RegenerationEnch;
import com.curlip.unleashed.framework.SimpleItem;
import com.curlip.unleashed.framework.interfaces.UnleashedBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedEnchantment;
import com.curlip.unleashed.framework.interfaces.UnleashedItem;
import com.curlip.unleashed.framework.registers.Register;
import com.curlip.unleashed.items.ConsumeMiner;
import com.curlip.unleashed.items.TNTMiner;
import com.curlip.unleashed.items.chargers.ChargeCore;
import com.curlip.unleashed.items.chargers.ChargeCoreCharger;
import com.curlip.unleashed.items.chargers.EnergyCrystal;
import com.curlip.unleashed.items.chargers.EnergyCrystalCharger;

@Mod(modid=UnleashedInfo.MODID, version=UnleashedInfo.VERSION, name=UnleashedInfo.MODNAME)
public class UnleashedMod {
    
	public enum GUI {
		BACKPACK
	}

	@Instance(value = UnleashedInfo.MODID)
    public static UnleashedMod instance;
	
	public Register<UnleashedBlock> blockRegister = new Register<UnleashedBlock>();
	public Register<UnleashedItem> itemRegister = new Register<UnleashedItem>();
	public Register<UnleashedEnchantment> enchRegister = new Register<UnleashedEnchantment>();
	
	public static ArmorMaterial BACKPACK = EnumHelper.addArmorMaterial("BACKPACK", "packpack", 0, new int[]{0, 0, 0, 0}, 0);
	
	public Configuration config;
	
	public boolean wipEnabled = false;
	public boolean damageMessageEnabled = true;
	
	private Logger log;
	
	public static CreativeTabs tabUnleashed = new CreativeTabs("tabUnleashed") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return instance.itemRegister.getByID("chargecore").getMinecraftItem();
        }
    };
    
    public static CreativeTabs tabCraftingUnleashed = new CreativeTabs("tabCraftingUnleashed") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return instance.itemRegister.getByID("elementpipe").getMinecraftItem();
        }
    };
    
    public static CreativeTabs tabWipUnleashed;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event){
		log = event.getModLog();
		
		//Config
		config = new Configuration(event.getSuggestedConfigurationFile());
		
        config.load();
        
        wipEnabled = config.getBoolean("enableWip", Configuration.CATEGORY_GENERAL, wipEnabled, 
        		"Should WIP (Work In Progress) features be added. \n"
        	  + "!!WARNING!! These features may have bad side-effect. \n"
        	  + "Crashed aren't uncommon, textures might not have been made, \n"
        	  + "features not implemented, ect. \n\n"
        	  + "Be Warned. \n");
        
        damageMessageEnabled = config.getBoolean("enableDamageMessages", Configuration.CATEGORY_GENERAL, damageMessageEnabled, 
        		"Should Damage Messages such as, 'You have been attacked by a \n"
        	  + "Zombie', be enabled. \n"
        	  + "Please note I have tried to make them as infrequent as possible, \n"
        	  + "whilst still making them useful. \n");
        
        if(wipEnabled){
        	event.getModLog().info("WIP features enabled.");
        }else{
        	event.getModLog().info("WIP features disabled.");
        }
        
        config.save();
		
        if(wipEnabled){ 
        	tabWipUnleashed = new CreativeTabs("tabWipUnleashed") {
                @Override
                @SideOnly(Side.CLIENT)
                public Item getTabIconItem() {
                    return Items.prismarine_shard;
                }
            };
        }
        
        blockRegister.add(new Sensor("sensor"));
        blockRegister.add(new Sensor("maker"));
    	itemRegister.add(new TNTMiner("tntminer"));
        itemRegister.add(new ConsumeMiner("consumeminer"));
        
		ChargerRegistry.instance.add(new EnergyCrystalCharger(0));
        ChargerRegistry.instance.add(new ChargeCoreCharger(0));
		
        blockRegister.add(new Gradient("simplegradient"));
        blockRegister.add(new Gradient("solidblock"));

        blockRegister.add(new RedstoneSlimeBlock("powerslime"));
        blockRegister.add(new RedstoneSoulSand("powersoulsand"));
        
        blockRegister.add(new Counter("counter"));
        
        //-- Start Items --//
        
        itemRegister.add(new SimpleItem("elementpipe", false));
        itemRegister.add(new SimpleItem("elementblockfunnel", false));
        
        itemRegister.add(new ChargeCore("chargecore"));
        itemRegister.add(new EnergyCrystal("energycrystal"));

        enchRegister.add(new RegenerationEnch(75, "regeneration"));
    }
        
    @EventHandler
    public void load(FMLInitializationEvent event){    	
    	CraftingHandler.register();
    	
    	if(damageMessageEnabled){
        	DamageMessageEvents damageMessageEvent = new DamageMessageEvents();
        	
        	MinecraftForge.EVENT_BUS.register(damageMessageEvent);
        }
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	registerModels();
    }
    
    private void registerModels(){
    	if(Side.CLIENT.isClient()) {
    		for(UnleashedBlock ublock : blockRegister.getAll()){
    			ublock.registerRender();
    		}
    		
    		for(UnleashedItem uitem : itemRegister.getAll()) {
    			uitem.registerRender();
    		}
    	}
    }
    
    public Logger getLog(){
    	return log;
    }
}
