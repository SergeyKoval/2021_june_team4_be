INSERT INTO countries (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d7', 'Ukraine')
ON CONFLICT (id) DO NOTHING;

INSERT INTO cities (id, name, country_id)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d8', 'Kyiv', '93577f24-f68f-403e-aa04-0a60c3a445d7')
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, login, password, first_name, last_name, email, phone, role, city_id)
VALUES ('3633f3cf-7208-4d67-923d-ce6b2cec29e3', 'adminov@mail.com',
        '$2a$12$fDSF9o5Yoc.Pu5XmcmXcx.JYUgv/PwEUEJB.EX94PCjz1IOO7pIYK',
        'Petro', 'Adminov', 'adminov@mail.com', '+380501113337', 'ADMIN', '93577f24-f68f-403e-aa04-0a60c3a445d8')
    ON CONFLICT (id) DO NOTHING;