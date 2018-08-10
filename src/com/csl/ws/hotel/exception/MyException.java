package com.csl.ws.hotel.exception;

/**
 * 自定义异常
 * @author Administrator
 *
 */
public class MyException extends Exception {

	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	public MyException(String errmsg) {
		getMessage(errmsg);
	}
	public  String getMessage(String errmsg){
		return errmsg;
	}
	private static final long serialVersionUID = 1L;

	
	public static void main(String[] args) {
		new MyException("出错了！");
		
	}
}
