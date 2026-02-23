window.onload = function() {
  //<editor-fold desc="Changeable Configuration Block">

  // the following lines will be replaced by docker/configurator, when it runs in a docker-container
  window.ui = SwaggerUIBundle({
    urls: [
      { url: './definitions/alfresco-core.yaml', name: 'Core API' },
      { url: './definitions/alfresco-search.yaml', name: 'Search API' },
      { url: './definitions/alfresco-search-sql.yaml', name: 'Search SQL API' },
      { url: './definitions/alfresco-auth.yaml', name: 'Authentication API' },
      { url: './definitions/alfresco-discovery.yaml', name: 'Discovery API' },
      { url: './definitions/alfresco-workflow.yaml', name: 'Workflow API' },
      { url: './definitions/alfresco-model.yaml', name: 'Model API' },
      { url: './definitions/alfresco-scim-v2.yaml', name: 'SCIM v2 API (BETA)' },
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
