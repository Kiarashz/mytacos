CREATE TABLE IF NOT EXISTS Ingredients (
	iid MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(50),
	itype varchar(10)
);

CREATE TABLE IF NOT EXISTS Tacos (
	tid MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(50),
	createdAt Timestamp
);

CREATE TABLE IF NOT EXISTS Ingredients_Tacos (
	tid MEDIUMINT NOT NULL,
	iid MEDIUMINT NOT NULL,
	CONSTRAINT fk_taco FOREIGN KEY (tid) REFERENCES Tacos(tid),
	CONSTRAINT fk_ingredient FOREIGN KEY (iid) REFERENCES Ingredients(iid)
);
 
CREATE TABLE IF NOT EXISTS Orders (
  oid MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
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

CREATE TABLE IF NOT EXISTS Orders_Tacos (
	tid MEDIUMINT NOT NULL,
	oid MEDIUMINT NOT NULL,
	CONSTRAINT fk_taco2 FOREIGN KEY (tid) REFERENCES Tacos(tid),
	CONSTRAINT fk_order FOREIGN KEY (oid) REFERENCES Orders(oid)
);
