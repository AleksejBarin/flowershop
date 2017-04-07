package com.accenture.flowershop.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.accenture.flowershop.business.FlowerService;
import com.accenture.flowershop.business.UserListService;
  
public class CheckUser extends Dispatcher {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6296014262010135169L;
	private static final Logger LOG = 	LoggerFactory.getLogger(CheckUser.class);
	
	@Autowired
	private UserListService userListService;	
	@Autowired
	private FlowerService flowerService;
	
	
	public String getServletInfo(){
        return "CheckUser servlet";
    }    

    public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	//User user = UserList.findUser(request.getParameter("user"));
/*        User user = userListService.findUser(request.getParameter("user"));
        if (user == null){
            this.forward("/registration.html", request, response);
         } else {
            if(!user.getPassword().equals(request.getParameter("password"))){ 
                this.forward("/registration.html", request, response);
            } else {                	
                	//@SuppressWarnings("resource")
					//ApplicationContext context =
              	    //	  new ClassPathXmlApplicationContext(new String[] {"addflower.xml"});
                	//    flList.addFlower((Flower)context.getBean("fl2"));
                	//Flower fl1 = (Flower)context.getBean("fl1");                	
                   	HttpSession session = request.getSession();
                   	session.setAttribute("user",user.getUser());
                   	session.setMaxInactiveInterval(30*60);
                   	Cookie loginCookie = new Cookie(user.getUser(),"user");
                   	loginCookie.setMaxAge(3*60);
                   	response.addCookie(loginCookie);               	 
                	this.forward("/OrderFin.jsp", request, response); 
                }
         }*/
    }

    

   }
