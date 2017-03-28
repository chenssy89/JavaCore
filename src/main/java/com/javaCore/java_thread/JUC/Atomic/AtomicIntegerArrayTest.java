package com.javaCore.java_thread.JUC.Atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicIntegerArrayTest
 *
 * @author:chenssy
 * @date : 2017/3/28 9:11
 */
public class  AtomicIntegerArrayTest{
    private static int[] value = new int[]{1,2,3,4,5};

    private static AtomicIntegerArray aia = new AtomicIntegerArray(value);

    public static void main(String[] args){
        aia.addAndGet(2,10);    //第三个元素 + 10
        System.out.println(aia.get(2));

        //内部进行复制，不会影响原本value
        aia.set(1,0);
        System.out.println(value[0]);
        System.out.println(aia.get(1));
    }

}
