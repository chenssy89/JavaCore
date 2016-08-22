package com.javaCore.java_thread.JUC.Lock.condition;

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
