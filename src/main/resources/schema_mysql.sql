/* 
 * CREATE database tacos IF NOT EXISTS character set = 'utf8';
 */

CREATE TABLE Ingredient ( 
	id MEDIUMINT NOT NULL AUTO_INCREMENT, 
	name varchar(50) NOT NULL, 
	ing_type varchar(3) NOT NULL,
	CONSTRAINT pk PRIMARY KEY (id)

);

CREATE TABLE Taco (
	id MEDIUMINT NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL, 
	CREATEdAt timestamp NOT NULL,
	CONSTRAINT pk PRIMARY KEY (id)
);

CREATE TABLE Orders (
  id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    deliveryName varchar(50) NOT NULL,
    deliveryStreet varchar(50) NOT NULL,
    deliveryCity varchar(50) NOT NULL,
    deliveryState varchar(2) NOT NULL,
    deliveryZip varchar(10) NOT NULL,
    ccNumber varchar(16) NOT NULL,
    ccExpiration varchar(5) NOT NULL,
    ccCVV varchar(3) NOT NULL,
    placedAt timestamp NOT NULL
);

CREATE TABLE Ingredient_Taco (
	tid varchar(10) NOT NULL,
	iid varchar(10) NOT NULL,
	CONSTRAINT fk_taco_id FOREIGN KEY (tid) REFERENCES Taco(id),
	CONSTRAINT fk_ingredient_id FOREIGN KEY (iid) REFERENCES Ingredient(id)
);


	
	