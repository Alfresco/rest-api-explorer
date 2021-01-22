#!/usr/bin/env bash

readonly RELEASE_VERSION=$1
readonly GITHUB_EMAIL=$2
readonly GITHUB_PASSWORD=$3

echo "RELEASE VERSION: $RELEASE_VERSION"

# Needed for testing it from the dev branch
#git checkout -f master

# Use full history for release
git checkout -B "${TRAVIS_BRANCH}"
# Add email to link commits to user
git config user.email "${GIT_EMAIL}"



mvn release:clean release:prepare release:perform \
      -B \
      -DskipTests \
      -Dbuild-number="${TRAVIS_BUILD_NUMBER}" \
      -DreleaseVersion="$RELEASE_VERSION" \
      -DignoreSnapshots \
      -DdevelopmentVersion="-SNAPSHOT" \
      -DscmCommentPrefix="[maven-release-plugin][skip ci] " \
      -Dusername="${GIT_EMAIL}" \
      -Dpassword="${GIT_PASSWORD}"
