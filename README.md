# InvenTree SDK (Java)

A Java [InvenTree](https://inventree.org/) ([GitHub](https://github.com/inventree/InvenTree)) api client.

## Usage

This library is published to Maven Central, so if you're using Maven simply include in your dependency pom like so:

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
