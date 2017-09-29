#! /bin/bash
set -e

echo "Validating OpenAPI specs..."

for filename in src/main/webapp/definitions/*.yaml; do
      docker run -v $(pwd):/app:rw docker-internal.alfresco.com/swagger-tester:1.0.0-beta.2 swagger validate "/app/$filename"
done

echo
echo "Removing exited docker images..."
docker rm -v $(docker ps -a -q -f status=exited)
