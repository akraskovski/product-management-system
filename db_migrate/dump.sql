--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: products; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA products;


ALTER SCHEMA products OWNER TO postgres;

SET search_path = products, pg_catalog;

--
-- Name: authority; Type: TYPE; Schema: products; Owner: postgres
--

CREATE TYPE authority AS ENUM (
    'ROLE_ADMIN',
    'ROLE_STOCK_MANAGER',
    'ROLE_STORE_MANAGER',
    'ANONYMOUS'
);


ALTER TYPE authority OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: product; Type: TABLE; Schema: products; Owner: postgres
--

CREATE TABLE product (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    cost double precision NOT NULL,
    type character varying(255)
);


ALTER TABLE product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: products; Owner: postgres
--

CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: products; Owner: postgres
--

ALTER SEQUENCE product_id_seq OWNED BY product.id;


--
-- Name: product_stock; Type: TABLE; Schema: products; Owner: postgres
--

CREATE TABLE product_stock (
    product_id integer NOT NULL,
    stock_id integer NOT NULL
);


ALTER TABLE product_stock OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: products; Owner: postgres
--

CREATE TABLE role (
    id integer NOT NULL,
    name authority
);


ALTER TABLE role OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: products; Owner: postgres
--

CREATE SEQUENCE role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_id_seq OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: products; Owner: postgres
--

ALTER SEQUENCE role_id_seq OWNED BY role.id;


--
-- Name: stock; Type: TABLE; Schema: products; Owner: postgres
--

CREATE TABLE stock (
    id integer NOT NULL,
    specialize character varying(255) NOT NULL
);


ALTER TABLE stock OWNER TO postgres;

--
-- Name: stock_id_seq; Type: SEQUENCE; Schema: products; Owner: postgres
--

CREATE SEQUENCE stock_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stock_id_seq OWNER TO postgres;

--
-- Name: stock_id_seq; Type: SEQUENCE OWNED BY; Schema: products; Owner: postgres
--

ALTER SEQUENCE stock_id_seq OWNED BY stock.id;


--
-- Name: stock_store; Type: TABLE; Schema: products; Owner: postgres
--

CREATE TABLE stock_store (
    stock_id integer NOT NULL,
    store_id integer NOT NULL
);


ALTER TABLE stock_store OWNER TO postgres;

--
-- Name: store; Type: TABLE; Schema: products; Owner: postgres
--

CREATE TABLE store (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE store OWNER TO postgres;

--
-- Name: store_id_seq; Type: SEQUENCE; Schema: products; Owner: postgres
--

CREATE SEQUENCE store_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE store_id_seq OWNER TO postgres;

--
-- Name: store_id_seq; Type: SEQUENCE OWNED BY; Schema: products; Owner: postgres
--

ALTER SEQUENCE store_id_seq OWNED BY store.id;


--
-- Name: user; Type: TABLE; Schema: products; Owner: postgres
--

CREATE TABLE "user" (
    id integer NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL
);


ALTER TABLE "user" OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: products; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: products; Owner: postgres
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- Name: user_role; Type: TABLE; Schema: products; Owner: postgres
--

CREATE TABLE user_role (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE user_role OWNER TO postgres;

--
-- Name: product id; Type: DEFAULT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY role ALTER COLUMN id SET DEFAULT nextval('role_id_seq'::regclass);


--
-- Name: stock id; Type: DEFAULT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY stock ALTER COLUMN id SET DEFAULT nextval('stock_id_seq'::regclass);


--
-- Name: store id; Type: DEFAULT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY store ALTER COLUMN id SET DEFAULT nextval('store_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- Data for Name: product; Type: TABLE DATA; Schema: products; Owner: postgres
--

INSERT INTO product (id, name, cost, type) VALUES (1, 'NE Hleb', 2.02, 'Food');


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: products; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 7, true);


--
-- Data for Name: product_stock; Type: TABLE DATA; Schema: products; Owner: postgres
--

INSERT INTO product_stock (product_id, stock_id) VALUES (1, 1);


--
-- Data for Name: role; Type: TABLE DATA; Schema: products; Owner: postgres
--

INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (2, 'ROLE_STOCK_MANAGER');
INSERT INTO role (id, name) VALUES (3, 'ROLE_STORE_MANAGER');
INSERT INTO role (id, name) VALUES (4, 'ANONYMOUS');


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: products; Owner: postgres
--

SELECT pg_catalog.setval('role_id_seq', 4, true);


--
-- Data for Name: stock; Type: TABLE DATA; Schema: products; Owner: postgres
--

INSERT INTO stock (id, specialize) VALUES (1, 'Food');


--
-- Name: stock_id_seq; Type: SEQUENCE SET; Schema: products; Owner: postgres
--

SELECT pg_catalog.setval('stock_id_seq', 5, true);


--
-- Data for Name: stock_store; Type: TABLE DATA; Schema: products; Owner: postgres
--



--
-- Data for Name: store; Type: TABLE DATA; Schema: products; Owner: postgres
--



--
-- Name: store_id_seq; Type: SEQUENCE SET; Schema: products; Owner: postgres
--

SELECT pg_catalog.setval('store_id_seq', 1, false);


--
-- Data for Name: user; Type: TABLE DATA; Schema: products; Owner: postgres
--

INSERT INTO "user" (id, username, password) VALUES (1, 'Inna', 'qwerty');


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: products; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 1, true);


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: products; Owner: postgres
--

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: product_stock product_stock_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY product_stock
    ADD CONSTRAINT product_stock_pkey PRIMARY KEY (product_id, stock_id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: stock stock_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (id);


--
-- Name: stock_store stock_store_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY stock_store
    ADD CONSTRAINT stock_store_pkey PRIMARY KEY (stock_id, store_id);


--
-- Name: store store_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY store
    ADD CONSTRAINT store_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: product_stock fk_product_stock_product1; Type: FK CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY product_stock
    ADD CONSTRAINT fk_product_stock_product1 FOREIGN KEY (product_id) REFERENCES product(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: product_stock fk_product_stock_stock1; Type: FK CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY product_stock
    ADD CONSTRAINT fk_product_stock_stock1 FOREIGN KEY (stock_id) REFERENCES stock(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: stock_store fk_stock_store_stock1; Type: FK CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY stock_store
    ADD CONSTRAINT fk_stock_store_stock1 FOREIGN KEY (stock_id) REFERENCES stock(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: stock_store fk_stock_store_store1; Type: FK CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY stock_store
    ADD CONSTRAINT fk_stock_store_store1 FOREIGN KEY (store_id) REFERENCES store(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_role fk_user_role_role1; Type: FK CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT fk_user_role_role1 FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_role fk_user_role_user1; Type: FK CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT fk_user_role_user1 FOREIGN KEY (user_id) REFERENCES "user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

