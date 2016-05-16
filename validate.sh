#! /bin/bash
docker run -it -v $(pwd):/app:rw --rm alfresco/swagger-tester:1.0 swagger validate /app/src/main/webapp/definitions/alfresco-core.yaml
docker run -it -v $(pwd):/app:rw --rm alfresco/swagger-tester:1.0 swagger validate /app/src/main/webapp/definitions/alfresco-auth.yaml
docker run -it -v $(pwd):/app:rw --rm alfresco/swagger-tester:1.0 swagger validate /app/src/main/webapp/definitions/alfresco-workflow.yaml
