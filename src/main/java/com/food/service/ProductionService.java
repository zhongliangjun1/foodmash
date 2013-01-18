package com.food.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.PathParam;

import org.bson.types.ObjectId;

import com.food.dao.LikeRecordDAO;
import com.food.dao.ProductionDAO;
import com.food.model.Image;
import com.food.model.LikeRecord;
import com.food.model.Production;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 15, 2013 3:05:42 PM
 */
public class ProductionService {
	
	private ProductionDAO productionDAO = new ProductionDAO();
	private LikeRecordDAO likeRecordDAO = new LikeRecordDAO();
	private static final int ShowNumLimit = 200;
	private final Random random = new Random();
	
	/**
	 * 随机获取两件作品
	 * @return  无法获取list，则其size<2
	 */
	public List<Production> getRandomTwoProduction(){
		List<Production> list = new ArrayList<Production>();
		List<ObjectId> objectIds = productionDAO.getUsableProductionsList(ShowNumLimit);
		int num = objectIds.size();
		if(num>1){
			int firstLocation = random.nextInt(num);
			int secondLocation = getdifferenceLocation(firstLocation,num);
			if(secondLocation != -1){
				ObjectId firstObjectId = objectIds.get(firstLocation);
				ObjectId secondObjectId = objectIds.get(secondLocation);
				Production first = productionDAO.getProductionById(firstObjectId);
				Production second = productionDAO.getProductionById(secondObjectId);
				list.add(first);
				list.add(second);
			}			
		}		
		return list;
	}
	
	/**
	 * 获得下一个不同于当前的位置
	 * @param firstLocation
	 * @param num
	 * @return  －1表示无法返回下一个有效位置
	 */
	public int getdifferenceLocation(int firstLocation,int num){
		int location = -1; //如果没有可返回的，则为－1
		if(num>1){
			int temporary = firstLocation;
			while(temporary == firstLocation){
				temporary = random.nextInt(num);
			}
			location = temporary;
		}
		return location;
	}
	
	/**
	 * 处理被like作品
	 * @param likeId
	 * @param deviceId
	 * @return
	 */
	public boolean treateLike(String likeId, String deviceId){
		boolean result = false;
		if(likeId != null){
			Production p = productionDAO.getProductionById(likeId);
			if(p != null){
				p.setShowNum(p.getShowNum()+1);
				p.setLikeNum(p.getLikeNum()+1);
				boolean r = productionDAO.updateProduction(p); //增加作品like数&展示次数
				if(r == true){
					LikeRecord likeRecord = new LikeRecord();
					likeRecord.setProductionId(likeId);
					likeRecord.setLikerId(deviceId);
					likeRecordDAO.addLikeRecord(likeRecord); //增加作品like记录
					result = true;
				}
			}
		}		
		return result;
	}
	
	/**
	 * 处理dislike的作品
	 * @param dislikeId
	 * @return
	 */
	public boolean treatDislike(String dislikeId){
		boolean result = false;
		if(dislikeId != null){
			Production p = productionDAO.getProductionById(dislikeId);
			if(p != null){
				p.setShowNum(p.getShowNum()+1);
				result = productionDAO.updateProduction(p); //增加作品展示次数				
			}
		}		
		return result;
	}
	
	/**
	 * 添加作品记录
	 * @param deviceId
	 * @param review
	 * @param image
	 * @return
	 */
	public boolean addNewProduction(String deviceId, String review, Image image){
		boolean result = false;
		if(deviceId!=null && review!=null && image!=null){
			Production production = new Production();
			production.setAuthorId(deviceId);
			production.setReview(review);
			production.setImgSrc(image.getTempSrc());
			production.setLikeNum(0);
			production.setShowNum(0);
			production.setHasTreat(false);
			result = productionDAO.addProduction(production);
		}		
		return result;
	}
	
	
	
	
	

}
