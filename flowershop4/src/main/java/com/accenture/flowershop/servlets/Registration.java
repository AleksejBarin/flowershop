package com.accenture.flowershop.servlets;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.jms.JMSException;
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
import com.accenture.flowershop.model.entity.UserShopCart;
@Transactional
public class Registration extends Dispatcher {

	private static final long serialVersionUID = 1461715797965410712L;
	private Flower flowerreg = null;
	
	@Autowired
	private FlowerService flowerService;
	@Autowired
	private UserListService userListService;
	@Autowired
	private MessagerService messagerService;
	// Вставить метод в сервлет, чтобы в него можно было инжектить другие бины.
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}	   

	public String getServletInfo(){

        return "Registration servlet";

    }

    public void refreshPage(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException{
   	 	List<Flower> flList = flowerService.getFlowersWithPositiveCount();//
        request.setAttribute("flList", flList);   
		HttpSession session = request.getSession();        		   		
		String userLogin = (String) session.getAttribute("user");
    	List<UserShopCart> usclList = userListService.getUserShopCart(userLogin);	   	 	
        request.setAttribute("usclList", usclList);
		User user = userListService.findUser(userLogin);
        user = userListService.findUser(userLogin); //для рефреша депозита
        request.setAttribute("user", user);
        this.forward("/OrderFin.jsp", request, response); 
    }
	
    @Override 
    protected void doGet(HttpServletRequest request,HttpServletResponse response) 
    throws IOException,ServletException{
    	refreshPage(request,response);        
    }
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)

    throws ServletException, IOException {
        
        if (request.getParameter("login")!=null){        	
        	Flower newFlower = new Flower("rose","rosa",32);
        	flowerService.addFlower(newFlower);
        	newFlower = new Flower("myrtle","myrtus",43);
        	flowerService.addFlower(newFlower);
        	newFlower = new Flower("orchid","phalaenopsis",41);
        	flowerService.addFlower(newFlower);
        	newFlower = new Flower("lily","lilium",15);
        	flowerService.addFlower(newFlower);/**/
        	        	
        	String UserPswd = request.getParameter("password");
        	String userLogin = request.getParameter("user");
        	if (userListService.loginUser(userLogin, UserPswd)){
           	 	HttpSession session = request.getSession();
           	 	session.setAttribute("user",userLogin);
           	 	session.setMaxInactiveInterval(30*60);
           	 	String safeCookieName = URLEncoder.encode(userLogin, "UTF-8");
           	 	String safeCookieValue = URLEncoder.encode("user", "UTF-8");
           	 	response.addCookie(new Cookie(safeCookieName, safeCookieValue));
           	 	//loginCookie.setMaxAge(3*60);            	 	
    	        refreshPage(request,response);        		
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
        	//this.forward("/OrderFin.jsp", request, response);

        }else if (request.getParameter("sortmyflowerdesc")!=null) {    
        	
        	User user = userListService.findUser("e");
        	PrintWriter out = response.getWriter();
        	
    		String res = "";
    		try {
    			messagerService.CreateCon();
    			//messagerService.CreateCon("6",10);
    			String h="6"; Integer i = 53;
    			messagerService.SendEntityUserMessage(user);
    			messagerService.CreateCon(h,i);
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
    	      Enumeration<?> sesNames = session.getAttributeNames();        // <?>
    	       while (sesNames.hasMoreElements()) {
    	          String name = sesNames.nextElement().toString();
    	          Object value = session.getAttribute(name);
    	          out.println(name + " = " + value + "<br>");
    	       }          	 	
    		for(int i = 0; i < cookies.length; i++){
    			out.println("Cookie # "+ i +"<br>");
    			String safeCookieName = URLDecoder.decode(cookies[i].getName(), "UTF-8");
    			out.println("Name="+safeCookieName+"<br>");
    			out.println("Name="+cookies[i].getName()+"<br>");
    			out.println("Value="+cookies[i].getValue()+"<br>");
    			cookies[i].setMaxAge(-1);
    		}
    		out.println("</BODY>");
    		out.println("</HTML>");   

        }else if(request.getParameter("I Want To Buy")!=null){
        	PrintWriter out = response.getWriter();
        	boolean except = false;
        	String numberOfFlowers = request.getParameter("NumberOfFlowers");
            try{
            	int numberFlower = (Integer.parseInt(numberOfFlowers));  
            	if (numberFlower < 1) {
            		except = true;
            		out.println("Wrong Number :     "+numberOfFlowers);
            	}
            }catch(Exception e){            	   
            	out.println("Wrong Number :     "+numberOfFlowers);  
            	except = true;
            }
            
            if (!except){
        		HttpSession session = request.getSession();        		   		
        		String userLogin = (String) session.getAttribute("user");        		
        		User user = userListService.findUser(userLogin);
        		UserShopCart usc = new UserShopCart(user,flowerreg.getLocalName(),Integer.parseInt(numberOfFlowers));
        		userListService.addUserShopCart(usc);
        		refreshPage(request,response);            	          	
            }
  
        }else if(request.getParameter("test")!=null){
        	this.forward("/TestPage.jsp", request, response); 
        	
        }else if(request.getParameter("buy")!=null){ 
        	PrintWriter out = response.getWriter();
    		HttpSession session = request.getSession();        		   		
    		String userLogin = (String) session.getAttribute("user");
    		User user = userListService.findUser(userLogin);
    		if (!userListService.checkCountFlowersForBuyUserShopCart(userLogin).equals("")){
    			out.println("There is too many" + userListService.checkCountFlowersForBuyUserShopCart(userLogin));
    		}else if (userListService.getTotalSumOrder(userLogin) > user.getDeposite()){ 
    			out.println("There is not enough money on your account");
    		}else {
    			userListService.buyUserShopCart(userLogin);
    			refreshPage(request,response);
    		}   

        }
        
        List<Flower> flowersListReg = flowerService.sortAllFlowersByLocalName();      
    	for(Flower fl : flowersListReg){
    		if (request.getParameter(fl.getLocalName())!=null){

     			flowerreg = fl;
    	        request.setAttribute("flower", fl);
    	        this.forward("/buyFlower.jsp", request, response);       			
    		}
    		if (request.getParameter((fl.getLocalName())+"reset")!=null){
        		HttpSession session = request.getSession();        		   		
        		String userLogin = (String) session.getAttribute("user");
        		User user = userListService.findUser(userLogin);
        		List<UserShopCart> uscList = userListService.getUserShopCart(userLogin);
        		for (UserShopCart usc : uscList){
        			if (usc.getFlowerName().equals(fl.getLocalName())){
        				userListService.deleteUserShopCart(usc);
        			}
        		}
        		refreshPage(request,response);
    		}
    	}
        
    }

}
