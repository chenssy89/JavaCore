package com.javaCore.java_thread.JUC.Lock.ReentrantLock._1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
	private final Lock queueLock = new ReentrantLock();

	public void printJob(Object document) {
		try {
			queueLock.lock();
			System.out.println(Thread.currentThread().getName() + ": Going to print a document");
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
			Thread.sleep(duration);
			System.out.printf(Thread.currentThread().getName() + ": The document has been printed\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}
	}
}
