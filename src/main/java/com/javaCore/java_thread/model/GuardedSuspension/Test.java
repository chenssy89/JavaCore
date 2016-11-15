package com.javaCore.java_thread.model.GuardedSuspension;

/**
 * @Author: chenssy
 * @Date: 2016/10/26 22:35
 */
public class Test {
    public static void main(String[] args){
        RequestQueue requestQueue = new RequestQueue();

        //开启服务端进程
        for(int i = 0 ; i < 10 ; i++){
            new ServerThread(requestQueue,"ServerThread_" + i).start();
        }

        //开启客户端进程
        for(int i = 0 ; i < 10 ; i++){
            new ClientThread(requestQueue,"ClientThread_" + i).start();
        }
    }
}
