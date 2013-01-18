<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		getBasic(); 		     
	});
	
	function getBasic(){
		$.post("rest/image/jsonpost",
		    {
				param1:"哈哈",
				param2:1
			},
			function(data,status){
					  /* var obj=eval("("+data+")");
					  var people=obj["people"]; 
				      alert("Data: " + people.name + "\nStatus: " + status); */
			});     
	}
	
</script>
<title>Insert title here</title>
</head>
<body>

    <FORM action="rest/image/fileWithformparams" method="post" enctype="multipart/form-data" >  
	    <P>  
	        name: <INPUT type="text" name="name"><BR>  
	        step: <INPUT type="text" name="age"><BR>  
	        file: <INPUT type="file" name="imgfile"><BR>
	        <INPUT type="submit" value="Send">  
	    </P>  
	</FORM> 
	
	<FORM action="rest/foodmash/upload" method="post" enctype="multipart/form-data" >  
	    <p>add production</p>
	    <P>  
	        deviceId: <INPUT type="text" name="deviceId"><BR>  
	        review: <INPUT type="text" name="review"><BR>  
	        imgfile: <INPUT type="file" name="imgfile"><BR>
	        <INPUT type="submit" value="Send">  
	    </P>  
	</FORM> 
	
	

</body>
</html>