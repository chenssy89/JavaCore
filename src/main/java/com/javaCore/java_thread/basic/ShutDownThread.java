package com.javaCore.java_thread.basic;

import com.sun.java.accessibility.util.TopLevelWindowListener;
import com.sun.xml.internal.ws.util.Pool;

import java.util.concurrent.TimeUnit;

/**
 * 安全有效地终止线程
 *
 * @author:chenssy
 * @date : 2016/8/16 8:57
 */
public class ShutDownThread {
    static class Runner implements Runnable{
        private long i ;
        private volatile boolean on = true;
        @Override
        public void run() {
            while(on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancle(){
            on = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one,"CountThread");
        countThread.start();

        //休眠1秒，main线程对CountThread进行中断，使CountThread能够感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two,"CountThread");
        countThread.start();

        //休眠1秒，main线程对Ruuner two 进行取消，使得CountThread能够感知on为false而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancle();
    }
}
