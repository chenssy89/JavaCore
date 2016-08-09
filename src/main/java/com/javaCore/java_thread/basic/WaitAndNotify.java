package com.javaCore.java_thread.basic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * ${todo}
 *
 * @author:chenssy
 * @date : 2016/8/9 13:00
 */
public class WaitAndNotify {
    static boolean flag = true;
    static Object lock = new Object();
    static class WaitThread implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while (flag){
                    System.out.println(Thread.currentThread() + "flag is true . wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + "flag is false . wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class NotifyThread implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() + "hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock){
                System.out.println(Thread.currentThread() + "hold lock again . notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        Thread waitThread = new Thread(new WaitThread(),"waitThread");
        waitThread.start();

        Thread notifyThread = new Thread(new NotifyThread(),"notifyThread");
        notifyThread.start();
    }
}
