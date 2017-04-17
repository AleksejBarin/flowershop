package com.accenture.flowershop.business;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(endpointInterface = "com.accenture.flowershop.business.OrderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private FlowerService flowerService;
	
    @PersistenceContext
    private EntityManager entityManager;	
	
	public void IncreaseCountAllFlowers(int count){
		flowerService.IncreaseCountAllFlowersByOne(count);
	}

}