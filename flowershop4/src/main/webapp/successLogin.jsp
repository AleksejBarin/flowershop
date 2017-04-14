<%@page import="com.accenture.flowershop.model.entity.User"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <title>Page success</title>

    </head>

    <body>
    
    <form action=AddLegalEntityUser method="post">
   

    <br>

    <h1>Successful</h1>
 
<%
	User newUser = (User)request.getAttribute("newUser");

	if(newUser.equals(null)){
		out.println("TTTTTTTT");
		
	}else{
		out.println("TTTTTTTT2");
	}
%>
   User: <%= out.println(newUser.getUserLogin())%><br>

   Discount: <%= newUser.getDiscount()%><br>

   Password: <%= newUser.getPassword()%><br>

   Phone: <%= newUser.getPhone()%><br>
   
   </form>

    </body>

</html> 
