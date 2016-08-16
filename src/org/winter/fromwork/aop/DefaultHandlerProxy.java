package org.winter.fromwork.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.winter.fromwork.ApplicationBeanFactory;

import com.app.aop.ProxyInstance;
import com.app.service.DtService;

public class DefaultHandlerProxy<T> {

	
	

	public  void handle(String beanId,HashMap<String, Object> beans){
		
		
		  final Object bean = ApplicationBeanFactory.getBean(beanId);
		
		
		final List<Object> listListener = new ArrayList<>();
		
		
		for ( Entry<String ,Object> e: beans.entrySet()) {
			
			Class<?>[] interfaces = e.getValue().getClass().getInterfaces();
			for (Class<?> class1 : interfaces) {
				if("org.winter.fromwork.aop.ProxyInterceptListener".equals(class1.getName())){
				
					
					listListener.add( e);
				}
			}
		
		}
		
		
		
		
		
		
		@SuppressWarnings("unchecked")
		T proxyInstance =  (T) Proxy.newProxyInstance(bean.getClass().getClassLoader(),
				bean.getClass().getInterfaces(), new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					
						for (Object obj : listListener) {
							
							Method[] declaredMethods = obj.getClass().getDeclaredMethods();
							if("before".equals(declaredMethods.getClass().getName())){
								System.out.println("hahah");
							}
							
							
							//p.before(proxy, method, args);
						}
						Object invoke = method.invoke(bean, args);
						/*for (Object obj : listListener) {
							System.out.println(obj);
							ProxyInterceptListener p = (ProxyInterceptListener) obj;
							p.after(proxy, method, args);
						}*/
						return invoke;
					}
				});
		
		beans.put(beanId+"Proxy", proxyInstance);
	}
}
