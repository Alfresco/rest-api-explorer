FROM tomcat:9.0.65-jre17-temurin-focal

COPY target/api-explorer.war /usr/local/tomcat/webapps/api-explorer.war