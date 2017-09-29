#! /bin/bash
set -e

for filename in src/main/webapp/definitions/*.yaml; do
      docker run -v $(pwd):/app:rw docker-internal.alfresco.com/swagger-tester:1.0.0-beta.2 swagger validate "/app/$filename"
done
