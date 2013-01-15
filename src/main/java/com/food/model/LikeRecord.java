package com.food.model;
/**
 * 作品被like的一条记录
 * @author liangjun.zhong
 * @version 创建时间：Jan 16, 2013 12:42:34 AM
 */
public class LikeRecord extends Model {
	
	private String productionId; //Production的id
	private String likerId; //like此Production的deviceId
	private String addTime;
	
	public String getProductionId() {
		return productionId;
	}
	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}
	public String getLikerId() {
		return likerId;
	}
	public void setLikerId(String likerId) {
		this.likerId = likerId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	

}
