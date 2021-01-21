#!/usr/bin/env bash

readonly RELEASE_VERSION=$1
readonly GITHUB_EMAIL=$2
readonly GITHUB_PASSWORD=$3
readonly GITHUB_TOKEN=$4

echo "RELEASE VERSION: $RELEASE_VERSION"

#mvn -DreleaseVersion=${release.version} -DignoreSnapshots -DdevelopmentVersion=${next.version} release:prepare release:perform
