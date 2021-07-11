Dropwizard Swagger 2.x and OpenAPI 3.x Integration
==================

A Dropwizard bundle that serves Swagger UI static content and loads Swagger endpoints.

Current version has been tested with Dropwizard 2.0.23 and Swagger 2.0.10 which supports OpenApi 3.0 specifications

Requirements
--------------
* Dropwizard 2.x.x
* Swagger API 2.0.10
* Swagger UI 3.51.1

Usage
-------------

* Add the Maven dependency (available in Maven Central)

```xml
<dependency>
    <groupId>in.vectorpro.dropwizard</groupId>
    <artifactId>dropwizard-swagger</artifactId>
    <version>2.0.23-2</version>
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

* To see all the properties that can be used to customize Swagger UI see [SwaggerBundleConfiguration.java](src/main/java/com/ossterdam/dropwizard/swagger/SwaggerBundleConfiguration.java)