package com.javaCore.java_thread.threadPool.custom;

import java.util.List;
import java.util.Vector;

/**
 * 自定义线程池
 * @author chenssy
 * @date 2016/10/31 21:38
 */
public class ThreadPool {
    private static ThreadPool instance = null;

    //空闲的线程队列
    private List<Thread> idleThreads;

    private int threadCounter;
    private boolean isShutDown = false;

    private ThreadPool(){
        this.idleThreads = new Vector<Thread>(5);
        threadCounter = 0;
    }

    public  int getCreatedThreadsCount(){
        return threadCounter;
    }

    public synchronized static ThreadPool getInstance(){
        if(instance == null){
            instance = new ThreadPool();
        }
        return instance;
    }

    //将线程加入线程池中
    protected  synchronized void repool(PThread thread){
        if(!isShutDown){
            idleThreads.add(thread);
        }else{
            thread.shutDown();
        }
    }
    
    /**
     * 停止池中所有线程
     * 
     * @author chenssy
     * @date 2016-10-31
     * @since v1.0.0
     */
    public synchronized void shutDown(){
        isShutDown = true;
        for(int i = 0 ; i < idleThreads.size() ; i++){
            PThread thread = (PThread) idleThreads.get(i);
            thread.shutDown();
        }
    }

    public synchronized void start(Runnable runnable){
        PThread thread = null;
        //有空闲线程 直接使用
        if(idleThreads.size() > 0){
            int lastIndex = idleThreads.size() - 1;
            thread = (PThread) idleThreads.get(lastIndex);

            idleThreads.remove(thread);

            //执行任务
            thread.setTarget(thread);
        }else{      //没有空闲线程，则创建新的线程
            threadCounter++;
            thread = new PThread(runnable,"PThread #" + threadCounter,this);

            //启动该线程
            thread.start();
        }
    }
}
