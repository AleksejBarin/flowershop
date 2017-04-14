package com.accenture.flowershop.servlets;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.jms.JMSException;
//import javax.jms.JMSException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.accenture.flowershop.business.CustomerIntegrationService;
import com.accenture.flowershop.business.FlowerService;
import com.accenture.flowershop.business.UserListService;
import com.accenture.flowershop.messager.MessagerService;
import com.accenture.flowershop.model.entity.Flower;
import com.accenture.flowershop.model.entity.IndividualCustomer;
import com.accenture.flowershop.model.entity.LegalEntityCustomer;
import com.accenture.flowershop.model.entity.User;
import com.accenture.flowershop.model.entity.UserAddress;
@Transactional
public class Registration extends Dispatcher {

	private static final long serialVersionUID = 1461715797965410712L;
	public String userLoginSession = "1";
	private Flower flowerreg = null;
	
	@Autowired
	private FlowerService flowerService;
	@Autowired
	private UserListService userListService;
	@Autowired
	private MessagerService messagerService;
	// ¬ставить метод в сервлет, чтобы в него можно было инжектить другие бины.
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}	   


	public String getServletInfo(){

        return "Registration servlet";

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)

    throws ServletException, IOException {
    	
   	 	//List<Flower> flList = flowerService.SortFlowersByLocalName();//
   	 	
        //request.setAttribute("flList", flList);        

        
        if (request.getParameter("login")!=null){
	   	 	List<Flower> flList = flowerService.SortFlowersByLocalName();//
	        request.setAttribute("flList", flList);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderFin.jsp");//
        	
        	
        	/*Flower newFlower = new Flower("rose","rosa",5);
        	flowerService.addFlower(newFlower);
        	newFlower = new Flower("myrtle","myrtus",7);
        	flowerService.addFlower(newFlower);
        	newFlower = new Flower("orchid","phalaenopsis",6);
        	flowerService.addFlower(newFlower);
        	newFlower = new Flower("lily","lilium",5);
        	flowerService.addFlower(newFlower);*/
        	//List<Flower> lfl = flowerService.SortFlowersByLocalName();
        	
        	
        	String UserPswd = request.getParameter("password");
        	String userLogin = request.getParameter("user");
        	if (userListService.loginUser(userLogin, UserPswd)){
           	 	HttpSession session = request.getSession();
           	 	session.setAttribute("user",userLogin);
           	 	session.setMaxInactiveInterval(30*60);
           	 	Cookie loginCookie = new Cookie(userLogin,"user");
           	 	loginCookie.setMaxAge(3*60);
           	 	response.addCookie(loginCookie);            	 	
           	 	
        		this.forward("/OrderFin.jsp", request, response); 
        	}else{
        		 this.forward("/registration.html", request, response);
        	}
        	 
        } else if (request.getParameter("registration")!=null) {

            this.forward("/legalOrIndividual.html", request, response);
            
        } else if (request.getParameter("IamIndividualCustomer")!=null) {

            this.forward("/registration.html", request, response);
            
        } else if (request.getParameter("IamLegalEntityCustomer")!=null) {

            this.forward("/registrationLegal.html", request, response);

        } else  if (request.getParameter("findflower")!=null){         	
        	Flower flower = flowerService.findFlowerByLocalName(request.getParameter("localName or scientName"));
        	if (flower == null){
        		flower = flowerService.findFlowerByScientName(request.getParameter("localName or scientName"));        		
        	}    
        	if (flower != null){
    	        response.setContentType("text/html");
    	        PrintWriter out = response.getWriter();        		
        		
                	String result = "<html><head><title>Insert title here</title></head><body>"
                			+ "<h1> Sort </h1>"
                			+ "<table border=1><caption>Flower</caption>"
                			+ "<tr><th>LocalName</th><th>ScientName</th><th>Count</th></tr>";
                	      	   
                	   result = result+"<tr><td>"+flower.getLocalName()+"</td><td>"+flower.getScientName()+"</td><td>"+flower.getFlowerCount()+"</td></tr>";
                	result = result+"<input type=\"button\" onclick=\"history.back();\" value=\"Back\"/>"
                			+ "</table></body></html>";
               		out.println(result); 
        	}else{
            	PrintWriter out = response.getWriter();
            	out.println("No one Flower found");
        	}
        	
        } else if (request.getParameter("goout")!=null) {

            this.forward("/login.html", request, response);

        }else if (request.getParameter("sortmyflower")!=null) {
        	PrintWriter out = response.getWriter();
        	//out.println(flowerService.SortOurFlowers());
        	Properties prop = new Properties();
        	String propFileName = "conf.properties";
        	//InputStream input = getClass().getClassLoader().getResourceAsStream(propFileName);
        	InputStream  input = new FileInputStream("conf.properties");
        	/*OutputStream output = new FileOutputStream("conf.properties"); 
        	try {        		       		
        		prop.setProperty("fileForMarshall", "customer.xml");
        		prop.store(output, null);
        	}catch (IOException ex) {
        		ex.printStackTrace();
        	}*/
        	try {          		
        		prop.load(input); 
        		propFileName = prop.getProperty("fileForMarshall");
        	}catch (IOException ex) {
        		ex.printStackTrace();
        	}
        		
			ApplicationContext context =  new ClassPathXmlApplicationContext("application-context.xml");
			CustomerIntegrationService converter = (CustomerIntegrationService)context.getBean("CustomerIntegrationService");			
			UserAddress userAddress = new UserAddress();			
			User customer = new User("er","dw","uiui","",23,userAddress);
			converter.convertFromObjectToXML(customer,propFileName);
			User customer2 = (User)converter.convertFromXMLToObject(propFileName);
			out.println(customer2.getPassword());
        	
        	//flowerListService.sortFlowerNameByCount();
        	//this.forward("/OrderFin.jsp", request, response);

        }else if (request.getParameter("sortmyflowerdesc")!=null) {    
        	
        	User user = userListService.findUser("e");
        	PrintWriter out = response.getWriter();

        	
    		String res = "";
    		try {
    			messagerService.CreateCon();
    			//messagerService.CreateCon("e",10);
    			messagerService.SendEntityUserMessage(user);
    			messagerService.CreateCon();
    			res = messagerService.readFile("input.xml", StandardCharsets.UTF_8);
    			//res = messagerService.GetMessages();
    			//messagerService.deleteMessageFromQueue(res);
    			
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		out.println(res);

        	
           	//this.forward("/OrderFin.jsp", request, response);
           	
           	
        } else if (request.getParameter("findinn")!=null) {
        	response.setContentType("text/html");
        	String result;
        	PrintWriter out = response.getWriter();
        	String inn = request.getParameter("localName or scientName");
        	List<LegalEntityCustomer> resultList = userListService.findCustomerByInn(inn);
        	if(resultList.isEmpty()){result = "No one Exemplar found";}
        	else{result = Integer.toString(resultList.size())+"   Exemplar found";} 
        	out.println(result); 
        	
           	
        } else if (request.getParameter("findsurname")!=null) {

        	response.setContentType("text/html");
        	String result;
        	PrintWriter out = response.getWriter();
        	String surname = request.getParameter("localName or scientName");
        	List<IndividualCustomer> resultList = userListService.findCustomerBySurname(surname);
        	if(resultList.isEmpty()){result = "No one Exemplar found";}
        	else{result = Integer.toString(resultList.size())+"   Exemplar found";} 
        	out.println(result); 
            
        } else if (request.getParameter("findname")!=null) {

        	response.setContentType("text/html");
        	String result;
        	PrintWriter out = response.getWriter();
        	String userlogin = request.getParameter("localName or scientName");
        	List<User> resultList = userListService.findCustomerByUserlogin(userlogin);        	
        	if(resultList.isEmpty()){result = "No one Exemplar found";}
        	else{result = Integer.toString(resultList.size())+"   Exemplar found" ;} 
        	out.println(result);            
  
        	
        } else if (request.getParameter("findinn2")!=null) {

        	response.setContentType("text/html");
        	String result;
        	PrintWriter out = response.getWriter();
        	String username = request.getParameter("localName or scientName");
        	List<User> resultList = userListService.findCustomerByInn2(username);
        	if(resultList.isEmpty()){result = "No one Exemplar found";}
        	else{result = Integer.toString(resultList.size())+"   Exemplar found";} 
        	out.println(result);  
           	
           	
        }else if (request.getParameter("showcookie")!=null) {
        	
       	
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		out.println("<HTML>");
    		out.println("<HEAD>");
    		out.println("<TITLE>");
    		out.println("You bought the");
    		out.println("</TITLE>");
    		out.println("</HEAD>");
    		out.println("<BODY>");
    		out.println("<h1> Sort </h1>");    		
    		HttpSession session = request.getSession();
    		out.println(session.getId()+"<br>");    		
    		out.println(session.getAttribute("user")+"<br>");
    		out.println(session.getServletContext()+"<br>"+"<br>"+"<br>");
    		
    		out.println("Your session name=   "+session.getAttribute("user")+"<br>");
    		Cookie[] cookies = request.getCookies();
    		for(Cookie cookie:cookies){
    			if (session.getAttribute("user").equals(cookie.getName()))
    				out.println("Your cookie value=   "+cookie.getValue()+"<br>");
    		}    		
    		
    	      Enumeration sesNames = session.getAttributeNames();
    	       while (sesNames.hasMoreElements()) {
    	          String name = sesNames.nextElement().toString();
    	          Object value = session.getAttribute(name);
    	          out.println(name + " = " + value + "<br>");
    	       }
    		
    		
    		for(int i = 0; i < cookies.length; i++){
    			out.println("Name="+cookies[i].getName()+"<br>");
    			out.println("Value="+cookies[i].getValue()+"<br>");
    			cookies[i].setMaxAge(-1);
    		}

    		out.println("</BODY>");
    		out.println("</HTML>");   
/*        	
        		int count = Integer.parseInt(request.getParameter("count"));
        		/*UserShopCart userShopCard = new UserShopCart();
        		userShopCard.setCount(count);
        		userShopCard.setFlowerName(flower.getLocalName());        		
        		User user = UserList.findUser(userName);
        		user.getShopCard().add(userShopCard); ///       
        			HttpSession session = request.getSession();
        		session.setAttribute(flower.getLocalName(), count); */
        }else if(request.getParameter("I Want To Buy")!=null){
        	PrintWriter out = response.getWriter();
        	boolean except = false;
        	String username = request.getParameter("NumberOfFlowers");
            try{
            	int numberFlower = (Integer.parseInt(username));          	   
            }catch(Exception e){            	   
            	out.println("Wrong Number :     "+username);  
            	except = true;
            }
            if (!except){
        		HttpSession session = request.getSession();        		   		
        		String userLogin = (String) session.getAttribute("user");
        		
        		if (userLogin.equals(null)){out.println("us"); }
        		
        		userListService.orderSeveralFlowersByPrice10(flowerreg.getLocalName(), userLogin, Integer.parseInt(username));
    	   	 	List<Flower> flList = flowerService.SortFlowersByLocalName();
    	        request.setAttribute("flList", flList);
    	        this.forward("/OrderFin.jsp", request, response); 
            	//out.println(username);           	
            }
  
        }else if(request.getParameter("test")!=null){
        	this.forward("/TestPage.jsp", request, response); 
        }
        List<Flower> flowersListReg = flowerService.SortFlowersByLocalName();      
    	for(Flower fl : flowersListReg){
    		if (request.getParameter(fl.getLocalName())!=null){

      /*  		HttpSession session = request.getSession();        		   		
        		String userLogin = (String) session.getAttribute("user");
    			
    			//User user = userListService.findUser(userLogin);
    			userListService.orderOneFlowerByPrice10(fl.getLocalName(), userLogin);
    	   	 	List<Flower> flList = flowerService.SortFlowersByLocalName();//
    	        request.setAttribute("flList", flList);*/
    			flowerreg = fl;
    	        request.setAttribute("flower", fl);
    	        this.forward("/buyFlower.jsp", request, response);   
    			//this.forward("/OrderFin.jsp", request, response);    			

    		}
    	}
        
    }

}
