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
package org.alfresco.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import io.swagger.util.Json;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * Test to verify the API Explorer WAR is functioning correctly.
 *
 * @author Gavin Cornwell
 */
public class APIExplorerIntegrationTest
{
    static final String YAML = ".yaml";
    static final String JSON = ".json";

    @Test
    public void testIndexPage() throws Exception
    {
        // get index page content
        String indexPageContent = this.retrievePageContent("http://localhost:8085/api-explorer", 200);
        
        // make sure the content is correct
        int titleIndex = indexPageContent.indexOf("<title>Alfresco Content Services REST API Explorer</title>");
        assertTrue("Expected to find page title", titleIndex != -1);
        
        int coreAPIIndex = indexPageContent.indexOf("<option value=\"definitions/alfresco-core.yaml\">Core API</option>");
        assertTrue("Expected to find Core API option", coreAPIIndex != -1);
        
        int workflowAPIIndex = indexPageContent.indexOf("<option value=\"definitions/alfresco-workflow.yaml\">Workflow API</option>");
        assertTrue("Expected to find Workflow API option", workflowAPIIndex != -1);

        int authAPIIndex = indexPageContent.indexOf("<option value=\"definitions/alfresco-auth.yaml\">Authentication API</option>");
        assertTrue("Expected to find Authentication API option", authAPIIndex != -1);

        int searchAPIIndex = indexPageContent.indexOf("<option value=\"definitions/alfresco-search.yaml\">Search API</option>");
        assertTrue("Expected to find Search API option", searchAPIIndex != -1);

        int discoveryAPIIndex = indexPageContent.indexOf("<option value=\"definitions/alfresco-discovery.yaml\">Discovery API</option>");
        assertTrue("Expected to find Discovery API option", discoveryAPIIndex != -1);
    }
    
    @Test
    public void testCoreAPIDefinition() throws Exception
    {
        String coreDefinitionUrl = "http://localhost:8085/api-explorer/definitions/alfresco-core";
        
        // get core definition content
        String coreDefinitionContent = this.retrievePageContent(coreDefinitionUrl+YAML, 200);
        
        // make sure the content is correct
        int swaggerIndex = coreDefinitionContent.indexOf("swagger:");
        assertTrue("Expected to find 'swagger:'", swaggerIndex != -1);
        
        int nodeInfoEndpointIndex = coreDefinitionContent.indexOf("'/nodes/{nodeId}':");
        assertTrue("Expected to find '/nodes/{nodeId}':", nodeInfoEndpointIndex != -1);
        
        int commentsEndpointIndex = coreDefinitionContent.indexOf("'/nodes/{nodeId}/comments':");
        assertTrue("Expected to find '/nodes/{nodeId}/comments':", commentsEndpointIndex != -1);
        
        int sharedLinksEndpointIndex = coreDefinitionContent.indexOf("'/shared-links':");
        assertTrue("Expected to find '/shared-links':", sharedLinksEndpointIndex != -1);
        
        // ensure the core API definition can be read and parsed
        validateCoreDefn(coreDefinitionUrl+YAML);
        validateCoreDefn(coreDefinitionUrl+JSON);
    }

    private void validateCoreDefn(String coreDefinitionUrl) {
        Swagger swagger = validateSwaggerDef(coreDefinitionUrl, "Alfresco Content Services REST API", "Core API", "1");
        Map<String, Path> paths = swagger.getPaths();
        assertNotNull("Expected to retrieve a map of paths", paths);
        assertTrue("Expected to find /nodes/{nodeId} path", paths.containsKey("/nodes/{nodeId}"));
        assertTrue("Expected to find /nodes/{nodeId}/comments path", paths.containsKey("/nodes/{nodeId}/comments"));
        assertTrue("Expected to find /shared-links path", paths.containsKey("/shared-links"));
    }


    @Test
    public void testAuthAPIDefinition() throws Exception {
        String definitionUrl = "http://localhost:8085/api-explorer/definitions/alfresco-auth";

        // get definition content
        String definitionContent = this.retrievePageContent(definitionUrl + YAML, 200);
        // make sure the content is correct
        int swaggerIndex = definitionContent.indexOf("swagger:");
        assertTrue("Expected to find 'swagger:'", swaggerIndex != -1);

        validateAuthDefn(definitionUrl + YAML);
        validateAuthDefn(definitionUrl + JSON);
    }

    @Test
    public void testSearchAPIDefinition() throws Exception {
        String definitionUrl = "http://localhost:8085/api-explorer/definitions/alfresco-search";

        // get definition content
        String definitionContent = this.retrievePageContent(definitionUrl + YAML, 200);

        // make sure the content is correct
        int swaggerIndex = definitionContent.indexOf("swagger:");
        assertTrue("Expected to find 'swagger:'", swaggerIndex != -1);

        validateSearchDefn(definitionUrl + YAML);
        validateSearchDefn(definitionUrl + JSON);
    }

    private void validateAuthDefn(String definitionUrl) {
        Swagger swagger = validateSwaggerDef(definitionUrl, "Alfresco Content Services REST API", "Authentication API", "1");
        Map<String, Path> paths = swagger.getPaths();
        assertNotNull("Expected to retrieve a map of paths", paths);
        assertTrue("Expected to find /tickets path", paths.containsKey("/tickets"));
    }

    @Test
    public void testWorkflowAPIDefinition() throws Exception
    {
        String workflowDefinitionUrl = "http://localhost:8085/api-explorer/definitions/alfresco-workflow";
        
        // get workflow definition content
        String workflowDefinitionContent = this.retrievePageContent(workflowDefinitionUrl+YAML, 200);
        
        // make sure the content is correct
        int swaggerIndex = workflowDefinitionContent.indexOf("swagger:");
        assertTrue("Expected to find 'swagger:'", swaggerIndex != -1);
        
        int deploymentEndpointIndex = workflowDefinitionContent.indexOf("'/deployments/{deploymentId}':");
        assertTrue("Expected to find ''/deployments/{deploymentId}':", deploymentEndpointIndex != -1);
        
        // ensure the workflow API definition can be read and parsed
        validateWorkflow(workflowDefinitionUrl+YAML);
        validateWorkflow(workflowDefinitionUrl+JSON);
    }

    private void validateWorkflow(String workflowDefinitionUrl) {
        Swagger swagger = validateSwaggerDef(workflowDefinitionUrl, "Alfresco Content Services REST API", "Workflow API", "1");
        Map<String, Path> paths = swagger.getPaths();
        assertNotNull("Expected to retrieve a map of paths", paths);
        assertTrue("Expected to find /deployments/{deploymentId} path", paths.containsKey("/deployments/{deploymentId}"));
    }

    private void validateSearchDefn(String definitionUrl) {
        Swagger swagger = validateSwaggerDef(definitionUrl, "Alfresco Content Services REST API", "Search API", "1");
        Map<String, Path> paths = swagger.getPaths();
        assertNotNull("Expected to retrieve a map of paths", paths);
        assertTrue("Expected to find /search path", paths.containsKey("/search"));
    }

    @Test
    public void testDiscoveryAPIDefinition() throws Exception {
        String definitionUrl = "http://localhost:8085/api-explorer/definitions/alfresco-discovery";

        // get definition content
        String definitionContent = this.retrievePageContent(definitionUrl + YAML, 200);

        // make sure the content is correct
        int swaggerIndex = definitionContent.indexOf("swagger:");
        assertTrue("Expected to find 'swagger:'", swaggerIndex != -1);

        validateDiscoveryDefn(definitionUrl + YAML);
        validateDiscoveryDefn(definitionUrl + JSON);
    }

    private void validateDiscoveryDefn(String definitionUrl) {
        Swagger swagger = validateSwaggerDef(definitionUrl, "Alfresco Content Services REST API", "Discovery API", "1");
        Map<String, Path> paths = swagger.getPaths();
        assertNotNull("Expected to retrieve a map of paths", paths);
        assertTrue("Expected to find /discovery path", paths.containsKey("/discovery"));
    }

    private Swagger validateSwaggerDef(String definitionUrl, String title, String description, String version) {
        Swagger swagger = new SwaggerParser().read(definitionUrl);
        assertNotNull("Expected the API definition to parse successfully: "+ definitionUrl, swagger);
        assertEquals("Incorrect title", title, swagger.getInfo().getTitle());
        assertTrue("Expected description to contain: " + description, swagger.getInfo().getDescription().indexOf(description) != -1);
        assertEquals("Incorrect version", version, swagger.getInfo().getVersion());
        return swagger;
    }

    @Test
    public void testAllDefinitions() throws Exception
    {
        String defs = this.retrievePageContent("http://localhost:8085/api-explorer/definitions/index.jsp", 200);
        List<String> definitions = Json.mapper().readValue(defs, new TypeReference<List<String>>(){});
        assertNotNull(definitions);
        assertEquals("5 definitions in 2 formats should be 10.", 10, definitions.size());
    }

    public String retrievePageContent(String url, int expectedStatus) throws Exception
    {
        String pageContent = null;
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try 
        {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try 
            {
                // make sure the status was as expected
                int actualStatus = response.getStatusLine().getStatusCode();
                assertEquals("Expected status code of " + expectedStatus + " but received " + actualStatus, 
                            expectedStatus, actualStatus);
                
                // grab the page content
                HttpEntity entity = response.getEntity();
                StringWriter writer = new StringWriter();
                IOUtils.copy(entity.getContent(), writer, "UTF-8");
                pageContent = writer.toString();
                
                // ensure it is fully consumed
                EntityUtils.consume(entity);
            } 
            finally 
            {
                response.close();
            }
        } 
        finally 
        {
            httpclient.close();
        }
        
        // make sure we got some content
        assertNotNull("Expected content from the index page", pageContent);
        
        return pageContent;
    }
}
