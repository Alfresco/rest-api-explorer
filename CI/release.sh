#!/usr/bin/env bash

echo "RELEASE VERSION: ${RELEASE_VERSION}"

#mvn -DreleaseVersion=${release.version} -DignoreSnapshots -DdevelopmentVersion=${next.version} release:prepare release:perform
