package com.javaCore.proxy._2;

/**
 * 
 * 真实角色：代理角色所代表的真实对象，是我们最终要引用的对象
 * 
 * @Project:JavaCore
 * @file:RealSubject.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : <a href="http://cmsblogs.com">http://cmsblogs.com</a>
 * @qq : 122448894
 *
 * @data:2016年1月6日
 */
public class RealSubject implements Subject{

	private String name;
	
	public RealSubject(String name){
		this.name = name;
	}
	
	@Override
	public void request() {
		System.out.println(name + "----真实角色......");
	}
}
