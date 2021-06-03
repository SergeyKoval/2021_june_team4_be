DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id    SERIAL PRIMARY KEY,
    price INT,
    id_order  INT NOT NULL REFERENCES person(id) ON DELETE CASCADE
);
INSERT INTO orders
VALUES (1, 333, 1),
       (2, 999, 1),
       (3, 2222, 2),
       (4, 333, 3),
       (5, 999, 1);
