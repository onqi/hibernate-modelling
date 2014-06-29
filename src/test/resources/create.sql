use test;

CREATE TABLE company (id INT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL,
     PRIMARY KEY (id)
) ENGINE=InnoDb;

CREATE TABLE unit (id INT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL,
     company_id INT,
     PRIMARY KEY (id),
     FOREIGN KEY (company_id) REFERENCES company(id)
) ENGINE=InnoDb;

CREATE TABLE user (id INT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL,
     unit_id INT,
     PRIMARY KEY (id),
     FOREIGN KEY (unit_id) REFERENCES unit(id)
) ENGINE=InnoDb;

CREATE TABLE device (id INT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL,
     user_id INT,
	  PRIMARY KEY (id),
     FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDb;

INSERT INTO company(id, name) values (1, "one");
INSERT INTO company(id, name) values (2, "two");
INSERT INTO company(id, name) values (3, "three");
INSERT INTO company(id, name) values (4, "four");
INSERT INTO company(id, name) values (5, "five");

INSERT INTO unit(id, name, company_id) values(1, "one", 1);
INSERT INTO unit(id, name, company_id) values(2, "two", 2);
INSERT INTO unit(id, name, company_id) values(3, "three", 3);
INSERT INTO unit(id, name, company_id) values(4, "four", 4);
INSERT INTO unit(id, name, company_id) values(5, "five", 1);

INSERT INTO user(id, name, unit_id) values(1, "one", 1);
INSERT INTO user(id, name, unit_id) values(2, "two", 2);
INSERT INTO user(id, name, unit_id) values(3, "three", 3);
INSERT INTO user(id, name, unit_id) values(4, "four", 4);
INSERT INTO user(id, name, unit_id) values(5, "five", 1);
INSERT INTO user(id, name, unit_id) values(6, "six", 5);

INSERT INTO device(id, name, user_id) values(1, "one", 1);
INSERT INTO device(id, name, user_id) values(2, "two", 2);
INSERT INTO device(id, name, user_id) values(3, "three", 3);
INSERT INTO device(id, name, user_id) values(4, "four", 4);
INSERT INTO device(id, name, user_id) values(5, "five", 1);
INSERT INTO device(id, name, user_id) values(6, "six", 1);
INSERT INTO device(id, name, user_id) values(7, "seven", 6);
INSERT INTO device(id, name, user_id) values(8, "eight", 6);

