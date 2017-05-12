package com.ufclub.ljs.autoInvest.model.AutoInvestSettingcom.JVM.stack;

/**
 * @author chenssy
 * @date 2016/11/19
 * @since v1.0.0
 */
public class StackGCTest {

    //如果一个局部变量被保存在局部变量表中，那么GC就能引用到这个局部变量所指向的内存空间
    //从而在GC时，可能无法回收这部分空间
    public static  void test1(){
        {
            byte[] bytes = new byte[6 * 1024 * 1024];
        }
        System.gc();
        System.out.println("first explict gc over");
    }

    //手工释放该空间，将变量设置为null
    public static void test2(){
        {
            byte[] bytes = new byte[6 * 1024 * 1024];
            bytes = null;
        }
        System.gc();
        System.out.println("first explict gc over");
    }

    //或者重新声明一个新的局部变量，从而复用该变量的字，使其所占有的空间可以被GC回收
    public static void test3(){
        {
            byte[] bytes = new byte[6 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
        System.out.println("first explict gc over");
    }


    public static void main(String[] args){
       test1();
//        test2();
    }
}
