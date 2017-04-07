package com.accenture.flowershop.model.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tb_IndividualCustomer")
public class IndividualCustomer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer indCustId;	
	private String name;	
	private String surname;	
	private Calendar birthDate;		
	private Gender gender;

    public IndividualCustomer(Integer indCustId, String name, String surname, Calendar birthDate, Gender gender) {
       super( );
       this.indCustId = indCustId;
       this.name = name;
       this.surname = surname;
       this.birthDate = birthDate;
       this.gender = gender;
       
    }
    public IndividualCustomer(String name, String surname, Calendar birthDate, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        
     }
    public IndividualCustomer( ) {
    	super();
    }		
	public Integer getIndCustId(){return indCustId;}
	   
	public void setIndCustId(Integer indCustId){this.indCustId = indCustId;}
	
	public String getName(){return name;}
	   
	public void setName(String name){this.name = name;}
	
	public String getSurname(){return surname;}
	   
	public void setSurname(String surname){this.surname = surname;}	
	
	public Calendar getBirthDate(){return birthDate;}
	   
	public void setBirthDate(Calendar birthDate){this.birthDate = birthDate;}	
	
	public Gender getGender(){return gender;}
	   
	public void setGender(Gender gender){this.gender = gender;}

}
