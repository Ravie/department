FROM department_postgresdb

RUN test -d /docker-entrypoint-initdb.d || mkdir /docker-entrypoint-initdb.d
COPY init.sh /docker-entrypoint-initdb.d/init.sh
