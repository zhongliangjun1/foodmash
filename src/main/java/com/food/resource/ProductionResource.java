package com.food.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

import com.food.dao.AwardProductionDAO;
import com.food.dao.ProductionDAO;
import com.food.enums.CodeStatus;
import com.food.model.AwardProduction;
import com.food.model.CodeEntity;
import com.food.model.Image;
import com.food.model.PKentity;
import com.food.model.People;
import com.food.model.Production;
import com.food.service.JudgeService;
import com.food.service.ProductionService;
import com.food.service.UploadPicService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 15, 2013 12:17:44 AM
 */
@Path("foodmash")
public class ProductionResource {
	
	private ProductionService productionService = new ProductionService();
	private UploadPicService uploadPicService = new UploadPicService();
	private JudgeService judgeService = new JudgeService();
	
	private AwardProductionDAO awardProductionDAO = new AwardProductionDAO();
	private ProductionDAO productionDAO = new ProductionDAO();
	
	//http://localhost:8080/foodmash/rest/foodmash/refresh?deviceId=123a4op231
	//刷新随机获取两个作品，并检验是否有消息
	@GET
	@Path("/refresh")
	@Produces(MediaType.APPLICATION_JSON)
	public PKentity refresh(@QueryParam("deviceId") String deviceId){
		PKentity entity = new PKentity();
		if(deviceId!=null){
			List<Production> list = productionService.getRandomTwoProduction();
			if(list!=null && list.size()>1){
				entity.setFirstProduction(list.get(0));
				entity.setSecondProduction(list.get(1));
				entity.setHasMessage(judgeService.hasMessage(deviceId));
				entity.setCodeStatus(CodeStatus.Success.value);
			}else{
				entity.setCodeStatus(CodeStatus.ServerError.value);
			}
		}else{
			entity.setCodeStatus(CodeStatus.ClientError.value);
		}		
		
		return entity;
	}
	
	//http://localhost:8080/foodmash/rest/foodmash/like?likeId=50f43f47a334f65cfad5b04c&dislikeId=50f43f47a334f65cfad5b04d&deviceId=123a4op231
    //处理该两件作品评价，再获取两个作品，并检验是否有消息
	@GET
	@Path("/like")
	@Produces(MediaType.APPLICATION_JSON)
	public PKentity like(@QueryParam("likeId") String likeId,@QueryParam("dislikeId") String dislikeId,
			@QueryParam("deviceId") String deviceId){
		PKentity entity = new PKentity();
		if(likeId!=null && dislikeId!=null && deviceId!=null){
			boolean treatLike = productionService.treateLike(likeId, deviceId);
			boolean treatDislike = productionService.treatDislike(dislikeId);
			if(treatLike==true && treatDislike==true){
				entity = refresh(deviceId); //刷新随机获取两个作品，并检验是否有消息
			}else{
				entity.setCodeStatus(CodeStatus.ServerError.value);
			}
		}else{
			entity.setCodeStatus(CodeStatus.ClientError.value);
		}
		
		return entity;
	}
	
	//http://localhost:8080/foodmash/rest/foodmash/upload
	//deviceId  review  imgfile
	//上传一件作品，并检验是否有消息
	@POST
	@Path("/upload")	
	@Consumes(MediaType.MULTIPART_FORM_DATA) 	
	@Produces(MediaType.APPLICATION_JSON)
	public CodeEntity upload(@FormDataParam("deviceId") String deviceId, @FormDataParam("review") String review,
			@FormDataParam("imgfile") InputStream inputStream,
			@FormDataParam("imgfile") FormDataContentDisposition fileDetail  ){
		    CodeEntity entity = new CodeEntity();
		    if(deviceId!=null && review!=null && inputStream!=null && fileDetail!=null){
		    	File file = uploadPicService.convertInputstreamToLocationFile(inputStream, fileDetail);
		    	if(file!=null){
		    		try {
		    			Image image = uploadPicService.upload(file, fileDetail.getFileName()); 
		    			if(image!=null && image.getCode().equals("200")){ //成功完成图片上传
		    				boolean result = productionService.addNewProduction(deviceId, review, image);
		    				if(result){ //添加作品记录成功
		    					entity.setHasMessage(judgeService.hasMessage(deviceId));
			    				entity.setCodeStatus(CodeStatus.Success.value);
			    				return entity; //成功会提前返回 
		    				}	
		    			}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	}
		    	entity.setCodeStatus(CodeStatus.ServerError.value);
		    }else{
		    	entity.setCodeStatus(CodeStatus.ClientError.value);
		    }
		   
			return entity;				
	}
	
	//http://localhost:8080/foodmash/rest/foodmash/albumIds
	//获取精选集 id List 为作品的id List
	@GET
	@Path("/albumIds")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAwardProductionIds(){
	    List<String> list = null;
		List<ObjectId> objectIds = awardProductionDAO.getAwardProductions();
		int num = 0;
		if(objectIds!=null){
			Stack<String> stack = new Stack<String>();
			for(ObjectId objectId:objectIds){
				AwardProduction p = awardProductionDAO.getAwardProductionById(objectId);
				if(p!=null){
					stack.push(p.getProductionId()); //原作品的id入栈
					num = num+1;
				}
				//stack.push(objectId.toString());			
			}
			list = new LinkedList<String>();
			while(num>0){
				list.add(stack.pop()); //作品的id List
				num = num-1;
			}
		}		
		return list;
	}
	
	//http://localhost:8080/foodmash/rest/foodmash/production?productionId=50f98cd830042e2a542b1400
	//根据作品id获取作品详细内容
	@GET
	@Path("/production")
	@Produces(MediaType.APPLICATION_JSON)
	public Production getProductionById(@QueryParam("productionId") String productionId){
		return productionDAO.getProductionById(productionId);
	}
	
	
	
	
	
	

}
