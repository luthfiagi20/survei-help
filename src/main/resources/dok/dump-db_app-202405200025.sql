--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

-- Started on 2024-05-20 00:25:08

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
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3345 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 25534)
-- Name: dat_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dat_log (
    id_dat_log character varying(50) NOT NULL,
    id_ms_user character varying(100),
    aksi character varying(30),
    table_terdampak character varying(30),
    id_data character varying(50),
    ip character varying(20),
    "timestamp" timestamp with time zone,
    ket character varying(50)
);


ALTER TABLE public.dat_log OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 25522)
-- Name: ms_menu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ms_menu (
    id_ms_menu bigint NOT NULL,
    fg_aktif character varying(255),
    have_child character varying(255),
    icon character varying(255),
    id_parent bigint,
    link character varying(255),
    nama character varying(255),
    urutan_per_level bigint
);


ALTER TABLE public.ms_menu OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 25543)
-- Name: ms_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ms_role (
    id_ms_role character varying(30) NOT NULL,
    nama_role character varying(100) NOT NULL
);


ALTER TABLE public.ms_role OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 25561)
-- Name: ms_role_menu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ms_role_menu (
    id_ms_role_menu character varying(50) NOT NULL,
    id_ms_role character varying NOT NULL,
    id_ms_menu bigint NOT NULL
);


ALTER TABLE public.ms_role_menu OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 25529)
-- Name: ms_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ms_user (
    id_ms_user character varying(100) NOT NULL,
    fg_aktif character varying(2),
    nama character varying(60),
    pwd character varying(255),
    role character varying(20),
    telp character varying(60),
    last_login_ip character varying(20),
    last_login_time timestamp with time zone
);


ALTER TABLE public.ms_user OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 25568)
-- Name: ms_user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ms_user_role (
    id_ms_user_role character varying NOT NULL,
    id_ms_user character varying NOT NULL,
    id_ms_role character varying NOT NULL
);


ALTER TABLE public.ms_user_role OWNER TO postgres;

--
-- TOC entry 3336 (class 0 OID 25534)
-- Dependencies: 211
-- Data for Name: dat_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.dat_log VALUES ('3a99445e-268e-4277-8266-7884d4701019', 'adminih', 'Simpan Data', 'ms_user', 'tes3', '0:0:0:0:0:0:0:1', '2024-05-19 10:08:06.356-07', 'Tambah data PIC baru');
INSERT INTO public.dat_log VALUES ('5750d8a1-b15f-47fa-9e0f-8288007bd9d3', 'adminih', 'Update Data', 'ms_user', 'adminih', '0:0:0:0:0:0:0:1', '2024-05-19 10:08:51.985-07', 'Reset password pengguna');
INSERT INTO public.dat_log VALUES ('9960229f-77ae-4ccc-bfa1-e195c178ad69', 'tes3', 'Update Data', 'ms_user', 'adminih', '0:0:0:0:0:0:0:1', '2024-05-19 10:08:55.218-07', 'Reset password pengguna');
INSERT INTO public.dat_log VALUES ('d6fecf05-99b2-4d51-a9ef-9cec8cf54f05', 'adminih', 'Simpan Data', 'ms_user', 'terus', '0:0:0:0:0:0:0:1', '2024-05-19 10:21:59.205-07', 'Tambah data PIC baru');


--
-- TOC entry 3334 (class 0 OID 25522)
-- Dependencies: 209
-- Data for Name: ms_menu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ms_menu VALUES (2, '1', '0', 'edit_note', 1, 'menu1/rekam', 'Rekam Data', 1);
INSERT INTO public.ms_menu VALUES (4, '1', '0', 'format_list_bulleted', 1, 'menu1/daftar-data', 'Daftar Data', 2);
INSERT INTO public.ms_menu VALUES (6, '1', '0', 'edit_note', 5, 'menu2/rekam', 'Rekam Data', 1);
INSERT INTO public.ms_menu VALUES (7, '1', '0', 'format_list_bulleted', 5, 'menu2/daftar-data', 'Daftar Data', 2);
INSERT INTO public.ms_menu VALUES (8, '1', '0', 'assignment_ind', 0, 'admin/user', 'Admin Pengguna', 1);
INSERT INTO public.ms_menu VALUES (1, '1', '1', 'gavel', 0, 'menu1', 'Menu Satu', 3);
INSERT INTO public.ms_menu VALUES (3, '1', '0', 'description', 0, 'todolist', 'Daftar Pekerjaan', 2);
INSERT INTO public.ms_menu VALUES (5, '1', '1', 'pan_tool', 0, 'menu2', 'Menu Dua', 4);


--
-- TOC entry 3337 (class 0 OID 25543)
-- Dependencies: 212
-- Data for Name: ms_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ms_role VALUES ('ROLE_ADMIN', 'Administrator Aplikasi');
INSERT INTO public.ms_role VALUES ('ROLE_USER', 'Pengguna');


--
-- TOC entry 3338 (class 0 OID 25561)
-- Dependencies: 213
-- Data for Name: ms_role_menu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ms_role_menu VALUES ('8', 'ROLE_ADMIN', 8);
INSERT INTO public.ms_role_menu VALUES ('1', 'ROLE_USER', 1);
INSERT INTO public.ms_role_menu VALUES ('2', 'ROLE_USER', 2);
INSERT INTO public.ms_role_menu VALUES ('3', 'ROLE_USER', 3);
INSERT INTO public.ms_role_menu VALUES ('4', 'ROLE_USER', 4);
INSERT INTO public.ms_role_menu VALUES ('5', 'ROLE_USER', 5);
INSERT INTO public.ms_role_menu VALUES ('6', 'ROLE_USER', 6);
INSERT INTO public.ms_role_menu VALUES ('7', 'ROLE_USER', 7);


--
-- TOC entry 3335 (class 0 OID 25529)
-- Dependencies: 210
-- Data for Name: ms_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ms_user VALUES ('tes3', '1', 'Tes Dulu YA', '$2a$10$ZefyUa04tXV3Zhq6kpO5KemGjzRXEVB4v0wGo0eWeEBqwoXj/5MI6', 'ROLE_USER', '23423525', '0:0:0:0:0:0:0:1', '2024-05-19 10:10:17.936-07');
INSERT INTO public.ms_user VALUES ('adminih', '1', 'Babang Tamvan', '$2a$10$UGHp8r69YJn7rMz5bU3fsOPwQy5MBEUvWOjmZeLGVr2hW7Tq1yTR6', 'ROLE_ADMIN', '88888888', '0:0:0:0:0:0:0:1', '2024-05-19 10:21:41.975-07');
INSERT INTO public.ms_user VALUES ('terus', '1', 'terus', '$2a$10$FrSWJ9Yq3zSDB07t49pZ/OHNjfwhw6/A28I0MlJG40N/qOIU1wCl2', NULL, 'w3423', NULL, NULL);


--
-- TOC entry 3339 (class 0 OID 25568)
-- Dependencies: 214
-- Data for Name: ms_user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ms_user_role VALUES ('1', 'adminih', 'ROLE_ADMIN');
INSERT INTO public.ms_user_role VALUES ('2', 'adminih', 'ROLE_USER');
INSERT INTO public.ms_user_role VALUES ('3', 'tes3', 'ROLE_USER');
INSERT INTO public.ms_user_role VALUES ('dbd6c071-cbec-419b-99ca-f7574778c52d', 'terus', 'ROLE_USER');


--
-- TOC entry 3188 (class 2606 OID 25538)
-- Name: dat_log dat_log_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dat_log
    ADD CONSTRAINT dat_log_pk PRIMARY KEY (id_dat_log);


--
-- TOC entry 3184 (class 2606 OID 25528)
-- Name: ms_menu ms_menu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ms_menu
    ADD CONSTRAINT ms_menu_pkey PRIMARY KEY (id_ms_menu);


--
-- TOC entry 3186 (class 2606 OID 25540)
-- Name: ms_user ms_pic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ms_user
    ADD CONSTRAINT ms_pic_pkey PRIMARY KEY (id_ms_user);


--
-- TOC entry 3192 (class 2606 OID 25598)
-- Name: ms_role_menu ms_role_menu_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ms_role_menu
    ADD CONSTRAINT ms_role_menu_pk PRIMARY KEY (id_ms_role_menu);


--
-- TOC entry 3190 (class 2606 OID 25547)
-- Name: ms_role ms_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ms_role
    ADD CONSTRAINT ms_role_pkey PRIMARY KEY (id_ms_role);


--
-- TOC entry 3194 (class 2606 OID 25574)
-- Name: ms_user_role ms_user_role_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ms_user_role
    ADD CONSTRAINT ms_user_role_pk PRIMARY KEY (id_ms_user_role);


-- Completed on 2024-05-20 00:25:09

--
-- PostgreSQL database dump complete
--

