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
DEFS_DEST_DIR="$ARCHIVE_DEST_DIR/dist"
SWAGGER_INIT_DEST_DIR="$ARCHIVE_DEST_DIR/dist/swagger-initializer.js"

SWAGGER_UI_DOWNLOAD_URL="https://github.com/swagger-api/swagger-ui/archive/refs/tags/v${SWAGGER_UI_VERSION}.tar.gz"

cd "$BUILD_DIR"

echo "Cleaning up previous build workspace at $ARCHIVE_DEST_DIR"
rm -rf "$ARCHIVE_DEST_DIR"

mkdir -p "$ARCHIVE_DEST_DIR"
curl -sfL "$SWAGGER_UI_DOWNLOAD_URL" | tar -xz -C "$ARCHIVE_DEST_DIR" \
  --strip-components=1 swagger-ui-$SWAGGER_UI_VERSION/dist/

mkdir -p "$DEFS_DEST_DIR"
cp ./../../src/main/webapp/definitions/*.yaml "$DEFS_DEST_DIR"/
cp ./swagger-initializer.js "$SWAGGER_INIT_DEST_DIR"

AGS_DEFS_FILE="gs-classification-api.yaml"
AGS_DEFS_PATH="./../../src/main/webapp/definitions/$AGS_DEFS_FILE"

# Remove AGS entry from swagger-initializer.js if definition was not available
if [ ! -f "$DEFS_DEST_DIR/$AGS_DEFS_FILE" ]; then
  echo "Removing AGS entry from swagger-initializer.js"
  sed -i.bak "/$AGS_DEFS_FILE/d" "$SWAGGER_INIT_DEST_DIR"
  rm -f "$SWAGGER_INIT_DEST_DIR.bak"
fi

echo "Build completed at $ARCHIVE_DEST_DIR/dist"
echo "Verifying that all definitions files are present in $SWAGGER_INIT_DEST_DIR"

for file in "$DEFS_DEST_DIR"/*.yaml; do
  filename=$(basename "$file")
  if ! grep -Fq "$filename" "$SWAGGER_INIT_DEST_DIR"; then
    echo "Error: $filename is not present in $SWAGGER_INIT_DEST_DIR"
    exit 1
  fi
done

echo "All definitions files are present in $SWAGGER_INIT_DEST_DIR"
