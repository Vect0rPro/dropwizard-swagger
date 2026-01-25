/*
 * Copyright © 2021 Vector Pro (teamvectorpro@googlegroups.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.vectorpro.dropwizard.swagger;

import io.dropwizard.views.common.View;
import java.nio.charset.StandardCharsets;

/**
 * Serves the content of Swagger's index page which has been "templatized" to support dynamic
 * configuration of Swagger UI. The template calculates paths dynamically from the browser URL,
 * enabling the application to work behind proxies with different path prefixes.
 */
public class SwaggerView extends View {

  private final SwaggerViewConfiguration viewConfiguration;
  private final SwaggerOAuth2Configuration oauth2Configuration;

  public SwaggerView(
      final SwaggerViewConfiguration viewConfiguration,
      final SwaggerOAuth2Configuration oauth2Configuration) {
    super(viewConfiguration.getTemplateUrl(), StandardCharsets.UTF_8);
    this.viewConfiguration = viewConfiguration;
    this.oauth2Configuration = oauth2Configuration;
  }

  /** @return {@link SwaggerViewConfiguration} containing every properties to customize swagger */
  public SwaggerViewConfiguration getViewConfiguration() {
    return viewConfiguration;
  }

  /** @return {@link SwaggerOAuth2Configuration} containing every properties to init oauth2 */
  public SwaggerOAuth2Configuration getOauth2Configuration() {
    return oauth2Configuration;
  }
}
