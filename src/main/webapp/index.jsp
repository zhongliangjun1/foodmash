<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

</body>
</html>