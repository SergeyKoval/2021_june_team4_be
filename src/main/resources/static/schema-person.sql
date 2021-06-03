DROP TABLE IF EXISTS person;
CREATE TABLE person
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR(30) NOT NULL,
    second_name  VARCHAR(30) NOT NULL,
    number_phone VARCHAR(30) ,
    email        VARCHAR(30),
    login        VARCHAR(30) NOT NULL,
    password     VARCHAR(30) NOT NULL,
    role         VARCHAR(10) NOT NULL
);
INSERT INTO person
VALUES (1, 'Jon', 'Herring', '380675553311', 'Herring@gmail.com', 'Herring', 'password', 'user' ),
       (2, 'Max', 'Malcolm', '380895544331', 'Malcolm@gmail.com',  'Malcolm', 'password', 'user'),
       (3, 'Jack', 'Jackson', '380683337799', 'Jackson@gmail.com', 'Jackson', 'admin', 'admin')