package com.javaCore.java_thread.basic;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断测试<br>
 * 线程A不停地休眠，线程B一直运行，然后进行中断操作，观察两个线程的中断标识<Br>
 *
 *InterruptedException:当线程在活动之前或活动期间处于正在等待、休眠或占用状态且该线程被中断时，抛出该异常
 *
 * @author:chenssy
 * @date : 2016/8/16 8:44
 */
public class InterruptedThread {
    static class SleepThread implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(10);     //不停地休眠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyThread implements Runnable{
        @Override
        public void run() {
            while(true){
                //一直运行
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepThread(),"SleepThread");
        Thread busyThread = new Thread(new BusyThread(),"BusyThread");

        busyThread.setDaemon(true);     //设置为Daemon模式
        sleepThread.start();
        busyThread.start();

        //休眠五秒，两个线程充分运行
        TimeUnit.SECONDS.sleep(5);

        //终止线程
        sleepThread.interrupt();
        busyThread.interrupt();

        //观看终止状态
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());

        //防止主线程终止，导致两个线程立即退出
        TimeUnit.SECONDS.sleep(10);
    }
}
