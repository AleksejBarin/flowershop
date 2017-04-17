<%@page import="com.accenture.flowershop.model.entity.Flower"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flower Buy</title>
</head>
<body>
  <form action="Registration" method="post">

<%
Flower flower = (Flower)request.getAttribute("flower");
request.setAttribute("flower", flower);

%>

 <h1>Input number of <% out.println(flower.getLocalName()); %> </h1>

<br>Number of flowers: <input type="text" name="NumberOfFlowers" size="20"><br>

<input type="submit" name="I Want To Buy" value="I Want To Buy">
<!-- 
<a href="OrderFin.jsp">Cancel</a>
 -->



</form>
</body>
</html>
