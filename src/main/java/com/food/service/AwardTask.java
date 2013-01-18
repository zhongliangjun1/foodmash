package com.food.service;

import java.util.Iterator;
import java.util.TimerTask;

import com.food.dao.AwardProductionDAO;
import com.food.dao.ProductionDAO;
import com.food.model.AwardProduction;
import com.food.model.Production;

/**
 * 计算获奖作品任务
 * @author liangjun.zhong
 * @version 创建时间：Jan 18, 2013 8:16:14 PM
 */
public class AwardTask extends TimerTask{
	
	private static final int ShowNumLimit = 200;  
	private static final int AwardNum = 3;
	
	private ProductionDAO productionDAO = new ProductionDAO();
	private AwardProductionDAO awardProductionDAO = new AwardProductionDAO();

	@Override
	public void run() {
		// TODO Auto-generated method stub	
		Iterator<Production> iterator = productionDAO.getFillProductionsIterator(ShowNumLimit);
		//List<ObjectId> list = productionDAO.getFillProductionsList(ShowNumLimit);
		int count = AwardNum;
		if(iterator!=null){
			while(iterator.hasNext()){
				Production production = iterator.next();
				if(count>0){ //if 仍有获奖机会
					AwardProduction awardProduction = new AwardProduction();
					awardProduction.setProductionId(production.getStringId());
					awardProduction.setAuthorId(production.getAuthorId()); //设备id,冗余字段
					awardProduction.setInform(false); //消息尚未被消费
					awardProductionDAO.addAwardProduction(awardProduction);
				}
				production.setHasTreat(true);
				productionDAO.updateProduction(production); //更新原作品为已处理（是否获奖）
			}
		}		
	}
	
	

}
