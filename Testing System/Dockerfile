FROM maven:3.6.3-jdk-8 as build
WORKDIR /work
ADD src/ ./src/
ADD pom.xml ./
RUN mvn compile war:war

FROM tomcat:9.0-jdk8-openjdk
COPY --from=build /work/target/testingsystem.war /usr/local/tomcat/webapps/
ADD server.xml /usr/local/tomcat/conf/
CMD ["catalina.sh", "run"]
