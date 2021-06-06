DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id    BIGSERIAL PRIMARY KEY,
    price INT,
    serialnumber VARCHAR(80),
    id_person  INT NOT NULL REFERENCES persons(id) ON DELETE CASCADE
);
INSERT INTO orders
VALUES (1, 333, '12343er', 1),
       (2, 999, '54383fd', 1),
       (3, 2222, '56298rf', 2),
       (4, 333, '53849gf', 2),
       (5, 999, '24135fd', 1);
