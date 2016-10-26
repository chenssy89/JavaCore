package com.javaCore.java_thread.JUC.Lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Depot {
	private int depotSize; // �ֿ��С
	private Lock lock; // ��ռ��
	private int capaity; // �ֿ�����

	private Condition fullCondition;
	private Condition emptyCondition;

	public Depot() {
		this.depotSize = 0;
		this.lock = new ReentrantLock();
		this.capaity = 15;
		this.fullCondition = lock.newCondition();
		this.emptyCondition = lock.newCondition();
	}

	/**
	 * ��Ʒ���
	 * 
	 * @param value
	 */
	public void put(int value) {
		lock.lock();
		try {
			int left = value;
			while (left > 0) {
				// �������ʱ��������ߡ��ȴ�����ߡ����
				while (depotSize >= capaity) {
					fullCondition.await();
				}
				// ��ȡʵ�����������Ԥ�ƿ�棨�ֿ����п�� + ��������� > �ֿ����� ? �ֿ����� - �ֿ����п�� : �������
				// depotSize left capaity capaity - depotSize left
				int inc = depotSize + left > capaity ? capaity - depotSize
						: left;
				depotSize += inc;
				left -= inc;
				System.out.println(Thread.currentThread().getName()
						+ "----Ҫ�������: " + value + ";;ʵ�����������" + inc
						+ ";;�ֿ����������" + depotSize + ";;û�����������" + left);

				// ֪ͨ����߿��������
				emptyCondition.signal();
			}
		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
	}

	/**
	 * ��Ʒ����
	 * 
	 * @param value
	 */
	public void get(int value) {
		lock.lock();
		try {
			int left = value;
			while (left > 0) {
				// �ֿ��ѿգ�������ߡ��ȴ�����ߡ�������
				while (depotSize <= 0) {
					emptyCondition.await();
				}
				// ʵ����� �ֿ������� < Ҫ��ѵ����� ? �ֿ������� : Ҫ��ѵ�����
				int dec = depotSize < left ? depotSize : left;
				depotSize -= dec;
				left -= dec;
				System.out.println(Thread.currentThread().getName()
						+ "----Ҫ��ѵ�������" + value + ";;ʵ����ѵ�����: " + dec
						+ ";;�ֿ��ִ�������" + depotSize + ";;�ж��ټ���Ʒû����ѣ�" + left);

				// ֪ͨ����߿��������
				fullCondition.signal();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
