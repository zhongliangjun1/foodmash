package com.food.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import com.food.model.Image;
import com.food.model.People;
import com.food.service.UploadPicService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("image")
public class ImageResource {
	
	    //http://localhost:8080/foodmash/rest/image/pathparams/tt-123
		@GET
		@Path("/pathparams/{param1}-{param2}")
		@Produces("text/plain")
		public String getFromManyParams(@PathParam("param1") String msg1, @PathParam("param2") int msg2){
		//public Response getFromManyParams(@PathParam("param1") String msg1, @PathParam("param") int msg2){
			String result = "pathparam1:"+msg1+" pathparam2:"+msg2;
			return result;	
		}
		
		//http://localhost:8080/foodmash/rest/image/json/zhong-23
		@GET
		@Path("/json/{param1}-{param2}")
		@Produces(MediaType.APPLICATION_JSON)
		public People jsonManyParams(@PathParam("param1") String msg1, @PathParam("param2") int msg2){
		    People people = new People();
		    people.setId(0);
		    people.setName(msg1);
		    people.setAge(msg2);
			return people;	
		}
		
		@POST
		@Path("/jsonpost")
		@Produces(MediaType.APPLICATION_JSON)
		public People jsonManyParamsoost(@FormParam("param1") String msg1, @FormParam("param2") int msg2){
		    People people = new People();
		    people.setId(0);
		    people.setName(msg1);
		    people.setAge(msg2);
			return people;	
		}
		
		//http://localhost:8080/foodmash/index.jsp
		@POST
		@Path("/fileWithformparams")	
		@Consumes(MediaType.MULTIPART_FORM_DATA) 	
		@Produces(MediaType.APPLICATION_JSON)
		public People fileWithFormParams(@FormDataParam("name") String msg1, @FormDataParam("age") int msg2,
				@FormDataParam("imgfile") InputStream uploadedInputStream,
				@FormDataParam("imgfile") FormDataContentDisposition fileDetail  ){
				
			    People people = new People();
			    people.setId(0);
			    people.setName(msg1);
			    people.setAge(msg2);
			    
			    
			    String uploadedFileLocation = "/Users/mac/Downloads/eatIMAGE/" + fileDetail.getFileName();	    
				File file = writeToFile(uploadedInputStream, uploadedFileLocation);	
				if(file!=null){
					String output = "File uploaded to : " + uploadedFileLocation;
					UploadPicService s = new UploadPicService();
					try {
						Image image = s.upload(file, "test");
						System.out.println("get it:"+image.getTempSrc());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				return people;	
				//return Response.status(200).entity(output).build();			
		}
		
		//http://localhost:8080/foodmash/rest/image/start
		@GET
		@Path("/start")
		@Produces(MediaType.APPLICATION_JSON)
		public String strat(@QueryParam("token") String msg1){
		//public Response getFromManyParams(@PathParam("param1") String msg1, @PathParam("param") int msg2){
			//String result = "pathparam1:"+msg1+" pathparam2:"+msg2;
			String test = msg1;
			System.out.println(test);
			return msg1;
		}
		
		
		// save uploaded file to new location
		 		private File writeToFile(InputStream uploadedInputStream,String uploadedFileLocation) {		
		 			    File file = null;
		 				try {
		 					file = new File(uploadedFileLocation);
		 					OutputStream out = new FileOutputStream(file);
		 					int read = 0;
		 					byte[] bytes = new byte[1024];	 
		 					while ((read = uploadedInputStream.read(bytes)) != -1) {
		 						out.write(bytes, 0, read);
		 					}
		 					out.flush();
		 					out.close();
		 				} catch (IOException e) {	 
		 					e.printStackTrace();
		 				}
		 				return file;
		 		}
		
}
