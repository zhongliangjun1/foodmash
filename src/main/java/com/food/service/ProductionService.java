package com.food.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.types.ObjectId;

import com.food.dao.ProductionDAO;
import com.food.model.Production;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 15, 2013 3:05:42 PM
 */
public class ProductionService {
	
	private ProductionDAO productionDAO = new ProductionDAO();
	private static final int ShowNumLimit = 200;
	private Random random = new Random();
	
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
	
	
	
	
	

}
