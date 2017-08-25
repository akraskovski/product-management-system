--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.12
-- Dumped by pg_dump version 9.5.7

--
-- Name: authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE authority (
  id character varying(255) NOT NULL,
  authority character varying(255) NOT NULL
);

--
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cart (
  id character varying(255) NOT NULL,
  create_date timestamp without time zone,
  total_cost double precision NOT NULL
);


--
-- Name: cart_product_stock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cart_product_stock (
  id character varying(255) NOT NULL,
  product_count integer NOT NULL,
  cart_id character varying(255),
  product_stock_id character varying(255)
);


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product (
  id character varying(255) NOT NULL,
  cost double precision NOT NULL,
  details character varying(255),
  height double precision NOT NULL,
  image character varying(255),
  manufacture_date timestamp without time zone,
  name character varying(255) NOT NULL,
  type character varying(255),
  weight double precision NOT NULL,
  width double precision NOT NULL
);


--
-- Name: product_stock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product_stock (
  id character varying(255) NOT NULL,
  products_count integer NOT NULL,
  product_id character varying(255),
  stock_id character varying(255)
);

--
-- Name: stock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE stock (
  id character varying(255) NOT NULL,
  address character varying(255),
  close_time time without time zone,
  open_time time without time zone,
  phone character varying(255),
  specialize character varying(255) NOT NULL,
  square double precision NOT NULL
);


--
-- Name: stock_store; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE stock_store (
  store_id character varying(255) NOT NULL,
  stock_id character varying(255) NOT NULL
);


--
-- Name: store; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE store (
  id character varying(255) NOT NULL,
  address character varying(255),
  details character varying(255),
  discounts boolean NOT NULL,
  logo character varying(255),
  mail character varying(255),
  name character varying(255) NOT NULL,
  phone character varying(255),
  skype character varying(255)
);


--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "user" (
  id character varying(255) NOT NULL,
  avatar character varying(255),
  create_date timestamp without time zone,
  email character varying(255),
  first_name character varying(255),
  last_name character varying(255),
  password character varying(255) NOT NULL,
  phone character varying(255),
  username character varying(255) NOT NULL
);


--
-- Name: user_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_authority (
  user_id character varying(255) NOT NULL,
  authority_id character varying(255) NOT NULL
);


--
-- Data for Name: authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY authority (id, authority) FROM stdin;

--
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cart (id, create_date, total_cost) FROM stdin;

--
-- Data for Name: cart_product_stock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cart_product_stock (id, product_count, cart_id, product_stock_id) FROM stdin;


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product (id, cost, details, height, image, manufacture_date, name, type, weight, width) FROM stdin;


--
-- Data for Name: product_stock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product_stock (id, products_count, product_id, stock_id) FROM stdin;


--
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY stock (id, address, close_time, open_time, phone, specialize, square) FROM stdin;


--
-- Data for Name: stock_store; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY stock_store (store_id, stock_id) FROM stdin;


--
-- Data for Name: store; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY store (id, address, details, discounts, logo, mail, name, phone, skype) FROM stdin;


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "user" (id, avatar, create_date, email, first_name, last_name, password, phone, username) FROM stdin;


--
-- Data for Name: user_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_authority (user_id, authority_id) FROM stdin;


--
-- Name: authority_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY authority
  ADD CONSTRAINT authority_pkey PRIMARY KEY (id);


--
-- Name: cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cart
  ADD CONSTRAINT cart_pkey PRIMARY KEY (id);


--
-- Name: cart_product_stock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cart_product_stock
  ADD CONSTRAINT cart_product_stock_pkey PRIMARY KEY (id);


--
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
  ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: product_stock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_stock
  ADD CONSTRAINT product_stock_pkey PRIMARY KEY (id);


--
-- Name: stock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stock
  ADD CONSTRAINT stock_pkey PRIMARY KEY (id);


--
-- Name: store_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY store
  ADD CONSTRAINT store_pkey PRIMARY KEY (id);


--
-- Name: uk_589idila9li6a4arw1t8ht1gx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
  ADD CONSTRAINT uk_589idila9li6a4arw1t8ht1gx UNIQUE (phone);


--
-- Name: uk_c2g47ems40onwvlfr92s8wbvh; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stock
  ADD CONSTRAINT uk_c2g47ems40onwvlfr92s8wbvh UNIQUE (specialize);


--
-- Name: uk_d0p5ly1cv6guij7sq1mbnr8ec; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY store
  ADD CONSTRAINT uk_d0p5ly1cv6guij7sq1mbnr8ec UNIQUE (name);


--
-- Name: uk_jmivyxk9rmgysrmsqw15lqr5b; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
  ADD CONSTRAINT uk_jmivyxk9rmgysrmsqw15lqr5b UNIQUE (name);


--
-- Name: uk_nrgoi6sdvipfsloa7ykxwlslf; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY authority
  ADD CONSTRAINT uk_nrgoi6sdvipfsloa7ykxwlslf UNIQUE (authority);


--
-- Name: uk_ob8kqyqqgmefl0aco34akdtpe; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
  ADD CONSTRAINT uk_ob8kqyqqgmefl0aco34akdtpe UNIQUE (email);


--
-- Name: uk_sb8bbouer5wak8vyiiy4pf2bx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
  ADD CONSTRAINT uk_sb8bbouer5wak8vyiiy4pf2bx UNIQUE (username);


--
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
  ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: fk2hss8pxfa4bsab8x1l70c0dpf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cart_product_stock
  ADD CONSTRAINT fk2hss8pxfa4bsab8x1l70c0dpf FOREIGN KEY (cart_id) REFERENCES cart(id);


--
-- Name: fk4wlosoqgfrjiv2jy9ethaxa03; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stock_store
  ADD CONSTRAINT fk4wlosoqgfrjiv2jy9ethaxa03 FOREIGN KEY (stock_id) REFERENCES stock(id);


--
-- Name: fkcm7m4xu88wsy971275wpjq5he; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stock_store
  ADD CONSTRAINT fkcm7m4xu88wsy971275wpjq5he FOREIGN KEY (store_id) REFERENCES store(id);


--
-- Name: fkedkujbytp3omhrumbxx2p9mc6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cart_product_stock
  ADD CONSTRAINT fkedkujbytp3omhrumbxx2p9mc6 FOREIGN KEY (product_stock_id) REFERENCES product_stock(id);


--
-- Name: fkgvxjs381k6f48d5d2yi11uh89; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_authority
  ADD CONSTRAINT fkgvxjs381k6f48d5d2yi11uh89 FOREIGN KEY (authority_id) REFERENCES authority(id);


--
-- Name: fklpu1phje1bb3y9ww8k9fut4gh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_stock
  ADD CONSTRAINT fklpu1phje1bb3y9ww8k9fut4gh FOREIGN KEY (product_id) REFERENCES product(id);


--
-- Name: fko7c7kyld4x51e3jj1hjhk1xtw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cart
  ADD CONSTRAINT fko7c7kyld4x51e3jj1hjhk1xtw FOREIGN KEY (id) REFERENCES "user"(id);


--
-- Name: fkoluk1enhguiq9a9cgf4bve61p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_stock
  ADD CONSTRAINT fkoluk1enhguiq9a9cgf4bve61p FOREIGN KEY (stock_id) REFERENCES stock(id);


--
-- Name: fkscuh0v3acg0xp6skwcvuynl6x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_authority
  ADD CONSTRAINT fkscuh0v3acg0xp6skwcvuynl6x FOREIGN KEY (user_id) REFERENCES "user"(id);

--
-- PostgreSQL database dump complete
--

