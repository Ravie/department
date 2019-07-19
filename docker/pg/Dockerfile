FROM postgres:latest

COPY V1__InitDb.sql /docker-entrypoint-initdb.d/
COPY load_data.sql /docker-entrypoint-initdb.d/
