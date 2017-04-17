<%@page import="com.accenture.flowershop.model.entity.Flower"%>
<%@page import="com.accenture.flowershop.model.entity.User"%>
<%@page import="com.accenture.flowershop.model.entity.UserShopCart"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <form action="Registration" method="post">


<%
List<UserShopCart> usclList = null;
List<Flower> flList = (List<Flower>)request.getAttribute("flList");
usclList = (List<UserShopCart>)request.getAttribute("usclList");
%>
<div style="float:left;">
<table border=1><caption>FlowersGrid</caption>
<tr><th>LocalName</th><th>ScientName</th><th>Count</th><th>ButtonBUY</th></tr> 
<%  
if ((flList!=null)&&(!flList.isEmpty())){
	for(Flower fl : flList){%>
		<tr><td><%out.println(fl.getLocalName());%></td>
		<td><% out.println(fl.getScientName());%> </td>
		<td><% out.println(Integer.toString(fl.getFlowerCount()));%> </td>
		<td> <input type="submit" name=<% out.println(fl.getLocalName());%> value=<% out.println(fl.getLocalName());%>></td></tr><%	
	}	
}
%>
</table>
NameFlower: <input type="text" name="localName or scientName" size="20"><br>

	<small>
	<input type="submit" name="findflower" value="Find Flower">
	</small>
</div>


<div style="float:right;">
<table border=1 ><caption>Batch</caption>
<tr><th>LocalName</th><th>Count</th><th>Price</th><th>Sum</th></tr> 
<%  
double totalSum = 0;
if ((usclList!=null)&&(!usclList.isEmpty())){
	for(UserShopCart usc : usclList){%>
		<tr><td><%out.println(usc.getFlowerName());%></td>
		<td><% out.println(usc.getCount());%> </td>
		<td><% out.println("10");%> </td>
		<td><% out.println(usc.getCount()*10);%> </td>
		<td> <input type="submit" name=<% out.println(usc.getFlowerName() + "reset");%> value=<% out.println("reset");%>></td>
		</tr><%	
		totalSum = totalSum + usc.getCount()*10;
	}	
}
%>
</table>

<%	User user = (User)request.getAttribute("user"); %>
  Total sum:    <%= totalSum%><br> 
  Your deposite:    <%= user.getDeposite()%><br> 
  
	<small>
	<input type="submit" name="buy" value="BuyAll">
	</small>    
	
</div>
<br>


          <small>

              <input type="submit" name="goout" value="Logout">

              </small>
              
            <small>

              <input type="submit" name="test" value="Test">

              </small>            
        
	</form>
</body>
</html>