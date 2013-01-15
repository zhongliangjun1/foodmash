package com.food.enums;
/**
 * 返回状态
 * @author liangjun.zhong
 */
public enum CodeStatus {
	
	Refresh(100), //请刷新
	Success(200), //成功
	Redirection (300), //重定向
	ClientError(400), //客户端错误
	ServerError(500); //服务器端错误
	
	public int value;
	
	public int getValue() {
		return value;
	}

	private CodeStatus(int value){
		this.value = value;
	}

}
