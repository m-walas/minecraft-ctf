FROM openjdk:8u181-jre

WORKDIR /data

COPY ./server.properties .
COPY ./eula.txt .

# Amazon has no active link
# COPY ./paper-1.8.8-443.jar ./server.jar 

# Constant errors during startup
# COPY ./server_1.8.8.jar ./server.jar

COPY ./minecraft-server-1.8.8.jar ./server.jar


CMD ["java", "-Xms2G", "-Xmx2G", "-jar", "server.jar"]
