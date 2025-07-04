package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Build;
import com.w3asel.inventree.model.BuildOutputCreate;
import com.w3asel.inventree.model.BulkRequest;
import com.w3asel.inventree.model.PaginatedBuildList;
import com.w3asel.inventree.model.PaginatedStockItemList;
import com.w3asel.inventree.model.StockItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestBuildApi extends TestApi {
    private BuildApi api;

    @BeforeEach
    void setup() {
        api = new BuildApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.buildAllocateCreate(null, null);
        api.buildAutoAllocateCreate(null, null);
        api.buildCancelCreate(null, null);
        // api.buildCompleteCreate(null, null);
        api.buildCreate(null);
        // api.buildCreateOutputCreate(null, null);
        api.buildDeleteOutputsCreate(null, null);
        api.buildDestroy(null);
        api.buildFinishCreate(null, null);
        api.buildHoldCreate(null);
        api.buildIssueCreate(null);
        api.buildItemBulkDestroy(null);
        api.buildItemCreate(null);
        api.buildItemDestroy(null);
        api.buildItemList(null, null, null, null, null, null, null, null, null, null, null);
        api.buildItemMetadataPartialUpdate(null, null);
        api.buildItemMetadataRetrieve(null);
        api.buildItemMetadataUpdate(null, null);
        api.buildItemPartialUpdate(null, null);
        api.buildItemRetrieve(null);
        api.buildItemUpdate(null, null);
        api.buildLineCreate(null);
        api.buildLineDestroy(null);
        api.buildLineList(null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null);
        api.buildLinePartialUpdate(null, null);
        api.buildLineRetrieve(null);
        api.buildLineUpdate(null, null);
        // api.buildList(null, null, null, null, null, null, null, null, null, null, null, null,
        // null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        // null, null, null, null, null, null, null);
        api.buildMetadataPartialUpdate(null, null);
        api.buildMetadataRetrieve(null);
        api.buildMetadataUpdate(null, null);
        api.buildPartialUpdate(null, null);
        // api.buildRetrieve(null);
        api.buildScrapOutputsCreate(null, null);
        api.buildStatusRetrieve();
        api.buildUnallocateCreate(null, null);
        api.buildUpdate(null, null);
    }

    @Test
    void test() throws ApiException {
        // TODO verify results
        Build actual = api.buildRetrieve(1);
        assertNotNull(actual);

        int limit = 1000;

        api.buildItemList(limit, null, null, null, null, null, null, null, null, null, null);

        api.buildLineList(limit, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null);
    }

    @Disabled("outputCreate response type needs to be List<StockItem>")
    @Test
    void buildCreateOutputCreate_buildCompleteCreate() throws ApiException {
        // no outputs in default dataset
        int buildPk = 20;
        // arbitrarily chosen
        int locationId = 11;

        StockApi stockApi = new StockApi(apiClient);
        int limit = 10;
        PaginatedStockItemList previousBuildItems = stockApi.stockList(limit, null, null, null,
                null, null, null, null, null, null, null, null, null, buildPk, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, 0, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null);
        assertEquals(0, previousBuildItems.getCount(), "Expected no existing outputs");

        try {
            BuildOutputCreate outputCreate = new BuildOutputCreate().quantity(BigDecimal.ONE);
            // List<StockItem> outputResponse = api.buildCreateOutputCreate(buildPk, outputCreate);
            //
            // List<BuildOutput> buildOutputs =
            // outputResponse.stream().map(s -> new BuildOutput().output(s.getPk())).toList();
            //
            // BuildOutputComplete outputComplete =
            // new BuildOutputComplete().location(locationId).outputs(buildOutputs);
            // api.buildCompleteCreate(buildPk, outputComplete);

        } finally {
            // ensure any created items are cleaned up
            PaginatedStockItemList newBuildItems = stockApi.stockList(limit, null, null, null, null,
                    null, null, null, null, null, null, null, null, buildPk, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, 0, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null);

            assertTrue(newBuildItems.getCount() < limit,
                    "Can only clean up to " + limit + " items, found " + newBuildItems.getCount());
            List<Integer> newPks =
                    newBuildItems.getResults().stream().map(StockItem::getPk).toList();
            stockApi.stockBulkDestroy(new BulkRequest().items(newPks));
        }
    }

    private static void assertBuildEquals(JsonObject expected, Build actual, boolean list) {
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        if (list) {
            assertNull(actual.getNotes(), "Expect null notes on list query.");
        } else {
            InventreeDemoDataset.assertNullableEquals(String.class, "notes", fields,
                    actual.getNotes());
        }
        InventreeDemoDataset.assertEquals("barcode_hash", fields, actual.getBarcodeHash());
        InventreeDemoDataset.assertEquals("reference", fields, actual.getReference());
        InventreeDemoDataset.assertEquals("title", fields, actual.getTitle());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "parent", fields,
                actual.getParent());
        InventreeDemoDataset.assertEquals("part", fields, actual.getPart());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "sales_order", fields,
                actual.getSalesOrder());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "take_from", fields,
                actual.getTakeFrom());
        InventreeDemoDataset.assertEquals("external", fields, actual.getExternal());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "destination", fields,
                actual.getDestination());
        InventreeDemoDataset.assertEquals("quantity", fields, actual.getQuantity());
        InventreeDemoDataset.assertEquals("completed", fields, actual.getCompleted());
        InventreeDemoDataset.assertEquals("status", fields, actual.getStatus());
        InventreeDemoDataset.assertNullableEquals(String.class, "batch", fields, actual.getBatch());
        InventreeDemoDataset.assertEquals("creation_date", fields, actual.getCreationDate());
        InventreeDemoDataset.assertNullableEquals(LocalDate.class, "start_date", fields,
                actual.getStartDate());
        InventreeDemoDataset.assertNullableEquals(LocalDate.class, "target_date", fields,
                actual.getTargetDate());
        InventreeDemoDataset.assertNullableEquals(LocalDate.class, "completion_date", fields,
                actual.getCompletionDate());

        // user given as name in demo dataset file, just verify null state matches
        if (fields.get("issued_by").isJsonNull()) {
            assertNull(actual.getIssuedBy(), "Expected null issued-by");
        } else {
            assertNotNull(actual.getIssuedBy(), "Expected non-null issued-by");
        }
        if (fields.get("responsible").isJsonNull()) {
            assertNull(actual.getResponsible(), "Expected null responsible");
        } else {
            assertNotNull(actual.getResponsible(), "Expected non-null responsible");
        }

        InventreeDemoDataset.assertEquals("link", fields, actual.getLink());
        InventreeDemoDataset.assertEquals("priority", fields, actual.getPriority());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "project_code", fields,
                actual.getProjectCode());
        if (fields.get("status_custom_key").isJsonNull()) {
            InventreeDemoDataset.assertEquals("status", fields, actual.getStatusCustomKey());
        } else {
            InventreeDemoDataset.assertEquals("status_custom_key", fields,
                    actual.getStatusCustomKey());
        }
        InventreeDemoDataset.assertEquals("level", fields, actual.getLevel());



        // not directly available in demo dataset:
        // actual.getIssuedByDetail();
        // actual.getOverdue();
        // actual.getPartName();
        // actual.getPartDetail();
        // actual.getProjectCodeDetail();
        // actual.getProjectCodeLabel();
        // actual.getResponsibleDetail();
        // actual.getStatusText();

        // not directly available in Build object
        // metadata
        // reference_int - same as pk?
        // barcode_data
        // completed_by
        // lft
        // rght
        // tree_id
    }

    @Test
    void buildList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.BUILD, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedBuildList actual = api.buildList(limit, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, offset, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total build count");
        List<Build> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        Build actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.BUILD, actualFirst.getPk()).get(0);
        assertBuildEquals(expectedFirst, actualFirst, true);
    }

    @ParameterizedTest
    @CsvSource({"18", "23", "24"})
    void buildRetrieve(int pk) throws ApiException {
        Build actual = api.buildRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.BUILD, pk).get(0);
        assertBuildEquals(expected, actual, false);

        // verify data not directly in demo dataset
        // TODO overdue filter works like this, actual results come back not overdue
        // actual.getTargetDate() != null && actual.getTargetDate().isBefore(LocalDate.now());
        boolean expectedOverdue = false;

        String expectedIssuedByName;
        String expectedPartName;
        String expectedProjectCodeLabel;
        String expectedStatusText;
        switch (pk) {
            case 18:
                expectedIssuedByName = "allaccess";
                expectedPartName = "Blue Chair";
                expectedProjectCodeLabel = "PRO-INF";
                expectedStatusText = "Production";
                break;
            case 23:
                expectedIssuedByName = "admin";
                expectedPartName = "Red Furniture Set";
                expectedProjectCodeLabel = null;
                expectedStatusText = "On Hold";
                break;
            case 24:
                expectedIssuedByName = "admin";
                expectedPartName = "Green Furniture Set";
                expectedProjectCodeLabel = null;
                expectedStatusText = "Production";
                break;
            default:
                expectedIssuedByName = null;
                expectedPartName = null;
                expectedProjectCodeLabel = null;
                expectedStatusText = null;
                break;
        }
        assertEquals(expectedOverdue, actual.getOverdue(),
                "Incorrect overdue state: " + actual.getTargetDate() + " <=> " + LocalDate.now());
        assertEquals(expectedIssuedByName, actual.getIssuedByDetail().getUsername(),
                "Incorrect issuedBy username");
        assertEquals(expectedPartName, actual.getPartName(), "Incorrect part name");
        assertEquals(expectedProjectCodeLabel, actual.getProjectCodeLabel(),
                "Incorrect project code label");
        assertEquals(expectedStatusText, actual.getStatusText(), "Incorrect status text");

        // skipping:
        // actual.getPartDetail();
        // actual.getProjectCodeDetail();
        // actual.getResponsibleDetail();
    }
}
