FROM openjdk:15

WORKDIR /app

ADD ./wait /wait

RUN chmod +x /wait&&\
    cp  /usr/share/zoneinfo/Asia/Tokyo /etc/localtime

EXPOSE 80

CMD [ "java" ,"Main"]