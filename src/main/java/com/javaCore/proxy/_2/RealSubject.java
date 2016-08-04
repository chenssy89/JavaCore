package com.javaCore.proxy._2;

/**
 * 
 * ��ʵ��ɫ�������ɫ���������ʵ��������������Ҫ���õĶ���
 * 
 * @Project:JavaCore
 * @file:RealSubject.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : <a href="http://cmsblogs.com">http://cmsblogs.com</a>
 * @qq : 122448894
 *
 * @data:2016��1��6��
 */
public class RealSubject implements Subject{

	private String name;
	
	public RealSubject(String name){
		this.name = name;
	}
	
	@Override
	public void request() {
		System.out.println(name + "----��ʵ��ɫ......");
	}
}
