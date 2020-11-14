DROP TABLE IF EXISTS RESERVATION;
DROP TABLE IF EXISTS RESTAURANT_TABLE;
DROP TABLE IF EXISTS RESTAURANT;

CREATE TABLE RESTAURANT(
  RESTAURANT_ID INT AUTO_INCREMENT  PRIMARY KEY,
  RESTAURANT_NAME VARCHAR(32) NOT NULL
);
 
ALTER TABLE RESTAURANT ADD CONSTRAINT UNIQUE_NAME UNIQUE(RESTAURANT_NAME);

CREATE TABLE RESTAURANT_TABLE(
  TABLE_ID INT AUTO_INCREMENT  PRIMARY KEY,
  SEATING_CAPACITY INT NOT NULL,
  RESTAURANT_ID INT NOT NULL,
  foreign key (RESTAURANT_ID) references RESTAURANT(RESTAURANT_ID)
);

CREATE TABLE RESERVATION(
  RESERVATION_ID INT AUTO_INCREMENT  PRIMARY KEY,
  TABLE_ID INT NOT NULL,
  START TIMESTAMP NOT NULL,
  END TIMESTAMP NOT NULL,
  foreign key (TABLE_ID) references RESTAURANT_TABLE(TABLE_ID)
);