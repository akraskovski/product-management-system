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
-- Name: authorities; Type: TYPE; Schema: products; Owner: postgres
--

CREATE TYPE authorities AS ENUM (
    'ROLE_ADMIN',
    'ROLE_STOCK_MANAGER',
    'ROLE_STORE_MANAGER',
    'ANONYMOUS'
);


ALTER TYPE authorities OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: authority; Type: TABLE; Schema: products; Owner: postgres
--

CREATE TABLE authority (
    id integer NOT NULL,
    name authorities
);


ALTER TABLE authority OWNER TO postgres;

--
-- Name: authority_id_seq; Type: SEQUENCE; Schema: products; Owner: postgres
--

CREATE SEQUENCE authority_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE authority_id_seq OWNER TO postgres;

--
-- Name: authority_id_seq; Type: SEQUENCE OWNED BY; Schema: products; Owner: postgres
--

ALTER SEQUENCE authority_id_seq OWNED BY authority.id;


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
-- Name: user_authority; Type: TABLE; Schema: products; Owner: postgres
--

CREATE TABLE user_authority (
    user_id integer NOT NULL,
    authority_id integer NOT NULL
);


ALTER TABLE user_authority OWNER TO postgres;

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
-- Name: authority id; Type: DEFAULT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY authority ALTER COLUMN id SET DEFAULT nextval('authority_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


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
-- Data for Name: authority; Type: TABLE DATA; Schema: products; Owner: postgres
--

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_STOCK_MANAGER');
INSERT INTO authority (id, name) VALUES (3, 'ROLE_STORE_MANAGER');
INSERT INTO authority (id, name) VALUES (4, 'ANONYMOUS');


--
-- Name: authority_id_seq; Type: SEQUENCE SET; Schema: products; Owner: postgres
--

SELECT pg_catalog.setval('authority_id_seq', 1, false);


--
-- Data for Name: product; Type: TABLE DATA; Schema: products; Owner: postgres
--

INSERT INTO product (id, name, cost, type) VALUES (1, 'NE Hleb', 2.02, 'Food');


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: products; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 10, true);


--
-- Data for Name: product_stock; Type: TABLE DATA; Schema: products; Owner: postgres
--

INSERT INTO product_stock (product_id, stock_id) VALUES (1, 1);


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
-- Data for Name: user_authority; Type: TABLE DATA; Schema: products; Owner: postgres
--

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: products; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 1, true);


--
-- Name: authority authority_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY authority
    ADD CONSTRAINT authority_pkey PRIMARY KEY (id);


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
-- Name: user_authority user_authority_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY user_authority
    ADD CONSTRAINT user_authority_pkey PRIMARY KEY (user_id, authority_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: authority_id_uindex; Type: INDEX; Schema: products; Owner: postgres
--

CREATE UNIQUE INDEX authority_id_uindex ON authority USING btree (id);


--
-- Name: authority_name_uindex; Type: INDEX; Schema: products; Owner: postgres
--

CREATE UNIQUE INDEX authority_name_uindex ON authority USING btree (name);


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
-- Name: user_authority fk_user_authority_authority1; Type: FK CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY user_authority
    ADD CONSTRAINT fk_user_authority_authority1 FOREIGN KEY (authority_id) REFERENCES authority(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_authority fk_user_authority_user1; Type: FK CONSTRAINT; Schema: products; Owner: postgres
--

ALTER TABLE ONLY user_authority
    ADD CONSTRAINT fk_user_authority_user1 FOREIGN KEY (user_id) REFERENCES "user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

