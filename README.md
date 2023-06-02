# A Java client to get real random integers from https://random.org [![build](https://github.com/manoelcampos/random-org-java-client/actions/workflows/build.yml/badge.svg)](https://github.com/manoelcampos/random-org-java-client/actions/workflows/build.yml) [![javadoc](https://javadoc.io/badge2/com.manoelcampos/random-org-client/javadoc.svg)](https://javadoc.io/doc/com.manoelcampos/random-org-client)

## How to use

Create an account at https://random.org and get an API key.

Include the library inside your project's pom.xml:

```xml
<dependency>
    <groupId>com.manoelcampos</groupId>
    <artifactId>random-org-client</artifactId>
    <version>1.1.0</version>
</dependency>
```

## Example

Check an example inside the [main method here](https://github.com/manoelcampos/random-org-java-client/blob/master/src/main/java/com/manoelcampos/randomorg/RandomOrgClient.java#L115).

The example is loading the service API key from a .env file.
In order to make it work in your project, create a .env file inside your project root dir and insert your API key, as demonstrated in [ .env.example]( .env.example).

If you don't want to use an .env file, just pass the API key to the `RandomOrgClient` constructor.