# InvenTree SDK (Java)

A Java [InvenTree](https://inventree.org/) ([GitHub](https://github.com/inventree/InvenTree)) API client.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![javadoc](https://javadoc.io/badge2/com.w3asel/inventree-sdk-java/javadoc.svg)](https://javadoc.io/doc/com.w3asel/inventree-sdk-java)

## Usage

### Add Dependency

This library is published to [Maven Central](https://central.sonatype.com/artifact/com.w3asel/inventree-sdk-java), so if you're using Maven simply include in your dependency pom like so:

```xml
<dependency>
    <groupId>com.w3asel</groupId>
    <artifactId>inventree-sdk-java</artifactId>
    <version>1.1.421</version>
</dependency>
```

Alternately, if you're just looking for a jar file you can find it attached to the corresponding [release](https://github.com/1337joe/inventree-sdk-java/releases) here on GitHub.

The version number is a mix of the InvenTree release (1.1) and the schema version (421) with point releases after that when multiple client jars are released against the same schema.

The `main` branch of this repository tracks `InvenTree/master` and may not be compatible with the latest stable release. As it is not tied to an InvenTree release, it is published as `-SNAPSHOT` jars ([browsable here](https://central.sonatype.com/service/rest/repository/browse/maven-snapshots/com/w3asel/inventree-sdk-java/)), which may be consumed by [adding the central snapshot repository to your pom](https://central.sonatype.org/publish/publish-portal-snapshots/#consuming-snapshot-releases-for-your-project):

```xml
<repositories>
  <repository>
    <name>Central Portal Snapshots</name>
    <id>central-portal-snapshots</id>
    <url>https://central.sonatype.com/repository/maven-snapshots/</url>
    <releases>
      <enabled>false</enabled>
    </releases>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
  </repository>
</repositories>
```

### Calling a server

See the tests for examples of various calls to a InvenTree. Specifically, start here for a simple, self-contained file that connects to a server and retrieves Sales Orders: [TestClient.java](src/test/java/com/w3asel/inventree/TestClient.java)

Javadocs are hosted [here](https://javadoc.io/doc/com.w3asel/inventree-sdk-java) if you want to browse the available calls.

## Running Locally

Set up an [InvenTree dev environment](https://docs.inventree.org/en/stable/develop/devcontainer/).

```sh
invoke dev.setup-test
invoke dev.server
```

Be sure to run `invoke update` when pulling updates into your dev environment.

### Generate Schema from InvenTree

You can either browse to http://localhost:8000/api-doc/ (or the corresponding path on your instance, browsable from the About InvenTree dialog), or from a dev environment run:

```sh
invoke dev.schema --filename api.yaml --overwrite
```

If you just want to pull a pre-built copy of a specific version of the schema you can browse the [InvenTree schema repo](https://github.com/inventree/schema).

### Running tests

Since the API code is all generated from the schema, the tests in this repo exist to demonstrate that the API behaves as indicated by the schema and in the process provide examples of how to use the API.

To run the tests, ensure your local InvenTree server is running (as detailed above), then run:

```sh
mvn verify
```

When this completes, test results can be found in `target/surefire-reports`. Skipped tests indicate tests that are not yet implemented or don't work as expected (which should be documented in the code annotation). Failing tests shouldn't make it into the main branch of the repository, as they indicate that the API does not function as expected based on the schema and therefore either the test needs to be updated or InvenTree needs fixes for its schema generation.

Coverage information is located at `target/site/jacoco/index.html`. The goal is to eventually call every API function and verify the results in some way, ideally by comparing to the demo dataset for known values where possible. Some calls are not currently practical to test, either because there's no way to inject test data for queries to return or because there's no way to verify the results (such as when nothing is returned).
