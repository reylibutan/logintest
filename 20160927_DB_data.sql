--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.0
-- Started on 2016-09-27 17:17:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE data;
--
-- TOC entry 1996 (class 1262 OID 41612)
-- Name: data; Type: DATABASE; Schema: -; Owner: data_admin
--

CREATE DATABASE data WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE data OWNER TO data_admin;

\connect data

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 7 (class 2615 OID 41613)
-- Name: data; Type: SCHEMA; Schema: -; Owner: data_admin
--

CREATE SCHEMA data;


ALTER SCHEMA data OWNER TO data_admin;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 1997 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 174 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1999 (class 0 OID 0)
-- Dependencies: 174
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = data, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 41614)
-- Name: login; Type: TABLE; Schema: data; Owner: data_admin; Tablespace: 
--

CREATE TABLE login (
    login_time timestamp without time zone NOT NULL,
    "user" character varying(255) NOT NULL,
    attribute1 character varying(255),
    attribute2 character varying(255),
    attribute3 character varying(255),
    attribute4 character varying(255)
);


ALTER TABLE login OWNER TO data_admin;

--
-- TOC entry 1882 (class 2606 OID 41621)
-- Name: login_loginTime_user_pkey; Type: CONSTRAINT; Schema: data; Owner: data_admin; Tablespace: 
--

ALTER TABLE ONLY login
    ADD CONSTRAINT "login_loginTime_user_pkey" PRIMARY KEY (login_time, "user");


--
-- TOC entry 1998 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-09-27 17:17:41

--
-- PostgreSQL database dump complete
--

