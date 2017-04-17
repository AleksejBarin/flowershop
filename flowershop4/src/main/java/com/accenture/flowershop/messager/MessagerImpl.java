package com.accenture.flowershop.messager;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import com.accenture.flowershop.business.CustomerIntegrationService;
import com.accenture.flowershop.business.UserListService;
import com.accenture.flowershop.model.entity.User;

@Service
public class MessagerImpl implements MessagerService {
  
	//private JmsTemplate jmsTemplate;
	@Autowired
	UserListService userListService;
	
	private static final Logger LOG = 	LoggerFactory.getLogger(MessagerImpl.class);
	
	public void CreateCon() throws JMSException {
		ApplicationContext context =  new ClassPathXmlApplicationContext("application-context.xml");
		ActiveMQConnectionFactory connectionFactoryfromap = (ActiveMQConnectionFactory)context.getBean("connectionFactoryfromap");
		ActiveMQQueue outQueue = (ActiveMQQueue)context.getBean("outQueue");	
		final CustomerIntegrationService converter = (CustomerIntegrationService)context.getBean("CustomerIntegrationService");	
		Connection connection = connectionFactoryfromap.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		CustomerDiscount cd = new CustomerDiscount("3",15);
		String res = "";
		try {
			converter.convertFromObjectToXML(cd, "customer.xml");
			res = readFile("customer.xml", StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Message message = session.createTextMessage(res);
		//message.setIntProperty("amount", 10);
		
		MessageProducer producer = session.createProducer(outQueue);		
		MessageConsumer consumer = session.createConsumer(outQueue);
		
			
		consumer.setMessageListener(new MessageListener() {
		       public void onMessage(Message message) {
		    	    TextMessage msg = null; 
		    	    if (message instanceof TextMessage) { 
		    	    	msg = (TextMessage) message;
		    	    	LOG.info("SUCCESSLISTENER");
		    	    	
						try {							
							Object obj = converter.convertFromXMLToObject(msg);
							if(obj instanceof CustomerDiscount){
								CustomerDiscount cd2 = (CustomerDiscount)obj;
								
								userListService.ChangeUserDiscount(cd2.getUserlog(), cd2.getDiscount());
								LOG.info("SUCCESSWINDISC");
							}else if(obj instanceof User){
								LOG.info("SUCCESSWINUSER");
							}else{
								LOG.info("SUCCESSWIN(NOT)");
							}
							//CustomerDiscount cd2 = (CustomerDiscount)converter.convertFromXMLToObject(msg);
							//CustomerDiscount cd2 = (CustomerDiscount)converter.convertFromXMLToObject("input.xml");
							//LOG.info("SUCCESSLIS" + cd2.getUserlog());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    	    }		    	    
		       }
		});
		
		producer.send(message);
		
		connection.close();
	}

	public void CreateCon(String userlogin,Integer discount) throws JMSException {
		ApplicationContext context =  new ClassPathXmlApplicationContext("application-context.xml");
		ActiveMQConnectionFactory connectionFactoryfromap = (ActiveMQConnectionFactory)context.getBean("connectionFactoryfromap");
		ActiveMQQueue outQueue = (ActiveMQQueue)context.getBean("outQueue");	
		final CustomerIntegrationService converter = (CustomerIntegrationService)context.getBean("CustomerIntegrationService");	
		Connection connection = connectionFactoryfromap.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		CustomerDiscount cd = new CustomerDiscount(userlogin,discount);
		String res = "";
		try {
			converter.convertFromObjectToXML(cd, "customer.xml");
			res = readFile("customer.xml", StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Message message = session.createTextMessage(res);
		//message.setIntProperty("amount", 10);
		
		MessageProducer producer = session.createProducer(outQueue);		
		MessageConsumer consumer = session.createConsumer(outQueue);
		
			
		consumer.setMessageListener(new MessageListener() {
		       public void onMessage(Message message) {
		    	    TextMessage msg = null; 
		    	    if (message instanceof TextMessage) { 
		    	    	msg = (TextMessage) message;
		    	    	LOG.info("SUCCESSLISTENER");
		    	    	
						try {							
							Object obj = converter.convertFromXMLToObject(msg);
							if(obj instanceof CustomerDiscount){
								CustomerDiscount cd2 = (CustomerDiscount)obj;
								
								userListService.ChangeUserDiscount(cd2.getUserlog(), cd2.getDiscount());
								LOG.info("SUCCESSWINDISC");
							}else if(obj instanceof User){
								LOG.info("SUCCESSWINUSER");
							}else{
								LOG.info("SUCCESSWIN(NOT)");
							}
							//CustomerDiscount cd2 = (CustomerDiscount)converter.convertFromXMLToObject(msg);
							//CustomerDiscount cd2 = (CustomerDiscount)converter.convertFromXMLToObject("input.xml");
							//LOG.info("SUCCESSLIS" + cd2.getUserlog());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    	    }		    	    
		       }
		});
		
		producer.send(message);
		
		connection.close();
	}
	
	public  String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
	public void SendEntityUserMessage(User user) throws JMSException {	
		
		//User user2 = userListService.findUser("1");
		ApplicationContext context =  new ClassPathXmlApplicationContext("application-context.xml");
		ActiveMQConnectionFactory connectionFactoryfromap = (ActiveMQConnectionFactory)context.getBean("connectionFactoryfromap");
		ActiveMQQueue outQueue = (ActiveMQQueue)context.getBean("outQueue");	
		ActiveMQQueue inQueue = (ActiveMQQueue)context.getBean("inQueue");	
		CustomerIntegrationService converter = (CustomerIntegrationService)context.getBean("CustomerIntegrationService");		
		try {
			converter.convertFromObjectToXML(user,"input.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res = "";
		try {
			res = readFile("input.xml", StandardCharsets.UTF_8);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LOG.info("Attention"+res);
		Connection connection = connectionFactoryfromap.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Message message = session.createTextMessage(res);
		
		//TemporaryQueue inQueue = session.createTemporaryQueue();
		MessageProducer producer = session.createProducer(outQueue);
		//final MessageConsumer consumer  = session.createConsumer(inQueue);
		producer.send(message);
	/*	consumer.setMessageListener(new MessageListener() {
		       public void onMessage(Message message) {
		    	   LOG.info("SUCCESSSEND");
		    	  
		    	    TextMessage msg = null; 
		    	    try { 
		    	        if (message instanceof TextMessage) { 
		    	        	consumer.receive(100);
		    	            msg = (TextMessage) message; 
		    	            LOG.info("SUCCESSSEND");
		    	            consumer.receive();
		    	            // System.out.println("Reading message: " + msg.getText()); 
		    	        } else { 
		    	        	LOG.info("NOSUCCESS");
		    	             //System.out.println("Message is not a " + "TextMessage"); 
		    	        } 
		    	    //} catch (JMSException e) { 
		    	       // System.out.println("JMSException in onMessage(): " + e.toString()); 
		    	    } catch (Throwable t) { 
		    	        System.out.println("Exception in onMessage():" + t.getMessage()); 
		    	    }
		       }
		       
		});  */
		
		connection.close();	
		}
	
	public String GetMessages() throws JMSException{
		ApplicationContext context =  new ClassPathXmlApplicationContext("application-context.xml");
		ActiveMQConnectionFactory connectionFactoryfromap = (ActiveMQConnectionFactory)context.getBean("connectionFactoryfromap");
		ActiveMQQueue outQueue = (ActiveMQQueue)context.getBean("outQueue");
		
		Connection connection = connectionFactoryfromap.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		QueueBrowser browser = session.createBrowser(outQueue);
		Enumeration msgs = browser.getEnumeration();
		
		String res = "";
		LOG.info("Attention"+res);
		TextMessage msg = null; 
		ObjectMessage msg2 = null; 
		if ( !msgs.hasMoreElements() ) { 
			res = res + "No messages in queue";
		} else { 
		    while (msgs.hasMoreElements()) { 
		    	 
		    	//ObjectMessage objMessage = (ObjectMessage) msgs.nextElement();
		    	
		    	
		    	
		    	Message tempMsg = (Message) msgs.nextElement();
		    	if (tempMsg instanceof TextMessage) { 
    	            msg = (TextMessage) tempMsg; 
    	             if (msg.getText().equals(null)){
    	            	 LOG.info("Attention null");
    	            	 res = res + "Message: Empty" ; 
    	             }else{
    	            	 res = res + msg.getText();     //getText().getBytes(StandardCharsets.UTF_8);
    	            	 LOG.info("Attention"+res);
    	             }
    	            
		    	}else if (tempMsg instanceof ObjectMessage) { 
        	            msg2 = (ObjectMessage) tempMsg; 
        	             		       
        	            res = res + "ObjectMessage: " + msg2.toString(); 
		    	}else{
		    		res = res + "             NotTextMes";
		    	}
		        
		    	
		    	
		    }
		}
		connection.close();	
		return res;
	}
	
	public void deleteMessageFromQueue(String res) throws JMSException{		
			ApplicationContext context =  new ClassPathXmlApplicationContext("application-context.xml");
			ActiveMQConnectionFactory connectionFactoryfromap = (ActiveMQConnectionFactory)context.getBean("connectionFactoryfromap");
			ActiveMQQueue outQueue = (ActiveMQQueue)context.getBean("outQueue");
			//ActiveMQQueue inQueue = (ActiveMQQueue)context.getBean("inQueue");
			Connection connection = connectionFactoryfromap.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueBrowser browser = session.createBrowser(outQueue);
			Enumeration msgs = browser.getEnumeration();
			
			
			LOG.info("Attention"+res);
			TextMessage msg = null; 
			
			
			if ( !msgs.hasMoreElements() ) { 
				LOG.info("No messages in queue");
				//res = "No messages in queue";
			} else { 
				
			    while (msgs.hasMoreElements()) { 
			    	 
			    	Message tempMsg = (Message) msgs.nextElement();
			    	if (tempMsg instanceof TextMessage) { 
	    	            msg = (TextMessage) tempMsg; 
	    	             if (msg.getText().equals(null)){
	    	            	 LOG.info("Attention null");
	    	            	 //res = res + "Message: Empty" ; 
	    	             }else{
	    	            	 if (msg.getText().equals(res)){
	    	            		 LOG.info("SUCCESSDEL");
	    	            		 
	    	            		 MessageConsumer consumer = session.createConsumer(outQueue, "JMSMessageID='" +  msg.getJMSMessageID()  + "'");
	    	            		 //MessageConsumer consumer = session.createConsumer(outQueue);
	    	            		 
	    	            		 consumer.receive(1000);
	    	            		 
	    	            	     //LOG.info("SUCCESSDEL!!!"+ msgs.toString());
	    	            	     //deleteMessageFromQueue(res);
	    	            	 }
	    	            	 //res = res + msg.getText();     
	    	            	 
	    	             }
	    	            
			    	}else{
			    		//res = res + "             NotTextMes";
			    	}
			        
			    	
			    	
			    }
			}
				
			connection.close();
		}

}
