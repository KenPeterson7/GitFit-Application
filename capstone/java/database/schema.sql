BEGIN TRANSACTION;
DROP TABLE IF EXISTS users, profile, food, goal, meal, food_meal, workout, daily_intake;
DROP SEQUENCE IF EXISTS seq_profile_id, seq_food_id, seq_goal_id, seq_meal_id, seq_workout_id, seq_daily_intake_id;
CREATE TABLE users (
   user_id SERIAL NOT NULL,
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
   profile_id int NOT NULL DEFAULT nextval('seq_profile_id'),
   age int NOT NULL,
   height int NOT NULL,
   current_weight numeric NOT NULL,
   desired_weight numeric NOT NULL,
   birthday date NOT NULL,
   profile_pic varchar,
   current_star_streak int NOT NULL,
   high_start_streak int NOT NULL,
   username varchar(50) NOT NULL UNIQUE,
   displayname varchar(50),
   gender char(1),
   activity_level varchar(50) NOT NULL,
   CONSTRAINT PK_profile PRIMARY KEY (profile_id),
   CONSTRAINT FK_username FOREIGN KEY (username) REFERENCES users(username)
);
CREATE SEQUENCE seq_goal_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;
CREATE TABLE goal(
   goal_id int NOT NULL DEFAULT nextval('seq_goal_id'),
   profile_id int,
   daily_caloric_goal int NOT NULL,
   CONSTRAINT PK_goal PRIMARY KEY (goal_id),
   CONSTRAINT FK_profile_id FOREIGN KEY (profile_id) REFERENCES profile(profile_id)
   );

CREATE SEQUENCE seq_food_id
  INCREMENT BY 1
  START WITH 4001
  NO MAXVALUE;
CREATE TABLE food(
   food_id int NOT NULL DEFAULT nextval('seq_food_id'),
   food_name  varchar(50),
   food_type varchar(50),
   size varchar(50) NOT NULL,
   number_servings int NOT NULL,
   caloric_amount int NOT NULL,
   CONSTRAINT PK_food PRIMARY KEY (food_id)
   );
CREATE SEQUENCE seq_meal_id
  INCREMENT BY 1
  START WITH 5001
  NO MAXVALUE;
CREATE TABLE meal(
   meal_id int NOT NULL DEFAULT nextval('seq_meal_id'),
   profile_id int NOT NULL,
   meal_type varchar(50),
   meal_date date NOT NULL,
   CONSTRAINT PK_meal PRIMARY KEY (meal_id),
   CONSTRAINT FK_profile_id FOREIGN KEY (profile_id) REFERENCES profile(profile_id)
   );

CREATE TABLE food_meal(
   meal_id int NOT NULL,
   food_id int NOT NULL,
   CONSTRAINT PK_meal_food PRIMARY KEY (meal_id, food_id),
   CONSTRAINT FK_meal_id FOREIGN KEY (meal_id) REFERENCES meal(meal_id),
   CONSTRAINT FK_food_id FOREIGN KEY (food_id) REFERENCES food(food_id)
   );

CREATE SEQUENCE seq_daily_intake_id
  INCREMENT BY 1
  START WITH 6001
  NO MAXVALUE;
CREATE TABLE daily_intake(
   daily_intake_id int NOT NULL DEFAULT nextval('seq_daily_intake_id'),
   profile_id int NOT NULL,
   log_date date,
   total_calories int NOT NULL,
   CONSTRAINT PK_daily_intake PRIMARY KEY (daily_intake_id),
   CONSTRAINT FK_profile_id FOREIGN KEY (profile_id) REFERENCES profile(profile_id)
);

CREATE SEQUENCE seq_workout_id
  INCREMENT BY 1
  START WITH 7001
  NO MAXVALUE;
CREATE TABLE workout(
   workout_id int NOT NULL DEFAULT nextval('seq_workout_id'),
   profile_id int NOT NULL,
   name_of_workout varchar(100),
   type_of_workout varchar(100),
   duration numeric,
   workout_date date NOT NULL,
   calories_burned numeric NOT NULL,
   CONSTRAINT PK_workout PRIMARY KEY (workout_id),
   CONSTRAINT FK_profile_id FOREIGN KEY (profile_id) REFERENCES profile(profile_id)
   );

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user4','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
-- insert into Profile(age, height, current_weight, desired_weight, birthday, profile_pic, current_star_streak, high_start_streak, username, displayname, gender, activity_level)
-- values(25, 72, 225, 210, '1997-01-01', "", 1, 5, 'user', 'Tester', 'M', 'High');

insert into Profile(age, height, current_weight, desired_weight, birthday, profile_pic, current_star_streak, high_start_streak, username, displayname, gender, activity_level)
values(25, 72, 225, 210, '1997-01-01', '', 1, 5, 'user', 'Tester', 'M', 'High');

insert into Profile(age, height, current_weight, desired_weight, birthday, profile_pic, current_star_streak, high_start_streak, username, displayname, gender, activity_level)
values(35, 60, 280, 250, '1987-01-01', '', 3, 3, 'user2', 'Display Name Test', 'F', 'Low');

insert into Profile(age, height, current_weight, desired_weight, birthday, profile_pic, current_star_streak, high_start_streak, username, displayname, gender, activity_level)
values(22, 81, 262, 215, '2000-01-01', '', 0, 4, 'user3', 'Tech Fitness Display Test', 'M', 'Medium');

INSERT INTO food (food_name, food_type, size, number_servings, caloric_amount)
VALUES ('steak', 'meat', '50', 1, 500);
INSERT INTO food (food_name, food_type, size, number_servings, caloric_amount)
VALUES ('rice', 'starch', '25', 2, 240);
INSERT INTO food (food_name, food_type, size, number_servings, caloric_amount)
VALUES ('apple', 'fruit', '10', 1, 50);
INSERT INTO food (food_name, food_type, size, number_servings, caloric_amount)
VALUES ('pear', 'fruit', '10', 1, 40);

INSERT INTO meal (meal_type, meal_date, profile_id)
VALUES ('Breakfast', '2022-09-29', 2001);
INSERT INTO meal (meal_type, meal_date, profile_id)
VALUES ('Lunch', '2022-09-29', 2001);
INSERT INTO meal (meal_type, meal_date, profile_id)
VALUES ('Dinner', '2022-09-30', 2001);
INSERT INTO meal (meal_type, meal_date, profile_id)
VALUES ('Snacks', '2022-09-30', 2001);

insert into goal(profile_id, daily_caloric_goal) values(2001, 300);
insert into goal(profile_id, daily_caloric_goal) values(2002, 350);
insert into goal(profile_id, daily_caloric_goal) values(2003, 400);

insert into food_meal(meal_id, food_id) values(5001, 4001);
insert into food_meal(meal_id, food_id) values(5002, 4002);
insert into food_meal(meal_id, food_id) values(5003, 4003);
insert into food_meal(meal_id, food_id) values(5004, 4004);

insert into workout(profile_id, name_of_workout, type_of_workout, duration, workout_date, calories_burned) values(2001, 'Run', 'Cardio', 45, '2022-09-29', 250);
insert into workout(profile_id, name_of_workout, type_of_workout, duration, workout_date, calories_burned) values(2001, 'Full Body Workout', 'Strength Training', 90, '2022-09-30', 355.55);
insert into workout(profile_id, name_of_workout, type_of_workout, duration, workout_date, calories_burned) values(2002, 'Biking', 'Cardio', 120, '2022-09-29', 472.99);
insert into workout(profile_id, name_of_workout, type_of_workout, duration, workout_date, calories_burned) values(2002, 'Yoga', 'Anaerobic', 60, '2022-09-30', 321.2);


-- CREATE USER final_capstone_owner
-- WITH PASSWORD 'finalcapstone';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;
GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;
-- CREATE USER final_capstone_appuser
-- WITH PASSWORD 'finalcapstone';
GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;
GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;
-- ROLLBACK;
COMMIT TRANSACTION;