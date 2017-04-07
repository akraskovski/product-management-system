--
-- Name: authorities; Type: TYPE; Schema: pms; Owner: postgres
--

CREATE TYPE authorities AS ENUM (
    'ROLE_ADMIN',
    'ROLE_STOCK_MANAGER',
    'ROLE_STORE_MANAGER'
);


ALTER TYPE authorities OWNER TO postgres;

--
-- Name: authority; Type: TABLE; Schema: pms; Owner: postgres
--

CREATE TABLE authority (
    id integer NOT NULL,
    name authorities NOT NULL
);


ALTER TABLE authority OWNER TO postgres;

--
-- Name: authority_id_seq; Type: SEQUENCE; Schema: pms; Owner: postgres
--

CREATE SEQUENCE authority_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE authority_id_seq OWNER TO postgres;

--
-- Name: authority_id_seq; Type: SEQUENCE OWNED BY; Schema: pms; Owner: postgres
--

ALTER SEQUENCE authority_id_seq OWNED BY authority.id;


--
-- Name: product; Type: TABLE; Schema: pms; Owner: postgres
--

CREATE TABLE product (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    cost double precision NOT NULL,
    type character varying(255),
    details character varying(500)
);


ALTER TABLE product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: pms; Owner: postgres
--

CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: pms; Owner: postgres
--

ALTER SEQUENCE product_id_seq OWNED BY product.id;


--
-- Name: product_stock; Type: TABLE; Schema: pms; Owner: postgres
--

CREATE TABLE product_stock (
    product_id integer NOT NULL,
    stock_id integer NOT NULL
);


ALTER TABLE product_stock OWNER TO postgres;

--
-- Name: stock; Type: TABLE; Schema: pms; Owner: postgres
--

CREATE TABLE stock (
    id integer NOT NULL,
    specialize character varying(255) NOT NULL,
    address character varying(255),
    phone integer,
    square double precision
);


ALTER TABLE stock OWNER TO postgres;

--
-- Name: stock_id_seq; Type: SEQUENCE; Schema: pms; Owner: postgres
--

CREATE SEQUENCE stock_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stock_id_seq OWNER TO postgres;

--
-- Name: stock_id_seq; Type: SEQUENCE OWNED BY; Schema: pms; Owner: postgres
--

ALTER SEQUENCE stock_id_seq OWNED BY stock.id;


--
-- Name: stock_store; Type: TABLE; Schema: pms; Owner: postgres
--

CREATE TABLE stock_store (
    stock_id integer NOT NULL,
    store_id integer NOT NULL
);


ALTER TABLE stock_store OWNER TO postgres;

--
-- Name: store; Type: TABLE; Schema: pms; Owner: postgres
--

CREATE TABLE store (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    details character varying(500),
    address character varying(255),
    phone integer,
    skype character varying(45),
    discounts boolean,
    mail character varying(45)
);


ALTER TABLE store OWNER TO postgres;

--
-- Name: store_id_seq; Type: SEQUENCE; Schema: pms; Owner: postgres
--

CREATE SEQUENCE store_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE store_id_seq OWNER TO postgres;

--
-- Name: store_id_seq; Type: SEQUENCE OWNED BY; Schema: pms; Owner: postgres
--

ALTER SEQUENCE store_id_seq OWNED BY store.id;


--
-- Name: user; Type: TABLE; Schema: pms; Owner: postgres
--

CREATE TABLE "user" (
    id integer NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL
);


ALTER TABLE "user" OWNER TO postgres;

--
-- Name: user_authority; Type: TABLE; Schema: pms; Owner: postgres
--

CREATE TABLE user_authority (
    user_id integer NOT NULL,
    authority_id integer NOT NULL
);


ALTER TABLE user_authority OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: pms; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: pms; Owner: postgres
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- Name: authority id; Type: DEFAULT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY authority ALTER COLUMN id SET DEFAULT nextval('authority_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- Name: stock id; Type: DEFAULT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY stock ALTER COLUMN id SET DEFAULT nextval('stock_id_seq'::regclass);


--
-- Name: store id; Type: DEFAULT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY store ALTER COLUMN id SET DEFAULT nextval('store_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- Data for Name: authority; Type: TABLE DATA; Schema: pms; Owner: postgres
--

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_STOCK_MANAGER');
INSERT INTO authority (id, name) VALUES (3, 'ROLE_STORE_MANAGER');

--
-- Name: authority authority_pkey; Type: CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY authority
    ADD CONSTRAINT authority_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: product_stock product_stock_pkey; Type: CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY product_stock
    ADD CONSTRAINT product_stock_pkey PRIMARY KEY (product_id, stock_id);


--
-- Name: stock stock_pkey; Type: CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (id);


--
-- Name: stock_store stock_store_pkey; Type: CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY stock_store
    ADD CONSTRAINT stock_store_pkey PRIMARY KEY (stock_id, store_id);


--
-- Name: store store_pkey; Type: CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY store
    ADD CONSTRAINT store_pkey PRIMARY KEY (id);


--
-- Name: user_authority user_authority_pkey; Type: CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY user_authority
    ADD CONSTRAINT user_authority_pkey PRIMARY KEY (user_id, authority_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: authority_id_uindex; Type: INDEX; Schema: pms; Owner: postgres
--

CREATE UNIQUE INDEX authority_id_uindex ON authority USING btree (id);


--
-- Name: authority_name_uindex; Type: INDEX; Schema: pms; Owner: postgres
--

CREATE UNIQUE INDEX authority_name_uindex ON authority USING btree (name);


--
-- Name: product_name_uindex; Type: INDEX; Schema: pms; Owner: postgres
--

CREATE UNIQUE INDEX product_name_uindex ON product USING btree (name);


--
-- Name: stock_specialize_uindex; Type: INDEX; Schema: pms; Owner: postgres
--

CREATE UNIQUE INDEX stock_specialize_uindex ON stock USING btree (specialize);


--
-- Name: store_name_uindex; Type: INDEX; Schema: pms; Owner: postgres
--

CREATE UNIQUE INDEX store_name_uindex ON store USING btree (name);


--
-- Name: user_username_uindex; Type: INDEX; Schema: pms; Owner: postgres
--

CREATE UNIQUE INDEX user_username_uindex ON "user" USING btree (username);


--
-- Name: product_stock fk_product_stock_product1; Type: FK CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY product_stock
    ADD CONSTRAINT fk_product_stock_product1 FOREIGN KEY (product_id) REFERENCES product(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: product_stock fk_product_stock_stock1; Type: FK CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY product_stock
    ADD CONSTRAINT fk_product_stock_stock1 FOREIGN KEY (stock_id) REFERENCES stock(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: stock_store fk_stock_store_stock1; Type: FK CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY stock_store
    ADD CONSTRAINT fk_stock_store_stock1 FOREIGN KEY (stock_id) REFERENCES stock(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: stock_store fk_stock_store_store1; Type: FK CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY stock_store
    ADD CONSTRAINT fk_stock_store_store1 FOREIGN KEY (store_id) REFERENCES store(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_authority fk_user_authority_authority1; Type: FK CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY user_authority
    ADD CONSTRAINT fk_user_authority_authority1 FOREIGN KEY (authority_id) REFERENCES authority(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_authority fk_user_authority_user1; Type: FK CONSTRAINT; Schema: pms; Owner: postgres
--

ALTER TABLE ONLY user_authority
    ADD CONSTRAINT fk_user_authority_user1 FOREIGN KEY (user_id) REFERENCES "user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

