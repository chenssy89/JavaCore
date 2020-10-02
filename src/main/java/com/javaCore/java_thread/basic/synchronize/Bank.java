package com.javaCore.java_thread.basic.synchronize;
//multithreading through implementing runnable
public class Bank implements Runnable{
	
	private Account account;
	
	public Bank(Account account){
		this.account = account;
	}
	
	@Override	
	public void run() {
		for(int i = 0 ; i < 100 ; i++){
			account.SubtractAmount((double)1000);
			System.out.println("Company subtract account : 1000 , the balance : " + account.getBalance()  );
		}
	}
}
