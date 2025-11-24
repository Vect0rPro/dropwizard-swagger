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

import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nullable;

/**
 * Contains all configurable parameters required to render the SwaggerUI View from the default
 * template
 */
public class SwaggerViewConfiguration {

  private static final String DEFAULT_TITLE = "Swagger UI";
  private static final String DEFAULT_TEMPLATE = "index.ftl";
  private static final Set<String> DEFAULT_CODE_SNIPPET_TARGETS = Sets.newHashSet("shell_wget", "python_requests",
            "java_okhttp", "node_request", "go_native");

  @Nullable
  private String pageTitle;

  @Nullable
  private String templateUrl;

  @Nullable
  private String validatorUrl;

  private boolean showApiSelector;
  private boolean showAuth;

  private Set<String> codeSnippetTargets;

  public SwaggerViewConfiguration() {
    this.pageTitle = DEFAULT_TITLE;
    this.templateUrl = DEFAULT_TEMPLATE;
    this.validatorUrl = null;
    this.showApiSelector = true;
    this.showAuth = true;
    this.codeSnippetTargets = DEFAULT_CODE_SNIPPET_TARGETS;
  }

  @Nullable
  public String getPageTitle() {
    return pageTitle;
  }

  public void setPageTitle(@Nullable String title) {
    this.pageTitle = title;
  }

  @Nullable
  public String getTemplateUrl() {
    return templateUrl;
  }

  public void setTemplateUrl(@Nullable String templateUrl) {
    this.templateUrl = templateUrl;
  }

  @Nullable
  public String getValidatorUrl() {
    return validatorUrl;
  }

  public void setValidatorUrl(@Nullable String validatorUrl) {
    this.validatorUrl = validatorUrl;
  }

  public boolean isShowApiSelector() {
    return showApiSelector;
  }

  public void setShowApiSelector(boolean showApiSelector) {
    this.showApiSelector = showApiSelector;
  }

  public boolean isShowAuth() {
    return showAuth;
  }

  public void setShowAuth(boolean showAuth) {
    this.showAuth = showAuth;
  }

  public Set<String> getCodeSnippetTargets() {
    return codeSnippetTargets;
  }

  public void setCodeSnippetTargets(Set<String> codeSnippetTargets) {

    this.codeSnippetTargets = codeSnippetTargets;

    // Syntax highlighting with swagger snippet generator js works only if node is included in the snippet targets
    // Otherwise snippets are generated without syntax highlighting
    // Hence identifying if node is missing and adding it to the set
    final boolean noTargetStartsWithNode = this.codeSnippetTargets.stream()
            .noneMatch(target -> target.startsWith("node"));
    if (noTargetStartsWithNode) {
        this.codeSnippetTargets.add("node_request");
    }
  }
}
