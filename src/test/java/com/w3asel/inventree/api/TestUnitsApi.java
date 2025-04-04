package com.w3asel.inventree.api;

import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.CustomUnit;
import com.w3asel.inventree.model.PaginatedCustomUnitList;
import com.w3asel.inventree.model.PatchedCustomUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

public class TestUnitsApi extends TestApi {
    private UnitsApi api;

    @BeforeEach
    public void setup() {
        api = new UnitsApi(apiClient);
    }

    @Disabled("Result is not currently paginated or even a list")
    @Test
    public void unitsAllList() throws ApiException {
        // TODO Result is not currently paginated or even a list
        int limit = 10;
        int offset = 0;
        api.unitsAllList(limit, offset);
    }

    @Test
    public void unitsCreateDestroy() throws ApiException {
        int initialCount = api.unitsList(1, null, null, null).getCount();

        // TODO document what's being selected from or figure out how to otherwise resolve
        // newUnit.definition("Definition") => "'Definition' is not defined in the unit registry"

        // set only required fields
        CustomUnit newUnit = new CustomUnit().name("Name").definition("gram");
        Assertions.assertNull(newUnit.getPk(), "Unsubmitted item should not have PK");

        CustomUnit actual = api.unitsCreate(newUnit);
        Assertions.assertNotNull(actual.getPk(), "Created item should have PK");

        try {
            int createdCount = api.unitsList(1, null, null, null).getCount();
            Assertions.assertEquals(initialCount + 1, createdCount, "Count should have increased");

            CustomUnit retrieved = api.unitsRetrieve(actual.getPk());
            Assertions.assertEquals(actual.getPk(), retrieved.getPk(), "PKs don't match");
            Assertions.assertEquals(actual.getName(), retrieved.getName(), "Names don't match");
            Assertions.assertEquals(actual.getDefinition(), retrieved.getDefinition(),
                    "Definitions don't match");
            Assertions.assertEquals(actual.getSymbol(), retrieved.getSymbol(),
                    "Symbols don't match");
        } finally {
            api.unitsDestroy(actual.getPk());
        }

        int deletedCount = api.unitsList(1, null, null, null).getCount();
        Assertions.assertEquals(initialCount, deletedCount, "Count should have reset");

        // verify item not found
        try {
            api.unitsRetrieve(actual.getPk());
            Assertions.fail("Expected 404 Not Found");
        } catch (ApiException e) {
            Assertions.assertTrue(e.getMessage().contains("Not Found"),
                    "Invalid key should not have been found.");
        }
    }

    private void assertCustomUnitEquals(JsonObject expected, CustomUnit actual) {
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertEquals("name", fields, actual.getName());
        InventreeDemoDataset.assertEquals("symbol", fields, actual.getSymbol());
        InventreeDemoDataset.assertEquals("definition", fields, actual.getDefinition());
    }

    @Test
    public void unitsList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.CUSTOM_UNIT, null);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedCustomUnitList actual = api.unitsList(limit, offset, null, null);
        Assertions.assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect custom units list count");
        List<CustomUnit> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        CustomUnit actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.CUSTOM_UNIT, actualFirst.getPk()).get(0);
        assertCustomUnitEquals(expectedFirst, actualFirst);
    }


    @Test
    public void unitsPartialUpdate() throws ApiException {
        // Assumes retrieve/create/destroy work, ensure that test is working before debugging this

        int initialCount = api.unitsList(1, null, null, null).getCount();

        // set only required fields
        CustomUnit newUnit = new CustomUnit().name("Updatable").definition("gram");

        CustomUnit createdUnit = api.unitsCreate(newUnit);
        Assertions.assertNotNull(createdUnit.getPk(), "Created item should have PK");

        int createdCount = api.unitsList(1, null, null, null).getCount();
        Assertions.assertEquals(initialCount + 1, createdCount, "Count should have increased");

        try {
            PatchedCustomUnit modifiedUnit = new PatchedCustomUnit().name("Potato");

            CustomUnit updatedUnit = api.unitsPartialUpdate(createdUnit.getPk(), modifiedUnit);

            Assertions.assertEquals(createdUnit.getPk(), updatedUnit.getPk(),
                    "Updated pk should match created");

            int modifiedCount = api.unitsList(1, null, null, null).getCount();
            Assertions.assertEquals(createdCount, modifiedCount,
                    "Count should not have increased on modify");

            CustomUnit retrieved = api.unitsRetrieve(createdUnit.getPk());
            Assertions.assertEquals(updatedUnit.getPk(), retrieved.getPk(), "PKs don't match");
            Assertions.assertEquals(modifiedUnit.getName(), retrieved.getName(),
                    "Names don't match");
            Assertions.assertEquals(updatedUnit.getDefinition(), retrieved.getDefinition(),
                    "Definitions don't match");
            Assertions.assertEquals(updatedUnit.getSymbol(), retrieved.getSymbol(),
                    "Symbols don't match");
        } finally {
            api.unitsDestroy(createdUnit.getPk());
        }
    }

    @ParameterizedTest
    @CsvSource({"1"})
    public void stockTrackRetrieve(int customUnit) throws ApiException {
        CustomUnit actual = api.unitsRetrieve(customUnit);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.CUSTOM_UNIT, customUnit).get(0);
        assertCustomUnitEquals(expected, actual);
    }

    @Test
    public void unitsUpdate() throws ApiException {
        // Assumes retrieve/create/destroy work, ensure that test is working before debugging this

        int initialCount = api.unitsList(1, null, null, null).getCount();

        // set only required fields
        CustomUnit newUnit = new CustomUnit().name("Updatable").definition("gram");

        CustomUnit createdUnit = api.unitsCreate(newUnit);
        Assertions.assertNotNull(createdUnit.getPk(), "Created item should have PK");

        int createdCount = api.unitsList(1, null, null, null).getCount();
        Assertions.assertEquals(initialCount + 1, createdCount, "Count should have increased");

        try {
            CustomUnit modifiedUnit =
                    new CustomUnit().name("Potato").definition("dozen").symbol("dz");

            CustomUnit updatedUnit = api.unitsUpdate(createdUnit.getPk(), modifiedUnit);

            Assertions.assertEquals(createdUnit.getPk(), updatedUnit.getPk(),
                    "Updated pk should match created");

            int modifiedCount = api.unitsList(1, null, null, null).getCount();
            Assertions.assertEquals(createdCount, modifiedCount,
                    "Count should not have increased on modify");

            CustomUnit retrieved = api.unitsRetrieve(createdUnit.getPk());
            Assertions.assertEquals(updatedUnit.getPk(), retrieved.getPk(), "PKs don't match");
            Assertions.assertEquals(modifiedUnit.getName(), retrieved.getName(),
                    "Names don't match");
            Assertions.assertEquals(modifiedUnit.getDefinition(), retrieved.getDefinition(),
                    "Definitions don't match");
            Assertions.assertEquals(modifiedUnit.getSymbol(), retrieved.getSymbol(),
                    "Symbols don't match");
        } finally {
            api.unitsDestroy(createdUnit.getPk());
        }
    }
}
