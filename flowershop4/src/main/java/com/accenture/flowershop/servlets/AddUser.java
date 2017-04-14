package com.accenture.flowershop.servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.jms.JMSException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.accenture.flowershop.business.UserListService;
import com.accenture.flowershop.messager.MessagerService;
import com.accenture.flowershop.model.entity.Gender;
import com.accenture.flowershop.model.entity.IndividualCustomer;
import com.accenture.flowershop.model.entity.User;
import com.accenture.flowershop.model.entity.UserAddress;
public class AddUser extends Dispatcher {

	@Autowired
	private UserListService userListService;
	@Autowired
	private MessagerService messagerService;
	
	private static final long serialVersionUID = 4054414597069364373L;
	private static final Logger LOG = 	LoggerFactory.getLogger(AddUser.class);
	
	public String getServletInfo(){
        return "Add user servlet";
    }   

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}	
	
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {    	   
        //ServletContext ctx = getServletContext();
        if (request.getParameter("save")!=null){
               String userName = request.getParameter("username");
               String surname = request.getParameter("surname");
               String birthdate = request.getParameter("birthdate");
               String userlogin = request.getParameter("userlogin");
               String password = request.getParameter("password");
               String phone = request.getParameter("phone");               
               String city = request.getParameter("city");
               String street = request.getParameter("street");
               String building = request.getParameter("building");
               String Sex = request.getParameter("Sex");
               UserAddress userAddress = new UserAddress(city,street,building);
               User newUser = new User(userName,userlogin,password,phone,0,userAddress);               
              /* try{
            	   newUser.setDiscount(Integer.parseInt(discount));            	   
               }catch(Exception e){            	   
            	   newUser.setDiscount(0);
               }*/
               
               Gender gender = Gender.Male;
               if (Sex.equals("Female")) {gender= Gender.Female;}
                
               IndividualCustomer iCustomer = new IndividualCustomer(userName,surname,Calendar.getInstance(),gender);
               boolean res = userListService.addUser(newUser);  
               if (res) {            	            	
            	   LOG.info(gender+"Customer with name =    "+newUser.getUserName()+"  password =   " + newUser.getPassword()+
            			"    was created "+newUser.getDiscount());
            	   userListService.addIndividualUser(iCustomer);
            	   try {
					messagerService.SendEntityUserMessage(newUser);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	   request.setAttribute("newUser", newUser);
            	   this.forward("/successRegistration.jsp", request, response);
               } else {
            	   this.forward("/errorRegistration.html", request, response);
               }
        } else if (request.getParameter("cancel")!=null){
               this.forward("/login.html", request, response);
    }

}

}
