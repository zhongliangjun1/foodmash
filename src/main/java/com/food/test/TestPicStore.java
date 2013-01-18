package com.food.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dianping.piccentercloud.storage.api.HttpUploadAPI;
import com.dianping.piccentercloud.storage.api.Token;

public class TestPicStore {

	public void upload() throws FileNotFoundException, IOException{
		
		//构建HttpUploadAPI对象，设置请求的url+请求超时时间+读取的超时时间
		HttpUploadAPI uploadApi = new HttpUploadAPI("test", "test");
		uploadApi.setRequestURL("http://192.168.8.92:8500/upload/cloud/api/test");
		uploadApi.setConnectTimeout(10000); //http连接超时时间
		uploadApi.setReadTimeout(10000);      //http读取的超时时间

		//构建token
		Token token = new Token();
		token.setBiz("test");
		token.setAccount("test");
		token.setExpiredTime(System.currentTimeMillis() / 1000 + 30 * 60);
//		token.setOwnerName("liangjun.zhong");
//		token.setCallBackUrl("callbackUrl");
//		token.setClientType(1);

		//http请求头信息
		Map<String,String> header = new HashMap<String,String>();
		header.put("ClientIPByProxy", "127.0.0.1");
		//注意:由于公司应用都走反向代理，为了获取用户ip请加入上述代码

		//发送请求，获取返回数据
		Map<String,String> map = (Map<String,String>)uploadApi.execute(token, 
				new File("/Users/mac/Downloads/eatIMAGE/original_50at_54c30000051d125d.jpg"), "test", header);

		//成功的返回字段：code+url+width+height
		System.out.println(map.get("code"));
		System.out.println(map.get("url"));
		System.out.println(map.get("width"));
		System.out.println(map.get("height"));
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		TestPicStore test = new TestPicStore();
        test.upload();
	}

}
