package com.javaCore.java_thread.JUC.Phaser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Phaser;

public class PhaserTest_2 {
	public static void main(String[] args) throws InterruptedException, IOException {
		Phaser phaser = new Phaser(6);
		int i = 0;
		for(i = 1 ; i <= 6 ; i++){
			Task_02 task = new Task_02(phaser);
			Thread thread = new Thread(task,"task_" + i);
			thread.start();
		}
		System.out.println("Press ENTER to continue");  
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
        reader.readLine();  
        //���»س���ִ��
        phaser.arriveAndDeregister(); 
	}
	
	
	static class Task_02 implements Runnable{
		private final Phaser phaser;
		
		public Task_02(Phaser phaser){
			this.phaser = phaser;
		}
		
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "��ʼִ������...");
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName() + "����ִ������...");
		}
	}
}
