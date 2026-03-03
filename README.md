# Welcome to the Alfresco REST API Explorer

[![Build Status](https://github.com/Alfresco/rest-api-explorer/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/Alfresco/rest-api-explorer/actions/workflows/ci.yml)
[![Publish to GitHub Pages](https://github.com/Alfresco/rest-api-explorer/actions/workflows/gh-pages.yml/badge.svg)](https://github.com/Alfresco/rest-api-explorer/actions/workflows/gh-pages.yml)
![GitHub](https://img.shields.io/github/license/Alfresco/rest-api-explorer?color=brightgreen)

The API Explorer allows you to browse and experiment with the REST APIs available in the Alfresco platform.

## Quickstart

You can find the pre-built Docker image on Docker Hub:
[alfresco/rest-api-explorer](https://hub.docker.com/r/alfresco/rest-api-explorer).

You can browse the latest GitHub Pages version of the API Explorer at
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

Build the Docker image:

```sh
docker build -t api-explorer .
```

Run the Docker container:

```sh
docker run -p 8080:8080 api-explorer
```

## License

See [LICENSE](LICENSE) file for details.
