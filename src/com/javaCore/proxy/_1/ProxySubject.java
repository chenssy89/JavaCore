package com.javaCore.proxy._1;

public class ProxySubject implements Subject{

	private RealSubject realSubject;
	
	public ProxySubject(RealSubject realSubject){
		this.realSubject = realSubject;
	}
	
	@Override
	public void request() {
		System.out.println("调用代理前......");
		realSubject.request();
		System.out.println("调用代理后......");
	}

}
