package com.javaCore.java_thread.model.GuardedSuspension;

/**
 * @Author: chenssy
 * @Date: 2016/10/26 22:27
 */
public class ServerThread extends Thread{
    private RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue,String name){
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while(true){
            final Request request = requestQueue.getRequest();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName() + " handles " + request);
        }
    }
}
