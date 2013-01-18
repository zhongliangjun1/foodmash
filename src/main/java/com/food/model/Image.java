package com.food.model;
/**
 * 
 * @author liangjun.zhong
 */
public class Image {
	
	private String code;
	
	private String tempSrc; //临时图片地址，可根据需求组装成最终所需类型
	private String imgSrc; //图片根据裁剪类型确定的最终地址
	private String filename; //图片名
	
	private String naturalWidth; //图片原宽
	private String naturalHeight; //图片原长
	private String cutWidth; //裁剪后宽
	private String cutHeight; //裁剪后高
	
	public static final String UNCHANGE = "x"; //按目标尺寸长或宽中压缩比大的一个比例值进行等比缩放
	public static final String BLANK = "o"; //短边不足部分留白填充
	public static final String CLIP = "c"; //多出部分对称裁剪掉
	
	private static final String ADDRESS = "http://192.168.8.92:8400";
	
	/**
	 * 获取图片的最终URL
	 * @param cutType
	 * @param cutWidth
	 * @param cutHeight
	 * @return
	 */
	public String getImgSrc(String cutType, String cutWidth, String cutHeight) {
		if(tempSrc!=null && cutType!=null && cutWidth!=null && cutHeight!=null){
			imgSrc= ADDRESS + tempSrc + "("+ cutWidth + cutType + cutHeight + ")/thumb.jpg"; // 明天注意下这边的拼接是否正确
		}
		return imgSrc;
	}
	
	/**
	 * 获取临时图片地址，可根据需求组装成最终所需类型
	 * @return
	 */
	public String getTempSrc() {
		return tempSrc;
	}
	
	/**
	 * 获取图片前缀URL
	 * @return
	 */
	public String getPrefixSrc(){
		if(tempSrc!=null){
			tempSrc = ADDRESS + tempSrc;
		}
		return tempSrc;
	}
	
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	public void setTempSrc(String tempSrc) {
		this.tempSrc = tempSrc;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getNaturalWidth() {
		return naturalWidth;
	}
	public void setNaturalWidth(String naturalWidth) {
		this.naturalWidth = naturalWidth;
	}
	public String getNaturalHeight() {
		return naturalHeight;
	}
	public void setNaturalHeight(String naturalHeight) {
		this.naturalHeight = naturalHeight;
	}
	public String getCutWidth() {
		return cutWidth;
	}
	public void setCutWidth(String cutWidth) {
		this.cutWidth = cutWidth;
	}
	public String getCutHeight() {
		return cutHeight;
	}
	public void setCutHeight(String cutHeight) {
		this.cutHeight = cutHeight;
	}
	
	
	

}
