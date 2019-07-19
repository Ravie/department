#!/bin/bash
set -e

psql -v ON_ERROR_STOP-1 --username "$POSTGRES_USER" <<-EOSQL
    --
-- PostgreSQL database dump
--

-- Dumped from database version 11.4 (Ubuntu 11.4-0ubuntu0.19.04.1)
-- Dumped by pg_dump version 11.4 (Ubuntu 11.4-0ubuntu0.19.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS departments;
--
-- Name: departments; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE departments WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'ru_RU.UTF-8' LC_CTYPE = 'ru_RU.UTF-8';


ALTER DATABASE departments OWNER TO postgres;

\connect departments

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    author_id bigint
);


ALTER TABLE public.department OWNER TO postgres;

--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    salary double precision NOT NULL,
    author_id bigint,
    department_id bigint NOT NULL
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: logins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.logins (
    id bigint NOT NULL,
    activation_code character varying(255),
    active boolean NOT NULL,
    email character varying(255),
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL
);


ALTER TABLE public.logins OWNER TO postgres;

--
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_role (
    user_id bigint NOT NULL,
    access_level character varying(255)
);


ALTER TABLE public.user_role OWNER TO postgres;

--
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.department VALUES (14, 'Finance', 2);
INSERT INTO public.department VALUES (15, 'Human Resources', 2);
INSERT INTO public.department VALUES (16, 'Production', 2);
INSERT INTO public.department VALUES (3, 'Marketing', 2);
INSERT INTO public.department VALUES (17, 'Development', 2);
INSERT INTO public.department VALUES (18, 'Quality Management', 2);
INSERT INTO public.department VALUES (19, 'Sales', 2);
INSERT INTO public.department VALUES (20, 'Research', 2);
INSERT INTO public.department VALUES (21, 'Customer Service', 2);


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employee VALUES (4, 'Bezalel Simmel', 11280, 2, 3);
INSERT INTO public.employee VALUES (53, 'Parto Bamford', 11280, 2, 3);
INSERT INTO public.employee VALUES (54, 'Kyoichi Maliniak', 11280, 2, 3);
INSERT INTO public.employee VALUES (55, 'Anneke Preusig', 11280.0200000000004, 2, 17);
INSERT INTO public.employee VALUES (59, 'Tzvetan Zielinski', 11280.0100000000002, 2, 3);
INSERT INTO public.employee VALUES (60, 'Saniya Kalloufi', 11280, 2, 17);
INSERT INTO public.employee VALUES (61, 'Sumant Peac', 11280, 2, 14);
INSERT INTO public.employee VALUES (62, 'Duangkaew Piveteau', 11280, 2, 16);
INSERT INTO public.employee VALUES (63, 'Mary Sluis', 11280.0100000000002, 2, 15);
INSERT INTO public.employee VALUES (64, 'Patricio Bridgland', 11280, 2, 14);
INSERT INTO public.employee VALUES (65, 'Eberhardt Terkki', 11280.0200000000004, 2, 15);
INSERT INTO public.employee VALUES (66, 'Berni Genin', 11280, 2, 3);
INSERT INTO public.employee VALUES (68, 'Guoxiang Nooteboom', 11280, 2, 3);
INSERT INTO public.employee VALUES (69, 'Kazuhito Cappelletti', 11280.0100000000002, 2, 16);
INSERT INTO public.employee VALUES (72, 'Cristinel Bouloucos', 11280, 2, 14);
INSERT INTO public.employee VALUES (74, 'Kazuhide Peha', 11280.0200000000004, 2, 3);
INSERT INTO public.employee VALUES (75, 'Lillian Haddadi', 12412412, 2, 3);
INSERT INTO public.employee VALUES (76, 'Mayuko Warwick', 11280.0100000000002, 2, 18);


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.flyway_schema_history VALUES (1, '1', 'InitDb', 'SQL', 'V1__InitDb.sql', -1098097612, 'postgres', '2019-07-16 22:33:49.42539', 31, true);


--
-- Data for Name: logins; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.logins VALUES (2, '51a03e64-5136-4be7-a8bf-7aa0fd4b4f87', true, 'ravijkee@yandex.ru', '$2a$08$/LM4OhreRYfgcBHmcbyo1eT8s2eAeT3Wc94HMrm941atTK7/.DGkG', 'Ravie');
INSERT INTO public.logins VALUES (1, 'be38efaf-c335-4d67-962d-155f2dd19d8f', true, 'ravijkee@gmail.com', '$2a$08$XSeNU9gu4Mk3ZHQPoyEm7.v5PorjFjWklMH73EU6rVhW5pAcJKT4i', 'A');
INSERT INTO public.logins VALUES (7, NULL, true, 'ravijkee@yandex.ru', '$2a$08$vnp0L6r.6yoTTpbIbjOxze8dAF30IOZRnAReQeZ2fI0dXS1f.ZkEe', '123123');
INSERT INTO public.logins VALUES (9, NULL, true, 'ravijkee@yandex.ru', '$2a$08$acAZanXhcTqolBolIDhcHe.YSj3lGrgUq7.hL8CyJtG49DcdXDwgi', 'RavieTest');


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_role VALUES (1, 'USER');
INSERT INTO public.user_role VALUES (1, 'ADMIN');
INSERT INTO public.user_role VALUES (2, 'USER');
INSERT INTO public.user_role VALUES (2, 'ADMIN');
INSERT INTO public.user_role VALUES (7, 'USER');
INSERT INTO public.user_role VALUES (9, 'USER');


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 76, true);


--
-- Name: department department_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (id);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: logins logins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.logins
    ADD CONSTRAINT logins_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: department dep_author_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT dep_author_fk FOREIGN KEY (author_id) REFERENCES public.logins(id);


--
-- Name: employee employee_author_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_author_fk FOREIGN KEY (author_id) REFERENCES public.logins(id);


--
-- Name: employee employee_dep_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_dep_fk FOREIGN KEY (department_id) REFERENCES public.department(id);


--
-- Name: user_role user_role_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_user_fk FOREIGN KEY (user_id) REFERENCES public.logins(id);


--
-- PostgreSQL database dump complete
--

EOSQL