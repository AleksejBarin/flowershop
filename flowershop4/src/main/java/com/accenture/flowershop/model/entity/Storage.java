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
	   
	   
	public Storage(String storageName, String flowerLocalName, int flowersCount) {
		// TODO Auto-generated constructor stub
		this.storageName = storageName;
		this.flowerLocalName = flowerLocalName;
		this.flowersCount = flowersCount;
	} 
	public Storage(){
		this.storageName = "";
		this.flowerLocalName = "";
		this.flowersCount = 0;		   
	}
	   
	public String getStorageName(){return storageName;}
	   
	public void setStorageName(String storageName){this.storageName = storageName;}
	   
	public String getFlowerLocalName(){return flowerLocalName;}

	public void setFlowerLocalName(String flowerLocalName){this.flowerLocalName = flowerLocalName;}

	public int getflowersCount(){return flowersCount;}

	public void setflowersCount(int flowersCount){this.flowersCount = flowersCount;}

}

