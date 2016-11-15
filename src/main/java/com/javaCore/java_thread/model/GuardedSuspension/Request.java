package com.javaCore.java_thread.model.GuardedSuspension;

/**
 * @Author: chenssy
 * @Date: 2016/10/26 22:13
 */
public class Request {
    private String name;

    public Request(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "[Request " + name + "]";
    }
}
