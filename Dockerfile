FROM neovn/oracle-jdk:1.8.301
#RUN useradd -ms /bin/bash root
USER root
WORKDIR /app
COPY ./target/*1.0.jar open-identity.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dlog4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector","-jar" ,"open-identity.jar"]