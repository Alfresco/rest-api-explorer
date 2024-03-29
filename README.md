# Welcome to the Alfresco REST API Explorer

[![Build Status](https://github.com/Alfresco/rest-api-explorer/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/Alfresco/rest-api-explorer/actions/workflows/ci.yml)
[![pre-commit](https://img.shields.io/badge/pre--commit-enabled-brightgreen?logo=pre-commit&logoColor=white)](https://github.com/pre-commit/pre-commit)
![GitHub](https://img.shields.io/github/license/Alfresco/rest-api-explorer?color=brightgreen)

The API Explorer allows you to browse and experiment with the REST APIs available in the Alfresco platform.

#### Building and deploying the war
- `mvn install`

You now have a `target/api-explorer.war`, drop this into your Application server that is running alfresco.war

#### For development only
You can run the project as a packaged web application using an embedded Jetty server.  
This is useful for changing documentation and endpoint descriptions but it means that the "Try it Out!" button will not work.

- `mvn jetty:run-war`

Now the application is running at [http://localhost:8085/api-explorer](http://localhost:8085/api-explorer/)

### License
Copyright (C) 2016-2021 Alfresco Software Limited

This file is part of an unsupported extension to Alfresco.

Alfresco Software Limited licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
