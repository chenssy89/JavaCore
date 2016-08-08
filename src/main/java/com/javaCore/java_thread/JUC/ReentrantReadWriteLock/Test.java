package com.javaCore.java_thread.JUC.ReentrantReadWriteLock;

public class Test {
	public static void main(String[] args) {
		PricesInfo pricesInfo = new PricesInfo();
		
		Reader[] readers = new Reader[5];
		Thread[] readerThread = new Thread[5];
		for (int i=0; i<5; i++){
			readers[i]=new Reader(pricesInfo);
			readerThread[i]=new Thread(readers[i]);
		}
		
		Writer writer=new Writer(pricesInfo);
		Thread threadWriter=new Thread(writer);
		
		for (int i=0; i<5; i++){
			readerThread[i].start();
		}
		threadWriter.start();
	}
}
