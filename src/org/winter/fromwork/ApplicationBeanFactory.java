package org.winter.fromwork;

import java.util.HashMap;
import java.util.Map;



public class ApplicationBeanFactory {

	private static HashMap<String, Object> map =null;

	
	
	
	
	public static Object  getBean(String id){
		Object obj = null;
		if((obj =map.get(id+"Proxy")) ==null){
			obj= map.get(id);
		}
		return obj;
		
	}
	
	
	public static HashMap<String, Object> getAllBean(){
		return map;
	}
	
	
	

	
}
