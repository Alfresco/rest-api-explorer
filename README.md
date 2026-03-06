# Welcome to the Alfresco REST API Explorer

[![Build Status](https://github.com/Alfresco/rest-api-explorer/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/Alfresco/rest-api-explorer/actions/workflows/ci.yml)
[![Publish to GitHub Pages](https://github.com/Alfresco/rest-api-explorer/actions/workflows/gh-pages.yml/badge.svg)](https://github.com/Alfresco/rest-api-explorer/actions/workflows/gh-pages.yml)
![GitHub](https://img.shields.io/github/license/Alfresco/rest-api-explorer?color=brightgreen)

The API Explorer allows you to browse and experiment with the REST APIs available in the Alfresco platform.

## Quickstart

You can find the pre-built Docker image on Docker Hub:
[alfresco/rest-api-explorer](https://hub.docker.com/r/alfresco/rest-api-explorer).

To run the latest built API Explorer on `localhost:8080` using Docker, use the
following command:

```sh
docker run -p 8080:8080 alfresco/rest-api-explorer:master
```

You can also browse the latest GitHub Pages version of the API Explorer directly at
[https://alfresco.github.io/rest-api-explorer/](https://alfresco.github.io/rest-api-explorer/).

## Development

### Building WAR file

- `mvn install`

You now have a `target/api-explorer.war`, drop this into your Application server that is running alfresco.war

### Running via local Jetty

You can run the project as a packaged web application using an embedded Jetty server.
This is useful for changing documentation and endpoint descriptions but it means that the "Try it Out!" button will not work.

- `mvn jetty:run-war`

Now the application is running at [http://localhost:8085/api-explorer](http://localhost:8085/api-explorer/)

### Building for GitHub Pages

The GitHub Pages build is located in the `.github/gh-pages` directory. It is
suitable if you want something quick and easy to test locally without any java
dependencies. More details in the [GitHub Pages Build Scripts](.github/gh-pages/README.md) section.

### Building with Docker

You can build a Docker image of the API Explorer and run it in a container.

Note: For a local version with AGS API definition, you will need to have the
`gs-classification-api.yaml` file in the `src/main/webapp/definitions/`
directory before building the Docker image. You can fetch it using the GitHub
CLI:

```sh
gh api "repos/Alfresco/alfresco-enterprise-repo/contents/amps/ags/rm-enterprise/rm-enterprise-rest-api-explorer/src/main/webapp/definitions/gs-classification-api.yaml?ref=master" \
  -H "Accept: application/vnd.github.v3.raw" > src/main/webapp/definitions/gs-classification-api.yaml
```

Build the Docker image:

```sh
docker build -t api-explorer . --load
```

Run the Docker container:

```sh
docker run -p 8080:8080 api-explorer
```

Browse the API Explorer at [http://localhost:8080](http://localhost:8080).

## License

See [LICENSE](LICENSE) file for details.
