package com.javaCore.java_thread.JUC.ReentrantLock;

public class Job implements Runnable{
	private PrintQueue printQueue;
    
    public Job(PrintQueue printQueue){
        this.printQueue = printQueue;
    }
    
    @Override
    public void run() {
        printQueue.printJob(new Object());
    }
}
