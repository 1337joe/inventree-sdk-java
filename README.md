# InvenTree SDK (Java)

A Java [InvenTree](https://inventree.org/) ([GitHub](https://github.com/inventree/InvenTree)) api client.

## Usage

Simply include in your dependency pom like so:

```xml
<dependency>
    <groupId>com.w3asel</groupId>
    <artifactId>inventree-sdk-java</artifactId>
    <version>0.18.317</version>
</dependency>
```

The version number is a mix of the InvenTree release (0.18) and the schema version (317) with point releases after that when multiple client jars are released against the same schema.

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
