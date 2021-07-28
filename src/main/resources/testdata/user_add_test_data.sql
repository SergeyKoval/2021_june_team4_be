INSERT INTO countries (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d7', 'Ukraine')
ON CONFLICT (id) DO NOTHING;

INSERT INTO cities (id, name, country_id)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d8', 'Kyiv', '93577f24-f68f-403e-aa04-0a60c3a445d7')
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, login, password, first_name, last_name, email, phone, role, city_id)
VALUES ('91cf19dd-2af7-49ee-825e-94c0831ba1f1', 'Andreyev@gmail.com',
        '$2a$12$eJnv/OFi2baPDYN8uxxQiuoYN1iezTVx71bWXLo/cpuRHTW6mwbhy',
        'Andrey', 'Andreyev', 'Andreyev@gmail.com', '+380501113337', 'USER', '93577f24-f68f-403e-aa04-0a60c3a445d8')
ON CONFLICT (id) DO NOTHING;
