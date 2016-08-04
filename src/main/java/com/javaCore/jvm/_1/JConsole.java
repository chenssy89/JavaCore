package com.javaCore.jvm._1;

import java.util.ArrayList;
import java.util.List;


public class JConsole {
	static class OOMObject{
		public byte[] placeholder = new byte[64 * 1024];
	}
	
	public static void fillHeap(int numb) throws InterruptedException {
		List<OOMObject> list = new ArrayList<JConsole.OOMObject>();
		for(int i = 0 ; i < numb ; i++){
			Thread.sleep(500);
			list.add(new OOMObject());
		}
		System.gc();
	}
	
	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
	}
}
