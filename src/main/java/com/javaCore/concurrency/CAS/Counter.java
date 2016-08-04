package com.javaCore.concurrency.CAS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示CAS操作。在java中自旋CAS实现的基本思路是循环进行CAS操作直到成功为止
 *
 * @author:chenssy
 * @date : 2016/8/4 9:09
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;
    /**
     * 线程不安全的++
     */
    private void count(){
        i++;
    }

    /**
     * 使用AtomicInteger线程安全的++
     */
    private void safeCount(){
        for(;;){
            int i = atomicI.get();
            if(atomicI.compareAndSet(i,++i)){
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();

        for(int i = 0 ; i < 100 ; i++){
            Thread j = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0 ; j < 1000 ; j++){
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(j);
        }

        for(Thread t : ts){
            t.start();
        }

        for(Thread t : ts){
            t.join();       //等待线程执行完成
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

}
