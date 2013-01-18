package com.food.model;


/**
 * 作品
 * @author liangjun.zhong
 * @version 创建时间：Jan 14, 2013 11:41:23 PM
 */
public class Production extends Model {
	
	//Model中的ObjectId作为Production的id：String getStringId()
	
	private String authorId; //设备id
	private String imgSrc; //图片URL
	private String review; //图片描述
	private String addTime;
	
	private int showNum; //展示次数
	private int likeNum; //被like次数
	private boolean hasTreat; //满足展示次数是否已被计算获奖
	
	
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public int getShowNum() {
		return showNum;
	}
	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public boolean isHasTreat() {
		return hasTreat;
	}
	public void setHasTreat(boolean hasTreat) {
		this.hasTreat = hasTreat;
	}
	
	
	

}
