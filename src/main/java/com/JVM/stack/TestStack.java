package com.JVM.stack;

import org.junit.Test;

/**
 * 测试当前栈的深度
 *
 * @author chenssy
 * @date 2016-11-15
 * @since v1.0.0
 */
public class TestStack {
    private int count = 0;

    public void recursion(){
        count++;
        recursion();
    }

    @Test
    public void testStack(){
        try {
            recursion();
        } catch (Throwable e) {
            System.out.println("Deep of stack is " + count);
            e.printStackTrace();
        }
    }

}
