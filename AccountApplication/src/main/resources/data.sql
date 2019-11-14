DROP TABLE IF EXISTS account;
 
CREATE TABLE account (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  amount INT NOT NULL,
  user_id INT NOT NULL
);
 
INSERT INTO account (amount, user_id) VALUES
  (150000, 1),
  (250000, 4),
  (240000, 5),
  (110000, 4),
  (82000, 3);