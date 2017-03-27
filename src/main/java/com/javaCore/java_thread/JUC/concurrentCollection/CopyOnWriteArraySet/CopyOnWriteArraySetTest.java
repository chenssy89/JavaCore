package com.javaCore.java_thread.JUC.concurrentCollection.CopyOnWriteArraySet;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetTest {
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
