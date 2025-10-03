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
import com.w3asel.inventree.model.BomItem;
import com.w3asel.inventree.model.BomItemSubstitute;
import com.w3asel.inventree.model.PaginatedBomItemList;
import com.w3asel.inventree.model.PaginatedBomItemSubstituteList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

public class TestBomApi extends TestApi {
    private BomApi api;

    @BeforeEach
    void setup() {
        api = new BomApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.bomBulkDestroy(null);
        api.bomCreate(null);
        api.bomDestroy(null);
        // api.bomList(null, null, null, null, null, null, null, null, null, null, null, null, null,
        // null, null, null, null, null, null, null);
        api.bomMetadataPartialUpdate(null, null);
        api.bomMetadataRetrieve(null);
        api.bomMetadataUpdate(null, null);
        api.bomPartialUpdate(null, null);
        // api.bomRetrieve(null);
        api.bomSubstituteCreate(null);
        api.bomSubstituteDestroy(null);
        // api.bomSubstituteList(null, null, null, null, null, null);
        api.bomSubstituteMetadataPartialUpdate(null, null);
        api.bomSubstituteMetadataRetrieve(null);
        api.bomSubstituteMetadataUpdate(null, null);
        api.bomSubstitutePartialUpdate(null, null);
        // api.bomSubstituteRetrieve(null);
        api.bomSubstituteUpdate(null, null);
        api.bomUpdate(null, null);
        api.bomValidatePartialUpdate(null, null);
        api.bomValidateUpdate(null, null);
    }

    private static void assertBomItemEquals(JsonObject expected, BomItem actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("part", fields, actual.getPart());
        assertFieldEquals("sub_part", fields, actual.getSubPart());
        assertFieldEquals("quantity", fields, actual.getQuantity());
        assertFieldEquals("optional", fields, actual.getOptional());
        assertFieldEquals("consumable", fields, actual.getConsumable());
        assertFieldEquals("setup_quantity", fields, actual.getSetupQuantity());
        assertFieldEquals("attrition", fields, actual.getAttrition());
        assertNullableFieldEquals(Double.class, "rounding_multiple", fields,
                actual.getRoundingMultiple());
        assertFieldEquals("reference", fields, actual.getReference());
        assertFieldEquals("note", fields, actual.getNote());
        assertFieldEquals("validated", fields, actual.getValidated());
        assertFieldEquals("inherited", fields, actual.getInherited());
        assertFieldEquals("allow_variants", fields, actual.getAllowVariants());

        // not directly available in demo dataset:
        // actual.getAvailableStock()
        // actual.getAvailableSubstituteStock();
        // actual.getAvailableVariantStock();
        // actual.getBuilding();
        // actual.getCanBuild();
        // actual.getExternalStock();
        // actual.getOnOrder();
        // actual.getPartDetail();
        // actual.getPricingMax();
        // actual.getPricingMaxTotal();
        // actual.getPricingMin();
        // actual.getPricingMinTotal();
        // actual.getPricingUpdated();
        // actual.getSubPartDetail();
        // actual.getSubstitutes();

        // not available through schema-mapped API:
        // metadata
        // checksum
    }

    @Test
    void bomList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.BOM_ITEM, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedBomItemList actual = api.bomList(limit, null, null, null, null, null, null, null,
                offset, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total bom item count");
        List<BomItem> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        BomItem actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.BOM_ITEM, actualFirst.getPk()).get(0);
        assertBomItemEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"1,,,", "375,true,false,false", "1,false,true,true"})
    void bomRetrieve(int pk, Boolean requestCanBuild, Boolean requestPartDetail,
            Boolean requestSubPartDetail) throws ApiException {
        BomItem actual =
                api.bomRetrieve(pk, requestCanBuild, requestPartDetail, requestSubPartDetail);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.BOM_ITEM, pk).get(0);
        assertBomItemEquals(expected, actual);

        // verify data not directly in demo dataset
        Double canBuild;
        switch (pk) {
            case 1:
                canBuild = 41.0;
                break;
            case 375:
                canBuild = 551.0;
                break;
            default:
                canBuild = null;
        }

        // defaults to true
        if (requestCanBuild == null || requestCanBuild) {
            assertEquals(canBuild, actual.getCanBuild(), "Incorrect canBuild");
        } else {
            assertNull(actual.getCanBuild(), "Expected unpopulated canBuild");
        }

        if (requestPartDetail == null || !requestPartDetail) {
            assertNull(actual.getPartDetail(), "Expected unpopulated part details");
        } else {
            assertNotNull(actual.getPartDetail(), "Expected populated part details");
            assertEquals(actual.getPart(), actual.getPartDetail().getPk(),
                    "Incorrect part details");
        }
        if (requestSubPartDetail == null || !requestSubPartDetail) {
            assertNull(actual.getPartDetail(), "Expected unpopulated sub-part details");
        } else {
            assertNotNull(actual.getSubPartDetail(), "Expected populated sub-part details");
            assertEquals(actual.getSubPart(), actual.getSubPartDetail().getPk(),
                    "Incorrect sub-part details");
        }
    }

    private static void assertBomItemSubstituteEquals(JsonObject expected,
            BomItemSubstitute actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("bom_item", fields, actual.getBomItem());
        assertFieldEquals("part", fields, actual.getPart());

        // not directly available in demo dataset:
        // actual.getPartDetail();

        // not available through schema-mapped API:
        // metadata
    }

    @Test
    void bomSubstituteList() throws ApiException {
        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.BOM_ITEM_SUBSTITUTE, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedBomItemSubstituteList actual =
                api.bomSubstituteList(limit, null, offset, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total bom item count");
        List<BomItemSubstitute> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        BomItemSubstitute actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.BOM_ITEM_SUBSTITUTE, actualFirst.getPk()).get(0);
        assertBomItemSubstituteEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"1"})
    void bomSubstituteRetrieve(int pk) throws ApiException {
        BomItemSubstitute actual = api.bomSubstituteRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.BOM_ITEM_SUBSTITUTE, pk).get(0);
        assertBomItemSubstituteEquals(expected, actual);
    }
}
