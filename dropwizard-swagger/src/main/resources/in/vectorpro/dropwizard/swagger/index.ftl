<#--

    Copyright © 2021 Vector Pro (teamvectorpro@googlegroups.com)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

-->
<#-- @ftlvariable name="" type="SwaggerView" -->
<!-- HTML for static distribution bundle build -->
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>${viewConfiguration.pageTitle}</title>
    <script>
      // Calculate contextPath dynamically from browser URL
      // Example: example.com/path1/swagger -> contextPath = /path1
      (function() {
        var pathname = window.location.pathname;
        // Remove trailing slash if present (IE11 compatible)
        if (pathname.charAt(pathname.length - 1) === '/') {
          pathname = pathname.slice(0, -1);
        }
        // Remove last segment (e.g., "swagger")
        var lastSlashIndex = pathname.lastIndexOf('/');
        var contextPath = lastSlashIndex > 0 ? pathname.substring(0, lastSlashIndex) : '';
        // Calculate swaggerAssetsPath
        var swaggerAssetsPath = contextPath + '/swagger-static';

        // Calculate dynamic server URL for API requests
        var dynamicServerUrl = window.location.origin + contextPath;

        // Store in global scope for later use
        window.swaggerContextPath = contextPath;
        window.swaggerAssetsPath = swaggerAssetsPath;
        window.swaggerDynamicServerUrl = dynamicServerUrl;

        // Dynamically write stylesheet and favicon links
        document.write('<link rel="stylesheet" type="text/css" href="' + swaggerAssetsPath + '/swagger-ui.css" />');
        document.write('<link rel="stylesheet" type="text/css" href="' + swaggerAssetsPath + '/index.css" />');
        document.write('<link rel="icon" type="image/png" href="' + swaggerAssetsPath + '/favicon-32x32.png" sizes="32x32" />');
        document.write('<link rel="icon" type="image/png" href="' + swaggerAssetsPath + '/favicon-16x16.png" sizes="16x16" />');
      })();
    </script>
  </head>

  <body>
    <div id="swagger-ui"></div>
    <script>
      // Dynamically write script tags using calculated swaggerAssetsPath
      document.write('<script src="' + window.swaggerAssetsPath + '/swagger-ui-bundle.js"><\/script>');
      document.write('<script src="' + window.swaggerAssetsPath + '/swagger-ui-standalone-preset.js"><\/script>');
      document.write('<script src="' + window.swaggerAssetsPath + '/swagger-snippet-generator.min.js"><\/script>');
    </script>

    <script>
    window.onload = function() {
      var contextPath = window.swaggerContextPath;
      var dynamicServerUrl = window.swaggerDynamicServerUrl;

      const snippetTargets = [
        <#list viewConfiguration.codeSnippetTargets as target>
          {target: '${target}'}<#if target_has_next>,</#if>
        </#list>
      ];

      // Begin Swagger UI call region
      const ui = SwaggerUIBundle({
        url: contextPath + "/swagger.json",
        <#if viewConfiguration.validatorUrl??>
        validatorUrl: "${viewConfiguration.validatorUrl}",
        <#else>
        validatorUrl: null,
        </#if>
        dom_id: "#swagger-ui",
        deepLinking: true,
        docExpansion: "none",
        tagsSorter: "alpha",
        operationsSorter: "alpha",
        presets: [
          SwaggerUIBundle.presets.apis,
          SwaggerUIStandalonePreset
        ],
        plugins: [
          SwaggerUIBundle.plugins.DownloadUrl,
          SwaggerSnippetGenerator(snippetTargets)
        ],
        oauth2RedirectUrl: window.location.protocol + "//" + window.location.host + contextPath + "/oauth2-redirect.html",
        layout: "StandaloneLayout",
        requestSnippetsEnabled: true,
        // Callback when Swagger UI finishes loading the spec
        onComplete: function() {
          try {
            // Get the current spec from Swagger UI's state
            var state = ui.getState();
            if (state && state.getIn) {
              var specJs = state.getIn(['spec', 'json']);
              if (specJs && specJs.toJS) {
                var spec = specJs.toJS();
                if (spec) {
                  // Create dynamic server entry based on current browser URL
                  var dynamicServer = { url: dynamicServerUrl };
                  // Get existing servers or empty array
                  var existingServers = Array.isArray(spec.servers) ? spec.servers : [];
                  // Only add if not already present
                  var alreadyExists = existingServers.some(function(s) { return s.url === dynamicServerUrl; });
                  if (!alreadyExists) {
                    // Prepend dynamic server to make it the default selection
                    var newServers = [dynamicServer].concat(existingServers);
                    // Update spec with new servers
                    spec.servers = newServers;
                    ui.specActions.updateJsonSpec(spec);
                  }
                }
              }
            }
          } catch (e) {
            console.warn('Failed to inject dynamic server URL:', e);
          }
        }
      });

      ui.initOAuth({
        clientId: "${oauth2Configuration.clientId!"your-client-id"}",
        clientSecret: "${oauth2Configuration.clientSecret!"your-client-secret-if-required"}",
        realm: "${oauth2Configuration.realm!"your-realms"}",
        appName: "${oauth2Configuration.appName!"your-app-name"}",
        scopeSeparator: "${oauth2Configuration.scopeSeparator!" "}",
        additionalQueryStringParams: {
        <#list oauth2Configuration.additionalQueryStringParams?keys as additionalQueryStringParamKey>
          "${additionalQueryStringParamKey}": "${oauth2Configuration.additionalQueryStringParams[additionalQueryStringParamKey]}"
        </#list>
        }
      })
      // End Swagger UI call region

      window.ui = ui
    }
  </script>
  </body>
</html>
