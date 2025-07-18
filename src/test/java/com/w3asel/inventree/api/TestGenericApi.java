package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.CustomState;
import com.w3asel.inventree.model.GenericStateClass;
import com.w3asel.inventree.model.PaginatedCustomStateList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Collections;
import java.util.List;

public class TestGenericApi extends TestApi {
    private GenericApi api;

    @BeforeEach
    void setup() {
        api = new GenericApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.genericStatusCustomCreate(null);
        api.genericStatusCustomDestroy(null);
        // api.genericStatusCustomList(null, null, null, null, null, null);
        api.genericStatusCustomPartialUpdate(null, null);
        // api.genericStatusCustomRetrieve(null);
        api.genericStatusCustomUpdate(null, null);
        // api.genericStatusRetrieve(null);
        // api.genericStatusRetrieveAll();
    }

    public static void assertCustomStateEquals(JsonObject expected, CustomState actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("reference_status", fields, actual.getReferenceStatus());
        assertFieldEquals("logical_key", fields, actual.getLogicalKey());
        assertFieldEquals("key", fields, actual.getKey());
        assertFieldEquals("name", fields, actual.getName());
        assertFieldEquals("label", fields, actual.getLabel());
        assertFieldEquals("color", fields, actual.getColor());

        // doesn't map directly to demo dataset - dataset has a list, not single value
        // actual.getModel())

        // not directly available in demo dataset:
        // actual.getModelName();
    }

    @Test
    void genericStatusCustomList() throws ApiException {
        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.CUSTOM_USER_STATE, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedCustomStateList actual =
                api.genericStatusCustomList(limit, null, offset, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect total generic status custom count");
        List<CustomState> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        CustomState actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.CUSTOM_USER_STATE, actualFirst.getPk()).get(0);
        assertCustomStateEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"1"})
    void genericStatusCustomRetrieve(int pk) throws ApiException {
        CustomState actual = api.genericStatusCustomRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.CUSTOM_USER_STATE, pk).get(0);
        assertCustomStateEquals(expected, actual);

        // verify data not directly in demo dataset
        List<String> expectedModelName;
        switch (pk) {
            case 1:
                // TODO dataset has a list of models, why isn't that represented here?
                expectedModelName = List.of("Stock", "Stock Item");
                break;
            default:
                expectedModelName = Collections.emptyList();
        }
        assertTrue(expectedModelName.contains(actual.getModelName()),
                "Incorrect model name, expected one of " + expectedModelName + " but found "
                        + actual.getModelName());
    }

    @Disabled("Can't figure out what a valid statusModel string is")
    @ParameterizedTest
    @CsvSource({"InvenTree.build.status_codes.BuildStatus"/* , "stock", "stockitem" */})
    void genericStatusRetrieve2(String statusModel) throws ApiException {
        // TODO statusModel must be a valid class, should restrict to enum
        GenericStateClass actual = api.genericStatusRetrieve(statusModel);
    }

    @Disabled("Void return")
    @Test
    void genericStatusRetrieveAll() throws ApiException {
        api.genericStatusRetrieveAll();
    }
}
