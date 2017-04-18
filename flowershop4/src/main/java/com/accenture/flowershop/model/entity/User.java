package com.accenture.flowershop.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_ABSTRACTUSER")
public class User  {
   @Id  	   
   private String userlogin;
   private String username;
   private String password;
   private String phone;
   @Embedded
   private UserAddress userAddress;
   @OneToMany(fetch = FetchType.EAGER,mappedBy="user")
   private List<OrderUser> orderList = new ArrayList<OrderUser>();
   
   public  void addDocument(OrderUser ou)   {
	   orderList.add(ou);	
	   ou.setUser(this);	// Bidirectional consistency should be managed programmatically
   }  

   private Integer discount;
   public double deposite;
   
   @OneToMany(fetch = FetchType.EAGER,mappedBy="user")
   private List<UserShopCart> userShopCart = new ArrayList<UserShopCart>(); 
   
   public  void addUserShopCart(UserShopCart usc)   {
	   userShopCart.add(usc);	
	   usc.setUser(this);	// Bidirectional consistency should be managed programmatically
   }  
   
   //@GeneratedValue(strategy = GenerationType.AUTO) 	
   public User(String username, String userlogin, String password, String phone, Integer discount,UserAddress userAddress) {
	      super( );	
	      this.userlogin = userlogin;
	      this.username = username;
	      this.password = password;
	      this.phone = phone;
	      this.discount = discount;
	      this.userAddress = userAddress;	
	      this.deposite = 100;
	      
	   }

   public User( ) {
      super();
   }   

   public String getUserlogin(){return userlogin;}  // for castor marshaller
   
   public String getUsername(){return username;}  // for castor marshaller
   
   public UserAddress getUserAddress(){return userAddress;}// for castor marshaller
   
   public String getUserLogin(){return userlogin;}
	   
   public void setUserLogin(String userlogin){this.userlogin = userlogin;}
	   
   public String getUserName(){return username;}
   
   public void setUserName(String username){this.username = username;}

   public String getPassword(){return password;}
   
   public void setPassword(String password){this.password = password;}

   public String getPhone(){return phone;}

   public void setPhone(String phone){this.phone = phone;}

   public UserAddress getAddress(){return userAddress;}

   public void setAddress(UserAddress userAddress){this.userAddress = userAddress;}

   public Integer getDiscount(){return discount;}

   public void setDiscount(Integer discount){this.discount = discount;}

   public double getDeposite(){return deposite;}

   public void setDeposite(double deposite){this.deposite = deposite;}   
   
   public List<OrderUser> getOrderList(){return orderList;}

   public List<UserShopCart> getShopCard(){return userShopCart;}
   
}
