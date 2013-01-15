package com.food.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.food.enums.CodeStatus;
import com.food.model.PKentity;
import com.food.model.Production;
import com.food.service.JudgeService;
import com.food.service.ProductionService;



/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 15, 2013 12:17:44 AM
 */
@Path("foodmash")
public class ProductionResource {
	
	private ProductionService productionService = new ProductionService();
	private JudgeService judgeService = new JudgeService();
	
	//http://localhost:8080/foodmash/rest/foodmash/entry?deviceId=123a4op231
	@GET
	@Path("/entry")
	@Produces(MediaType.APPLICATION_JSON)
	public PKentity entryMain(@PathParam("deviceId") String deviceId){
		PKentity entity = new PKentity();
		List<Production> list = productionService.getRandomTwoProduction();
		if(list!=null && list.size()>1){
			entity.setFirstProduction(list.get(0));
			entity.setSecondProduction(list.get(1));
			entity.setHasMessage(judgeService.hasMessage(deviceId));
			entity.setCodeStatus(CodeStatus.Success.value);
		}else{
			entity.setCodeStatus(CodeStatus.ServerError.value);
		}
		
		return entity;
	}
	
	
	

}
