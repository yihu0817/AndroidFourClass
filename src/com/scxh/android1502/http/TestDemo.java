package com.scxh.android1502.http;

public class TestDemo {
	public void main(String[] args){
		Boss boss = new Boss();
		
		Employe employe = new Employe();
		employe.setCallBackInterface(boss);
		
		employe.doSome();
	}
}
