package org.winter.fromwork;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class DefaultActionMapping {

	public Object[] getActionAndMethod(String requestURI, String contextPath) {
		
		
		String relativePath = requestURI.substring(contextPath.length());
		HashMap<String, Object> beans = ApplicationBeanFactory.getAllBean();
		List<Object> actionList = new ArrayList<>();
		
		for(Entry<String, Object> entry :beans.entrySet()){
			Object obj = entry.getValue();
			if(obj.getClass().getAnnotation(Action.class) != null){
				actionList.add(obj);
				
			}
		}
		
		for (Object action : actionList) {
			
			Method[] methods = action.getClass().getDeclaredMethods();
			for (Method method : methods) {
				
				ActionMethod actionMethod = method.getAnnotation(ActionMethod.class);
				if(actionMethod !=null && relativePath.equals(actionMethod.url())){
					
					Object[] actionAndMethod = new Object[]{action,method};
					
					return actionAndMethod;
				}
				
			}
			
			
			
			
		}

		return null;
		
	}

}
