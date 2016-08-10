package com.javaCore.java_thread.basic;

import java.util.concurrent.TimeUnit;

/**
 * Thread.join()
 *
 * @author:chenssy
 * @date : 2016/8/10 8:54
 */
public class JoinThread {
    static class Domino implements Runnable{
        private Thread thread;

        Domino(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();

        for(int i = 0 ; i < 10 ; i++){
            Thread thread = new Thread(new Domino(previous),String.valueOf(i));
            thread.start();

            previous = thread;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate");
    }
}
