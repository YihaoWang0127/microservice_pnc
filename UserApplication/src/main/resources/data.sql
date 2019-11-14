DROP TABLE IF EXISTS users;
 
CREATE TABLE users (
  id INT NOT NULL auto_increment,
  name VARCHAR(250) NOT NULL,
  salary INT NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  PRIMARY KEY(id)
);
 
INSERT INTO users (name, salary, email) VALUES
  ('David', 15000, 'david@hotmail.com'),
  ('Bill', 20000, 'bill@hotmail.com'),
  ('Mary', 20000, 'mary@hotmail.com'),
  ('Richard', 10000, 'richard@hotmail.com'),
  ('Jase', 8000, 'jase@hotmail.com');