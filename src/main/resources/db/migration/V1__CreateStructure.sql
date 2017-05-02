CREATE TYPE AUTHORITIES AS ENUM (
  'ROLE_ADMIN',
  'ROLE_STOCK_MANAGER',
  'ROLE_STORE_MANAGER'
);

CREATE TABLE authority (
  id   INTEGER     NOT NULL,
  name AUTHORITIES NOT NULL
);
CREATE SEQUENCE authority_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
ALTER SEQUENCE authority_id_seq OWNED BY authority.id;

CREATE TABLE product (
  id      INTEGER                NOT NULL,
  name    CHARACTER VARYING(255) NOT NULL,
  cost    DOUBLE PRECISION       NOT NULL,
  type    CHARACTER VARYING(255),
  details CHARACTER VARYING(500),
  image   CHARACTER VARYING(255),
  width   DOUBLE PRECISION,
  height  DOUBLE PRECISION,
  weight  DOUBLE PRECISION
);
CREATE SEQUENCE product_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
ALTER SEQUENCE product_id_seq OWNED BY product.id;

CREATE TABLE product_stock (
  product_id INTEGER NOT NULL,
  stock_id   INTEGER NOT NULL
);
CREATE TABLE stock (
  id         INTEGER                NOT NULL,
  specialize CHARACTER VARYING(255) NOT NULL,
  address    CHARACTER VARYING(255),
  phone      CHARACTER VARYING(50),
  square     DOUBLE PRECISION
);
CREATE SEQUENCE stock_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
ALTER SEQUENCE stock_id_seq OWNED BY stock.id;

CREATE TABLE stock_store (
  stock_id INTEGER NOT NULL,
  store_id INTEGER NOT NULL
);

CREATE TABLE store (
  id        INTEGER                NOT NULL,
  name      CHARACTER VARYING(255) NOT NULL,
  details   CHARACTER VARYING(500),
  address   CHARACTER VARYING(255),
  phone     CHARACTER VARYING(50),
  skype     CHARACTER VARYING(45),
  discounts BOOLEAN,
  mail      CHARACTER VARYING(45),
  logo      CHARACTER VARYING(255)
);
CREATE SEQUENCE store_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
ALTER SEQUENCE store_id_seq OWNED BY store.id;

CREATE TABLE "user" (
  id       INTEGER           NOT NULL,
  username CHARACTER VARYING NOT NULL,
  password CHARACTER VARYING NOT NULL
);

CREATE TABLE user_authority (
  user_id      INTEGER NOT NULL,
  authority_id INTEGER NOT NULL
);
CREATE SEQUENCE user_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
ALTER SEQUENCE user_id_seq OWNED BY "user".id;

ALTER TABLE ONLY authority
  ALTER COLUMN id SET DEFAULT nextval('authority_id_seq' :: REGCLASS);
ALTER TABLE ONLY product
  ALTER COLUMN id SET DEFAULT nextval('product_id_seq' :: REGCLASS);
ALTER TABLE ONLY stock
  ALTER COLUMN id SET DEFAULT nextval('stock_id_seq' :: REGCLASS);
ALTER TABLE ONLY store
  ALTER COLUMN id SET DEFAULT nextval('store_id_seq' :: REGCLASS);
ALTER TABLE ONLY "user"
  ALTER COLUMN id SET DEFAULT nextval('user_id_seq' :: REGCLASS);

ALTER TABLE ONLY authority
  ADD CONSTRAINT authority_pkey PRIMARY KEY (id);
ALTER TABLE ONLY product
  ADD CONSTRAINT product_pkey PRIMARY KEY (id);
ALTER TABLE ONLY product_stock
  ADD CONSTRAINT product_stock_pkey PRIMARY KEY (product_id, stock_id);
ALTER TABLE ONLY stock
  ADD CONSTRAINT stock_pkey PRIMARY KEY (id);
ALTER TABLE ONLY stock_store
  ADD CONSTRAINT stock_store_pkey PRIMARY KEY (stock_id, store_id);
ALTER TABLE ONLY store
  ADD CONSTRAINT store_pkey PRIMARY KEY (id);
ALTER TABLE ONLY user_authority
  ADD CONSTRAINT user_authority_pkey PRIMARY KEY (user_id, authority_id);
ALTER TABLE ONLY "user"
  ADD CONSTRAINT user_pkey PRIMARY KEY (id);

CREATE UNIQUE INDEX authority_id_uindex
  ON authority USING BTREE (id);
CREATE UNIQUE INDEX authority_name_uindex
  ON authority USING BTREE (name);
CREATE UNIQUE INDEX product_name_uindex
  ON product USING BTREE (name);
CREATE UNIQUE INDEX stock_specialize_uindex
  ON stock USING BTREE (specialize);
CREATE UNIQUE INDEX store_name_uindex
  ON store USING BTREE (name);
CREATE UNIQUE INDEX user_username_uindex
  ON "user" USING BTREE (username);

ALTER TABLE ONLY product_stock
  ADD CONSTRAINT fk_product_stock_product1 FOREIGN KEY (product_id) REFERENCES product (id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ONLY product_stock
  ADD CONSTRAINT fk_product_stock_stock1 FOREIGN KEY (stock_id) REFERENCES stock (id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ONLY stock_store
  ADD CONSTRAINT fk_stock_store_stock1 FOREIGN KEY (stock_id) REFERENCES stock (id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ONLY stock_store
  ADD CONSTRAINT fk_stock_store_store1 FOREIGN KEY (store_id) REFERENCES store (id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ONLY user_authority
  ADD CONSTRAINT fk_user_authority_authority1 FOREIGN KEY (authority_id) REFERENCES authority (id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE ONLY user_authority
  ADD CONSTRAINT fk_user_authority_user1 FOREIGN KEY (user_id) REFERENCES "user" (id) ON UPDATE CASCADE ON DELETE CASCADE;