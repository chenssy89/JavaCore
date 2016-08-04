package com.javaCore.proxy._1;

public class ProxySubject implements Subject{

	private RealSubject realSubject;
	
	public ProxySubject(RealSubject realSubject){
		this.realSubject = realSubject;
	}
	
	@Override
	public void request() {
		realSubject.request();
	}

}
