package com.scxh.android1502.http;

import com.scxh.android1502.http.Employe.CallBackInterface;

public class Boss implements CallBackInterface {

	@Override
	public void execute(String message) {
		System.out.println(message);
	}

}
