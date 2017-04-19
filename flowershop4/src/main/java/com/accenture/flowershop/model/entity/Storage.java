package com.accenture.flowershop.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Storage")
@Table(name = "tb_Storage")
public class Storage {

	@Id
	public String storageName;

	public String flowerLocalName;

	public int flowersCount;
	
	public double price;
	   
	   
	public Storage(String storageName, String flowerLocalName, int flowersCount, double price) {
		// TODO Auto-generated constructor stub
		this.storageName = storageName;
		this.flowerLocalName = flowerLocalName;
		this.flowersCount = flowersCount;
		this.price = price;
	} 
	public Storage(){
		this.storageName = "";
		this.flowerLocalName = "";
		this.flowersCount = 0;	
		this.price = 0;
	}
	
	public double getPrice(){return price;}
	   
	public void setPrice(double price){this.price = price;}
	   
	public String getStorageName(){return storageName;}
	   
	public void setStorageName(String storageName){this.storageName = storageName;}
	   
	public String getFlowerLocalName(){return flowerLocalName;}

	public void setFlowerLocalName(String flowerLocalName){this.flowerLocalName = flowerLocalName;}

	public int getFlowersCount(){return flowersCount;}

	public void setFlowersCount(int flowersCount){this.flowersCount = flowersCount;}

}

