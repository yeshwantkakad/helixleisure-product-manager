CREATE TABLE public.products
(
  id bigint NOT NULL,
  name character varying(255),
  quantity integer,
  sale_amount numeric(19,2),
  CONSTRAINT products_pkey PRIMARY KEY (id)
);

CREATE TABLE public.users
(
  id bigint NOT NULL,
  email character varying(255),
  first_name character varying(255),
  last_name character varying(255),
  last_password_reset_date timestamp without time zone,
  password character varying(255),
  username character varying(255),
  CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE public.authority
(
  id bigint NOT NULL,
  name character varying(255),
  CONSTRAINT authority_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user_authority
(
  user_id bigint NOT NULL,
  authority_id bigint NOT NULL,
  CONSTRAINT fkgvxjs381k6f48d5d2yi11uh89 FOREIGN KEY (authority_id)
      REFERENCES public.authority (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkhi46vu7680y1hwvmnnuh4cybx FOREIGN KEY (user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE user_generator;

INSERT INTO USERS (id, username, password, first_name, last_name, email, last_password_reset_date) VALUES (1, 'user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Yeshwant', 'Kakad', 'user@example.com', '2017-10-01 21:58:58.508-07');

INSERT INTO AUTHORITY (id, name) VALUES (1, 'ROLE_USER');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
