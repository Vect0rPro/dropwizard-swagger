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
<!DOCTYPE html>
<html>
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

      // Dynamically write favicon links
      document.write('<link rel="icon" type="image/png" href="' + swaggerAssetsPath + '/images/favicon-32x32.png" sizes="32x32" />');
      document.write('<link rel="icon" type="image/png" href="' + swaggerAssetsPath + '/images/favicon-16x16.png" sizes="16x16" />');

      // Dynamically write stylesheet links
      document.write('<link href="' + swaggerAssetsPath + '/css/typography.css" media="screen" rel="stylesheet" type="text/css"/>');
      document.write('<link href="' + swaggerAssetsPath + '/css/reset.css" media="screen" rel="stylesheet" type="text/css"/>');
      document.write('<link href="' + swaggerAssetsPath + '/css/screen.css" media="screen" rel="stylesheet" type="text/css"/>');
      document.write('<link href="' + swaggerAssetsPath + '/css/reset.css" media="print" rel="stylesheet" type="text/css"/>');
      document.write('<link href="' + swaggerAssetsPath + '/css/print.css" media="print" rel="stylesheet" type="text/css"/>');

      // Dynamically write script tags
      document.write('<script src="' + swaggerAssetsPath + '/lib/object-assign-pollyfill.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/jquery-1.8.0.min.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/jquery.slideto.min.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/jquery.wiggle.min.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/jquery.ba-bbq.min.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/handlebars-4.0.5.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/lodash.min.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/backbone-min.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/swagger-ui.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/highlight.9.1.0.pack.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/highlight.9.1.0.pack_extended.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/jsoneditor.min.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/marked.js" type="text/javascript"><\/script>');
      document.write('<script src="' + swaggerAssetsPath + '/lib/swagger-oauth.js" type="text/javascript"><\/script>');
    })();
  </script>

  <!-- Some basic translations -->
  <!-- Note: If enabling translations, add these inside the IIFE above: -->
  <!-- document.write('<script src="' + swaggerAssetsPath + '/lang/translator.js" type="text/javascript"><\/script>'); -->
  <!-- document.write('<script src="' + swaggerAssetsPath + '/lang/ru.js" type="text/javascript"><\/script>'); -->
  <!-- document.write('<script src="' + swaggerAssetsPath + '/lang/en.js" type="text/javascript"><\/script>'); -->

  <script type="text/javascript">
    $(function () {
      var contextPath = window.swaggerContextPath;

      hljs.configure({
        highlightSizeThreshold: 5000
      });

      // Pre load translate...
      if(window.SwaggerTranslator) {
        window.SwaggerTranslator.translate();
      }
      window.swaggerUi = new SwaggerUi({
        url: contextPath + "/swagger.json",
        <#if viewConfiguration.validatorUrl??>
        validatorUrl: "${viewConfiguration.validatorUrl}",
        <#else>
        validatorUrl: null,
        </#if>
        dom_id: "swagger-ui-container",
        supportedSubmitMethods: ['get', 'post', 'put', 'delete', 'patch'],
        onComplete: function(swaggerApi, swaggerUi){
          if(typeof initOAuth == "function") {
            initOAuth({
              clientId: "your-client-id",
              clientSecret: "your-client-secret-if-required",
              realm: "your-realms",
              appName: "your-app-name",
              scopeSeparator: " ",
              additionalQueryStringParams: {}
            });
          }

          if(window.SwaggerTranslator) {
            window.SwaggerTranslator.translate();
          }
        },
        onFailure: function(data) {
          log("Unable to Load SwaggerUI");
        },
        docExpansion: "none",
        jsonEditor: false,
        apisSorter: "alpha",
        defaultModelRendering: 'schema',
        showRequestHeaders: false
      });

      window.swaggerUi.load();

      function log() {
        if ('console' in window) {
          console.log.apply(console, arguments);
        }
      }
  });
  </script>
</head>

<body class="swagger-section">
<div id='header'>
  <div class="swagger-ui-wrap">
    <a id="logo" href="http://dropwizard.io"><img class="logo__img" alt="swagger" height="30" width="30" src="myassets/dropwizard-logo.png" /><span class="logo__title">swagger</span></a>
    <form id='api_selector'>
      <div class='input'><input placeholder="http://example.com/api" id="input_baseUrl" name="baseUrl" type="text"/></div>
      <div id='auth_container'></div>
      <div class='input'><a id="explore" class="header__btn" href="#" data-sw-translate>Explore</a></div>
    </form>
  </div>
</div>

<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>
</html>
