package com.javaCore.java_thread.JUC.ThreadUtils.Exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Test {
	public static void main(String[] args) {
		List<String> buffer1 = new ArrayList<>();
		List<String> buffer2 = new ArrayList<>();
		
		Exchanger<List<String>> exchanger = new Exchanger<>();
		
		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);
		
		Thread thread1 = new Thread(producer);
		Thread thread2 = new Thread(consumer);
		
		thread1.start();
		thread2.start();
	}
}
