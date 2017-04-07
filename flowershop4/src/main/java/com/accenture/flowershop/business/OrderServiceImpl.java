package com.accenture.flowershop.business;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

@WebService(endpointInterface = "com.accenture.flowershop.business.OrderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private FlowerService flowerService;
	
	public void IncreaseCountAllFlowers(int count){
		flowerService.IncreaseCountAllFlowersByOne(count);
	}

}