CREATE TABLE IF NOT EXISTS customer(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  contact varchar(255) DEFAULT NULL,
  telephone varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  remark text,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO customer VALUES(1, 'customer1', 'jack', '18202908562', '544533631@qq.com', null);
INSERT INTO customer VALUES(2, 'customer2', 'dan', '1231214', '1321231@qq.com', null);
