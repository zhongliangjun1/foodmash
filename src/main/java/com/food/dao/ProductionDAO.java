package com.food.dao;

import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.jongo.Find;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.food.model.Production;
import com.food.mongo.JongoClient;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 15, 2013 12:19:36 AM
 */
public class ProductionDAO {
	
	private static Jongo jongo = JongoClient.getInstance();
	
	/**
	 * 获取所有符合展示条件限制的作品集迭代器
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Iterator getUsableProductionsIterator(int limit){
		//limit = limit+1; //less than limit
		Iterable<Production> productionsIterable = jongo.getCollection("productions").find("{ showNum: { $lt: "+limit+" } } ").as(Production.class);
		if(productionsIterable != null){
			return productionsIterable.iterator();
		}else{
			return null;
		}
	}
	
	/**
	 * 获取所有符合展示条件限制的作品集数目
	 * @param limit
	 * @return
	 */
	public long getUsableProductionsNum(int limit){
		//limit = limit+1; //less than limit
		long num = 0;
		MongoCollection productions = jongo.getCollection("productions");
		if(productions != null){
			num = productions.count("{ showNum: { $lt: "+limit+" } } ");
		}		
		return num;
	}
	
	/**
	 * 获取所有符合展示条件限制的作品集id List
	 * @param limit
	 * @return
	 */
	public List<ObjectId> getUsableProductionsList(int limit){
		//limit = limit+1; //less than limit
		List<ObjectId> list = null;
		MongoCollection productions = jongo.getCollection("productions");
		if(productions != null){
			list = productions.distinct("_id").query("{ showNum: { $lt: "+limit+" } } ").as(ObjectId.class);
		}	
		return list;
	}
	
	/**
	 * 获取所有达到展示次数，尚未被计算获奖的作品集id List
	 * @param limit
	 * @return
	 */
	public List<ObjectId> getFillProductionsList(int limit){
		limit = limit-1; //greater than limit
		List<ObjectId> list = null;
		MongoCollection productions = jongo.getCollection("productions");
		if(productions != null){
			list = productions.distinct("_id").query("{ showNum: { $gt: "+limit+" } , hasTreat:false} ").as(ObjectId.class);
		}	
		return list;
	}
	
	/**
	 * 获取所有达到展示次数，尚未被计算获奖的作品集 迭代器
	 * 按likeNum 降序
	 * @param limit
	 * @return
	 */
	public Iterator<Production> getFillProductionsIterator(int limit){
		limit = limit-1; //greater than limit
		Iterable<Production> productionsIterable = jongo.getCollection("productions").find("{ showNum: { $gt: "+limit+" }, hasTreat:false } ").sort("{likeNum: -1}").as(Production.class);
		if(productionsIterable != null){
			Iterator<Production> iterator = productionsIterable.iterator();
			return iterator;
		}else{
			return null;
		}
	}
	
	/**
	 * 根据作品的String id 获取作品
	 * @param id
	 * @return
	 */
	public Production getProductionById(String id){
		Production p = null;
		MongoCollection productions = jongo.getCollection("productions");
		if(productions != null){
			p = productions.findOne(new ObjectId(id)).as(Production.class);
		}
		return p;
	}
	
	/**
	 * 根据作品的ObjectId id 获取作品
	 * @param id
	 * @return
	 */
	public Production getProductionById(ObjectId id){
		Production p = null;
		MongoCollection productions = jongo.getCollection("productions");
		if(productions != null){
			p = productions.findOne(id).as(Production.class);
		}
		return p;
	}
	
	/**
	 * 更新原作品
	 * @param production 带有ObjectId
	 */
	public boolean  updateProduction(Production production){
		MongoCollection productions = jongo.getCollection("productions");
		if(productions != null){
		   productions.save(production);
		   return true;
		}else{
		   return false;
		}
	}
	
	/**
	 * 加入新作品
	 * @param production ObjectId为空
	 * @return
	 */
	public boolean addProduction(Production production){
		MongoCollection productions = jongo.getCollection("productions");
		if(productions != null){
		   productions.save(production);
		   return true;
		}else{
		   return false;
		}
	}
	
	

}
