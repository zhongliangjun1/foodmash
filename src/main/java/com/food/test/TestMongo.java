package com.food.test;

import java.util.Iterator;

import org.jongo.Jongo;

import com.food.model.Friend;
import com.food.mongo.JongoClient;

public class TestMongo {
    
	private static Jongo jongo = JongoClient.getInstance();
	
	public static void main(String[] args) {
		
		Iterable<Friend> friendsIterable = jongo.getCollection("friends").find().as(Friend.class);
		Iterator<Friend> friends = friendsIterable.iterator();
		if(friends != null){
			while(friends.hasNext()){
				Friend friend = friends.next(); 
				System.out.println(friend.getName());
			}
		}
		
		
       
	}

}
