package com.accenture.flowershop.business;

import java.util.List;

import com.accenture.flowershop.model.entity.Flower;
import com.accenture.flowershop.model.entity.IndividualCustomer;
import com.accenture.flowershop.model.entity.LegalEntityCustomer;
import com.accenture.flowershop.model.entity.User;

public interface FlowerService {
	
	List<Flower> getFlowersWithPositiveCount();
	
	List<Flower> findflowers();
	
	String SortOurFlowers();
	
    Flower findFlowerByLocalName(String localName);
    
    Flower findFlowerByScientName(String scientName);
	
	boolean addFlower(Flower flower);
	
	List<Flower> sortAllFlowersByLocalName();
	
	void increaseCountAllFlowersByOne(int count);
	
	
}
