package org.winter.fromwork.aop;

import java.lang.reflect.Method;

public interface ProxyInterceptListener {

	
	public void before(Object proxy, Method method, Object[] args);
	public void after(Object proxy, Method method, Object[] args);
}
