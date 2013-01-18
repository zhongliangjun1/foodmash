package com.food.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.food.model.AwardProduction;
import com.food.model.Production;
import com.food.mongo.JongoClient;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 18, 2013 11:23:11 PM
 */
public class AwardProductionDAO {
	
	private static Jongo jongo = JongoClient.getInstance();
	
	/**
	 * 记录获奖作品
	 * @param awardProduction
	 * @return
	 */
	public boolean addAwardProduction(AwardProduction awardProduction){
		MongoCollection awardProductions = jongo.getCollection("awardProductions");
		if(awardProduction != null){
		   awardProductions.save(awardProduction);
		   return true;
		}else{
		   return false;
		}
	}
	
	/**
	 * 检查设备是否有新的获奖通知
	 * @param deviceId
	 * @return
	 */
	public List<ObjectId> checkNewAwardProduction(String deviceId){
		//limit = limit+1; //less than limit
		List<ObjectId> list = null;
		MongoCollection awardProductions = jongo.getCollection("awardProductions");
		if(awardProductions != null){
			list = awardProductions.distinct("_id").query("{ productionId: "+deviceId+" , isInform:false } ").as(ObjectId.class);
		}	
		return list;
	}

}
