package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static com.w3asel.inventree.InventreeDemoDataset.assertNullableFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedSelectionEntryList;
import com.w3asel.inventree.model.PaginatedSelectionListList;
import com.w3asel.inventree.model.SelectionEntry;
import com.w3asel.inventree.model.SelectionList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TestSelectionApi extends TestApi {
    private SelectionApi api;

    @BeforeEach
    void setup() {
        api = new SelectionApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.selectionCreate(null);
        api.selectionDestroy(null);
        api.selectionEntryCreate(null, null);
        api.selectionEntryDestroy(null, null);
        // api.selectionEntryList(null, null, null, null, null, null, null, null);
        api.selectionEntryPartialUpdate(null, null, null);
        // api.selectionEntryRetrieve(null, null);
        api.selectionEntryUpdate(null, null, null);
        // api.selectionList(null, null);
        api.selectionPartialUpdate(null, null);
        // api.selectionRetrieve(null);
        api.selectionUpdate(null, null);
    }

    private void assertSelectionListEquals(JsonObject expected, SelectionList actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("name", fields, actual.getName());
        assertFieldEquals("description", fields, actual.getDescription());
        assertFieldEquals("locked", fields, actual.getLocked());
        assertFieldEquals("active", fields, actual.getActive());
        assertNullableFieldEquals(Integer.class, "source_plugin", fields, actual.getSourcePlugin());
        assertFieldEquals("source_string", fields, actual.getSourceString());
        assertNullableFieldEquals(SelectionEntry.class, "default", fields, actual.getDefault());
        assertFieldEquals("created", fields, actual.getCreated());
        assertFieldEquals("last_updated", fields, actual.getLastUpdated());

        // not directly available in demo dataset:
        // actual.getChoices();
        // actual.getEntryCount();

        // not available through schema-mapped API:
        // metadata
    }

    @Test
    void selectionList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.SELECTION_LIST, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        PaginatedSelectionListList actual = api.selectionList(limit, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total entry item count");
        List<SelectionList> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .collect(Collectors.toList());
        List<Integer> actualPks =
                actualList.stream().map(s -> s.getPk()).sorted().collect(Collectors.toList());
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        SelectionList actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.SELECTION_LIST, actualFirst.getPk()).get(0);
        assertSelectionListEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void selectionRetrieve(int id) throws ApiException {
        SelectionList actual = api.selectionRetrieve(id);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.SELECTION_LIST, id).get(0);
        assertSelectionListEquals(expected, actual);

        // verify data not directly in demo dataset
        int entryCount;
        switch (id) {
            case 1:
                entryCount = 315;
                break;
            default:
                entryCount = 0;
        }

        // TODO returns null, revisit when API fixed
        // assertEquals(entryCount, actual.getEntryCount());
        // not checking entry list here
    }

    private void assertSelectionEntryEquals(JsonObject expected, SelectionEntry actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getId());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        actual.getList();
        actual.getValue();
        actual.getLabel();
        actual.getDescription();
        actual.getActive();

        assertFieldEquals("list", fields, actual.getList());
        assertFieldEquals("value", fields, actual.getValue());
        assertFieldEquals("label", fields, actual.getLabel());
        assertFieldEquals("description", fields, actual.getDescription());
        assertFieldEquals("active", fields, actual.getActive());
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void selectionEntryList(int listPk) throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.SELECTION_LIST_ENTRY, null);
        expectedList.removeIf(
                s -> s.get(InventreeDemoDataset.FIELDS_KEY).getAsJsonObject().get("list").getAsInt() != listPk);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        PaginatedSelectionEntryList actual = api.selectionEntryList(listPk, limit, null, null, null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total entry item count");
        List<SelectionEntry> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .collect(Collectors.toList());
        List<Integer> actualPks =
                actualList.stream().map(s -> s.getId()).sorted().collect(Collectors.toList());
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        SelectionEntry actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.SELECTION_LIST_ENTRY, actualFirst.getId()).get(0);
        assertSelectionEntryEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"1,1", "1,2", "1,315"})
    void selectionEntryRetrieve(int listId, int entryPk) throws ApiException {
        SelectionEntry actual = api.selectionEntryRetrieve(entryPk, listId);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.SELECTION_LIST_ENTRY, entryPk).get(0);
        assertSelectionEntryEquals(expected, actual);
    }
}
