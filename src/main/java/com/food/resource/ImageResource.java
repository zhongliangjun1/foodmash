package com.food.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.food.model.People;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("image")
public class ImageResource {
	
	    //http://localhost:8080/foodmash/rest/image/pathparams/tt-123
		@GET
		@Path("/pathparams/{param1}-{param2}")
		@Produces("text/plain")
		public String getFromManyParams(@PathParam("param1") String msg1, @PathParam("param") int msg2){
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
				writeToFile(uploadedInputStream, uploadedFileLocation);						
				String output = "File uploaded to : " + uploadedFileLocation;
				
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
		private void writeToFile(InputStream uploadedInputStream,String uploadedFileLocation) {
		 
				try {
					OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
					int read = 0;
					byte[] bytes = new byte[1024];
		 
					//out = new FileOutputStream(new File(uploadedFileLocation));
					while ((read = uploadedInputStream.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}
					out.flush();
					out.close();
				} catch (IOException e) {
		 
					e.printStackTrace();
				}		 
		}
		
}
