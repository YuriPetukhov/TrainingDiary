DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_user WHERE usename = 'YuriPetukhov') THEN
        CREATE USER YuriPetukhov WITH PASSWORD 'YuriPetukhov';
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_database WHERE datname = 'training_diary') THEN
        CREATE DATABASE training_diary
            WITH OWNER = YuriPetukhov
            ENCODING = 'UTF8'
            LC_COLLATE = 'en_US.utf8'
            LC_CTYPE = 'en_US.utf8'
            TABLESPACE = pg_default
            CONNECTION LIMIT = -1
            TEMPLATE template0;
    END IF;
END $$;
CREATE SCHEMA service_schema;
CREATE SCHEMA diary_schema;
