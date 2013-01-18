package com.food.test;

import com.google.gson.Gson;
/**
 * GetObjectFromJSON
 * @author liangjun.zhong
 */
public class TestGson {

	public void getObjectFromJSON(){
		
		String jsonData = " {\"firstProduction\":{\"showNum\":8,\"likeNum\":4,\"authorId\":\"2\",\"imgSrc\":\"/pc/test/2.jpg\",\"review\":\"test\",\"addTime\":null,\"id\":\"50f43f47a334f65cfad5b04d\",\"stringId\":\"50f43f47a334f65cfad5b04d\"},\"secondProduction\":{\"showNum\":5,\"likeNum\":6,\"authorId\":\"3\",\"imgSrc\":\"/pc/test/3.jpg\",\"review\":\"test id\",\"addTime\":null,\"id\":\"50f594810b8c2ece73c8a258\",\"stringId\":\"50f594810b8c2ece73c8a258\"},\"hasMessage\":false,\"codeStatus\":200} ";
		
		Gson gson = new Gson();
		PKentity entity = gson.fromJson(jsonData, PKentity.class);
		if(entity!=null){
			Production first = entity.getFirstProduction();
			Production second = entity.getSecondProduction();
			if(first!=null && second!=null){
				System.out.println(first.getId()+" | "+first.getImgSrc());
				System.out.println(second.getId()+" | "+second.getImgSrc());
			}else{
				System.out.println("null");
			}
		}
	    
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestGson test = new TestGson();
		test.getObjectFromJSON();
	}

}
