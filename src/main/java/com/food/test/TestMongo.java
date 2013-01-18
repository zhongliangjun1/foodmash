package com.food.test;

import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.food.dao.AwardProductionDAO;
import com.food.dao.ProductionDAO;
import com.food.model.Friend;
import com.food.model.Production;
import com.food.mongo.JongoClient;

public class TestMongo {
    
	private static Jongo jongo = JongoClient.getInstance();
	private static ProductionDAO productionDAO = new ProductionDAO();
	
	/*find*/
	public static void tryFind(int age){
		Iterable<Friend> friendsIterable = jongo.getCollection("friends").find("{age:"+age+"}").as(Friend.class);
		Iterator<Friend> friends = friendsIterable.iterator();
		if(friends != null){
			while(friends.hasNext()){
				Friend friend = friends.next(); 
				System.out.println(friend.getName()+":"+friend.getAge());
			}
		}else{
			System.out.println("without:"+age);
		}
	}
	
	public static void tryFindAward(){
		List<ObjectId> list = productionDAO.getFillProductionsList(1);
		if(list!=null){
			for(ObjectId objectId:list){
				System.out.println(objectId.toString());
			}
			System.out.println("test");
		}else{
			System.out.println("null");
		}
	}
	
	public static void tryFindAwardIterator(){
		Iterator<Production> iterator = productionDAO.getFillProductionsIterator(1);
		if(iterator!=null){
			while(iterator.hasNext()){
				Production p = (Production) iterator.next();
				System.out.println(p.getStringId()+"|"+p.getLikeNum());
			}
		}else{
			System.out.println("null");
		}
	}
	
	
	/*error org.jongo.MongoIterator cannot be cast to java.util.List*/
	public static void tryFindList(){
		List<Friend> list = (List<Friend>) jongo.getCollection("friends").find("{age:23}").as(Friend.class);
		System.out.println(list.size());
	}
	
	/*save*/
	public static void trySave(MongoCollection friends){		
		Friend friend = new Friend();
		friend.setName("yang");
		friend.setAge(23);
		friends.save(friend);
	}
	
	/*update*/
	public static void tryUpdate(MongoCollection friends){
		System.out.println("--------test update-----------------");
		tryFind(24);
		friends.update("{name:'yang',age:23}").with("{$inc: {age: 1}}");
		tryFind(24);
		System.out.println("--------test update one & multi-----------------");
		tryFind(24);
		friends.update("{age:23}").with("{$inc: {age: 1}}");
		tryFind(24);
		friends.update("{age:24}").multi().with("{$inc:{age:-1}}");
		tryFind(23);
		
		//friends.update("{name: 'Joe'}").upsert().multi().with("{$inc: {age: 1}}");	
		friends.update("{name: 'Joe'}").with("{name:'newJoe',age:20,step:1}");
	}
	
	public static void checkNewAwardProduction(){
		AwardProductionDAO dao = new AwardProductionDAO();
		List<ObjectId> list = dao.checkNewAwardProduction("deviceId3");
		if(list!=null && list.size()>0){
			System.out.println("new inform");
		}else{
			System.out.println("no new ");
		}
	}
	
	
	
	public static void main(String[] args) {
		MongoCollection friends = jongo.getCollection("friends");
		//trySave(friends);
		//tryFind(23);
		//tryUpdate(friends);
		//tryFindList();
		//tryFindAward();
		tryFindAwardIterator();
		checkNewAwardProduction();
		
	}

}
