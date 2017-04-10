INSERT INTO "user" (id, username, password) VALUES (1, 'admin', 'admin');
INSERT INTO "user" (id, username, password) VALUES (8, 'stock_manager', 'password1');
INSERT INTO "user" (id, username, password) VALUES (18, 'store_manager', 'password1');
INSERT INTO "user" (id, username, password) VALUES (19, 'managed_profile', 'user_stock_store');
SELECT pg_catalog.setval('user_id_seq', 20, true);

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_STOCK_MANAGER');
INSERT INTO authority (id, name) VALUES (3, 'ROLE_STORE_MANAGER');
SELECT pg_catalog.setval('authority_id_seq', 4, false);

INSERT INTO product (id, name, cost, type, details) VALUES (1, 'Apple MacBook Air 13" [MMGF2]', 1783, 'Laptop', '13.3" 1440 x 900 глянцевый, Intel Core i5 1600 МГц, 8 ГБ, 128 Гб (SSD), Intel HD Graphics 6000, OS X, цвет крышки серебристый, цвет корпуса серебристый');
INSERT INTO product (id, name, cost, type, details) VALUES (8, 'Xiaomi Mi Book Air 13,3 Silver', 1799.96, 'Laptop', '13.3" 1920 x 1080 глянцевый, Intel Core i5 6200U 2300 МГц, 8 ГБ, 256 Гб (SSD), NVIDIA GeForce 940MX, Windows 10, цвет крышки серебристый, цвет корпуса серебристый');
INSERT INTO product (id, name, cost, type, details) VALUES (9, 'Apple MacBook Pro 13'''' Retina (MF839)', 2425, 'Laptop', '13.3" 2560 x 1600 глянцевый, Intel Core i5 2700 МГц, 8 ГБ, 128 Гб (SSD), Intel Iris Graphics 6100, OS X, цвет крышки серебристый, цвет корпуса серебристый');
INSERT INTO product (id, name, cost, type, details) VALUES (10, 'Lenovo IdeaPad 700-15ISK [80RU00BWPB]', 1380, 'Laptop', '15.6" 1920 x 1080 матовый, Intel Core i5 6300HQ 2300 МГц, 8 ГБ, 1000 ГБ (HDD), NVIDIA GeForce GTX 950M, DOS, цвет крышки черный, цвет корпуса черный');
INSERT INTO product (id, name, cost, type, details) VALUES (13, 'Samsung UE43KU6000U', 520, 'TV', '43" 3840x2160 (4K UHD), матрица VA, частота 100 Гц, индекс динамичных сцен 1300, Smart TV (Samsung Tizen), HDR, Wi-Fi');
INSERT INTO product (id, name, cost, type, details) VALUES (14, 'LG 55UH605V', 460, 'TV', '55" 3840x2160 (4K UHD), матрица IPS, частота 100 Гц, индекс динамичных сцен 1200, Smart TV (LG webOS), HDR, Wi-Fi');
INSERT INTO product (id, name, cost, type, details) VALUES (15, 'Samsung UE32K5500BU', 315, 'TV', '32" 1920x1080 (Full HD), матрица VA, частота 50 Гц, индекс динамичных сцен 400, Smart TV (Samsung Tizen), Wi-Fi');
INSERT INTO product (id, name, cost, type, details) VALUES (16, 'Samsung Galaxy S8 Dual SIM 64GB [G950FD]', 1200, 'Mobile', 'Android, экран 5.8" AMOLED (1440x2960), ОЗУ 4 ГБ, флэш-память 64 ГБ, карты памяти, камера 12 Мп, аккумулятор 3000 мАч, 2 SIM, цвет черный');
INSERT INTO product (id, name, cost, type, details) VALUES (17, 'Xiaomi Redmi 4 16GB Gray', 140.69999999999999, 'Mobile', 'Android, экран 5" IPS (720x1280), ОЗУ 2 ГБ, флэш-память 16 ГБ, карты памяти, камера 13 Мп, аккумулятор 4100 мАч, 2 SIM, цвет темно-серый');
SELECT pg_catalog.setval('product_id_seq', 18, true);

INSERT INTO stock (id, specialize, address, phone, square) VALUES (7, 'Laptop', 'Belarus, Grodno, Gorkogo st. 42', '80291254784', 86.879999999999995);
INSERT INTO stock (id, specialize, address, phone, square) VALUES (8, 'TV', 'Belarus, Minsk, Pobedy st. 92a', '80335236874', 140.5);
INSERT INTO stock (id, specialize, address, phone, square) VALUES (9, 'Mobiles', 'Poland, Warsaw, Legionowa st. 88 ', '347700000', 256.12799999999999);
SELECT pg_catalog.setval('stock_id_seq', 10, true);

INSERT INTO store (id, name, details, address, phone, skype, discounts, mail) VALUES (3, '5 Element', '5 элемент - интернет-магазин бытовой техники и аудио-видео в Беларуси: жк телевизоры, холодильники. Официальное дилерство ведущих мировых производителей', 'Belarus, Grodno, Pobedy st. 47', '80152825475', '', true, 'store@5elem.by');
INSERT INTO store (id, name, details, address, phone, skype, discounts, mail) VALUES (4, 'Электросила', 'Купить аудио-видео и бытовую технику по низким ценам в Минске и Беларуси. Официальная гарантия на все товары. Огромный Выбор в наших магазинах.', 'Belarus, Grodno, ', '80152356579', 'sila.by', false, 'electrosila@gmail.com');
INSERT INTO store (id, name, details, address, phone, skype, discounts, mail) VALUES (5, '"Allo"', 'Салоны цифровой техники “АЛЛО!” пользуются заслуженной популярностью у белорусских потребителей. Одна из существенных причин тому - богатый ассортимент, в котором представлены все основные категории современных мобильных устройств. Не только телефоныв рассрочку в Гродно но и множество других товаров.', 'Belarus, Minsk, Umanskaya st. 54', '801524578645', 'allo_by', NULL, '');
SELECT pg_catalog.setval('store_id_seq', 6, true);

INSERT INTO product_stock (product_id, stock_id) VALUES (1, 7);
INSERT INTO product_stock (product_id, stock_id) VALUES (8, 7);
INSERT INTO product_stock (product_id, stock_id) VALUES (9, 7);
INSERT INTO product_stock (product_id, stock_id) VALUES (10, 7);
INSERT INTO product_stock (product_id, stock_id) VALUES (14, 8);
INSERT INTO product_stock (product_id, stock_id) VALUES (13, 8);
INSERT INTO product_stock (product_id, stock_id) VALUES (15, 8);
INSERT INTO product_stock (product_id, stock_id) VALUES (17, 9);
INSERT INTO product_stock (product_id, stock_id) VALUES (16, 9);

INSERT INTO stock_store (stock_id, store_id) VALUES (7, 3);
INSERT INTO stock_store (stock_id, store_id) VALUES (8, 3);
INSERT INTO stock_store (stock_id, store_id) VALUES (9, 3);
INSERT INTO stock_store (stock_id, store_id) VALUES (7, 4);
INSERT INTO stock_store (stock_id, store_id) VALUES (8, 4);
INSERT INTO stock_store (stock_id, store_id) VALUES (9, 4);
INSERT INTO stock_store (stock_id, store_id) VALUES (9, 5);

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (8, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (18, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (19, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (19, 3);
