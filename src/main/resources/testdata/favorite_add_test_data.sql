-- create test data
INSERT INTO countries (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d7', 'Ukraine')
ON CONFLICT (id) DO NOTHING;

INSERT INTO cities (id, name, country_id)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d8', 'Kyiv', '93577f24-f68f-403e-aa04-0a60c3a445d7')
ON CONFLICT (id) DO NOTHING;

INSERT INTO vendors (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d2', 'Dog stuff')
ON CONFLICT (id) DO NOTHING;

INSERT INTO vendor_locations (id, vendor_id, city_id, latitude, longitude)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d3', '93577f24-f68f-403e-aa04-0a60c3a445d2',
         '93577f24-f68f-403e-aa04-0a60c3a445d8', '50.48192656464188', '50.48192656464199')
ON CONFLICT (id) DO NOTHING;

INSERT INTO vendor_locations (id, vendor_id, city_id, latitude, longitude)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d4', '93577f24-f68f-403e-aa04-0a60c3a445d2',
         '93577f24-f68f-403e-aa04-0a60c3a445d8', '50.48192656464111', '50.48192656464122')
ON CONFLICT (id) DO NOTHING;

INSERT INTO categories (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d6', 'Dogs')
ON CONFLICT (id) DO NOTHING;

INSERT INTO tags (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d5', 'Pets')
ON CONFLICT (id) DO NOTHING;

INSERT INTO discounts (id, category_id, name, description, value, discount_type, promo, vendor_id, start_time, end_time, archived)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d1', '93577f24-f68f-403e-aa04-0a60c3a445d6', 'Toy for your dog',
         'Dog stuff', 5, 'PERCENT', '1abcde1', '93577f24-f68f-403e-aa04-0a60c3a445d2', '2021-06-28 00:00:00.000000', '2021-07-20 00:00:00.000000', false)
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, login, password, first_name, last_name, email, phone, role, city_id)
VALUES ('91cf19dd-2af7-49ee-825e-94c0831ba1f1', 'Andreyev@gmail.com',
        '$2a$12$eJnv/OFi2baPDYN8uxxQiuoYN1iezTVx71bWXLo/cpuRHTW6mwbhy',
        'Andrey', 'Andreyev', 'Andreyev@gmail.com', '+380501113337', 'USER', '93577f24-f68f-403e-aa04-0a60c3a445d8')
ON CONFLICT (id) DO NOTHING;

INSERT INTO favorites (id, user_id, discount_id)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a4a1f3', '91cf19dd-2af7-49ee-825e-94c0831ba1f1', '93577f24-f68f-403e-aa04-0a60c3a445d1')
ON CONFLICT (id) DO NOTHING;

INSERT INTO coupons (id, user_id, discount_id, date)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a4a1c3', '91cf19dd-2af7-49ee-825e-94c0831ba1f1', '93577f24-f68f-403e-aa04-0a60c3a445d1'
, '2021-07-26 00:00:00.000000')
ON CONFLICT (id) DO NOTHING;