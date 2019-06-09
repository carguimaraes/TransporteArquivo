#FROM java:8
FROM openjdk:8-jre
MAINTAINER Carlos A L M Guimaraes

#RUN apt-get update && apt-get add tzdata &&\ 
#    cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime &&\ 
#    echo "America/Sao_Paulo" > /etc/timezone &&\ 
#    apt-get del tzdata && rm -rf /var/cache/apk/* 

RUN apt-get update && apt-get -y install cron && apt-get -y install procps

COPY mycron /etc/cron.d/mycron
RUN chmod 0644 /etc/cron.d/mycron

RUN mkdir -p /ftp/dados

WORKDIR /app
COPY transportearquivogma-0.0.1.jar .
#COPY exej.sh .
#RUN  chmod a+x exej.sh
RUN service cron start 
RUN crontab /etc/cron.d/mycron




 


