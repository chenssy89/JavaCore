package main.java.com.javaCore.java_thread.model.MasterWorker;

/**
 * @Author: chenssy
 * @Date: 2016/10/26 21:25
 */
public class PlusWorker extends Worker {
    @Override
    public Object handle(Object input) {
        Integer i = (Integer) input;
        return i * i * i;
    }
}
