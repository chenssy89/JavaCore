package com.javaCore.proxy._2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
	public static void main(String[] args) {
		RealSubject realSubject = new RealSubject("chenssy");
		InvocationHandler ds = new DynamicSubject(realSubject);
		
		//���ɴ�����
		Subject subject = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),ds);
		subject.request();
	}
}
