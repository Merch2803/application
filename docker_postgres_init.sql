CREATE USER merch WITH PASSWORD 'merch' CREATEDB;

CREATE DATABASE my_office
    WITH
        OWNER = merch
        ENCODING = 'UTF8'
        LC_COLLATE = 'en_US.utf8'
        LC_CTYPE = 'en_US.utf8'
        TABLESPACE = pg_default
        CONNECTION LIMIT = -1;