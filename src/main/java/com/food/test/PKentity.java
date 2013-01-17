package com.food.test;


/**
 * client
 * @author liangjun.zhong
 */
public class PKentity {
	
	private Production firstProduction; 
	private Production secondProduction;
	private boolean hasMessage; //是否有新消息
	private int codeStatus; //response status
	
	public Production getFirstProduction() {
		return firstProduction;
	}
	public void setFirstProduction(Production firstProduction) {
		this.firstProduction = firstProduction;
	}
	public Production getSecondProduction() {
		return secondProduction;
	}
	public void setSecondProduction(Production secondProduction) {
		this.secondProduction = secondProduction;
	}
	public boolean isHasMessage() {
		return hasMessage;
	}
	public void setHasMessage(boolean hasMessage) {
		this.hasMessage = hasMessage;
	}
	public int getCodeStatus() {
		return codeStatus;
	}
	public void setCodeStatus(int codeStatus) {
		this.codeStatus = codeStatus;
	}
	
	

}
