BEGIN TRANSACTION;


INSERT INTO meal_type (meal_type) VALUES ('Breakfast');

INSERT INTO meal_type (meal_type) VALUES ('Lunch');

INSERT INTO meal_type (meal_type) VALUES ('Dinner');

INSERT INTO meal_type (meal_type) VALUES ('Snacks');

INSERT INTO meal_type (meal_type) VALUES ('Dessert');




INSERT INTO weekday (weekday) VALUES ('Sunday'); 

INSERT INTO weekday (weekday) VALUES ('Monday'); 

INSERT INTO weekday (weekday) VALUES ('Tuesday');

INSERT INTO weekday (weekday) VALUES ('Wednesday');

INSERT INTO weekday (weekday) VALUES ('Thursday');

INSERT INTO weekday (weekday) VALUES ('Friday');

INSERT INTO weekday (weekday) VALUES ('Saturday');     

--ROLLBACK TRANSACTION;
COMMIT TRANSACTION;