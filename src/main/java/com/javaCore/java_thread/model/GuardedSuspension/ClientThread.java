package main.java.com.javaCore.java_thread.model.GuardedSuspension;

/**
 * @Author: chenssy
 * @Date: 2016/10/26 22:30
 */
public class ClientThread extends Thread{
    private RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue ,String name){
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++){
            Request request = new Request("RequestId:" + i  + "ThreadName:" + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " request " + request);

            requestQueue.addRequest(request);       //提交请求

            try {
                Thread.sleep(100);          //客户端请求速度快于服务端处理速度
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ClientThread name is "+Thread.currentThread().getName());
        }

        System.out.println(Thread.currentThread().getName() + "request end");
    }
}
