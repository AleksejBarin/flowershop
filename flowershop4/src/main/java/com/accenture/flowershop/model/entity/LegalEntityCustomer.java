package com.accenture.flowershop.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tb_LegalEntityCustomer")
public class LegalEntityCustomer {
	
	@Id
	private String name;
	
	private String inn;

    public LegalEntityCustomer(String name, String inn) {
	       super( );
	       this.name = name;
	       this.inn = inn;
	    }

    public LegalEntityCustomer( ) {
      super();
    }	
	
	public String getName(){return name;}
	   
	public void setName(String name){this.name = name;}
	
	public String getInn(){return inn;}
	   
	public void setInn(String inn){this.inn = inn;}
	
}
