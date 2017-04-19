package com.accenture.flowershop.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table(name = "tb_UserShopCart")
@Component
public class UserShopCart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer userShopCartId;
	
	@ManyToOne		// @ManyToOne can also be used in unidirectional manner
	@JoinColumn(name = "userlogin") 	
	private User user;
	
	private String userShopCartFlowername;

	private int userShopCartCount;
	
	private String status;	
	
	private String storage;
	
	public UserShopCart(User user, String userShopCartFlowername, int userShopCartCount){
		this.user = user;
		this.userShopCartFlowername = userShopCartFlowername;
		this.userShopCartCount = userShopCartCount;	
		this.status = "in batch";
		this.storage = "main";
	}
	
	public UserShopCart( ) {
		super();
	}
	
	
	
	public String getFlowerName(){return userShopCartFlowername;}

	public void setFlowerName(String userShopCartFlowername){this.userShopCartFlowername = userShopCartFlowername;}

	public int getCount(){return userShopCartCount;}

	public void setCount(int userShopCartCount){this.userShopCartCount = userShopCartCount;}
	
	public void setUser(User user){this.user = user;}
	
	public User getUser(){return this.user;}
	
	public void setStorage(String storage){this.storage = storage;}
	
	public String getStorage(){return this.storage;}
	
	public void setStatus(String status){this.status = status;}
	
	public String getStatus(){return this.status;}
	
	public void setUserShopCartId(int userShopCartId){this.userShopCartId = userShopCartId;}
	
	public int getUserShopCartId(){return this.userShopCartId;}
   
	public void addUserShopCartFlowerCount(int flowerCount){this.userShopCartCount += flowerCount;}

}
