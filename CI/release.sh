#!/usr/bin/env bash

# EXAMPLE: "7.0.0-A1"
echo "RELEASE VERSION: ${RELEASE_VERSION}"

# EXAMPLE: "7.0.0-SNAPSHOT"
echo "DEVELOPMENT VERSION: ${DEVELOPMENT_VERSION}"

if [ -z "${RELEASE_VERSION}" ] || [ -z "${DEVELOPMENT_VERSION}" ]; then
  echo "Please provide a Release and Development version in the format <acs-version>-<additional-info> (7.0.0-EA or 7.0.0-SNAPSHOT)"
  exit 1
fi

# Use full history for release
git checkout -B master

mvn release:clean release:prepare release:perform \
      -B \
      -DskipTests \
      -Dbuild-number="${BUILD_NUMBER}" \
      -DreleaseVersion="${RELEASE_VERSION}" \
      -DignoreSnapshots \
      -DdevelopmentVersion="${DEVELOPMENT_VERSION}" \
      -DscmCommentPrefix="[maven-release-plugin][skip ci] " \
      -Dusername="${GIT_USERNAME}" \
      -Dpassword="${GIT_PASSWORD}"
