# A Java client to get real random integers from https://random.org [![build](https://github.com/manoelcampos/random-org-java-client/actions/workflows/build.yml/badge.svg)](https://github.com/manoelcampos/random-org-java-client/actions/workflows/build.yml)

## How to use

Create an account at https://random.org and get an API key.

Include the library inside your project's pom.xml:

```xml
<dependency>
    <groupId>com.manoelcampos</groupId>
    <artifactId>random-org-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

Create a .env file inside your project root dir and insert your API key, as demonstrated in [ .env.example]( .env.example).

## Example

Check an example inside the [main method here](https://github.com/manoelcampos/random-org-java-client/blob/master/src/main/java/com/manoelcampos/randomorg/RandomOrgService.java#L115).