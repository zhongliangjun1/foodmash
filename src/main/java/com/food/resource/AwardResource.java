package com.food.resource;

import java.util.Timer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.food.service.AwardTask;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 19, 2013 1:11:19 AM
 */
@Path("admin")
public class AwardResource {
	
	
	//http://localhost:8080/foodmash/rest/admin/start?token=zhong
	//刷新随机获取两个作品，并检验是否有消息
	@GET
	@Path("/start")
	@Produces(MediaType.APPLICATION_JSON)
	public String startTimer(@QueryParam("token") String token){
		if(token.equals("zhong")){
			Timer timer = new Timer();
			AwardTask task = new AwardTask();
			long delay = 60*60*1000; //延迟一小时
			//long delay = 10*1000;
			long period = 4*60*60*1000; //每隔四小时
			
			timer.schedule(task, delay, period);		
			
			return "SUCCESS";
		}else{
			return "FALSE";
		}
		
	}

}
