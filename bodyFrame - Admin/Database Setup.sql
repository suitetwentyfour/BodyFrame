-- For clearing the databases
-- DROP TABLE users;
-- DROP TABLE user_profile;
-- DROP TABLE user_card_information;
-- DROP TABLE payment_history;
-- DROP TABLE activities;
-- DROP TABLE message;
-- DROP TABLE notification;
-- DROP TABLE support_ticket;
-- DROP DATABASE "BodyFrame";

-- Initial Build of the databases
CREATE DATABASE "BodyFrame"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE users
(
    user_id character varying(50) PRIMARY KEY,
    password character varying(150) NOT NULL,
    user_type character varying(10) NOT NULL,
    email character varying(50) UNIQUE,
    assigned_to character varying(50),
    member_since date NOT NULL default now(),
    paid_until date NOT NULL default now(),
    last_sign_in date NOT NULL default now()
);

CREATE TABLE user_profile
(
    user_id character varying(50) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    birth_day date NOT NULL,
    address character varying(150) NOT NULL,
    phone_number character (12) NOT NULL,
    sex character (1) NOT NULL,
    goal character varying(50) NOT NULL,
    weight integer NOT NULL,
    height integer NOT NULL,
    profile_picture text
);

CREATE TABLE user_card_information
(
  user_id character varying(50) NOT NULL,
  first_name character varying(50) NOT NULL,
  last_name character varying(150) NOT NULL,
  card_number character varying(19) NOT NULL,
  expiration character (5) NOT NULL,
  CVV smallint
);

CREATE TABLE payment_history
(
  payment_amount decimal NOT NULL,
  payee character varying(50) NOT NULL,
  payer character varying(50) NOT NULL,
  payment_id serial,
  memo text,
  payment_date timestamp NOT NULL default now()
);

CREATE TABLE activities
(
  activity_name character varying(50),
  activity_type character varying(10),
  activity_description character varying(50),
  due_date date NOT NULL default now(),
  media_link text,
  assigner character varying(50) NOT NULL,
  assignee character varying(50) NOT NULL,
  memo text,
  activity_id serial,
  activity_time integer,
  calories integer,
  completed boolean NOT NULL Default 'FALSE'
);

CREATE TABLE message
(
  user_id character varying(50) NOT NULL,
  message_from character varying(50) NOT NUll,
  message_date timestamp NOT NULL default now(),
  message text NOT NULL,
  message_id serial,
  read boolean NOT NULL Default 'FALSE'
);

CREATE TABLE notification
(
  user_id character varying(50) NOT NULL,
  notificaton_date timestamp NOT NULL default now(),
  notification text NOT NUll,
  notification_id serial,
  read boolean NOT NULL Default 'FALSE'
);

CREATE TABLE support_ticket
(
  user_id character varying(50) NOT NULL,
  ticket_id serial,
  ticket_information text NOT NULL,
  status character varying(50) NOT NULL default 'OPEN',
  ticket_date timestamp NOT NULL default now()
);

-- Insert the admin random nonsense password needs to be reset by the admin
insert into users(user_id, password, user_type, email) values('0X000000', 'password', 'ADMIN', 'adam@suitetwentyfour.com');
insert into users(user_id, password, user_type, email) values('0X000001', 'password', 'TRAINER', 'trainer@gmail.com');
insert into users(user_id, password, user_type, email, assigned_to) values('0X000002', 'password', 'USER', 'user@gmail.com', '0X000001');
insert into user_profile(user_id, first_name, last_name, birth_day, address, phone_number, sex, goal, weight, height)
  values('0X000002', 'test', 'smith', '01/01/1990', '100 main st', '505-555-5555', 'M', 'Lose Weight', 120, 150);
