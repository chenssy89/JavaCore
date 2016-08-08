package com.javaCore.java_thread.JUC.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Conference implements Runnable{
	private final CountDownLatch countDown;
	
	public Conference(int count){
		countDown = new CountDownLatch(count);
	}
	
	/**
	 * �����Ա�������arrive����������һ��CountDownLatch����countDown�������������-1
	 * @author:chenssy
	 * @data:2015��9��6��
	 *
	 * @param name
	 */
	public void arrive(String name){
		System.out.println(name + "����.....");
		//����countDown()������� - 1
		countDown.countDown();
		System.out.println("���� " + countDown.getCount() + "û�е���...");
	}
	
	@Override
	public void run() {
		System.out.println("׼�����ᣬ�μӻ�����Ա����Ϊ��" + countDown.getCount());
		//����await()�ȴ����е������Ա����
		try {
			countDown.await();
		} catch (InterruptedException e) {
		}
		System.out.println("������Ա�Ѿ�������鿪ʼ.....");
	}
}
