package com.javaCore.concurrency.condition;

public class Customer {
	private Depot depot;

	public Customer(Depot depot) {
		this.depot = depot;
	}

	public void consume(final int value) {
		new Thread() {
			public void run() {
				depot.get(value);
			}
		}.start();
	}
}
