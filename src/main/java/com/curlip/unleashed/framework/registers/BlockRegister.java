package com.curlip.unleashed.framework.registers;

import java.util.Collection;
import java.util.HashMap;

import com.curlip.unleashed.framework.interfaces.UnleashedBlock;
import com.curlip.unleashed.framework.interfaces.UnleashedItem;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegister {
	
	private HashMap<String, UnleashedBlock> blocks = new HashMap<String, UnleashedBlock>();
	
	public void addBlock(UnleashedBlock block){
		blocks.put(block.getID(), block);
	}
	
	public UnleashedBlock getByID(String id){
		return blocks.get(id);
	}
	
	public Collection<UnleashedBlock> getAllBlocks(){
		return blocks.values();
	}
}
