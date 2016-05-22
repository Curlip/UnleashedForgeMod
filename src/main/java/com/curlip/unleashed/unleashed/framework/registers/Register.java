package com.curlip.unleashed.framework.registers;

import java.util.Collection;
import java.util.HashMap;

import com.curlip.unleashed.framework.interfaces.UnleashedItem;
import com.curlip.unleashed.framework.interfaces.UnleashedRegisterable;

public class Register<T extends UnleashedRegisterable> {

	private HashMap<String, T> objs = new HashMap<String, T>();
	
	public void add(T obj){
		objs.put(obj.getID(), obj);
	}
	
	public T getByID(String id){
		return objs.get(id);
	}
	
	public Collection<T> getAll(){
		return objs.values();
	}
}
