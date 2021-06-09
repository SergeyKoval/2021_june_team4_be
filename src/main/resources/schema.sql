DROP TABLE IF EXISTS persons CASCADE;
CREATE TABLE persons
(
    id           BIGSERIAL PRIMARY KEY ,
    first_name   VARCHAR(30) NOT NULL,
    second_name  VARCHAR(30) NOT NULL,
    telephone    VARCHAR(20) ,
    email        VARCHAR(40),
    login        VARCHAR(30) NOT NULL,
    password     VARCHAR(30) NOT NULL,
    role         VARCHAR(10) NOT NULL
);


DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id    BIGSERIAL PRIMARY KEY,
    price INT,
    serial_number VARCHAR(80),
    person_id INTEGER REFERENCES persons(id) ON DELETE CASCADE ON UPDATE CASCADE
);




