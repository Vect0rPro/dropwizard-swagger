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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * For the meaning of all these properties please refer to Swagger documentation or {@link
 * SwaggerConfiguration}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwaggerBundleConfiguration {

  /**
   * This is the only property that is required for Swagger to work correctly.
   *
   * <p>It is a comma separated list of the all the packages that contain the {@link
   * io.swagger.v3.oas.annotations.OpenAPIDefinition} annotated resources
   */
  @NotEmpty private String resourcePackage = "";

  @Nullable private String title;

  @Nullable private String version;

  @Nullable private String description;

  @Nullable private String termsOfServiceUrl;

  @Nullable private String contact;

  @Nullable private String contactEmail;

  @Nullable private String contactUrl;

  @Nullable private String license;

  @Nullable private String licenseUrl;

  @Nullable private List<Server> servers;

  private SwaggerViewConfiguration swaggerViewConfiguration = new SwaggerViewConfiguration();
  private SwaggerOAuth2Configuration swaggerOAuth2Configuration = new SwaggerOAuth2Configuration();
  private boolean prettyPrint = true;

  private String contextRoot = "/";
  private boolean enabled = true;
  private boolean includeSwaggerResource = true;
  private boolean readAllResources = true;

  /**
   * For most of the scenarios this property is not needed.
   *
   * <p>This is not a property for Swagger but for bundle to set up Swagger UI correctly. It only
   * needs to be used of the root path or the context path is set programmatically and therefore
   * cannot be derived correctly. The problem arises in that if you set the root path or context
   * path in the run() method in your Application subclass the bundle has already been initialized
   * by that time and so does not know you set the path programmatically.
   */
  @Nullable private String uriPrefix;

  @JsonProperty
  public String getResourcePackage() {
    return resourcePackage;
  }

  @JsonProperty
  public void setResourcePackage(String resourcePackage) {
    this.resourcePackage = resourcePackage;
  }

  @Nullable
  @JsonProperty
  public String getTitle() {
    return title;
  }

  @JsonProperty
  public void setTitle(@Nullable String title) {
    this.title = title;
  }

  @Nullable
  @JsonProperty
  public String getVersion() {
    return version;
  }

  @JsonProperty
  public void setVersion(@Nullable String version) {
    this.version = version;
  }

  @Nullable
  @JsonProperty
  public String getDescription() {
    return description;
  }

  @JsonProperty
  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  @Nullable
  @JsonProperty
  public String getTermsOfServiceUrl() {
    return termsOfServiceUrl;
  }

  @JsonProperty
  public void setTermsOfServiceUrl(@Nullable String termsOfServiceUrl) {
    this.termsOfServiceUrl = termsOfServiceUrl;
  }

  @Nullable
  @JsonProperty
  public String getContact() {
    return contact;
  }

  @JsonProperty
  public void setContact(@Nullable String contact) {
    this.contact = contact;
  }

  @Nullable
  @JsonProperty
  public String getContactEmail() {
    return contactEmail;
  }

  @JsonProperty
  public void setContactEmail(@Nullable String contactEmail) {
    this.contactEmail = contactEmail;
  }

  @Nullable
  @JsonProperty
  public String getContactUrl() {
    return contactUrl;
  }

  @JsonProperty
  public void setContactUrl(@Nullable String contactUrl) {
    this.contactUrl = contactUrl;
  }

  @Nullable
  @JsonProperty
  public String getLicense() {
    return license;
  }

  @JsonProperty
  public void setLicense(@Nullable String license) {
    this.license = license;
  }

  @Nullable
  @JsonProperty
  public String getLicenseUrl() {
    return licenseUrl;
  }

  @JsonProperty
  public void setLicenseUrl(@Nullable String licenseUrl) {
    this.licenseUrl = licenseUrl;
  }

  @Nullable
  @JsonProperty
  public String getUriPrefix() {
    return uriPrefix;
  }

  @JsonProperty
  public void setUriPrefix(@Nullable String uriPrefix) {
    this.uriPrefix = uriPrefix;
  }

  @JsonProperty
  public SwaggerViewConfiguration getSwaggerViewConfiguration() {
    return swaggerViewConfiguration;
  }

  @JsonProperty
  public void setSwaggerViewConfiguration(final SwaggerViewConfiguration swaggerViewConfiguration) {
    this.swaggerViewConfiguration = swaggerViewConfiguration;
  }

  @JsonProperty
  public SwaggerOAuth2Configuration getSwaggerOAuth2Configuration() {
    return swaggerOAuth2Configuration;
  }

  @JsonProperty("oauth2")
  public void setSwaggerOAuth2Configuration(
      final SwaggerOAuth2Configuration swaggerOAuth2Configuration) {
    this.swaggerOAuth2Configuration = swaggerOAuth2Configuration;
  }

  @JsonProperty
  public boolean isPrettyPrint() {
    return prettyPrint;
  }

  @JsonProperty
  public void setIsPrettyPrint(final boolean isPrettyPrint) {
    this.prettyPrint = isPrettyPrint;
  }

  @JsonProperty
  public String getContextRoot() {
    return contextRoot;
  }

  @JsonProperty
  public void setContextRoot(String contextRoot) {
    this.contextRoot = contextRoot;
  }

  @JsonProperty
  public boolean isEnabled() {
    return enabled;
  }

  @JsonProperty
  public void setIsEnabled(final boolean isEnabled) {
    this.enabled = isEnabled;
  }

  @JsonProperty
  public boolean isIncludeSwaggerResource() {
    return includeSwaggerResource;
  }

  @JsonProperty
  public void setIncludeSwaggerResource(final boolean include) {
    this.includeSwaggerResource = include;
  }

  @JsonProperty
  public boolean isReadAllResources() {
    return readAllResources;
  }

  @JsonProperty
  public void setReadAllResources(final boolean include) {
    this.readAllResources = include;
  }

  @Nullable
  @JsonProperty
  public List<Server> getServers() {
    return servers;
  }

  @JsonProperty
  public void setServers(@Nullable List<Server> servers) {
    this.servers = servers;
  }

  @JsonIgnore
  public SwaggerConfiguration build() {
    if (Strings.isNullOrEmpty(resourcePackage)) {
      throw new IllegalStateException(
          "Resource package needs to be specified"
              + " for Swagger to correctly detect annotated resources");
    }

    OpenAPI oas = new OpenAPI();
    final Info info =
        new Info()
            .title(title)
            .version(version)
            .description(description)
            .contact(new Contact().email(contactEmail).name(contact).url(contactUrl))
            .license(new License().name(license).url(licenseUrl))
            .termsOfService(termsOfServiceUrl);

    final String[] exclusions = {SwaggerResource.PATH};
    return new SwaggerConfiguration()
        .openAPI(oas.info(info).servers(servers))
        .prettyPrint(prettyPrint)
        .readAllResources(readAllResources)
        .ignoredRoutes(Arrays.stream(exclusions).collect(Collectors.toSet()))
        .resourcePackages(Arrays.stream(resourcePackage.split(",")).map(String::trim).collect(Collectors.toSet()));
  }
}
