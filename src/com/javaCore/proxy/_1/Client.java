package com.javaCore.proxy._1;

public class Client {
	public static void main(String[] args) {
		RealSubject realSubject = new RealSubject("chenssy");
		
		ProxySubject proxySubject = new ProxySubject(realSubject);
		proxySubject.request();
	}
}