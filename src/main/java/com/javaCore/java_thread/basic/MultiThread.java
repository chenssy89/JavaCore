package com.javaCore.java_thread.basic;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * java线程管理<br>
 * ThreadMXBean : Java 虚拟机线程系统的管理接口<Br>
 * ThreadInfo : 线程信息
 *
 * @author:chenssy
 * @date : 2016/8/8 8:42
 */
public class MultiThread {
    public static void main(String[] args){
        //获取java线程管理的Bean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息，仅获取线程和线程的堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);

        for(ThreadInfo info : threadInfos){
            System.out.println("[" + info.getThreadId() + "] :" + info.getThreadName());
        }
    }
}
