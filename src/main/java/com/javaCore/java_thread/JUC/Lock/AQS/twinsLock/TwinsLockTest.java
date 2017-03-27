package com.javaCore.java_thread.JUC.Lock.AQS.twinsLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * ${todo}
 *
 * @author:chenssy
 * @date : 2016/10/14 10:44
 */
public class TwinsLockTest {
    public static void main(String args[]) throws InterruptedException {
        final Lock lock = new TwinsLock();

        class Work extends Thread{
            @Override
            public void run() {
                lock.lock();

                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        for(int i = 0 ; i < 10 ; i++){
            Work work = new Work();
            work.setDaemon(true);
            work.start();
        }

        for(int i = 0 ; i < 10 ; i++){
            TimeUnit.SECONDS.sleep(1);
            //应该是成对输出！！
            System.out.println("");
        }
    }
}
