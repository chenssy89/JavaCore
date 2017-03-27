package com.javaCore.java_thread.JUC.Lock.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义独占锁<Br>
 *
 * @author:chenssy
 * @date : 2016/10/8 8:42
 */
public class Mutex implements Lock{

    private static class MySync extends AbstractQueuedSynchronizer{
        protected boolean isHeldExclusively(){
            return getState() >= 1;
        }

        protected boolean tryAcquire(int acquires){
            if(compareAndSetState(0,acquires)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int releases){
            if(getState() == 0){
                throw new IllegalMonitorStateException();
            }

            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        protected Condition newCondition(){
            return new ConditionObject();
        }
    }

    /*
     *  下面的方法直接使用sync代理即可
     */
    private final MySync sync = new MySync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
