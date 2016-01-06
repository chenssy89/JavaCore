package com.javaCore.proxy._2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理处理器
 * 
 * @Project:JavaCore
 * @file:DynamicSubject.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : <a href="http://cmsblogs.com">http://cmsblogs.com</a>
 * @qq : 122448894
 *
 * @data:2016年1月6日
 */
public class DynamicSubject implements InvocationHandler{

	private Object object;
	
	public DynamicSubject(Object object){
		this.object = object;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("调用代理类之前，执行的方法...." + method);
		
		method.invoke(object, args);
		
		System.out.println("调用代理类之后，执行的方法...." + method);
		
		return null;
	}

}
