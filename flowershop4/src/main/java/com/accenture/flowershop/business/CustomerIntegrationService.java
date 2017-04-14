package com.accenture.flowershop.business;


import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

public class CustomerIntegrationService {

	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public Marshaller getMarshaller() {
		return marshaller;
	}
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public void convertFromObjectToXML(Object object, String filepath)
		throws IOException {
			FileOutputStream os = null;
		try {
			os = new FileOutputStream(filepath);
			getMarshaller().marshal(object, new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	public Object convertFromXMLToObject(String xmlfile) throws IOException {
			FileInputStream is = null;
		try {
			is = new FileInputStream(xmlfile);
			return getUnmarshaller().unmarshal(new StreamSource(is));
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	public Object convertFromXMLToObject(TextMessage message) throws IOException {
		
    	Properties prop = new Properties();
    	String propFileName = "conf.properties";    	
    	InputStream  input = new FileInputStream("conf.properties");

    	try {          		
    		prop.load(input); 
    		propFileName = prop.getProperty("fileForMarshall");
    	}catch (IOException ex) {
    		ex.printStackTrace();
    	}
    	
    	try {
    	    BufferedWriter out = new BufferedWriter(new FileWriter(propFileName));
    	    try {
				out.write(message.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    	    out.close();
    	}
    	catch (IOException e)
    	{
    	    System.out.println("Exception ");

    	}
    	
    	return convertFromXMLToObject(propFileName);
    	
	}
	
}


