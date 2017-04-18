<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="com.accenture.flowershop.model.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Reg page</title>

    </head>

    <body>

    <h1>Registration complete</h1>

   
   
<%
User user = (User)request.getAttribute("newUser");
%>

   User: <%= user.getUserLogin()%><br>

   Phone: <%= user.getPhone()%><br>

   Password:    <%= user.getPassword()%><br>        
 
   Discount: <%= user.getDiscount()%><br>
 
   Registered.<br><br>

   <a href="login.html">Go</a>


    </body>

</html>
