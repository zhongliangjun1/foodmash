package com.food.test;

import java.util.List;

import org.bson.types.ObjectId;

import com.food.dao.ProductionDAO;
import com.food.model.Production;

/**
 * 类说明
 * @author liangjun.zhong
 * @version 创建时间：Jan 15, 2013 1:19:30 AM
 */
public class ProductionDAOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProductionDAO dao = new ProductionDAO();
		List<ObjectId> list = dao.getUsableProductionsList(10);
		if(list!=null){
			for(ObjectId p :  list){
				System.out.println(p.toString());
			}
		}
		Production p = dao.getProductionById("50f43f47a334f65cfad5b04c");
		if(p!=null){
			System.out.println(p.getImgSrc()+p.getLikeNum());
		}
	}

}
