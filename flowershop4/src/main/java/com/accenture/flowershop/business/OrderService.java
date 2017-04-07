package com.accenture.flowershop.business;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface OrderService {
	
	void IncreaseCountAllFlowers( @WebParam(name = "count") int count);

}
