package com.accenture.flowershop.business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.flowershop.model.entity.Flower;
import com.accenture.flowershop.model.entity.IndividualCustomer;
import com.accenture.flowershop.model.entity.LegalEntityCustomer;
import com.accenture.flowershop.model.entity.OrderItem;
import com.accenture.flowershop.model.entity.OrderUser;
import com.accenture.flowershop.model.entity.User;
import com.accenture.flowershop.model.entity.UserShopCart;
import com.google.gson.Gson;
@Service
public class UserListImpl implements UserListService{		
   
	private final static Logger LOG = LoggerFactory.getLogger(UserListImpl.class);
	
    @PersistenceContext
    private EntityManager entityManager;
    
    
    @Transactional
    public List<UserShopCart> findUserShopCart (String userLogin){
    	//User user = entityManager.find(User.class, userLogin); 
    	//return user.getShopCard();
    	
		User user = entityManager.find(User.class, userLogin);
    	TypedQuery<UserShopCart> q = (TypedQuery<UserShopCart>) entityManager.createQuery(
    			"SELECT c from  UserShopCart  AS c WHERE c.user = :user" , UserShopCart.class);
    	q.setParameter("user", user);
    	List<UserShopCart> resultList = q.getResultList();
    	return resultList;
    }
    
    @Transactional
    public User findUser(String userLogin){  

    	User user = entityManager.find(User.class, userLogin);   //,LockModeType.PESSIMISTIC_WRITE);
    	
    	return user;
    } 
    public User findUser2(String userLogin){   
    	//List<User> list = (List<User> )repository.findAll(CustomerRepository.P.namePredicate(name));

    	User user = entityManager.find(User.class, userLogin);   //,LockModeType.PESSIMISTIC_WRITE);
    	return user;
    }     
	@Transactional   
    public boolean addOrderItem(OrderItem newOrderItem){
       	entityManager.persist( newOrderItem);        	
       	LOG.info("Зарегистрировался новый заказ на цену: {}", newOrderItem.getPrice());
       	return true;
    }
	
	@Transactional   
    public void addnewUserShopCart(UserShopCart newUserShopCart){
       	entityManager.persist( newUserShopCart);        	
       	LOG.info("Цветок  " +newUserShopCart.getFlowerName()+" куплен пользователем"+newUserShopCart.getUser().getUserLogin() );
       	//return true;
    }
	
	@Transactional   
    public boolean orderOneFlowerByPrice10(String flowerLocalName, String userlogin){
		//entityManager.flush();
		User user = entityManager.find(User.class, userlogin);
		Flower flower = entityManager.find(Flower.class, flowerLocalName);
		entityManager.lock(flower, LockModeType.PESSIMISTIC_WRITE);
		entityManager.lock(user, LockModeType.PESSIMISTIC_WRITE);		
		
		UserShopCart newUserShopCart = new UserShopCart(user,flower.getLocalName(),1);
		addnewUserShopCart(newUserShopCart);
		OrderItem newOrderItem = new OrderItem(flower,1,10);
		addOrderItem(newOrderItem);
		OrderUser newOrderUser = new OrderUser(user);
		addOrderUser(newOrderUser);
		flower.descrease();
		user.setDeposite(user.getDeposite()-10);
		entityManager.merge(flower);
		entityManager.merge(user);
		
		
		entityManager.lock(flower, LockModeType.NONE);
		entityManager.lock(user, LockModeType.NONE);
		//entityManager.refresh(LockModeType.PESSIMISTIC_WRITE);
       	return true;
    }
	
	@Transactional   
    public boolean addUser(User newUser){
        User exUser = findUser(newUser.getUserLogin());
        if (exUser!=null) return false;
        
        if ((newUser.getPassword()!=null)&&(!newUser.getPassword().isEmpty())){
        	entityManager.persist( newUser );        	
        	LOG.info("Зарегистрировался новый пользователь: {}", newUser.getUserLogin());
        	return true;
        }             
        return false;
    }
	
	@Transactional   
    public void addLegalEntityCustomer(LegalEntityCustomer newLegalEntityCustomer){
		entityManager.persist( newLegalEntityCustomer ); 
	}


	@Transactional
	public boolean addOrderUser(OrderUser orderUser){
		entityManager.persist( orderUser );
		return false;
	}
	
	
	@Transactional   
    public void addIndividualUser(IndividualCustomer newIndividualCustomer){
		entityManager.persist( newIndividualCustomer ); 
	}

	public List<OrderUser> findOrder1(String userLogin){
		User user = entityManager.find(User.class, userLogin);
    	return user.getOrderList();
	}	
	public List<OrderUser> findOrder2(String userLogin){
		User user = entityManager.find(User.class, userLogin);
    	TypedQuery<OrderUser> q = (TypedQuery<OrderUser>) entityManager.createQuery(
    			"SELECT c from  OrderUser  AS c WHERE c.user = :user" , OrderUser.class);
    	q.setParameter("user", user);
    	List<OrderUser> resultList = q.getResultList();
		return resultList;
	}		
	
	public List<LegalEntityCustomer> findCustomerByInn(String inn){		
    	TypedQuery<LegalEntityCustomer> q = (TypedQuery<LegalEntityCustomer>) entityManager.createQuery(
    			"SELECT c from  LegalEntityCustomer  AS c WHERE c.inn = :inn" , LegalEntityCustomer.class);
    	q.setParameter("inn", inn);
    	List<LegalEntityCustomer> resultList = q.getResultList();
		return resultList;
	}

	public List<IndividualCustomer> findCustomerBySurname(String surname){		
    	TypedQuery<IndividualCustomer> q = (TypedQuery<IndividualCustomer>) entityManager.createQuery(
    			"SELECT c from  IndividualCustomer  AS c WHERE c.surname = :surname" , IndividualCustomer.class);
    	q.setParameter("surname", surname);
    	List<IndividualCustomer> resultList = q.getResultList();
		return resultList;
	}

	public List<User> findCustomerByUserlogin(String userlogin){		
    	TypedQuery<User> q = (TypedQuery<User>) entityManager.createQuery(
    			"SELECT c from  User  AS c WHERE c.userlogin = :userlogin" , User.class);
    	q.setParameter("userlogin", userlogin);
    	List<User> resultList = q.getResultList();
		return resultList;
	}	
	
	public List<User> findCustomerByInn2(String inn){		
    	TypedQuery<User> q = (TypedQuery<User>) entityManager.createQuery(
    			"SELECT c from  User  AS c WHERE c.username IN (SELECT name from  LegalEntityCustomer  AS e WHERE e.inn = :inn)" , User.class);
    	
    	q.setParameter("inn", inn);
    	List<User> resultList = q.getResultList();
		return resultList;
	}	
	
	
	public boolean loginUser(String login, String password) {
		User user = entityManager.find(User.class, login);
		if (!user.getPassword().equals(password)){return false;}
		
		return true;
	}
	
}
