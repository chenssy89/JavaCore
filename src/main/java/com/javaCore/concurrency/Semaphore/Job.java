package com.javaCore.concurrency.Semaphore;

/**
 * 
 * @Project:javaConcurrency
 * @file:Job.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : http://cmsblogs.com
 * @qq : 122448894
 *
 * @data:2015��9��6��
 */
public class Job implements Runnable{
	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue){
		this.printQueue = printQueue;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Going to print a job");
		printQueue.printJob(new Object());
		System.out.println(Thread.currentThread().getName() + " the document has bean printed");
	}

}
