BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, profile, food, goal, meal, food_meal, workout, daily_intake;
DROP SEQUENCE IF EXISTS seq_user_id, seq_profile_id, seq_food_id, seq_goal_id, seq_meal_id, seq_workout_id, seq_daily_intake_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE SEQUENCE seq_profile_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;
CREATE TABLE profile (
	profile_id SERIAL,
	age int NOT NULL,
	height int NOT NULL,
	current_weight numeric NOT NULL,
	desired_weight numeric NOT NULL,
	birthday date NOT NULL,
	profile_pic BYTEA,
	current_star_streak int NOT NULL,
	high_start_streak int NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	CONSTRAINT PK_profile PRIMARY KEY (profile_id),
	CONSTRAINT FK_username FOREIGN KEY (username) REFERENCES users(username)
);

CREATE SEQUENCE seq_goal_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;
CREATE TABLE goal(
	goal_id SERIAL,
	profile_id SERIAL,
	daily_caloric_goal int NOT NULL,
	CONSTRAINT PK_goal PRIMARY KEY (goal_id),
	CONSTRAINT FK_profile_id FOREIGN KEY (profile_id) REFERENCES profile(profile_id)
	);
	
CREATE SEQUENCE seq_food_id
  INCREMENT BY 1
  START WITH 4001
  NO MAXVALUE;
CREATE TABLE food(
	food_id SERIAL,
	food_name  varchar(50),
	type varchar(50),
	size numeric NOT NULL,
	number_servings int NOT NULL,
	meal varchar(50),
	caloric_amount int NOT NULL,
	CONSTRAINT PK_food PRIMARY KEY (food_id)
	);

CREATE SEQUENCE seq_meal_id
  INCREMENT BY 1
  START WITH 5001
  NO MAXVALUE;
CREATE TABLE meal(
	meal_id SERIAL,
	meal_name varchar(50),
	CONSTRAINT PK_meal PRIMARY KEY (meal_id)
	);
	
CREATE TABLE food_meal(
	meal_id SERIAL,
	food_id SERIAL,
	CONSTRAINT PK_meal_food PRIMARY KEY (meal_id, food_id),
	CONSTRAINT FK_meal_id FOREIGN KEY (meal_id) REFERENCES meal(meal_id),
	CONSTRAINT FK_food_id FOREIGN KEY (food_id) REFERENCES food(food_id)
	);
	
CREATE SEQUENCE seq_daily_intake_id
  INCREMENT BY 1
  START WITH 6001
  NO MAXVALUE;
CREATE TABLE daily_intake(
	intake_id SERIAL,
	profile_id SERIAL,
	log_date date,
	total_calories int NOT NULL,
	CONSTRAINT PK_daily_intake PRIMARY KEY (intake_id),
	CONSTRAINT FK_profile_id FOREIGN KEY (profile_id) REFERENCES profile(profile_id)
);
	

CREATE SEQUENCE seq_workout_id
  INCREMENT BY 1
  START WITH 7001
  NO MAXVALUE;
CREATE TABLE workout(
	workout_id SERIAL, 
	name_of_workout varchar(100), 
	type_of_workout varchar(100),
	duration numeric,
	CONSTRAINT PK_workout PRIMARY KEY (workout_id)
	);

-- ROLLBACK;

COMMIT TRANSACTION;
