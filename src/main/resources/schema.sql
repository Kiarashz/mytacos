CREATE TABLE IF NOT EXISTS Ingredients (
	id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(50),
	itype varchar(10)
);

CREATE TABLE IF NOT EXISTS Tacos (
	id MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(50),
	createdAt Timestamp
);

CREATE TABLE IF NOT EXISTS Ingredients_Tacos (
	taco MEDIUMINT NOT NULL,
	ingredient MEDIUMINT NOT NULL,
	CONSTRAINT fk_taco FOREIGN KEY (taco) REFERENCES Tacos(id),
	CONSTRAINT fk_ingredient FOREIGN KEY (ingredient) REFERENCES Ingredients(id)
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

CREATE TABLE IF NOT EXISTS Orders_Tacos (
	taco MEDIUMINT NOT NULL,
	torder MEDIUMINT NOT NULL,
	CONSTRAINT fk_taco2 FOREIGN KEY (taco) REFERENCES Tacos(id),
	CONSTRAINT fk_order FOREIGN KEY (torder) REFERENCES Ingredients(id)
);
