package main.java.com.javaCore.java_thread.model.MasterWorker;

import java.util.Map;
import java.util.Queue;

/**
 * @Author: chenssy
 * @Date: 2016/10/26 21:15
 */
public class Worker implements Runnable{
    protected Queue<Object> workerQueue;

    protected Map<String,Object> resultMap ;

    public void setWorkerQueue(Queue<Object> workerQueue) {
        this.workerQueue = workerQueue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true){
            Object input = workerQueue.poll();
            if(input == null){
                break;
            }

            System.out.println("子任务【" + Thread.currentThread().getName() + "】开始执行,执行对象为--" + input.toString());
            Object result = handle(input);
            System.out.println("子任务【" + Thread.currentThread().getName() + "】执行完成,结果为：" + result.toString());
            resultMap.put(Integer.toString(input.hashCode()),result);
        }
    }

    /**
     * 子任务处理逻辑，在子类中覆盖执行具体逻辑
     *
     * @author chenssy
     * @date 2016-10-26
     * @since v1.0.0
     */
    public Object handle(Object input) {
        return input;
    }
}
