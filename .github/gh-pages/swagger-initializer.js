window.onload = function() {
  //<editor-fold desc="Changeable Configuration Block">

  // the following lines will be replaced by docker/configurator, when it runs in a docker-container
  window.ui = SwaggerUIBundle({
    urls: [
      { url: './alfresco-core.yaml', name: 'Core API' },
      { url: './alfresco-search.yaml', name: 'Search API' },
      { url: './alfresco-search-sql.yaml', name: 'Search SQL API' },
      { url: './alfresco-auth.yaml', name: 'Authentication API' },
      { url: './alfresco-discovery.yaml', name: 'Discovery API' },
      { url: './alfresco-workflow.yaml', name: 'Workflow API' },
      { url: './alfresco-model.yaml', name: 'Model API' },
      { url: './alfresco-scim-v2.yaml', name: 'SCIM v2 API (BETA)' },
    ],
    // disable "Try it out" as we don't have a real backend to test against
    supportedSubmitMethods: [],
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
