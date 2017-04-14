package com.accenture.flowershop.messager;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.jms.JMSException;

import com.accenture.flowershop.model.entity.User;

public interface MessagerService {
	
	public void CreateCon() throws JMSException;
	
	public void CreateCon(String userlogin, int discount) throws JMSException;
	
	public void SendEntityUserMessage(User user)throws JMSException;
	
	public String GetMessages() throws JMSException;
	
	public  String readFile(String path, Charset encoding) throws IOException; 
	
	public void deleteMessageFromQueue(String res) throws JMSException;
			
	
}
