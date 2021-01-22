#!/usr/bin/env bash

echo "RELEASE VERSION: $RELEASE_VERSION"

# Needed for testing it from the dev branch
#git checkout -f master

# Use full history for release
git checkout -B "${TRAVIS_BRANCH}"
# Add email to link commits to user
git config user.email "${GITHUB_EMAIL}"



mvn release:clean release:prepare release:perform \
      -B \
      -DskipTests \
      -Dbuild-number="${TRAVIS_BUILD_NUMBER}" \
      -DreleaseVersion="$RELEASE_VERSION" \
      -DignoreSnapshots \
      -DdevelopmentVersion="-SNAPSHOT" \
      -DscmCommentPrefix="[maven-release-plugin][skip ci] " \
      -Dusername="${GITHUB_USERNAME}" \
      -Dpassword="${GITHUB_PASSWORD}"
