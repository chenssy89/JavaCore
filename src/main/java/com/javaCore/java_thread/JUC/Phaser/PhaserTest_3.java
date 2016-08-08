package com.javaCore.java_thread.JUC.Phaser;

import java.util.concurrent.Phaser;

public class PhaserTest_3 {
	public static void main(String[] args) {
		Phaser phaser = new Phaser(3){
			/**
			 * registeredParties:�߳�ע�������
			 * phase:����÷������߳���
			 */
			 protected boolean onAdvance(int phase, int registeredParties) { 
				 System.out.println(Thread.currentThread().getName() + "ִ��onAdvance����.....;phase:" + phase + "registeredParties=" + registeredParties);
				 return phase == 2; 
			 }
		};
		
		for(int i = 0 ; i < 3 ; i++){
			Task_03 task = new Task_03(phaser);
			Thread thread = new Thread(task,"task_" + i);
			thread.start();
		}
		while(!phaser.isTerminated()){
			phaser.arriveAndAwaitAdvance();	//���߳�һֱ�ȴ�
		}
		System.out.println("���߳������Ѿ�����....");
	}
	
	static class Task_03 implements Runnable{
		private final Phaser phaser;
		
		public Task_03(Phaser phaser){
			this.phaser = phaser;
		}
		
		@Override
		public void run() {
			do{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "��ʼִ������...");
				phaser.arriveAndAwaitAdvance();
			}while(!phaser.isTerminated());
		}
	}
}
