package com.javaCore.java_thread.JUC.ReentrantReadWriteLock;

public class Reader implements Runnable{
	
	private PricesInfo pricesInfo;
	
	public Reader(PricesInfo pricesInfo){
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + "--Price 1:" + pricesInfo.getPrice1());
			System.out.println(Thread.currentThread().getName() + "--Price 1:" + pricesInfo.getPrice2());
		}
	}

}
