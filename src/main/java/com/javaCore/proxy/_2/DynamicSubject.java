package com.javaCore.proxy._2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ��������
 * 
 * @Project:JavaCore
 * @file:DynamicSubject.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : <a href="http://cmsblogs.com">http://cmsblogs.com</a>
 * @qq : 122448894
 *
 * @data:2016��1��6��
 */
public class DynamicSubject implements InvocationHandler{

	private Object object;
	
	public DynamicSubject(Object object){
		this.object = object;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("���ô�����֮ǰ��ִ�еķ���...." + method);
		
		method.invoke(object, args);
		
		System.out.println("���ô�����֮��ִ�еķ���...." + method);
		
		return null;
	}

}
