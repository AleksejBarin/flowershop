package com.accenture.flowershop.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Component;
@Component
public class OrderFlower extends Dispatcher {

    public String getServletInfo(){

        return "OrderFlower servlet";

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)

    throws ServletException, IOException {

        ServletContext ctx = getServletContext();

          if (request.getParameter("findflower")!=null){

            this.forward("/FindFlower", request, response);

        } else if (request.getParameter("goout")!=null) {

            this.forward("/login.html", request, response);

        }else if (request.getParameter("ordermyflower")!=null) {

            this.forward("/login.html", request, response); 
        }

    }

}