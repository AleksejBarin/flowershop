package com.accenture.flowershop.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_OrderItem")
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer orderItemId;
	
	@ManyToOne		// @ManyToOne can also be used in unidirectional manner
    @JoinColumn(name = "flower") 	
	private Flower flower;
	
	private int flcount;
	
	private double price;
	
	public OrderItem(Flower flower,int flcount, double price){
		this.flower = flower;
		this.flcount = flcount;
		this.price = price;		
	}
	
	public OrderItem(){
		super();
	}
	
	public void setOrderItemId(int orderItemId){this.orderItemId = orderItemId;}
	
	public int getOrderItemId(){return this.orderItemId;}
	
	public void setFlower(Flower flower){this.flower = flower;}
	
	public Flower getFlower(){return this.flower;}
	
	public void setFlcount(int flcount){this.flcount = flcount;}
	
	public int getFlcount(){return this.flcount;}
	
	public void setPrice(double price){this.price = price;}
	
	public double getPrice(){return this.price;}
	
}
