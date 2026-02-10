package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        // doesn't map directly to demo dataset - dataset has a ContentType appLabel/model pair, not
        // pk/modelName
        // actual.getModel())
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
                .collect(Collectors.toList());
        List<Integer> actualPks =
                actualList.stream().map(c -> c.getPk()).sorted().collect(Collectors.toList());
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
        // model pk may change and can't be determined from the demoData, just check name that
        // matches the ContentType appLabel/model pair
        String expectedModelName;
        switch (pk) {
            case 1:
                expectedModelName = "Stock Item";
                break;
            default:
                expectedModelName = null;
        }
        assertEquals(expectedModelName, actual.getModelName(), "Incorrect model name");
    }

    @ParameterizedTest
    @CsvSource({"BuildStatus"})
    void genericStatusRetrieve(String statusModel) throws ApiException {
        GenericStateClass actual = api.genericStatusRetrieve(statusModel);
        assertEquals(statusModel, actual.getStatusClass(), "Incorrect status model");
    }

    @Test
    void genericStatusRetrieveAll() throws ApiException {
        Map<String, Object> actual = api.genericStatusRetrieveAll();
        assertFalse(actual.isEmpty(), "Expected populated map of states");
    }
}
