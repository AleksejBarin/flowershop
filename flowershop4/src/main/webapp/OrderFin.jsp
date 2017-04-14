<%@page import="com.accenture.flowershop.model.entity.Flower"%>
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
List<Flower> flList = (List<Flower>)request.getAttribute("flList");
%>
<table border=1><caption>FlowersGrid</caption>
<tr><th>LocalName</th><th>ScientName</th><th>Count</th><th>ButtonBUY</th></tr> 
<%  
//response.
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
<br>
NameFlower: <input type="text" name="localName or scientName" size="20"><br>

          <small>

              <input type="submit" name="findflower" value="Find Flower">

          </small>

          <small>

              <input type="submit" name="goout" value="Logout">

              </small>
              
            <small>

              <input type="submit" name="test" value="Test">

              </small>            
          
	</form>
</body>
</html>