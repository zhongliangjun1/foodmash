package com.food.dao;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.food.model.LikeRecord;
import com.food.mongo.JongoClient;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 16, 2013 12:49:18 AM
 */
public class LikeRecordDAO {
	
	private static Jongo jongo = JongoClient.getInstance();
	
	/**
	 * 新增对某作品的like记录
	 * @param likeRecord ObjectId为空
	 * @return
	 */
	public boolean addLikeRecord(LikeRecord likeRecord){
		MongoCollection likeRecords = jongo.getCollection("likeRecords");
		if(likeRecords != null){
			likeRecords.save(likeRecord);
		   return true;
		}else{
		   return false;
		}
	}

}
