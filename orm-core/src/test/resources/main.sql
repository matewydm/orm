CREATE TABLE person
(
Id Integer primary KEY ,
name VARCHAR (40),
lastname VARCHAR (40),
age INTEGER ,
birth_date DATE
);
CREATE TABLE address
(
  Id INTEGER PRIMARY KEY ,
  PERSON_ID Integer FOREIGN KEY REFERENCES person,
  STREET VARCHAR(40)
);