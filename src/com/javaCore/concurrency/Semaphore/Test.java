package com.javaCore.concurrency.Semaphore;

public class Test {
	public static void main(String[] args) {
		Thread[] threads = new Thread[5];
		
		PrintQueue printQueue = new PrintQueue();
		
		for(int i = 0 ; i < 5 ; i++){
			threads[i] = new Thread(new Job(printQueue),"Thread_" + i);
		}
		
		for(int i = 0 ; i < 5 ; i++){
			threads[i].start();
		}
	}
}
