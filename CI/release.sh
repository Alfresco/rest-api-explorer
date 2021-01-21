#!/usr/bin/env bash

readonly RELEASE_VERSION=$1

echo "RELEASE VERSION: $RELEASE_VERSION"

#mvn -DreleaseVersion=${release.version} -DignoreSnapshots -DdevelopmentVersion=${next.version} release:prepare release:perform
