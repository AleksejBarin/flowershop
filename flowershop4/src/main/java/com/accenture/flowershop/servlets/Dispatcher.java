package com.accenture.flowershop.servlets;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;

import java.io.IOException;

import javax.servlet.RequestDispatcher;

import javax.servlet.http.*;
@Component
   public class Dispatcher extends HttpServlet  { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -3304454848870544677L;

	protected void forward(String address, HttpServletRequest request, HttpServletResponse response)

    throws ServletException, IOException{

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);

        dispatcher.forward(request, response);

    }

   }
