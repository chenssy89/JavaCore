package com.javaCore.java_thread.JUC.ThreadUtils.Exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * �����
 * @Project:javaConcurrency
 * @file:Producer.java
 *
 * @Author:chenssy
 * @email:chenssy995812509@163.com
 * @url : http://cmsblogs.com
 * @qq : 122448894
 *
 * @data:2015��9��21��
 */
public class Producer implements Runnable{
	
	/**
	 * ����ߺ�����߽��н�������ݽṹ
	 */
	private List<String> buffer;
	
	/**
	 * ͬ������ߺ�����ߵĽ�������
	 */
	private final Exchanger<List<String>> exchanger;
	
	Producer(List<String> buffer,Exchanger<List<String>> exchanger){
		this.buffer = buffer;
		this.exchanger = exchanger;
	}
	
	@Override
	public void run() {
		int cycle = 1;
		for(int i = 0 ; i < 10 ; i++){
			System.out.println("Producer : Cycle :" + cycle);
			for(int j = 0 ; j < 10 ; j++){
				String message = "Event " + ((i * 10 ) + j);
				System.out.println("Producer : " + message);
				buffer.add(message);
			}
			
			//����exchange()������߽�����ݽ���
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Producer :" + buffer.size());
			cycle++ ;
		}
	}
}
