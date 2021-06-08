DROP TABLE IF EXISTS persons;
CREATE TABLE persons
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    first_name   VARCHAR(30) NOT NULL,
    second_name  VARCHAR(30) NOT NULL,
    number_phone VARCHAR(20) ,
    email        VARCHAR(40),
    login        VARCHAR(30) NOT NULL,
    password     VARCHAR(30) NOT NULL,
    role         VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    price INT(20),
    serialnumber VARCHAR(80),
    id_person  INT NOT NULL REFERENCES persons(id) ON DELETE CASCADE
);
