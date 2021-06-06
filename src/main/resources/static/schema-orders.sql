DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id    SERIAL PRIMARY KEY,
    price INT,
    serial_number CHAR,
    id_order  INT NOT NULL REFERENCES person(id) ON DELETE CASCADE
);
INSERT INTO orders
VALUES (1, 333, '12343er', 1),
       (2, 999, '54383fd', 1),
       (3, 2222, '56298rf', 2),
       (4, 333, '53849gf', 3),
       (5, 999, '24135fd', 1);
