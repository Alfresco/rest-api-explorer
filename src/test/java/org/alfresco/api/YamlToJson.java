/*
 * Copyright (C) 2005-2016 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfresco.api;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import io.swagger.util.Json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Generates the Json version of the Yaml file
 *
 * @author Gethin James
 */
public class YamlToJson {

    public static final String JSON_DESTINATION = "/target/api-explorer/definitions/";
    public static final String CORE_DEFINITION = "/src/main/webapp/definitions/alfresco-core.yaml";
    public static final String CORE_JSON_DEFINITION = JSON_DESTINATION + "alfresco-core.json";
    public static final String CORE_DEFINITION_TITLE = "Alfresco Core REST API";

    public static final String WORKFLOW_DEFINITION = "/src/main/webapp/definitions/alfresco-workflow.yaml";
    public static final String WORKFLOW_JSON_DEFINITION = JSON_DESTINATION + "alfresco-workflow.json";
    public static final String WORKFLOW_DEFINITION_TITLE = "Alfresco Workflow REST API";

    public static void main(String[] args) {

        String rootPath = args[0];

        try {
            if (new File(rootPath+JSON_DESTINATION ).mkdirs()) {

                //Core
                Swagger swagger = parseSwaggerDef(new File(rootPath+CORE_DEFINITION), CORE_DEFINITION_TITLE);
                new PrintWriter(rootPath+CORE_JSON_DEFINITION ).println(Json.pretty(swagger));

                //Workflow
                swagger = parseSwaggerDef(new File(rootPath+WORKFLOW_DEFINITION), WORKFLOW_DEFINITION_TITLE);
                new PrintWriter(rootPath+WORKFLOW_JSON_DEFINITION ).println(Json.pretty(swagger));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Failed to create a json definitions: "+e.getLocalizedMessage());
            System.exit(1);
        }

    }

    protected static Swagger parseSwaggerDef(File coreFile, String title) {
        Swagger swagger = new SwaggerParser().read(coreFile.getAbsolutePath());
        assert swagger != null;
        assert title.equals(swagger.getInfo().getTitle());
        return swagger;
    }
}
