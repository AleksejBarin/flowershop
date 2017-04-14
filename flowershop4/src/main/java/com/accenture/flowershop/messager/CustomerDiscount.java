package com.accenture.flowershop.messager;

public class CustomerDiscount {
	
	private String userlog;
	
	private Integer discount;
	
	public CustomerDiscount(){		
		super();
	}
	
	public CustomerDiscount(String userlog, Integer discount){
		this.discount = discount;
		this.userlog = userlog;
	}
	
	   public String getUserlog(){return userlog;}
	   
	   public void setUserlog(String userlog){this.userlog = userlog;}
	   
	   public Integer getDiscount(){return discount;}

	   public void setDiscount(Integer discount){this.discount = discount;}
}
