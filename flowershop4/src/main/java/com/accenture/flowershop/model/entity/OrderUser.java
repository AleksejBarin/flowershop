package com.accenture.flowershop.model.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "tb_order")
public class OrderUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer orderId;
	
	@ManyToOne		// @ManyToOne can also be used in unidirectional manner
    @JoinColumn(name = "userlogin") 	
    private User user;
	
	public String status;
	
	public Calendar creationDate;
	
	public Calendar closingData;
	
	public double sumOrder;
	
	public OrderUser(User user){
		this.user = user;
		this.status = "in batch";
		this.creationDate = Calendar.getInstance();
		this.closingData = Calendar.getInstance();
		this.sumOrder = 0;		
	}
	
   public OrderUser( ) {
	      super();
   }
	
	public void setOrderId(int orderId){this.orderId = orderId;}
	
	public int getOrderId(){return this.orderId;}
	
	public void setUser(User user){this.user = user;}
	
	public User getUser(){return this.user;}
	
	public void setStatus(String status){this.status = status;}
	
	public String getStatus(){return this.status;}
	
	public void setCreationDate(Calendar creationDate){this.creationDate = creationDate;}
	
	public Calendar getCreationDate(){return this.creationDate;}
	
	public void setClosingData(Calendar closingData){this.closingData = closingData;}
	
	public Calendar getClosingData(){return this.closingData;}
	
	public void setSumOrder(double sumOrder){this.sumOrder = sumOrder;}
	
	public double getSumOrder(){return this.sumOrder;}
	
	
}
