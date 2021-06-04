BEGIN TRANSACTION;


DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id CASCADE;
DROP TABLE IF EXISTS recipes CASCADE;
DROP TABLE IF EXISTS daily_plan CASCADE;
DROP TABLE IF EXISTS ingredients CASCADE;
DROP TABLE IF EXISTS measurement_type CASCADE;
DROP TABLE IF EXISTS recipe_ingredients CASCADE;
DROP TABLE IF EXISTS meals CASCADE;
DROP TABLE IF EXISTS daily_plan_meals CASCADE;
DROP TABLE IF EXISTS meals_recipes CASCADE;
DROP TABLE IF EXISTS meal_plan CASCADE;
DROP TABLE IF EXISTS meal_type CASCADE;
DROP TABLE IF EXISTS weekday CASCADE;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');



CREATE TABLE recipes (
        recipe_id serial NOT NULL,
        user_id int NOT NULL,
        name varchar(200), 
        servings numeric,
        directions varchar(10000),
 
CONSTRAINT pk_recipe_id PRIMARY KEY (recipe_id), 
CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
       
);



CREATE TABLE meal_plan (
        meal_plan_id serial NOT NULL,
        user_id int NOT NULL,
        mp_name varchar(80),

CONSTRAINT pk_meal_plan_id PRIMARY KEY(meal_plan_id),
CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);



CREATE TABLE weekday (
        weekday_id serial NOT NULL,
        weekday varchar(15) NOT NULL,

CONSTRAINT pk_weekday_id PRIMARY KEY(weekday_id)
);



CREATE TABLE daily_plan (
        dp_id serial NOT NULL, 
        meal_plan_id int NOT NULL,
        weekday_id int NOT NULL,

CONSTRAINT pk_dp_id PRIMARY KEY (dp_id),
CONSTRAINT fk_meal_plan_id FOREIGN KEY (meal_plan_id) REFERENCES meal_plan(meal_plan_id), 
CONSTRAINT fk_weekday_id FOREIGN KEY (weekday_id) REFERENCES weekday(weekday_id)
);



CREATE TABLE ingredients (
        ingredient_id serial NOT NULL,
        name varchar(300) NOT NULL,
        
CONSTRAINT pk_ingredient_id PRIMARY KEY (ingredient_id)
);



CREATE TABLE measurement_type (
        mt_id serial NOT NULL,
        mt_name varchar(60) NOT NULL,

CONSTRAINT pk_mt_id PRIMARY KEY (mt_id)
);



CREATE TABLE recipe_ingredients (
        
        recipe_ingr_id serial NOT NULL,
        recipe_id int NOT NULL, 
        ingredient_id int NOT NULL,
        mt_type_id int, 
        mt_quantity numeric,
        
CONSTRAINT pk_recipe_ingr_id PRIMARY KEY (recipe_ingr_id),        
CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
CONSTRAINT fk_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id),
CONSTRAINT fk_mt_type_id FOREIGN KEY (mt_type_id) REFERENCES measurement_type(mt_id)

);


CREATE TABLE meal_type (
        meal_type_id serial NOT NULL,
        meal_type varchar(40),
        
CONSTRAINT pk_meal_type_id PRIMARY KEY (meal_type_id)

);



CREATE TABLE meals (
        meal_id serial NOT NULL,
        dp_id int NOT NULL,
        meal_type_id int NOT NULL,
        
CONSTRAINT pk_meal_id PRIMARY KEY (meal_id),
CONSTRAINT dp_id FOREIGN KEY (dp_id) REFERENCES daily_plan(dp_id),
CONSTRAINT meal_type_id FOREIGN KEY (meal_type_id) REFERENCES meal_type(meal_type_id)
);



--CREATE TABLE daily_plan_meals (
--        dp_meals_id serial NOT NULL,
--        dp_id int NOT NULL, 
--        meal_id int NOT NULL,

--CONSTRAINT pk_dp_meals_id PRIMARY KEY (dp_meals_id), 
--CONSTRAINT fk_dp_id FOREIGN KEY (dp_id) REFERENCES daily_plan(dp_id),
--CONSTRAINT fk_meal_id FOREIGN KEY (meal_id) REFERENCES meals(meal_id)      
--);


CREATE TABLE meals_recipes (
        meal_recipe_id serial NOT NULL,
        meal_id int NOT NULL,
        recipe_id int NOT NULL,
        
CONSTRAINT pk_meal_recipe_id PRIMARY KEY (meal_recipe_id),
CONSTRAINT fk_meal_id FOREIGN KEY (meal_id) REFERENCES meals(meal_id),
CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)  
);

--ROLLBACK TRANSACTION;
COMMIT TRANSACTION;
