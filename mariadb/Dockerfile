FROM mariadb:latest

ADD ./conf/my.cnf /etc/mysql/conf.d/my.cnf
ADD ./conf/hosts /etc/hosts
ADD ./conf/access.cnf /home/access.cnf

RUN ln -sf  /usr/share/zoneinfo/Asia/Tokyo /etc/localtime &&\
    chmod 644 /etc/mysql/conf.d/my.cnf

EXPOSE 3306

# CMD [ "mysql" ]