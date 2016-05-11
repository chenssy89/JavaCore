package com.javaCore.concurrency.CopyOnWriteArraySet;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * CopyOnWriteArraySet�ǡ��̰߳�ȫ���ļ��ϣ���HashSet�Ƿ��̰߳�ȫ��<br>
 * �ڲ��������£�����̲߳���Setʱ:<br>
 * --��listΪHashSet�����ܻ����ConcurrentModification�쳣<br>
 * --��listΪCopyOnWriteArraySet���򲻻�����쳣
 * @Project:javaConcurrency
 * @file:CopyOnWriteArraySetTest.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : http://cmsblogs.com
 * @qq : 122448894
 *t
 * @data:2015��10��12��
 */
public class CopyOnWriteArraySetTest {
//	private static Set<String> setes = new HashSet<String>();
	
	private static Set<String> setes = new CopyOnWriteArraySet<String>();
	
	public static void main(String[] args) {
		new MyThread("testA").start();
		new MyThread("testB").start();
	}
	
	private static class MyThread extends Thread{
		MyThread(String name){
			super(name);
		}
		public void run(){
			for(int i = 0 ; i < 5 ; i++){
				setes.add(Thread.currentThread().getName() + "--" + i);
				printAll();
			}
		}
	}

	public static void printAll() {
		Iterator<String> iterator = setes.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next() + "   ");
		}
		System.out.println("");
	}
}
