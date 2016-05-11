package com.javaCore.concurrency.CopyOnWriteArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList Ϊ�̰߳�ȫ�Ķ�̬����,ArrayList���̲߳���ȫ��<br>
 * �ڲ��������£�����̲߳���listʱ:<br>
 * --��listΪArrayList�����ܻ����ConcurrentModification�쳣<br>
 * --��listΪCopyWriteArrayList���򲻻�����쳣
 * @Project:javaConcurrency
 * @file:CopyOnWriteArrayListTest.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : http://cmsblogs.com
 * @qq : 122448894
 *
 * @data:2015��10��9��
 */
public class CopyOnWriteArrayListTest {
//	private static List<String> list = new ArrayList<>();
	private static List<String> list = new CopyOnWriteArrayList<String>();
	
	public static void main(String[] args) {
		new MyThread("testA").start();
		new MyThread("testB").start();
	}
	
	private static class MyThread extends Thread{
		public MyThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			for(int i = 0 ; i < 10 ; i++){
				list.add(Thread.currentThread().getName() + "-" + i );
				printAll();
			}
		}
	}
	
	private static void printAll() {
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			String value = iterator.next();
			System.out.print(value + "     ");
		}
		System.out.println();
	}

}
