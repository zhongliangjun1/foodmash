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
		List<ObjectId> list = null;
		MongoCollection awardProductions = jongo.getCollection("awardProductions");
		if(awardProductions != null){
			list = awardProductions.distinct("_id").query("{ authorId: \""+deviceId+"\" , isInform:false } ").as(ObjectId.class);
		}	
		return list;
	}
	
	/**
	 * 获取精选集合id List
	 * @return
	 */
	public List<ObjectId> getAwardProductions(){
		List<ObjectId> list = null;
		MongoCollection awardProductions = jongo.getCollection("awardProductions");
		if(awardProductions != null){
			list = awardProductions.distinct("_id").as(ObjectId.class);
		}
		return list;
	}
	
	/**
	 * 根据获奖记录的id，获取获奖记录
	 * @param id
	 * @return
	 */
	public AwardProduction getAwardProductionById(String id){
		AwardProduction p = null;
		MongoCollection awardProductions = jongo.getCollection("awardProductions");
		if(awardProductions != null){
			p = awardProductions.findOne(new ObjectId(id)).as(AwardProduction.class);
		}
		return p;
	}
	
	/**
	 * 根据记录的ObjectId id 获取记录
	 * @param id
	 * @return
	 */
	public AwardProduction getAwardProductionById(ObjectId id){
		AwardProduction p = null;
		MongoCollection awardProductions = jongo.getCollection("awardProductions");
		if(awardProductions != null){
			p = awardProductions.findOne(id).as(AwardProduction.class);
		}
		return p;
	}

}
