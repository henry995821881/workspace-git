package com.app.action;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.winter.fromwork.Action;
import org.winter.fromwork.ActionMethod;
import org.winter.fromwork.ApplicationBeanFactory;
import org.winter.fromwork.aop.ProxyInterceptListener;

import com.app.aop.ProxyInstance;
import com.app.service.DtService;

@Action
public class Dt0001Action {
	
	
	@ActionMethod(url="/edit.do")
	public String editItem(HttpServletRequest request,HttpServletResponse response,Map data){
		
		
		
		Object he = data.get("validationToken");
		request.setAttribute("he","sdfjoewjdsf");
		return "/winter/edit.jsp";
	}
	
	@ActionMethod(url="/queryItem.do")
	public String queryItem(HttpServletRequest request,HttpServletResponse response,Map data){
	
		
		
		return "/winter/queryItem.jsp";
		
	}

}
