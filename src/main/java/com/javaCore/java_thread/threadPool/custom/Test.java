package main.java.com.javaCore.java_thread.threadPool.custom;

/**
 * @author chenssy
 * @date 2016/10/31
 * @since v1.0.0
 */
public class Test {

    public static void main(String[] args){
        long begin1 = System.currentTimeMillis();
        for(int i = 0 ; i < 500 ; i++){
            new Thread(new MyThread("no pool_"+i)).start();
        }
        long end1 = System.currentTimeMillis();

        long begin2 = System.currentTimeMillis();
        for(int i = 0 ; i < 500 ; i++){
            ThreadPool.getInstance().start(new MyThread("pool_"+i));
        }
        long end2 = System.currentTimeMillis();

        System.out.println("no pool times:" + (end1 - begin1));
        System.out.println("pool times:" + (end2 - begin2));
    }

    private static class MyThread implements Runnable{
        String name = null;
        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
