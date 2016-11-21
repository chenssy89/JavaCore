package com.JVM.stack;

/**
 * 局部变量对GC的影响；
 *
 *
 * @author chenssy
 * @date 2016-11-17
 * @since v1.0.0
 */
public class WordReuseTest {
    public static void test(){
        {
            byte[] b = new byte[6 * 1024 * 1024];
        }
        System.gc();
        System.out.println("first explict gc over");
    }

    public static void main(String[] args){
        test();
    }
}
