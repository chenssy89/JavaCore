package main.java.com.javaCore.java_thread.threadPool.custom;

/**
 * @author chenssy
 * @date 2016/10/31
 * @since v1.0.0
 */
public class PThread extends Thread{
    //线程池
    private ThreadPool pool;

    //任务
    private Runnable target;

    private boolean isShutDown = false;
    private boolean isIdle = false;

    public PThread(Runnable target,String name,ThreadPool pool){
        super(name);
        this.pool = pool;
        this.target = target;
    }

    public Runnable getTarget(){
        return target;
    }

    public boolean isIdle(){
        return isIdle;
    }

    public void run(){
        while(!isShutDown){
            isIdle = true;
            if(target != null){
                target.run();       //运行线程
            }

            //任务结束了，到闲置状态
            isIdle = true;

            try {
                pool.repool(this);
                synchronized (this){
                    //线程空闲，等待任务到来
                    wait();
                }
            } catch (InterruptedException e) {

            }
            isIdle = false;
        }
    }

    public synchronized void setTarget(Runnable newTarget){
        target = newTarget;
        //设置任务后，通知run方法，执行任务
        notifyAll();
    }

    //关闭线程
    public synchronized void shutDown(){
        isShutDown = true;
        notifyAll();
    }
}
