-- create test data
INSERT INTO countries (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d7', 'Ukraine');
INSERT INTO cities (id, name, country_id)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d8', 'Kyiv', '93577f24-f68f-403e-aa04-0a60c3a445d7');
INSERT INTO vendors (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d2', 'Dog stuff');
INSERT INTO vendor_locations (id, vendor_id, city_id, latitude, longitude)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d3', '93577f24-f68f-403e-aa04-0a60c3a445d2', '93577f24-f68f-403e-aa04-0a60c3a445d8', '50.48192656464188', '50.48192656464199');
INSERT INTO vendor_locations (id, vendor_id, city_id, latitude, longitude)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d4', '93577f24-f68f-403e-aa04-0a60c3a445d2', '93577f24-f68f-403e-aa04-0a60c3a445d8', '50.48192656464111', '50.48192656464122');
INSERT INTO categories (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d6', 'Dogs');
INSERT INTO tags (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d5', 'Pets');
INSERT INTO discounts (id, category_id, name, description, value, discount_type, promo, vendor_id, start_time, end_time, archived)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d1', '93577f24-f68f-403e-aa04-0a60c3a445d6', 'Toy for your dog', 'Dog stuff', 5, 'PERCENT', '1abcde1', '93577f24-f68f-403e-aa04-0a60c3a445d2', '2021-06-28 00:00:00.000000', '2021-07-20 00:00:00.000000', false)