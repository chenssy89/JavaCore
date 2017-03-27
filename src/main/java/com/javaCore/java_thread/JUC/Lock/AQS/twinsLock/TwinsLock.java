package com.javaCore.java_thread.JUC.Lock.AQS.twinsLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * ${todo}
 *
 * @author:chenssy
 * @date : 2016/10/14 9:21
 */
public class TwinsLock implements Lock{

    private class TwinsSync extends AbstractQueuedSynchronizer{
        public TwinsSync(int count){
            if(count < 0){
                throw new IllegalArgumentException("Count must large than zero.");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for(;;){
                int current = getState();
                int newCount = current - reduceCount;
                if(newCount < 0 || compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for(;;){
                int current = getState();
                int newCount = current - arg;
                if(newCount <0 || compareAndSetState(current,newCount)){
                    return true;
                }
            }

        }
    }

    private final TwinsSync sync = new TwinsSync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        //doSomething
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
