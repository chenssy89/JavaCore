package com.ufclub.ljs.autoInvest.model.AutoInvestSettingcom.JVM.heap;

/**
 * Java GC测试
 * @author chenssy
 * @date 2016/11/19
 * @since v1.0.0
 */
public class HeapGCTest {
    //VM参数:-Xms=40 -Xmx40m -Xmn20m
    public static void main(String[] args){
        byte[] b1 = new byte[1024 * 1024 / 2];
        byte[] b2 = new byte[2014 * 1024 * 8];

        b2 = null;
        b2 = new byte[2014 * 1024 * 8];
    }
}
