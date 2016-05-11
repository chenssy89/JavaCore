package com.javaCore.concurrency.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * ��ӡ����
 * @Project:javaConcurrency
 * @file:PrintQueue.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : http://cmsblogs.com
 * @qq : 122448894
 *
 * @data:2015��9��6��
 */
public class PrintQueue {
	private final Semaphore semaphore;   //�����ź���
	
	public PrintQueue(){
		semaphore = new Semaphore(1);
	}
	
	public void printJob(Object document){
		try {
			semaphore.acquire();//����acquire��ȡ�ź���
			long duration = (long) (Math.random() * 10);
			System.out.println( Thread.currentThread().getName() + 
					"PrintQueue : Printing a job during " + duration);
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  finally{
			semaphore.release();  //�ͷ��ź���
		}
	}
}
