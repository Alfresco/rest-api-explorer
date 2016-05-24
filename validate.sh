#! /bin/bash
set -e

for filename in src/main/webapp/definitions/*.yaml; do
      docker run -v $(pwd):/app:rw alfresco/swagger-tester:1.0 swagger validate "/app/$filename"
done
