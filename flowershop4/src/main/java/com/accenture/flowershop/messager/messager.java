package com.accenture.flowershop.messager;

import javax.persistence.PersistenceContext;

//@Service
public class messager {
//    @PersistenceContext
//    private ActiveMQConnectionFactory connectionFactoryfromap;
//    @PersistenceContext
//    private ActiveMQQueue esbToMcpRequestQueue;
    
    
/*	public void CreateCon() throws JMSException {
		Connection connection = connectionFactoryfromap.createConnection(); 
		

		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
		Message message = session.createTextMessage("I‘m JMS message");
		message.setIntProperty("amount", 10);
		//TemporaryQueue inQueue = session.createTemporaryQueue();
		MessageProducer producer = session.createProducer(esbToMcpRequestQueue);
		MessageConsumer consumer  = session.createConsumer(esbToMcpRequestQueue);
		producer.send(message);
		message = consumer.receive();

		connection.close();
	 }*/

}
