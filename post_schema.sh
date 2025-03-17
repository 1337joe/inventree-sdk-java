#!/usr/bin/env bash

schema_file="${1:-schema.yml}"

# find fields that are conditionally removed
field_list=$(find src/backend/ -name \*serializers.py -exec sed -n "s/^.*pass # self.fields.pop('\([^']\+\)'.*$/\1/p" {} \; | sort -u)
# remove them from the lists of required fields
for field in $field_list; do
    sed -i "/- $field$/d" "$schema_file"
done

# restore original conditional removal of fields
find src/backend/ -name \*serializers.py -exec sed -i "s/pass # self.fields.pop/self.fields.pop/g" {} \;
