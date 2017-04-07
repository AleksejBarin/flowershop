create table tb_Order
(
		
	orderId Integer PRIMARY KEY AUTO_INCREMENT,

	userlogin  varchar(55),

	status  varchar(55),
	
	creationDate DATE,
	
	closingData DATE,
	
	sumOrder double  
	
);