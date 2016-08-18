package com.javaCore.java_thread.JUC.Phaser;

import java.util.concurrent.Phaser;

public class PhaserTest_5 {
	public static void main(String[] args) {
		Phaser phaser = new Phaser(1);		//�൱��CountDownLatch(1) 
		
		//���������
		for(int i = 0 ; i < 3 ; i++){
			Task_05 task = new Task_05(phaser);
			Thread thread = new Thread(task,"PhaseTest_" + i);
			thread.start();
		}
		
		try {
			//�ȴ�3��
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		phaser.arrive();		//countDownLatch.countDown()
	}
	
	static class Task_05 implements Runnable{
		private final Phaser phaser;
		
		Task_05(Phaser phaser){
			this.phaser = phaser;
		}
		
		@Override
		public void run() {
			phaser.awaitAdvance(phaser.getPhase());		//countDownLatch.await()
			System.out.println(Thread.currentThread().getName() + "ִ������...");
		}
	}
}
