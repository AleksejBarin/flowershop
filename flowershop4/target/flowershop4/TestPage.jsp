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

<br>
Input: <input type="text" name="localName or scientName" size="20"><br>



         <small>

              <input type="submit" name="sortmyflower" value="Marshaller">

              </small>  
          <small>

              <input type="submit" name="sortmyflowerdesc" value="JSM">

              </small>    
              
                                     
          <small>

              <input type="submit" name="showcookie" value="ShowCookie">

              </small> <br>

          <small>

              <input type="submit" name="findinn" value="findinn">

              </small> 
              
          <small>

              <input type="submit" name="findsurname" value="findsurname">

              </small> 
              
          <small>

              <input type="submit" name="findname" value="findlogin">

              </small>    
              
          <small>

              <input type="submit" name="findinn2" value="findinn2">

              </small>    

</form>



</body>
</html>