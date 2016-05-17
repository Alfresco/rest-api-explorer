#! /bin/bash
docker run -v $(pwd):/app:rw alfresco/swagger-tester:1.0 swagger validate /app/src/main/webapp/definitions/alfresco-core.yaml
docker run -v $(pwd):/app:rw alfresco/swagger-tester:1.0 swagger validate /app/src/main/webapp/definitions/alfresco-auth.yaml
docker run -v $(pwd):/app:rw alfresco/swagger-tester:1.0 swagger validate /app/src/main/webapp/definitions/alfresco-workflow.yaml
