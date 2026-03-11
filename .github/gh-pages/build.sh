#!/usr/bin/env bash
set -euo pipefail
SWAGGER_UI_VERSION="${SWAGGER_UI_VERSION:-4.19.1}"

# Ensure required tools are available
if ! command -v curl >/dev/null 2>&1; then
  echo "Error: 'curl' is required but not installed. Install curl and retry." >&2
  exit 1
fi

BUILD_DIR=$(dirname "$0")
ARCHIVE_DEST_DIR="./build"
SWAGGER_UI_DEST_DIR="$ARCHIVE_DEST_DIR/api-explorer"
SWAGGER_INIT_JS_PATH="$SWAGGER_UI_DEST_DIR/swagger-initializer.js"
SWAGGER_UI_DOWNLOAD_URL="https://github.com/swagger-api/swagger-ui/archive/refs/tags/v${SWAGGER_UI_VERSION}.tar.gz"

cd "$BUILD_DIR"

echo "Cleaning up previous build workspace at $ARCHIVE_DEST_DIR"
rm -rf "$ARCHIVE_DEST_DIR"
mkdir -p "$ARCHIVE_DEST_DIR"

echo "Create index.html with redirect to api-explorer/ folder"
cat > "$ARCHIVE_DEST_DIR/index.html" <<EOF
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="refresh" content="0; url=api-explorer/">
  <title>Redirecting to API Explorer</title>
</head>
<body>
  <p>Redirecting to <a href="api-explorer/">API Explorer</a>...</p>
</body>
</html>
EOF

echo "Creating Swagger UI directory at $SWAGGER_UI_DEST_DIR"
mkdir -p "$SWAGGER_UI_DEST_DIR"

echo "Downloading Swagger UI version $SWAGGER_UI_VERSION and unpacking to $SWAGGER_UI_DEST_DIR"
curl -sfL "$SWAGGER_UI_DOWNLOAD_URL" | tar -xz -C "$SWAGGER_UI_DEST_DIR" \
  --strip-components=2 swagger-ui-$SWAGGER_UI_VERSION/dist/

echo "Copying API definitions and initializer config to $SWAGGER_UI_DEST_DIR"
cp ./../../src/main/webapp/definitions/*.yaml "$SWAGGER_UI_DEST_DIR"/
cp ./swagger-initializer.js "$SWAGGER_INIT_JS_PATH"

echo "Build completed at $SWAGGER_UI_DEST_DIR"
echo "Verifying that all definitions files are present in $SWAGGER_INIT_JS_PATH"

for file in "$SWAGGER_UI_DEST_DIR"/*.yaml; do
  filename=$(basename "$file")
  if ! grep -Fq "$filename" "$SWAGGER_INIT_JS_PATH"; then
    echo "Error: $filename is not present in $SWAGGER_INIT_JS_PATH"
    exit 1
  fi
done

echo "All definitions files are present in $SWAGGER_INIT_JS_PATH"
