package com.javaCore.java_thread.basic.synchronize;

public class Company implements Runnable{
	
	private Account account;
	
	public Company(Account account){
		this.account = account;
	}
	
	@Override
	public void run() {
		for(int i = 0 ; i < 99 ; i++){
			account.addAmount(1000);
			System.out.println("Company add account : 1000 , the balance : " + account.getBalance()  );
		}
	}

}
