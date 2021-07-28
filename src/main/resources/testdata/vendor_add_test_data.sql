INSERT INTO vendors (id, name, description, archived, contacts)
VALUES  ('93577f24-f68f-403e-aa04-0a60c3a445d6', 'Royal Canin', 'Really good pet food', false, '+380675432199')
ON CONFLICT (id) DO NOTHING;