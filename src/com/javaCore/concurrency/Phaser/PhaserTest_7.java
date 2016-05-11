package com.javaCore.concurrency.Phaser;

import java.util.concurrent.Phaser;

public class PhaserTest_7 {
	private static final int TASKS_PER_PHASER  = 4;
	
	public static void main(String[] args) {
		final int phaseToTerminate = 3;
		
		Phaser phaser = new Phaser(){
			@Override  
            protected boolean onAdvance(int phase, int registeredParties) {  
                System.out.println("====== " + phase + " ======");  
                return phase == phaseToTerminate || registeredParties == 0;  
            }  
		};
		
		final Task_07[] tasks = new Task_07[10];
		Builder(tasks,0,tasks.length,phaser);
		
		for (int i = 0; i < tasks.length; i++) {  
            System.out.println("starting thread, id: " + i);  
            final Thread thread = new Thread(tasks[i]);  
            thread.start();  
        }  
	}
	
	private static void Builder(Task_07[] tasks, int lo, int hi,
			Phaser phaser) {
		if (hi - lo > TASKS_PER_PHASER) {  
            for (int i = lo; i < hi; i += TASKS_PER_PHASER) {  
                int j = Math.min(i + TASKS_PER_PHASER, hi);  
                Builder(tasks, i, j, new Phaser(phaser));  
            }  
        } else {  
            for (int i = lo; i < hi; ++i)  
                tasks[i] = new Task_07(i, phaser);  
        }  
	}

	static class Task_07 implements Runnable{
		private final Phaser phaser;
		private int i ;
		
		Task_07(int i , Phaser phaser){
			this.i = i;
			this.phaser = phaser;
			this.phaser.register();
		}
		@Override
		public void run() {
			while (!phaser.isTerminated()) {  
                try {  
                    Thread.sleep(200);  
                } catch (InterruptedException e) {  
                }  
                System.out.println("in Task.run(), phase: " + phaser.getPhase()    + ", id: " + this.i);  
                phaser.arriveAndAwaitAdvance();  
            }  
        }  
	}
}
