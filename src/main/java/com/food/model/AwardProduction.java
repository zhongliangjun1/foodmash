package com.food.model;
/**
 * 获奖记录
 * @author liangjun.zhong
 * @version 创建时间：Jan 18, 2013 7:50:49 PM
 */
public class AwardProduction {
	
	private String productionId; //Production的id	
	private String addTime;
	private boolean isInform; //该获奖作品是否作者已知
	
	//作者联系信息
	private String authorName;
	private String authorPhone;
	private String authorAddress;
	
	public String getProductionId() {
		return productionId;
	}
	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public boolean isInform() {
		return isInform;
	}
	public void setInform(boolean isInform) {
		this.isInform = isInform;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorPhone() {
		return authorPhone;
	}
	public void setAuthorPhone(String authorPhone) {
		this.authorPhone = authorPhone;
	}
	public String getAuthorAddress() {
		return authorAddress;
	}
	public void setAuthorAddress(String authorAddress) {
		this.authorAddress = authorAddress;
	}
	
	

}
