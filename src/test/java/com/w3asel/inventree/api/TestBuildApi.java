package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static com.w3asel.inventree.InventreeDemoDataset.assertNullableFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Build;
import com.w3asel.inventree.model.BuildItem;
import com.w3asel.inventree.model.BuildLine;
import com.w3asel.inventree.model.BuildOutputComplete;
import com.w3asel.inventree.model.BuildOutputCreate;
import com.w3asel.inventree.model.BuildOutputQuantity;
import com.w3asel.inventree.model.BulkRequest;
import com.w3asel.inventree.model.PaginatedBuildItemList;
import com.w3asel.inventree.model.PaginatedBuildLineList;
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
        api.buildConsumeCreate(null, null);
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
        // api.buildItemList(null, null, null, null, null, null, null, null, null, null, null);
        api.buildItemMetadataPartialUpdate(null, null);
        api.buildItemMetadataRetrieve(null);
        api.buildItemMetadataUpdate(null, null);
        api.buildItemPartialUpdate(null, null);
        // api.buildItemRetrieve(null);
        api.buildItemUpdate(null, null);
        api.buildLineCreate(null);
        api.buildLineDestroy(null);
        // api.buildLineList(null, null, null, null, null, null, null, null, null, null, null, null,
        // null, null, null);
        api.buildLinePartialUpdate(null, null);
        // api.buildLineRetrieve(null);
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
    void buildCreateOutputCreate_buildCompleteCreate() throws ApiException {
        // no outputs in default dataset
        int buildPk = 20;
        // arbitrarily chosen
        int locationId = 11;

        StockApi stockApi = new StockApi(apiClient);
        int limit = 10;
        int offset = 0;
        PaginatedStockItemList previousBuildItems = stockApi.stockList(limit, null, null, null,
                null, null, null, null, null, null, null, null, null, buildPk, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, offset,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null);
        assertEquals(0, previousBuildItems.getCount(), "Expected no existing outputs");

        try {
            BigDecimal quantity = BigDecimal.ONE;
            BuildOutputCreate outputCreate = new BuildOutputCreate().quantity(quantity);
            List<StockItem> outputResponse = api.buildCreateOutputCreate(buildPk, outputCreate);

            List<BuildOutputQuantity> buildOutputs = outputResponse.stream()
                    .map(s -> new BuildOutputQuantity().output(s.getPk()).quantity(quantity))
                    .toList();

            BuildOutputComplete outputComplete =
                    new BuildOutputComplete().location(locationId).outputs(buildOutputs);
            api.buildCompleteCreate(buildPk, outputComplete);

        } finally {
            // ensure any created items are cleaned up
            PaginatedStockItemList newBuildItems = stockApi.stockList(limit, null, null, null, null,
                    null, null, null, null, null, null, null, null, buildPk, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null, offset,
                    null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null);

            assertTrue(newBuildItems.getCount() < limit,
                    "Can only clean up to " + limit + " items, found " + newBuildItems.getCount());
            List<Integer> newPks =
                    newBuildItems.getResults().stream().map(StockItem::getPk).toList();
            stockApi.stockBulkDestroy(new BulkRequest().items(newPks));
        }
    }

    private static void assertBuildEquals(JsonObject expected, Build actual, boolean list) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        if (list) {
            assertNull(actual.getNotes(), "Expect null notes on list query.");
        } else {
            assertNullableFieldEquals(String.class, "notes", fields, actual.getNotes());
        }
        assertFieldEquals("barcode_hash", fields, actual.getBarcodeHash());
        assertFieldEquals("reference", fields, actual.getReference());
        assertFieldEquals("title", fields, actual.getTitle());
        assertNullableFieldEquals(Integer.class, "parent", fields, actual.getParent());
        assertFieldEquals("part", fields, actual.getPart());
        assertNullableFieldEquals(Integer.class, "sales_order", fields, actual.getSalesOrder());
        assertNullableFieldEquals(Integer.class, "take_from", fields, actual.getTakeFrom());
        assertFieldEquals("external", fields, actual.getExternal());
        assertNullableFieldEquals(Integer.class, "destination", fields, actual.getDestination());
        assertFieldEquals("quantity", fields, actual.getQuantity());
        assertFieldEquals("completed", fields, actual.getCompleted());
        assertFieldEquals("status", fields, actual.getStatus());
        assertNullableFieldEquals(String.class, "batch", fields, actual.getBatch());
        assertFieldEquals("creation_date", fields, actual.getCreationDate());
        assertNullableFieldEquals(LocalDate.class, "start_date", fields, actual.getStartDate());
        assertNullableFieldEquals(LocalDate.class, "target_date", fields, actual.getTargetDate());
        assertNullableFieldEquals(LocalDate.class, "completion_date", fields,
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

        assertFieldEquals("link", fields, actual.getLink());
        assertFieldEquals("priority", fields, actual.getPriority());
        assertNullableFieldEquals(Integer.class, "project_code", fields, actual.getProjectCode());
        if (fields.get("status_custom_key").isJsonNull()) {
            assertFieldEquals("status", fields, actual.getStatusCustomKey());
        } else {
            assertFieldEquals("status_custom_key", fields, actual.getStatusCustomKey());
        }
        assertFieldEquals("level", fields, actual.getLevel());

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
                null, null, null, null, null, null, null, null, null, null, null, null, null, null);
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

    private static void assertBuildItemEquals(JsonObject expected, BuildItem actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("build_line", fields, actual.getBuildLine());
        assertFieldEquals("stock_item", fields, actual.getStockItem());
        assertFieldEquals("quantity", fields, actual.getQuantity());
        assertNullableFieldEquals(Integer.class, "install_into", fields, actual.getInstallInto());

        // not directly available in demo dataset:
        // actual.getBomReference();
        // actual.getBuild();
        // actual.getBuildDetail();
        // actual.getLocation();
        // actual.getLocationDetail();
        // actual.getPartDetail();
        // actual.getStockItem();
        // actual.getStockItemDetail();
        // actual.getSupplierPartDetail();

        // not directly available in Build object
        // metadata
    }

    @Test
    void buildItemList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.BUILD_ITEM, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedBuildItemList actual = api.buildItemList(limit, null, null, null, null, null, null,
                null, offset, null, null, null, null, null, null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total build item count");
        List<BuildItem> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        BuildItem actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.BUILD_ITEM, actualFirst.getPk()).get(0);
        assertBuildItemEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"267"})
    void buildItemRetrieve(int pk) throws ApiException {
        BuildItem actual = api.buildItemRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.BUILD_ITEM, pk).get(0);
        assertBuildItemEquals(expected, actual);

        // verify data not directly in demo dataset

        String expectedBomReference;
        Integer expectedBuild;
        Integer expectedLocation;
        Integer expectedStockItem;
        switch (pk) {
            case 267:
                expectedBomReference = "PCB1";
                expectedBuild = 26;
                expectedLocation = 7;
                expectedStockItem = 329;
                break;
            default:
                expectedBomReference = null;
                expectedBuild = null;
                expectedLocation = null;
                expectedStockItem = null;
                break;
        }
        assertEquals(expectedBomReference, actual.getBomReference(), "Incorrect BOM reference");
        assertEquals(expectedBuild, actual.getBuild(), "Incorrect build");
        assertEquals(expectedLocation, actual.getLocation(), "Incorrect location");
        assertEquals(expectedStockItem, actual.getStockItem(), "Incorrect stock item");

        // skipping:
        // actual.getBuildDetail();
        // actual.getLocationDetail();
        // actual.getPartDetail();
        // actual.getStockItemDetail();
        // actual.getSupplierPartDetail();
    }

    private static void assertBuildLineEquals(JsonObject expected, BuildLine actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("build", fields, actual.getBuild());
        assertFieldEquals("bom_item", fields, actual.getBomItem());
        assertFieldEquals("quantity", fields, actual.getQuantity());


        // not directly available in demo dataset:
        // actual.getAllocated();
        // actual.getAllocations();
        // actual.getAllowVariants();
        // actual.getAssemblyDetail();
        // actual.getAvailableStock();
        // actual.getAvailableSubstituteStock();
        // actual.getAvailableVariantStock();
        // actual.getBomItemDetail();
        // actual.getBuildDetail();
        // actual.getBuildReference();
        // actual.getConsumable();
        // actual.getConsumed();
        // actual.getExternalStock();
        // actual.getInherited();
        // actual.getInProduction();
        // actual.getOnOrder();
        // actual.getOptional();
        // actual.getPart();
        // actual.getPartDetail();
        // actual.getReference();
        // actual.getScheduledToBuild();
        // actual.getTestable();
        // actual.getTrackable();
    }

    @Test
    void buildLineList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.BUILD_LINE, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedBuildLineList actual =
                api.buildLineList(limit, null, null, null, null, null, null, null, null, null, null,
                        null, offset, null, null, null, null, null, null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total build line count");
        List<BuildLine> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        BuildLine actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.BUILD_LINE, actualFirst.getPk()).get(0);
        assertBuildLineEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"410"})
    void buildLineRetrieve(int pk) throws ApiException {
        BuildLine actual = api.buildLineRetrieve(pk, null, null, null, null, null);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.BUILD_LINE, pk).get(0);
        assertBuildLineEquals(expected, actual);

        // verify data not directly in demo dataset
        Double expectedAllocated;
        Boolean expectedAllowVariants;
        Double expectedAvailableStock;
        Double expectedAvailableSubstituteStock;
        Double expectedAvailableVariantStock;
        String expectedBuildReference;
        Boolean expectedConsumable;
        Double expectedExternalStock;
        Boolean expectedInherited;
        Double expectedInProduction;
        Double expectedOnOrder;
        Boolean expectedOptional;
        Integer expectedPart;
        String expectedReference;
        Double expectedScheduledToBuild;
        Boolean expectedTestable;
        Boolean expectedTrackable;

        switch (pk) {
            case 410:
                expectedAllocated = 100d;
                expectedAllowVariants = false;
                expectedAvailableStock = 0d;
                expectedAvailableSubstituteStock = 0d;
                expectedAvailableVariantStock = 0d;
                expectedBuildReference = "BO0026";
                expectedConsumable = false;
                expectedExternalStock = 0d;
                expectedInherited = false;
                expectedInProduction = 0d;
                expectedOnOrder = 1800d;
                expectedOptional = false;
                expectedPart = 68;
                expectedReference = "PCB1";
                expectedScheduledToBuild = 0d;
                expectedTestable = false;
                expectedTrackable = false;
                break;
            default:
                expectedAllocated = null;
                expectedAllowVariants = null;
                expectedAvailableStock = null;
                expectedAvailableSubstituteStock = null;
                expectedAvailableVariantStock = null;
                expectedBuildReference = null;
                expectedConsumable = null;
                expectedExternalStock = null;
                expectedInherited = null;
                expectedInProduction = null;
                expectedOnOrder = null;
                expectedOptional = null;
                expectedPart = null;
                expectedReference = null;
                expectedScheduledToBuild = 0d;
                expectedTestable = null;
                expectedTrackable = null;
                break;
        }

        assertEquals(expectedAllocated, actual.getAllocated(), "Incorrect allocated");
        assertEquals(expectedAllowVariants, actual.getAllowVariants(), "Incorrect allow variants");
        assertEquals(expectedAvailableStock, actual.getAvailableStock(),
                "Incorrect available stock");
        assertEquals(expectedAvailableSubstituteStock, actual.getAvailableSubstituteStock(),
                "Incorrect available substitute stock");
        assertEquals(expectedAvailableVariantStock, actual.getAvailableVariantStock(),
                "Incorrect available variant stock");
        assertEquals(expectedBuildReference, actual.getBuildReference(),
                "Incorrect build reference");
        assertEquals(expectedConsumable, actual.getConsumable(), "Incorrect consumable");
        assertEquals(expectedExternalStock, actual.getExternalStock(), "Incorrect external stock");
        assertEquals(expectedInherited, actual.getInherited(), "Incorrect inherited");
        assertEquals(expectedInProduction, actual.getInProduction(), "Incorrect in production");
        assertEquals(expectedOnOrder, actual.getOnOrder(), "Incorrect on order");
        assertEquals(expectedOptional, actual.getOptional(), "Incorrect optional");
        assertEquals(expectedPart, actual.getPart(), "Incorrect part");
        assertEquals(expectedReference, actual.getReference(), "Incorrect reference");
        assertEquals(expectedScheduledToBuild, actual.getScheduledToBuild(),
                "Incorrect scheduled to build");
        assertEquals(expectedTestable, actual.getTestable(), "Incorrect testable");
        assertEquals(expectedTrackable, actual.getTrackable(), "Incorrect trackable");

        // skipping:
        actual.getAllocations();
        // actual.getBomItemDetail();
        // actual.getBuildDetail();
        // actual.getPartDetail();
    }

    @ParameterizedTest
    @CsvSource({"18", "23", "24"})
    void buildRetrieve(int pk) throws ApiException {
        Build actual = api.buildRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.BUILD, pk).get(0);
        assertBuildEquals(expected, actual, false);

        // verify data not directly in demo dataset
        boolean expectedOverdue =
                actual.getTargetDate() != null && actual.getTargetDate().isBefore(LocalDate.now());

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
