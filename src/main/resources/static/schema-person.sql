DROP TABLE IF EXISTS person;
CREATE TABLE person
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR(30) NOT NULL,
    second_name  VARCHAR(30) NOT NULL,
    number_phone VARCHAR(30) ,
    email        VARCHAR(30),
    birth_day    DATE    DEFAULT null,
    is_woman     BOOLEAN DEFAULT TRUE


);
INSERT INTO person
VALUES (1, 'Test', 'Herring', '380675553311', 'Herring@gmail.com', '1991-01-20', TRUE),
       (2, 'Test', 'Malcolm', '380895544331', 'Malcolm@gmail.com', '1993-06-29', FALSE),
       (3, 'Test', 'Jackson', '380683337799', 'Jackson@gmail.com', '1901-06-11', FALSE)