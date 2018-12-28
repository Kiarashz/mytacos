DROP DATABASE tacos;
CREATE DATABASE tacos character set UTF8mb4 collate utf8mb4_bin; 
USE tacos;

SELECT * FROM Ingredients;

SELECT t.name, i.name 
FROM Ingredients i 
	JOIN Ingredients_Tacos it USING (iid) 
	JOIN Tacos t USING (tid);