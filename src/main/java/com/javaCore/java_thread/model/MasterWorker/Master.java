package com.javaCore.java_thread.model.MasterWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @Author: chenssy
 * @Date: 2016/10/26 21:03
 */
public class Master {
    //任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedDeque<Object>();
    //Work进程队列
    protected Map<String,Thread> threadMap = new HashMap<String, Thread>();
    //子任务处理结果集
    protected Map<String,Object> resultMap = new ConcurrentHashMap<String,Object>();

    /**
     * 判断任务是否结束
     *
     * @author chenssy
     * @date 2016-10-26
     * @since v1.0.0
     */
    public boolean isComplete(){
        for(Map.Entry<String,Thread> entry : threadMap.entrySet()){
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    public Master(Worker worker,int countWorker){
        worker.setWorkerQueue(workQueue);
        worker.setResultMap(resultMap);

        for(int i = 0 ; i < countWorker ; i++){
            threadMap.put(Integer.toString(i),new Thread(worker,Integer.toString(i)));
        }
    }

    /**
     * 提交一个任务
     *
     * @author chenssy
     * @date 2016-10-26
     * @since v1.0.0
     */
    public void submit(Object object){
        workQueue.add(object);
    }

    /**
     * 获取结果集
     *
     * @author chenssy
     * @date 2016-10-26
     * @since v1.0.0
     */
    public Map<String,Object> getResultMap(){
        return  resultMap;
    }

    /**
     * 执行子任务
     *
     * @author chenssy
     * @date 2016-10-26
     * @since v1.0.0
     */
    public void execute(){
        for (Map.Entry<String,Thread> entery:threadMap.entrySet()){
            entery.getValue().start();
        }
    }
}
