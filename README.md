# InvenTree SDK (Java)

A Java [InvenTree](https://inventree.org/) ([GitHub](https://github.com/inventree/InvenTree)) API client.

[![javadoc](https://javadoc.io/badge2/com.w3asel/inventree-sdk-java/javadoc.svg)](https://javadoc.io/doc/com.w3asel/inventree-sdk-java)

## Usage

### Add Dependency

This library is published to [Maven Central](https://central.sonatype.com/artifact/com.w3asel/inventree-sdk-java), so if you're using Maven simply include in your dependency pom like so:

```xml
<dependency>
    <groupId>com.w3asel</groupId>
    <artifactId>inventree-sdk-java</artifactId>
    <version>0.17.294</version>
</dependency>
```

Alternately, if you're just looking for a jar file you can find it attached to the corresponding [release](https://github.com/1337joe/inventree-sdk-java/releases) here on GitHub.

The version number is a mix of the InvenTree release (0.17) and the schema version (294) with point releases after that when multiple client jars are released against the same schema.

The `main` branch of this repository tracks `InvenTree/master` and may not be compatible with the latest stable release. As such, it is published as `-SNAPSHOT` jars, which may be consumed by [adding the central snapshot repository to your pom](https://central.sonatype.org/publish/publish-portal-snapshots/#consuming-snapshot-releases-for-your-project).

### Calling a server

See the tests for examples of various calls to a InvenTree. Specifically, start here for a simple, self-contained file that connects to a server and retrieves Sales Orders: [TestClient.java](src/test/java/com/w3asel/inventree/TestClient.java)

Javadocs are hosted [here](https://javadoc.io/doc/com.w3asel/inventree-sdk-java) if you want to browse the available calls.

## Running a Local InvenTree Server

Set up an [InvenTree dev environment](https://docs.inventree.org/en/stable/develop/devcontainer/).

```sh
invoke dev.setup-test
invoke dev.server
```

Be sure to run `invoke update` when pulling updates into your dev environment.

### Generate Schema from InvenTree

You can either browse to http://localhost:8000/api-doc/ (or the corresponding path on your instance, browsable from the About InvenTree dialog), or from a dev environment run:

```sh
invoke dev.schema --ignore-warnings --filename api.yaml --overwrite
```

If you just want to pull a pre-built copy of a specific version of the schema you can browse the [InvenTree schema repo](https://github.com/inventree/schema).
