INSERT INTO categories (id, name)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d6', 'Dogs')
ON CONFLICT (id) DO NOTHING;