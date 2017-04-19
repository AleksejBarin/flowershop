package com.accenture.flowershop.servlets;

import java.sql.Date;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accenture.flowershop.business.OrderService;

public class ScheduleTask extends TimerTask {
	 
    Date now;
    int count;
    // Добавляем таск
    @Override
    public void run() {
    	count = (int) (Math.random() * 20);
    	//refreshPage(request ,response);
		//OrderServiceImplService service = new OrderServiceImplService(); 	// create service
		//OrderService sei = service.getOrderServiceImplPort();	// get SEI interface	
		//sei.increaseCountAllFlowers(15);						// call WS operation
        now = new Date(System.currentTimeMillis());
        System.out.println("Текущая дата и время : " + now);
    }
 
}