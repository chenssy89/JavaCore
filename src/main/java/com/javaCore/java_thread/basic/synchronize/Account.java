package com.javaCore.java_thread.basic.synchronize;


public class Account {
	private double balance = 0.0f;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public synchronized void addAmount(double amount){
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
		tmp += amount;
		balance = tmp;
	}
	
	public synchronized void SubtractAmount(double amount){
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
		tmp -= amount;
		balance = tmp;
	}

}
