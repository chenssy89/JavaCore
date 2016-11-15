package com.javaCore.java_thread.model.GuardedSuspension;

import java.util.LinkedList;
import java.util.List;

/**
 * 维护Request请求信息
 *
 * @Author: chenssy
 * @Date: 2016/10/26 22:23
 */
public class RequestQueue {
    private LinkedList queue = new LinkedList();

    public synchronized Request getRequest(){
        while(queue.size() == 0){
            try{
                wait();         //等待，直到有Request
            }catch(InterruptedException e){

            }
        }

        return (Request) queue.remove();
    }

    public synchronized void addRequest(Request request){
        queue.add(request);
        notifyAll();            //唤醒getRequest方法
    }
}
