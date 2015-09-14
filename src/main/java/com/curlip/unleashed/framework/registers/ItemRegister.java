package com.curlip.unleashed.framework.registers;

import java.util.Collection;
import java.util.HashMap;

import com.curlip.unleashed.framework.interfaces.UnleashedItem;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegister {

	private HashMap<String, UnleashedItem> items = new HashMap<String, UnleashedItem>();
	
	public void addItem(UnleashedItem item){
		items.put(item.getID(), item);
	}
	
	public UnleashedItem getByID(String id){
		return items.get(id);
	}
	
	public Collection<UnleashedItem> getAllItems(){
		return items.values();
	}
}
