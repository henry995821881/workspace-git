package com.app.aop;

import java.lang.reflect.Method;

import org.winter.fromwork.aop.ProxyInterceptListener;

public class ProxyInstance implements ProxyInterceptListener {



	@Override
	public void before(Object proxy, Method method, Object[] args) {
		
		System.out.println("ProxyInstance.before()");
		
	}

	@Override
	public void after(Object proxy, Method method, Object[] args) {
		
		System.out.println("ProxyInstance.after()");
	}

}
