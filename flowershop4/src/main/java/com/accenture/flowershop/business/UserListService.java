package com.accenture.flowershop.business;

import java.util.List;


import com.accenture.flowershop.model.entity.IndividualCustomer;
import com.accenture.flowershop.model.entity.LegalEntityCustomer;
import com.accenture.flowershop.model.entity.OrderItem;
import com.accenture.flowershop.model.entity.OrderUser;
import com.accenture.flowershop.model.entity.User;
import com.accenture.flowershop.model.entity.UserShopCart;



public interface UserListService{	

	boolean orderOneFlowerByPrice10(String flowerLocalName,String userLogin);
	
	List<UserShopCart> findUserShopCart (String userLogin);

	User findUser(String userLogin);
	
	User findUser2(String userLogin);
	
	boolean addUser(User user);
	
	boolean addOrderUser(OrderUser orderUser);
	
	boolean loginUser(String login, String password);
	
	void addIndividualUser(IndividualCustomer newIndividualCustomer);
	
	void addLegalEntityCustomer(LegalEntityCustomer newLegalEntityCustomer);
	
	void addnewUserShopCart(UserShopCart newUserShopCart);
	
	List<LegalEntityCustomer> findCustomerByInn(String inn);
	
	List<IndividualCustomer> findCustomerBySurname(String surname);

	List<User> findCustomerByUserlogin(String userlogin);
	
	List<User> findCustomerByInn2(String inn);
	
	List<OrderUser> findOrder1(String userLogin);
	
	List<OrderUser> findOrder2(String userLogin);
	
	boolean addOrderItem(OrderItem newOrderItem);

}
