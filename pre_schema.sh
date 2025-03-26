#!/usr/bin/env bash

# comment out conditional removal of fields as it causes the schema generator to exclude them unconditionally
find src/backend/ -name \*serializers.py -exec sed -i "s/self.fields.pop/pass # self.fields.pop/g" {} \;
