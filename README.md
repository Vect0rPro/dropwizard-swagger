Dropwizard Swagger Integration
==================

[![Apache License V2.0](https://img.shields.io/badge/License-Apache%20V2-50ca22.svg)](https://github.com/Vect0rPro/dropwizard-swagger/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/in.vectorpro.dropwizard/dropwizard-swagger?label=Maven%20Central&color=50ca22)](https://central.sonatype.com/artifact/in.vectorpro.dropwizard/dropwizard-swagger)

A Dropwizard bundle that serves Swagger UI static content and loads Swagger endpoints.

Current version has been tested with Dropwizard 2.1.12 and Swagger 2.2.2 which supports OpenApi 3.0 specifications

Requirements
--------------
* Dropwizard 2.1.12
* Swagger API 2.2.2
* Swagger UI 5.27.0

Usage
-------------

* Add the Maven dependency (available in Maven Central)

```xml
<dependency>
    <groupId>in.vectorpro.dropwizard</groupId>
    <artifactId>dropwizard-swagger</artifactId>
    <version>2.1.12-1</version>
</dependency>
```


* Add the following to your Configuration class:

```java
public class YourConfiguration extends Configuration {

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
```

* Add the following your configuration yaml (this is the minimal configuration you need):

```yaml
prop1: value1
prop2: value2

# the only required property is resourcePackage, for more config options see below
swagger:
  resourcePackage: <a comma separated string of the packages that contain your @OpenAPIDefinition annotated resources>

  # Configure page title or code snippet targets as needed
  swaggerViewConfiguration:
    pageTitle: "API Documentation"
    # Default value of code snippet targets is always provided by the bundle
    # Include supported snippet targets as per link provided below if modification to default value is required
    codeSnippetTargets:
      - java_okhttp
      - python_requests
```

Supported code snippet targets: [link](https://github.com/tronto20/swagger-snippet-generator?tab=readme-ov-file#targets)

* In your Application class:

```java
@Override
public void initialize(Bootstrap<YourConfiguration> bootstrap) {
    bootstrap.addBundle(new SwaggerBundle<YourConfiguration>() {
        @Override
        protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(YourConfiguration configuration) {
            return configuration.swaggerBundleConfiguration;
        }
    });
}
```

* As usual, add Swagger annotations to your resource classes and methods

* Open a browser and hit `http://localhost:<your_port>/swagger`

* To see all the properties that can be used to customize Swagger UI see [SwaggerBundleConfiguration.java](src/main/java/in/vectorpro/dropwizard/swagger/SwaggerBundleConfiguration.java)
