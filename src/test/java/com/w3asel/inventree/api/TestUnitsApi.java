package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.AllUnitListResponse;
import com.w3asel.inventree.model.CustomUnit;
import com.w3asel.inventree.model.PaginatedCustomUnitList;
import com.w3asel.inventree.model.PatchedCustomUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.ArrayList;
import java.util.List;

public class TestUnitsApi extends TestApi {
    private static final String CREATED_NAME = "TestUnit";
    private static final String UPDATED_NAME = "TestUpdate";

    private UnitsApi api;

    @BeforeEach
    void setup() {
        api = new UnitsApi(apiClient);
    }

    @AfterAll
    public static void cleanup() throws ApiException {
        UnitsApi api = new UnitsApi(apiClient);

        // gather units
        int limit = 10;
        List<CustomUnit> allUnits = new ArrayList<>();
        PaginatedCustomUnitList page;
        do {
            page = api.unitsList(limit, allUnits.size(), null, null);
            allUnits.addAll(page.getResults());
        } while (allUnits.size() < page.getCount());

        // ensure they weren't created by tests
        List<String> removed = new ArrayList<>();
        for (CustomUnit unit : allUnits) {
            if (CREATED_NAME.equals(unit.getName()) || UPDATED_NAME.equals(unit.getName())) {
                removed.add(unit.getName());
                api.unitsDestroy(unit.getPk());
            }
        }
        if (!removed.isEmpty()) {
            fail("Created CustomUnits not cleaned up: " + String.join(", ", removed));
        }
    }

    @Test
    void unitsAllRetrieve() throws ApiException {
        AllUnitListResponse actual = api.unitsAllRetrieve();
        assertNotNull(actual, "Missing all units response");
        assertNotNull(actual.getDefaultSystem(), "Missing default system");
        assertNotNull(actual.getAvailableSystems(), "Missing available system");
        assertTrue(actual.getAvailableSystems().contains(actual.getDefaultSystem()),
                "Default system not in available systems");
        assertNotNull(actual.getAvailableUnits(), "Missing available units");
        assertTrue(actual.getAvailableUnits().size() > 0, "Empty available units");
    }

    @Test
    void unitsCreateDestroy() throws ApiException {
        int initialCount = api.unitsList(1, null, null, null).getCount();

        // set only required fields
        CustomUnit newUnit = new CustomUnit().name(CREATED_NAME).definition("nautical_mile");
        assertNull(newUnit.getPk(), "Unsubmitted item should not have PK");

        CustomUnit actual = api.unitsCreate(newUnit);
        assertNotNull(actual.getPk(), "Created item should have PK");

        try {
            int createdCount = api.unitsList(1, null, null, null).getCount();
            assertEquals(initialCount + 1, createdCount, "Count should have increased");

            CustomUnit retrieved = api.unitsRetrieve(actual.getPk());
            assertEquals(actual.getPk(), retrieved.getPk(), "PKs don't match");
            assertEquals(actual.getName(), retrieved.getName(), "Names don't match");
            assertEquals(actual.getDefinition(), retrieved.getDefinition(),
                    "Definitions don't match");
            assertEquals(actual.getSymbol(), retrieved.getSymbol(), "Symbols don't match");
        } finally {
            api.unitsDestroy(actual.getPk());
        }

        int deletedCount = api.unitsList(1, null, null, null).getCount();
        assertEquals(initialCount, deletedCount, "Count should have reset");

        // verify item not found
        ApiException thrown = assertThrows(ApiException.class,
                () -> api.unitsRetrieve(actual.getPk()), "Retrieve missing pk should error");
        assertEquals(404, thrown.getCode(), "Expected HTTP 404 Not Found");
        assertTrue(thrown.getMessage().contains("Not Found"),
                "Should contain Not Found: " + thrown.getMessage());
    }

    private void assertCustomUnitEquals(JsonObject expected, CustomUnit actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("name", fields, actual.getName());
        assertFieldEquals("symbol", fields, actual.getSymbol());
        assertFieldEquals("definition", fields, actual.getDefinition());
    }

    @Test
    void unitsList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.CUSTOM_UNIT, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedCustomUnitList actual = api.unitsList(limit, offset, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect custom units list count");
        List<CustomUnit> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        CustomUnit actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.CUSTOM_UNIT, actualFirst.getPk()).get(0);
        assertCustomUnitEquals(expectedFirst, actualFirst);
    }


    @Test
    void unitsPartialUpdate() throws ApiException {
        // Assumes retrieve/create/destroy work, ensure that test is working before debugging this

        // set only required fields
        CustomUnit newUnit = new CustomUnit().name(CREATED_NAME).definition("gram");

        CustomUnit createdUnit = api.unitsCreate(newUnit);
        assertNotNull(createdUnit.getPk(), "Created item should have PK");

        int createdCount = api.unitsList(1, null, null, null).getCount();

        try {
            PatchedCustomUnit modifiedUnit = new PatchedCustomUnit().name(UPDATED_NAME);

            CustomUnit updatedUnit = api.unitsPartialUpdate(createdUnit.getPk(), modifiedUnit);

            assertEquals(createdUnit.getPk(), updatedUnit.getPk(),
                    "Updated pk should match created");

            int modifiedCount = api.unitsList(1, null, null, null).getCount();
            assertEquals(createdCount, modifiedCount, "Count should not have increased on modify");

            CustomUnit retrieved = api.unitsRetrieve(createdUnit.getPk());
            assertEquals(updatedUnit.getPk(), retrieved.getPk(), "PKs don't match");
            assertEquals(modifiedUnit.getName(), retrieved.getName(), "Names don't match");
            assertEquals(updatedUnit.getDefinition(), retrieved.getDefinition(),
                    "Definitions don't match");
            assertEquals(updatedUnit.getSymbol(), retrieved.getSymbol(), "Symbols don't match");
        } finally {
            api.unitsDestroy(createdUnit.getPk());
        }
    }

    @ParameterizedTest
    @CsvSource({"1"})
    void unitsRetrieve(int customUnit) throws ApiException {
        CustomUnit actual = api.unitsRetrieve(customUnit);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.CUSTOM_UNIT, customUnit).get(0);
        assertCustomUnitEquals(expected, actual);
    }

    @Test
    void unitsUpdate() throws ApiException {
        // Assumes retrieve/create/destroy work, ensure that test is working before debugging this

        // set only required fields
        CustomUnit newUnit = new CustomUnit().name(CREATED_NAME).definition("gram");

        CustomUnit createdUnit = api.unitsCreate(newUnit);
        assertNotNull(createdUnit.getPk(), "Created item should have PK");

        int createdCount = api.unitsList(1, null, null, null).getCount();

        try {
            CustomUnit modifiedUnit =
                    new CustomUnit().name(UPDATED_NAME).definition("dozen").symbol("dz");

            CustomUnit updatedUnit = api.unitsUpdate(createdUnit.getPk(), modifiedUnit);

            assertEquals(createdUnit.getPk(), updatedUnit.getPk(),
                    "Updated pk should match created");

            int modifiedCount = api.unitsList(1, null, null, null).getCount();
            assertEquals(createdCount, modifiedCount, "Count should not have increased on modify");

            CustomUnit retrieved = api.unitsRetrieve(createdUnit.getPk());
            assertEquals(updatedUnit.getPk(), retrieved.getPk(), "PKs don't match");
            assertEquals(modifiedUnit.getName(), retrieved.getName(), "Names don't match");
            assertEquals(modifiedUnit.getDefinition(), retrieved.getDefinition(),
                    "Definitions don't match");
            assertEquals(modifiedUnit.getSymbol(), retrieved.getSymbol(), "Symbols don't match");
        } finally {
            api.unitsDestroy(createdUnit.getPk());
        }
    }
}
