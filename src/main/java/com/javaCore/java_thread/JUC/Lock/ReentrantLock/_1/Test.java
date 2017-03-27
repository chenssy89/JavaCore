package com.javaCore.java_thread.JUC.Lock.ReentrantLock._1;

public class Test {
	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();

		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread " + i);
		}

		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
}
