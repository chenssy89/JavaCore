package com.javaCore.java_thread.model.MasterWorker;

import java.util.Map;
import java.util.Set;

/**
 * @Author: chenssy
 * @Date: 2016/10/26 21:32
 */
public class Test {
    public static void main(String[] args){
        //五个进程跑
        Master master = new Master(new PlusWorker(),5);

        //提交100个子任务
        for(int i = 0 ; i < 100 ; i++){
            master.submit(i);
        }

        master.execute();

        int result = 0 ;

        Map<String,Object> resultMap = master.getResultMap();

        while(resultMap.size() > 0 || !master.isComplete()){
            Set<String> keys = resultMap.keySet();
            String key = null;
            for(String s : keys){
                key = s;
                break;
            }

            Integer i = null;

            if(key != null){
                i = (Integer) resultMap.get(key);
            }
            if(i != null){
                result += i;
            }

            if(key != null){
                resultMap.remove(key);
            }
        }
    }
}
