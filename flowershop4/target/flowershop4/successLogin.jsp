<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Page success</title>

    </head>

    <body>

    <br>

    <h1>Successful</h1>

   <jsp:useBean id="user" class="com.accenture.flowershop.model.entity.User" scope="application"/>

   User: <%= user.getUserName()%><br>

   Email: <%= user.getDiscount()%><br>

   Password: <%= user.getPassword()%><br>

   Phone: <%= user.getPhone()%><br>

    </body>

</html> 
