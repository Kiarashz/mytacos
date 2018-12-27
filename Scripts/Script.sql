USE tacos;

SELECT * FROM Ingredients;

SELECT t.name, i.name 
FROM Ingredients i 
	JOIN Ingredients_Tacos it USING (iid) 
	JOIN Tacos t USING (tid);