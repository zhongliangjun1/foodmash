package com.food.model;
/**
 * codeStatus 标志返回结果状态
 * @author liangjun.zhong
 * @version 创建时间：Jan 18, 2013 3:17:56 PM
 */
public class CodeEntity {
	
	private int codeStatus; //response status
	private boolean hasMessage; //是否有新消息
	

	public int getCodeStatus() {
		return codeStatus;
	}
	public void setCodeStatus(int codeStatus) {
		this.codeStatus = codeStatus;
	}
	public boolean isHasMessage() {
		return hasMessage;
	}
	public void setHasMessage(boolean hasMessage) {
		this.hasMessage = hasMessage;
	}
	
	
	
	

}
