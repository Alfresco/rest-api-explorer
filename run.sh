mvn package
docker build . -t alfresco/rest-api-explorer:latest
docker run -p 9090:8080 alfresco/rest-api-explorer