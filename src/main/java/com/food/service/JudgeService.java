package com.food.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.food.dao.AwardProductionDAO;

/**
 * 
 * @author liangjun.zhong
 */
public class JudgeService {
	
	private AwardProductionDAO awardProductionDAO = new AwardProductionDAO();
	
	/**
	 * 判断该设备是否有消息通知
	 * @param deviceId
	 * @return
	 */
	public boolean hasMessage(String deviceId){
		boolean result = false;
		List<ObjectId> list = awardProductionDAO.checkNewAwardProduction(deviceId);
		if(list!=null && list.size()>0){
			result = true;
		}
		return result;
	}
	
	
	

}
