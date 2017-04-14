package com.accenture.flowershop.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Flower")
@Table(name = "tb_Flower")
public class Flower {

	@Id
	public String localName;

	public String scientName;

	public int flowerCount;
	   
	@OneToMany(fetch = FetchType.EAGER,mappedBy="flower")
	private List<OrderItem> orderItemList = new ArrayList<OrderItem>();
   
   public  void addDocument(OrderItem oi)   {
	   orderItemList.add(oi);	
	   oi.setFlower(this);	// Bidirectional consistency should be managed programmatically
   } 
	   
	public Flower(String localName, String scientName, int flowerCount) {
		// TODO Auto-generated constructor stub
		this.localName = localName;
		this.scientName = scientName;
		this.flowerCount = flowerCount;
	} 
	public Flower(){
		this.localName = "";
		this.scientName = "";
		this.flowerCount = 0;		   
	}
	
	public void descrease(int number) 
	{
		if(this.flowerCount > number)
			this.flowerCount = flowerCount - number;
	}
	
	public String getLocalName(){return localName;}
	   
	public void setlocalName(String localName){this.localName = localName;}
	   
	public String getScientName(){return scientName;}

	public void setScientName(String scientName){this.scientName = scientName;}

	public int getFlowerCount(){return flowerCount;}

	public void setFlowerCount(int flowerCount){this.flowerCount = flowerCount;}
	
	public List<OrderItem> getOrderItmeList(){return orderItemList;}

}
