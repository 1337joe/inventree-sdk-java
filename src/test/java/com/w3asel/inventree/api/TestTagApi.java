package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedTagList;
import com.w3asel.inventree.model.Tag;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestTagApi extends TestApi {
    private TagApi api;

    @BeforeEach
    void setup() {
        api = new TagApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.tagCreate(null);
        api.tagDestroy(null);
        // api.tagList(null, null, null, null, null);
        api.tagPartialUpdate(null, null);
        // api.tagRetrieve(null);
        api.tagUpdate(null, null);
    }

    private static void assertTagEquals(JsonObject expected, Tag actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("name", fields, actual.getName());
        assertFieldEquals("slug", fields, actual.getSlug());
    }

    @Test
    void tagList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.TAG, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedTagList actual = api.tagList(limit, null, offset, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total tag count");
        List<Tag> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .collect(Collectors.toList());
        List<Integer> actualPks =
                actualList.stream().map(c -> c.getPk()).sorted().collect(Collectors.toList());
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        Tag actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.TAG, actualFirst.getPk()).get(0);
        assertTagEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void userGroupRetrieve(int pk) throws ApiException {
        Tag actual = api.tagRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.TAG, pk).get(0);
        assertTagEquals(expected, actual);
    }
}
