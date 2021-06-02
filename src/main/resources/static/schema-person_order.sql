DROP TABLE IF EXISTS person_order;
CREATE TABLE person_order
(
    person_id       FOREIGN KEY,
    order_id        FOREIGN KEY

);
INSERT INTO person_order
VALUES (1, 1),
       (1, 2),
       (3, 3),
       (4, 4),
       (1, 5);