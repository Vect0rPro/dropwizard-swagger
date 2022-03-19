Dropwizard Swagger Integration
==================

[![Apache License V2.0](http://img.shields.io/badge/license-Apache%20V2-50ca22.svg)](//github.com/Vect0rPro/dropwizard-swagger/blob/master/LICENSE)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/in.vectorpro.dropwizard/dropwizard-swagger/badge.svg)](https://maven-badges.herokuapp.com/maven-central/in.vectorpro.dropwizard/dropwizard-swagger)

A Dropwizard bundle that serves Swagger UI static content and loads Swagger endpoints.

Current version has been tested with Dropwizard 2.0.28 and Swagger 2.1.13 which supports OpenApi 3.0 specifications

Requirements
--------------
* Dropwizard 2.x.x
* Swagger API 2.1.13
* Swagger UI 4.6.2

Usage
-------------

* Add the Maven dependency (available in Maven Central)

```xml
<dependency>
    <groupId>in.vectorpro.dropwizard</groupId>
    <artifactId>dropwizard-swagger</artifactId>
    <version>2.0.28-1</version>
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
```

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
