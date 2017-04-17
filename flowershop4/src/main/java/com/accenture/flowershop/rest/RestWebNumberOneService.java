package com.accenture.flowershop.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.accenture.flowershop.business.FlowerService;
import com.accenture.flowershop.business.UserListService;
import com.accenture.flowershop.model.entity.Flower;
import com.accenture.flowershop.model.entity.OrderItem;
import com.accenture.flowershop.model.entity.User;
import com.accenture.flowershop.model.entity.UserShopCart;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component			/// ???
@Path("/hello")
@Scope(value="session")
public class RestWebNumberOneService {
	@Autowired
	private FlowerService flowerService;
	@Autowired
	private UserListService userListService;
	HttpServletRequest request;
//http://localhost:8080/com.vogella.jersey.first/rest/hello
	//	http://localhost:8080/com.accenture.flowershop.rest/rest/hello
  // This method is called if TEXT_PLAIN is request
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayPlainTextHello() {
    return "Hello #### ";
  }

  // This method is called if XML is request
  @GET
  @Produces(MediaType.TEXT_XML)
  public String sayXMLHello() {
    return "<?xml version=\"1.0\"?>" + "<hello> Hello!##! " + "</hello>";
  }

  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
    return "<html> " + "<title>" + "Hello " + "</title>"
        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/cart")
  public UserShopCart sayJsonHello() {
	  User user = new User();
	  UserShopCart userShopCart = new UserShopCart(user,"rose",2);
	    return userShopCart;
	  }
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/pineapple")
  public Flower sayJsonFlower() {
	  Flower flower = flowerService.findFlowerByLocalName("rose");
	  //Flower flower = new Flower("pineapple","rose",2);
	    return flower;
	  }
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  @Path("{Flowerweb}")  
  public List<OrderItem> getOrderItemByFlower(@PathParam("Flowerweb") String localName){
 // public Flower getOrderItemByFlower(@PathParam("Flower") String localName){
	  //public String getOrderItemByFlower(@PathParam("Flowerweb") String localName){
	  //Flower flower1 = new Flower("rose","rose",2);
	  Flower flower = flowerService.findFlowerByLocalName(localName);
	  
	  return flower.getOrderItmeList();
	  //return localName;
  }
 
  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
  @Path("/pineapple/{Flowerweb}")
  public int getOrderItemByFlower2(@PathParam("Flowerweb") String localName){
	  Flower flower = flowerService.findFlowerByLocalName(localName);
	  //Flower flower = new Flower("pineapple","rose",2);
	    return flower.getFlowerCount();
  }
  

  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
  @Path("/usercart/{userlogin}")
  public String getUserCart(@PathParam("userlogin") String userlogin){
	  String out = "";
	  for (UserShopCart usc :userListService.findAllUserShopCart(userlogin)){
		  out = out + usc.getFlowerName() + " " + usc.getCount()+"    ";
	  }
	  return out;
  }  

  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
  @Path("/usercart/{userlogin}/count")
  public int getUserCartCount(@PathParam("userlogin") String userlogin){
	  return userListService.findAllUserShopCart(userlogin).size();
  }    
  
  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void newUserShopCart(@FormParam("user") String userLogin,
                  @FormParam("userShopCartFlowername") String userShopCartFlowername,
                  @FormParam("userShopCartCount") String userShopCartCount,
                  @Context HttpServletResponse servletResponse) throws IOException {
	  User user = userListService.findUser(userLogin);
	  UserShopCart newUserShopCart = new UserShopCart(user, userShopCartFlowername,Integer.parseInt(userShopCartCount));
	  userListService.addnewUserShopCart(newUserShopCart);       
  }
  
}