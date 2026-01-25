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
    <link rel="stylesheet" type="text/css" href="${swaggerAssetsPath}/swagger-ui.css" />
    <link rel="stylesheet" type="text/css" href="${swaggerAssetsPath}/index.css" />
    <link rel="icon" type="image/png" href="${swaggerAssetsPath}/favicon-32x32.png" sizes="32x32" />
    <link rel="icon" type="image/png" href="${swaggerAssetsPath}/favicon-16x16.png" sizes="16x16" />
  </head>

  <body>
    <div id="swagger-ui"></div>
    <script src="${swaggerAssetsPath}/swagger-ui-bundle.js"> </script>
    <script src="${swaggerAssetsPath}/swagger-ui-standalone-preset.js"> </script>
    <script src="${swaggerAssetsPath}/swagger-snippet-generator.min.js"> </script>

    <script>
    window.onload = function() {

      const snippetTargets = [
        <#list viewConfiguration.codeSnippetTargets as target>
          {target: '${target}'}<#if target_has_next>,</#if>
        </#list>
      ];

      // Begin Swagger UI call region
      const ui = SwaggerUIBundle({
        url: "${contextPath}/swagger.json",
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
        oauth2RedirectUrl: window.location.protocol + "//" + window.location.host + "${contextPath}/oauth2-redirect.html",
        layout: "StandaloneLayout",
        requestSnippetsEnabled: true
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
