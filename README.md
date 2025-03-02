# inventree-java

An Inventree api client 



https://github.com/inventree/inventree-python

https://github.com/jellyfin/jellyfin-sdk-kotlin

https://drf-spectacular.readthedocs.io/en/latest/client_generation.html

## Test Server

```sh
invoke dev.setup --test
invoke dev.server
```

## Generate Schema

```sh
find src/backend/ -name \*serializers.py -exec sed -i "s/self.fields.pop/pass# self.fields.pop/g" {} \;
invoke dev.schema --ignore-warnings --filename api.yaml --overwrite
```
