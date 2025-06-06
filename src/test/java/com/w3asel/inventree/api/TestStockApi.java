package com.w3asel.inventree.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.GenericStateClass;
import com.w3asel.inventree.model.GenericStateValue;
import com.w3asel.inventree.model.PaginatedStockItemList;
import com.w3asel.inventree.model.PaginatedStockTrackingList;
import com.w3asel.inventree.model.StockItem;
import com.w3asel.inventree.model.StockTracking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestStockApi extends TestApi {
    private StockApi api;

    @BeforeEach
    public void setup() {
        api = new StockApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.stockAddCreate(null);
        api.stockAssignCreate(null);
        api.stockChangeStatusCreate(null);
        api.stockConvertCreate(null, null);
        api.stockCountCreate(null);
        api.stockCreate(null);
        api.stockBulkDestroy(null);
        api.stockDestroy(null);
        api.stockInstallCreate(null, null);
        // api.stockList(null, null, null, null, null, null, null, null, null, null, null, null,
        // null,
        // null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        // null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        // null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        // null, null, null, null, null, null, null);
        api.stockLocationCreate(null);
        api.stockLocationDestroy(null);
        api.stockLocationList(null, null, null, null, null, null, null, null, null, null, null,
                null, null);
        api.stockLocationMetadataPartialUpdate(null, null);
        api.stockLocationMetadataRetrieve(null);
        api.stockLocationMetadataUpdate(null, null);
        api.stockLocationPartialUpdate(null, null);
        api.stockLocationRetrieve(null);
        api.stockLocationTreeList(null, null, null);
        api.stockLocationTypeCreate(null);
        api.stockLocationTypeDestroy(null);
        api.stockLocationTypeList(null, null, null, null);
        api.stockLocationTypeMetadataPartialUpdate(null, null);
        api.stockLocationTypeMetadataRetrieve(null);
        api.stockLocationTypeMetadataUpdate(null, null);
        api.stockLocationTypePartialUpdate(null, null);
        api.stockLocationTypeRetrieve(null);
        api.stockLocationTypeUpdate(null, null);
        api.stockMergeCreate(null);
        api.stockMetadataPartialUpdate(null, null);
        api.stockMetadataRetrieve(null);
        api.stockMetadataUpdate(null, null);
        api.stockPartialUpdate(null, null);
        api.stockRemoveCreate(null);
        // api.stockRetrieve(null);
        api.stockReturnCreate(null, null);
        api.stockSerializeCreate(null, null);
        // api.stockStatusRetrieve();
        api.stockTestCreate(null);
        api.stockTestBulkDestroy(null);
        api.stockTestDestroy(null);
        api.stockTestList(null, null, null, null, null, null, null, null, null, null, null, null,
                null);
        api.stockTestMetadataPartialUpdate(null, null);
        api.stockTestMetadataRetrieve(null);
        api.stockTestMetadataUpdate(null, null);
        api.stockTestPartialUpdate(null, null);
        api.stockTestRetrieve(null);
        api.stockTestUpdate(null, null);
        // api.stockTrackList(null, null, null, null, null, null);
        // api.stockTrackRetrieve(null);
        // api.stockTrackStatusRetrieve();
        api.stockTransferCreate(null);
        api.stockUninstallCreate(null, null);
        api.stockUpdate(null, null);
    }

    @Test
    public void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.stockLocationList(limit, null, null, null, null, null, null, null, null, null, null,
                null, null);
        api.stockTestList(limit, null, null, null, null, null, null, null, null, null, null, null,
                null);
    }

    private static void assertStockItemEquals(JsonObject expected, StockItem actual) {
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertNullableEquals(String.class, "notes", fields, actual.getNotes());
        InventreeDemoDataset.assertEquals("barcode_hash", fields, actual.getBarcodeHash());

        OffsetDateTime expectedUpdated = fields.get("updated").isJsonNull() ? null
                : InventreeDemoDataset.parseOffsetDateTime(fields.get("updated").getAsString())
                        .truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertEquals(expectedUpdated, actual.getUpdated(), "Incorrect date");

        InventreeDemoDataset.assertNullableEquals(Integer.class, "parent", fields,
                actual.getParent());
        InventreeDemoDataset.assertEquals("part", fields, actual.getPart());
        InventreeDemoDataset.assertEquals("supplier_part", fields, actual.getSupplierPart());
        InventreeDemoDataset.assertEquals("location", fields, actual.getLocation());
        InventreeDemoDataset.assertEquals("packaging", fields, actual.getPackaging());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "belongs_to", fields,
                actual.getBelongsTo());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "customer", fields,
                actual.getCustomer());
        InventreeDemoDataset.assertNullableEquals(String.class, "serial", fields,
                actual.getSerial());
        InventreeDemoDataset.assertEquals("link", fields, actual.getLink());
        InventreeDemoDataset.assertNullableEquals(String.class, "batch", fields, actual.getBatch());
        InventreeDemoDataset.assertEquals("quantity", fields, actual.getQuantity());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "build", fields,
                actual.getBuild());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "consumed_by", fields,
                actual.getConsumedBy());
        InventreeDemoDataset.assertEquals("is_building", fields, actual.getIsBuilding());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "purchase_order", fields,
                actual.getPurchaseOrder());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "sales_order", fields,
                actual.getSalesOrder());
        InventreeDemoDataset.assertNullableEquals(LocalDate.class, "expiry_date", fields,
                actual.getExpiryDate());
        InventreeDemoDataset.assertNullableEquals(LocalDate.class, "stocktake_date", fields,
                actual.getStocktakeDate());
        InventreeDemoDataset.assertEquals("delete_on_deplete", fields, actual.getDeleteOnDeplete());
        InventreeDemoDataset.assertEquals("status", fields, actual.getStatus());
        InventreeDemoDataset.assertEquals("purchase_price_currency", fields,
                actual.getPurchasePriceCurrency());
        InventreeDemoDataset.assertNullableEquals(BigDecimal.class, "purchase_price", fields,
                actual.getPurchasePrice());
        InventreeDemoDataset.assertNullableEquals(Integer.class, "owner", fields,
                actual.getOwner());

        // inherits status value if null
        if (fields.get("status_custom_key").isJsonNull()) {
            InventreeDemoDataset.assertEquals("status", fields, actual.getStatusCustomKey());
        } else {
            InventreeDemoDataset.assertEquals("status_custom_key", fields,
                    actual.getStatusCustomKey());
        }

        // not directly available in demo dataset:
        // actual.getAllocated();
        // actual.getChildItems();
        // actual.getExpired();
        // actual.getInstalledItems();
        // actual.getInStock();
        // actual.getLocationDetail();
        // actual.getLocationPath();
        // actual.getMPN();
        // actual.getPartDetail();
        // actual.getPurchaseOrderReference();
        // actual.getSalesOrderReference();
        // actual.getSKU();
        // actual.getStale();
        // actual.getStatusText();
        // actual.getSupplierPartDetail();
        // actual.getTags();
        // actual.getTests();
        // actual.getTrackingItems();
        // actual.getUsePackSize();

        // not available through schema-mapped API:
        // metadata
        // barcode_data
        // serial_int
        // stocktake_user
        // review_needed
        // lft
        // rght
        // tree_id
        // level

    }

    @Test
    public void stockList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.STOCK_ITEM, null);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedStockItemList actual = api.stockList(limit, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, offset, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        Assertions.assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect total stock item count");
        List<StockItem> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        StockItem actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.STOCK_ITEM, actualFirst.getPk()).get(0);
        assertStockItemEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"2", "220"})
    public void stockRetrieve(int pk) throws ApiException {
        StockItem actual = api.stockRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.STOCK_ITEM, pk).get(0);
        assertStockItemEquals(expected, actual);

        // TODO verify non-demo dataset fields?
    }

    @Test
    public void stockStatusRetrieve() throws ApiException {
        GenericStateClass actual = api.stockStatusRetrieve();
        Assertions.assertEquals("StockStatus", actual.getPropertyClass(), "Incorrect status class");

        Map<String, GenericStateValue> actualValues = actual.getValues();
        Assertions.assertFalse(actualValues.isEmpty(), "Missing stock status values");

        // values
        String name = "ATTENTION";
        Assertions.assertTrue(actualValues.containsKey(name), "Missing " + name + " stock status");
        GenericStateValue actualValue = actualValues.get(name);
        Assertions.assertNull(actualValue.getCustom(),
                "Non-custom value should not be marked custom");
        Assertions.assertEquals(50, actualValue.getKey(), "Incorrect status code key");
        Assertions.assertNull(actualValue.getLogicalKey(), "Incorrect status code logical key");
        Assertions.assertEquals(name, actualValue.getName(), "Incorrect status code name");
        Assertions.assertEquals("Attention needed", actualValue.getLabel(),
                "Incorrect status code label");
    }

    @Disabled("Custom value is not included in returned list")
    @Test
    public void stockStatusRetrieve_custom() throws ApiException {
        GenericStateClass actual = api.stockStatusRetrieve();
        Map<String, GenericStateValue> actualValues = actual.getValues();

        // check for custom values
        List<JsonObject> customList =
                InventreeDemoDataset.getObjects(Model.CUSTOM_USER_STATE, null);

        // remove custom state models that don't apply here
        String targetModel = "stock";
        Iterator<JsonObject> customListIterator = customList.iterator();
        outer: while (customListIterator.hasNext()) {
            JsonObject nextFields = InventreeDemoDataset.getFields(customListIterator.next());
            JsonArray models = nextFields.get("model").getAsJsonArray();
            for (JsonElement model : models) {
                if (targetModel.equals(model.getAsString())) {
                    continue outer;
                }
            }
            customListIterator.remove();
        }
        Assertions.assertTrue(customList.size() > 0, "Expected custom user states: " + targetModel);

        JsonObject expectedCustom = InventreeDemoDataset.getFields(customList.get(0));

        String customName = expectedCustom.get("name").getAsString();
        Assertions.assertTrue(actualValues.containsKey(customName),
                "Missing " + customName + " custom stock status");
        GenericStateValue customActual = actualValues.get(customName);

        Assertions.assertTrue(customActual.getCustom(), "Custom value should be marked custom");
        InventreeDemoDataset.assertEquals("key", expectedCustom, customActual.getKey());

        // TODO is it intended that logical_key come back as null?
        // InventreeDemoDataset.assertEquals("logical_key", expectedCustom,
        // customActual.getLogicalKey());

        InventreeDemoDataset.assertEquals("name", expectedCustom, customActual.getName());
        InventreeDemoDataset.assertEquals("label", expectedCustom, customActual.getLabel());
        InventreeDemoDataset.assertEquals("color", expectedCustom, customActual.getColor());
    }

    private static void assertStockTrackingEquals(JsonObject expected, StockTracking actual) {
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertEquals("tracking_type", fields, actual.getTrackingType());
        InventreeDemoDataset.assertEquals("item", fields, actual.getItem());
        InventreeDemoDataset.assertNullableEquals(String.class, "notes", fields, actual.getNotes());

        // user given as name in demo dataset file, just verify null state matches
        if (fields.get("user").isJsonNull()) {
            Assertions.assertNull(actual.getUser(), "Expected null user");
        } else {
            Assertions.assertNotNull(actual.getUser(), "Expected non-null user");
        }

        OffsetDateTime expectedDate = fields.get("date").isJsonNull() ? null
                : InventreeDemoDataset.parseOffsetDateTime(fields.get("date").getAsString())
                        .truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertEquals(expectedDate, actual.getDate(), "Incorrect date");

        JsonObject expectedDeltas = fields.get("deltas").getAsJsonObject();
        // TODO can this be strongly typed in the schema?
        @SuppressWarnings("unchecked")
        Map<String, Object> actualDeltas = (Map<String, Object>) actual.getDeltas();
        for (String key : expectedDeltas.keySet()) {
            if (actualDeltas.get(key) instanceof Double) {
                Assertions.assertEquals(expectedDeltas.get(key).getAsDouble(),
                        actualDeltas.get(key), "Deltas mismatch on key: " + key);
            } else {
                Assertions.assertEquals(expectedDeltas.get(key).getAsString(),
                        actualDeltas.get(key), "Deltas mismatch on key: " + key);
            }
        }

        // not directly available in demo dataset:
        // actual.getLabel()
        // actual.getUser()

        // not available through schema-mapped API:
        Assertions.assertNull(actual.getItemDetail(), "Expected null item details");
        Assertions.assertNull(actual.getUserDetail(), "Expected null user details");
    }

    @Test
    public void stockTrackList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.STOCK_TRACKING, null);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedStockTrackingList actual =
                api.stockTrackList(limit, null, offset, null, null, null);
        Assertions.assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect total stock tracking count");
        List<StockTracking> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        StockTracking actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.STOCK_TRACKING, actualFirst.getPk()).get(0);
        assertStockTrackingEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"285", "1356", "1357"})
    public void stockTrackRetrieve(int stockTrack) throws ApiException {
        StockTracking actual = api.stockTrackRetrieve(stockTrack);
        JsonObject expected =
                InventreeDemoDataset.getObjects(Model.STOCK_TRACKING, stockTrack).get(0);
        assertStockTrackingEquals(expected, actual);

        // verify data not directly in demo dataset
        Integer expectedUser;
        String expectedLabel;
        switch (stockTrack) {
            case 285: // create, null user
                expectedUser = null;
                expectedLabel = "Stock item created";
                break;
            case 1357: // create, admin user
                expectedUser = 1;
                expectedLabel = "Stock item created";
                break;
            case 1356: // shipped (sale), admin user
                expectedUser = 1;
                expectedLabel = "Shipped against Sales Order";
                break;
            default:
                expectedUser = null;
                expectedLabel = null;
        }
        Assertions.assertEquals(expectedLabel, actual.getLabel(), "Incorrect label");
        Assertions.assertEquals(expectedUser, actual.getUser(), "Incorrect user");
    }

    @Test
    public void stockTrackStatusRetrieve() throws ApiException {
        GenericStateClass actual = api.stockTrackStatusRetrieve();
        Assertions.assertEquals("StockHistoryCode", actual.getPropertyClass(),
                "Incorrect status class");

        Map<String, GenericStateValue> actualValues = actual.getValues();
        Assertions.assertFalse(actualValues.isEmpty(), "Missing stock history values");

        // values
        String name = "CREATED";
        Assertions.assertTrue(actualValues.containsKey(name), "Missing " + name + " stock history");
        GenericStateValue actualValue = actualValues.get(name);
        Assertions.assertEquals(1, actualValue.getKey(), "Incorrect history code key");
        Assertions.assertEquals(name, actualValue.getName(), "Incorrect history code name");
        Assertions.assertEquals("Stock item created", actualValue.getLabel(),
                "Incorrect history code label");
    }
}
