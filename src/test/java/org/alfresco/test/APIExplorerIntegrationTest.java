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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.StringWriter;

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
    @Test
    public void testIndexPage() throws Exception
    {
        // get index page content
        String indexPageContent = this.retrievePageContent("http://localhost:8085/api-explorer", 200);
//        System.out.println(indexPageContent);
        
        // make sure the content is correct
        int titleIndex = indexPageContent.indexOf("<title>Alfresco API Explorer</title>");
        assertTrue("Expected to find page title", titleIndex != -1);
        
        int coreAPIIndex = indexPageContent.indexOf("<option value=\"definitions/alfresco-core.yaml\">Core API</option>");
        assertTrue("Expected to find Core API option", coreAPIIndex != -1);
        
        int workflowAPIIndex = indexPageContent.indexOf("<option value=\"definitions/alfresco-workflow.yaml\">Workflow API</option>");
        assertTrue("Expected to find Workflow API option", workflowAPIIndex != -1);
    }
    
    @Test
    public void testGetCommentsAPI() throws Exception
    {
        System.out.println("testGetCommentsAPI");
        
        // http://localhost:8085/api-explorer/#!/comments/getComments
    }
    
    @Test
    public void testCoreAPIDefinition() throws Exception
    {
        System.out.println("testCoreAPIDefinition");
        
        // http://localhost:8085/api-explorer/definitions/alfresco-core.yaml
        
        // TODO: consider testing the validity of the API definition
    }
    
    @Test
    public void testWorkflowAPIDefinition() throws Exception
    {
        System.out.println("testWorkflowAPIDefinition");
        
        // http://localhost:8085/api-explorer/definitions/alfresco-workflow.yaml
        
        // TODO: consider testing the validity of the API definition
    }
    
    public String retrievePageContent(String url, int expectedStatus) throws Exception
    {
        String pageContent = null;
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try 
        {
            System.out.println("Retrieving content for URL: " + url);
            
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
