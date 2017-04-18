package com.accenture.flowershop.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.flowershop.model.entity.Flower;
import com.accenture.flowershop.model.entity.LegalEntityCustomer;
import com.accenture.flowershop.model.entity.User;
import com.accenture.flowershop.servlets.AddUser;

@Service
public class FlowerServiceImpl implements FlowerService{        

	@PersistenceContext	
	private EntityManager entityManager ;
	private static final Logger LOG = 	LoggerFactory.getLogger(FlowerServiceImpl.class);
			
	@Transactional
	public void increaseCountAllFlowersByOne(int count){
		List<Flower> flList = sortAllFlowersByLocalName(); 
		for (Flower flower : flList){
			flower.setFlowerCount(flower.getFlowerCount()+count);
			entityManager.merge(flower);
		}
	}
	
	public Flower findFlowerByLocalName(String localName){     	
    	Flower flower = entityManager.find(Flower.class, localName);
    	return flower;
    }	

    public List<Flower> sortAllFlowersByLocalName(){
    	LOG.debug("SortFlowersByLocalName");
    	 List<Flower>    	 flList = (List<Flower>) entityManager.createQuery(
    			"SELECT c from  Flower  AS c Order By localName" , Flower.class).getResultList();
    	 LOG.debug("SortFlowersByLocalName2");
    	 return flList;
    }
    
    public List<Flower> getFlowersWithPositiveCount(){
    	LOG.debug("SortFlowersByLocalName");
    	 List<Flower>    	 flList = (List<Flower>) entityManager.createQuery(
    			"SELECT c from  Flower  AS c WHERE c.flowerCount > 0" , Flower.class).getResultList();
    	
    	 LOG.debug("SortFlowersByLocalName2");
    	 return flList;
    }
    
    public Flower findFlowerByScientName(String scientName){
    	TypedQuery<Flower> q = (TypedQuery<Flower>) entityManager.createQuery(
    			"SELECT c from  Flower  AS c WHERE c.scientName = :scientName" , Flower.class);
    	q.setParameter("scientName", scientName);
    	List<Flower> resultList = q.getResultList();
    	if(resultList.isEmpty()){return null;}
    	return resultList.get(0);
    }
    
    @Transactional  
	public boolean addFlower(Flower flower){
		Flower exFlower = findFlowerByLocalName(flower.getLocalName());
        if (exFlower!=null) return false;
        entityManager.persist(flower);
		return true;
	}
	
	
	public List<Flower> findflowers() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public String SortOurFlowers(){  
     	//List<Flower> flList = new ArrayList<Flower>();
    	
    	List<Flower> flList = (List<Flower>) entityManager.createQuery(
    			"SELECT c from  Flower  AS c Order By localName" , Flower.class).getResultList();
    	String buttonName = new String();
    	String result = "<html><head><title>Insert title here</title></head><body>"
    			+ "<h1> Sort </h1>"
    			+ "<table border=1><caption>FlowersGrid</caption>"
    			+ "<tr><th>LocalName</th><th>ScientName</th><th>Count</th><th>ButtonBUY</th></tr>";    			
    	for (Flower fl : flList){ 
    		buttonName = fl.getLocalName();
    	    result = result+"<tr><td>"+fl.getLocalName()+"</td><td>"
    		+fl.getScientName()+"</td><td>"+fl.getFlowerCount()
    		+"</td><td><button onclick=myFunction(this)>"+buttonName+"</button></td></tr>";
       	}     	
    	result = result+"<input type=\"button\" onclick=\"history.back();\" value=\"Back\"/>"
    			+ "</table>"
    			+"<input type=\"submit\" name=\"win\" value=\"win\">"
    			+"<script>function myFunction(elmnt) {elmnt.style.color = 'red';}</script>"
    			+ "</body></html>";
    	
        return result;
    }

}
