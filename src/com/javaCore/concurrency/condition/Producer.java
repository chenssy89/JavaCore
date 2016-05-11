package com.javaCore.concurrency.condition;

public class Producer {
    private Depot depot;
    
    public Producer(Depot depot){
        this.depot = depot;
    }
    
    public void produce(final int value){
        new Thread(){
            public void run(){
                depot.put(value);
            }
        }.start();
    }
}
