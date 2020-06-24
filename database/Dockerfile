FROM mariadb

COPY *.sql /docker-entrypoint-initdb.d/

ENV MYSQL_DATABASE=testing_system

EXPOSE 3306

CMD ["mysqld"]
