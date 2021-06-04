BEGIN TRANSACTION;


INSERT INTO meals (meal_type) 
VALUES('breakfast');

INSERT INTO meals (meal_type) 
VALUES('lunch');

INSERT INTO meals (meal_type) 
VALUES('dinner');

INSERT INTO meals (meal_type) 
VALUES('snack');

INSERT INTO meals (meal_type) 
VALUES('dessert');

COMMIT TRANSACTION;
--ROLLBACK;