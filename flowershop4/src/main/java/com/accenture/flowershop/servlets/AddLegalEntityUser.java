package com.accenture.flowershop.servlets;

import java.io.IOException;

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
import com.accenture.flowershop.model.entity.LegalEntityCustomer;
import com.accenture.flowershop.model.entity.User;
import com.accenture.flowershop.model.entity.UserAddress;
public class AddLegalEntityUser extends Dispatcher {

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
            String userlogin = request.getParameter("userlogin");
            String customerInn = request.getParameter("inn");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");              
            String city = request.getParameter("city");
            String street = request.getParameter("street");
            String building = request.getParameter("building");               
            UserAddress userAddress = new UserAddress(city,street,building);
            User newUser = new User(userName,userlogin,password,phone,0,userAddress);
            LegalEntityCustomer legalEntityCustomer = new LegalEntityCustomer(userName,customerInn);
            /*try{
               newUser.setDiscount(Integer.parseInt(discount));            	   
            }catch(Exception e){            	   
               newUser.setDiscount(0);
            }  */            
            boolean res = userListService.addUser(newUser);  
            if (res) {   
               userListService.addLegalEntityCustomer(legalEntityCustomer);
               try {
				messagerService.SendEntityUserMessage(newUser);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               LOG.info("Customer with name =    "+newUser.getUserName()+"  password =   " + newUser.getPassword()+
            	"    was created "+newUser.getDiscount());
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