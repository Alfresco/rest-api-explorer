FROM tomcat:8.5.20-jre8-alpine

COPY target/api-explorer.war /usr/local/tomcat/webapps/api-explorer.war