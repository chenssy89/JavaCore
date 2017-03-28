package com.javaCore.java_thread.JUC.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger<Br>
 *
 * addAndGet：以原子方式将输入的值与实例的值相加并返回结果<Br>
 * getAndIncrement：以原子方式将当前值加1,返回的自增之前的值<Br>
 * lazySet：最终会设置成newValue，使用lazySet设置值后，可能会导致其他线程在之后的一小段时间内还是可以读到旧值<Br>
 * getAndSet：以原子方式设置为newValue，返回oldValue<Br>
 *
 * @author:chenssy
 * @date : 2017/3/28 8:56
 */
public class AtomicIntegerTest {
    private static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args){
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.getAndSet(999));
        System.out.println(ai.get());
    }

}
