window.onload = function() {
  //<editor-fold desc="Changeable Configuration Block">

  // the following lines will be replaced by docker/configurator, when it runs in a docker-container
  window.ui = SwaggerUIBundle({
    urls: [
      { url: './definitions/alfresco-auth.yaml', name: 'Alfresco Auth API' },
      { url: './definitions/alfresco-core.yaml', name: 'Alfresco Core API' },
      { url: './definitions/alfresco-discovery.yaml', name: 'Alfresco Discovery API' },
      { url: './definitions/alfresco-model.yaml', name: 'Alfresco Model API' },
      { url: './definitions/alfresco-scim-v2.yaml', name: 'Alfresco SCIM v2 API' },
      { url: './definitions/alfresco-search-sql.yaml', name: 'Alfresco Search SQL API' },
      { url: './definitions/alfresco-search.yaml', name: 'Alfresco Search API' },
      { url: './definitions/alfresco-workflow.yaml', name: 'Alfresco Workflow API' }
    ],
    dom_id: '#swagger-ui',
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl
    ],
    layout: "StandaloneLayout"
  });

  //</editor-fold>
};
