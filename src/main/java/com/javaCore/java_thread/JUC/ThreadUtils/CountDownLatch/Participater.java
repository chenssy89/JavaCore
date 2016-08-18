package com.javaCore.java_thread.JUC.CountDownLatch;

public class Participater implements Runnable{
	private String name;
	private Conference conference;
	
	public Participater(String name,Conference conference){
		this.name = name;
		this.conference = conference;
	}

	@Override
	public void run() {
		conference.arrive(name);
	}
}
