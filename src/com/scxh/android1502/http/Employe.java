package com.scxh.android1502.http;

public class Employe {
	
	public interface CallBackInterface {
		public void execute(String message);
	}
	
	public CallBackInterface mCallBackInterface;
	
	public void setCallBackInterface(CallBackInterface callBackInterface){
		mCallBackInterface = callBackInterface;
	}
	
//	public Employe(CallBackInterface callBackInterface){
//		mCallBackInterface = callBackInterface;
//	}
	
	public void doSome(){
		//雇员开始做事
		for(int i =0;i < 10; i++){
			System.out.println("做第"+i+"件事完成");
		}
		
		mCallBackInterface.execute("事件已经做完！");
	}
}
