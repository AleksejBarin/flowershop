package com.accenture.flowershop.model.entity;

import javax.persistence.Embeddable;

@Embeddable
public class UserAddress {

	   private String city;

	   private String street;

	   private String building;

  
	  
	   public UserAddress(String city, String street, String building) {
	      super( );	  
	      this.city = city;
	      this.street = street;
	      this.building = building;
	   }

	   public UserAddress( ) {
	      super();
	   }	   

	   
	   public String getCity(){return city;}
	   
	   public void setCity(String city){this.city = city;}

	   public String getStreet(){return street;}
	   
	   public void setStreet(String street){this.street = street;}

	   public String getBuilding(){return building;}
	   
	   public void setBuilding(String building){this.building = building;}

}
