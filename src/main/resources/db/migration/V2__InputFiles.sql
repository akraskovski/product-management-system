INSERT INTO "user" (id, username, password) VALUES (1, 'admin', 'admin');
INSERT INTO "user" (id, username, password) VALUES (8, 'stock_manager', 'password1');
INSERT INTO "user" (id, username, password) VALUES (18, 'store_manager', 'password1');
INSERT INTO "user" (id, username, password) VALUES (19, 'managed_profile', 'user_stock_store');
SELECT pg_catalog.setval('user_id_seq', 20, true);

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_STOCK_MANAGER');
INSERT INTO authority (id, name) VALUES (3, 'ROLE_STORE_MANAGER');
SELECT pg_catalog.setval('authority_id_seq', 4, false);

INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (29, 'Королевство Сна Rosemary 200x200 (орех серый)', 2830, 'Кровать', 'классическая, 200x200 см, изголовье дерево, основание ортопедическое', '7ad1f054-ad37-48ff-b841-8da1cff78f31', 200, 200, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (30, 'Halmar Ronald 160/200', 210, 'Обеденный стол', 'обеденный стол, раздвижной, прямоугольная форма, 1600×900 мм, материал: МДФ (столешница), МДФ (опоры)', '384270f3-7a81-40d8-ac51-25334c121d34', 160, 90, 11);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (31, 'Мебель-класс Лидер (венге/дуб шамони) [МКД-211]', 75, 'Компьютерный стол', 'компьютерный стол, классический, прямоугольная форма, 1200×600 мм, материал: ЛДСП (столешница), ЛДСП (опоры)', 'ba7f59fa-6ae5-40f4-969e-a7fd845656e4', 120, 60, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (32, 'Signal Colin (серый)', 38, 'Обеденный стул', 'обеденный стул, ткань (сидение), металл (каркас)', 'ada33f09-b144-4505-a12b-f729a51fcf9b', 52, 45, 2.7999999999999998);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (19, 'Пинскдрев Олимп 5 (угловой)', 320.80000000000001, 'Диван', 'угловой, 3-местный, механизм "еврокнижка", настил пружинный блок "боннель", длина 247 см, спальное место 215x147 см', '508b63f1-bf29-40e5-9149-ef7534d961f2', 215, 147, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (20, 'Пинскдрев Редфорд (угловой)', 1745, 'Диван', 'угловой, 6-местный, механизм "раскладушка", настил пенополиуретан (поролон), длина 410 см, спальное место 198x135 см', '68ecc9b0-fa56-4d8a-bb52-e07886d9ecdb', 410, 135, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (21, 'Cавлуков-Мебель Ритис П-образный', 1020.65, 'Диван', 'угловой, 6-местный, механизм "дельфин", настил пенополиуретан (поролон)/блок независимых пружин, длина 330 см, спальное место 270x130 см', 'a36715cf-df4c-4b7f-876f-f992d8809963', 270, 130, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (22, 'Signal Olivia bianco', 68.5, 'Обеденный стол', 'обеденный стол, раздвижной, овальная форма, 1060×1060 мм, материал: МДФ (столешница), дерево (опоры)', '1071de46-29ad-4349-ac8d-b36e2feb4069', 106, 106, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (23, 'Ikea Лакк (черный/коричневый) [401.042.94]', 27, 'Журнальный столик', 'журнальный столик, классический, прямоугольная форма, 900×550 мм, материал: ЛДСП/пластик (столешница), ЛДСП (опоры)', 'f6452f22-14fd-481a-95aa-ad59d1dcb7b2', 90, 55, 5.9000000000000004);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (24, 'Ikea Фредде (черный) [502.190.44]', 399.99000000000001, 'Компьютерный стол', 'компьютерный стол, классический, прямоугольная форма, 1400×740 мм, материал: ЛДСП/пластик (столешница), окрашенная сталь (опоры)', 'e2f7d6f6-046c-42fc-adfd-36ec5e3fbadf', 140, 74, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (25, 'Мебель-класс Партнер [МК-22]', 106.2, 'Компьютерный стол', 'компьютерный стол, классический, прямоугольная форма, 1420×550 мм, материал: ЛДСП (столешница), ЛДСП (опоры)', '8c04a065-2311-474c-b05b-fb83db8864d2', 142, 55, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (26, 'ГрандМанар Cinzano 200x200 (с подъемным механизмом)', 580.79999999999995, 'Кровать', 'классическая, 200x200 см, изголовье искусственная кожа, основание ортопедическое, подъемный механизм, ящики', '5e072f16-d998-4ea1-8168-9b399ecbbbb6', 200, 200, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (27, 'Никкодом Дейтон 160x200 ПМ', 382, 'Кровать', 'классическая, 160x200 см, изголовье ЛДСП/ткань/искусственная кожа/дерево, основание ортопедическое, подъемный механизм, ящики', '84be69e9-3ee6-4220-a8f3-d2e09e91211f', 160, 200, 90);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (28, 'ГрандМанар Dionis 200х200 (с подъемным механизмом)', 410.19999999999999, 'Кровать', 'классическая, 200x200 см, изголовье искусственная кожа, основание ортопедическое, подъемный механизм, ящики', 'ca34d603-b399-4f6d-83f2-ab9f6190f650', 200, 200, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (33, 'Новый Стиль Amely chrome V 28', 27.5, 'Обеденный стул', 'обеденный стул, искусственная кожа (сидение), металл (каркас)', '30274673-a34e-4055-aae2-fb0a73a9bf48', 80, 62, NULL);
INSERT INTO product (id, name, cost, type, details, image, width, height, weight) VALUES (34, 'Новый Стиль Martin chrome', 30, 'Обеденный стул', 'обеденный стул, ткань (сидение), металл (каркас)', '70358ba5-8f10-4a09-8d5b-536b675c9667', 66, 28, 2);
SELECT pg_catalog.setval('product_id_seq',35, true);

INSERT INTO stock (id, specialize, address, phone, square) VALUES (11, 'Склад #1', 'г. Минск, ул. Заславская 31', '80152156482', 355);
INSERT INTO stock (id, specialize, address, phone, square) VALUES (12, 'Склад #2', 'г. Гродно, ул. Горького 21', '801524563257', 58.240000000000002);
SELECT pg_catalog.setval('stock_id_seq', 13, true);

INSERT INTO product_stock (product_id, stock_id) VALUES (19, 11);
INSERT INTO product_stock (product_id, stock_id) VALUES (20, 11);
INSERT INTO product_stock (product_id, stock_id) VALUES (21, 11);
INSERT INTO product_stock (product_id, stock_id) VALUES (29, 12);
INSERT INTO product_stock (product_id, stock_id) VALUES (30, 12);
INSERT INTO product_stock (product_id, stock_id) VALUES (31, 12);
INSERT INTO product_stock (product_id, stock_id) VALUES (22, 12);
INSERT INTO product_stock (product_id, stock_id) VALUES (23, 12);
INSERT INTO product_stock (product_id, stock_id) VALUES (24, 12);

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (8, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (18, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (19, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (19, 3);
