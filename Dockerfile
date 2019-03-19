FROM openjdk:8-jre-alpine

ENV VERTICLE_FILE gerenciador-clientes-restfull-0.1-fat.jar

ENV VERTICLE_HOME /usr/verticles/

COPY build/libs/$VERTICLE_FILE $VERTICLE_HOME/

WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $VERTICLE_FILE"]
