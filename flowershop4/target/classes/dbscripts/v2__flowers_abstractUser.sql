create table tb_AbstractUser
(
	userlogin varchar(55) NOT NULL,
	
	username varchar(55),   

	password  varchar(55),

	phone  varchar(55),
	
	discount int,
	
	deposite double,
	
	city varchar(55),   

	street  varchar(55),

	building  varchar(55),
	
	PRIMARY KEY (userlogin)   
	
	

);