package com.w3asel.inventree.api;

import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.BomItem;
import com.w3asel.inventree.model.BomItemSubstitute;
import com.w3asel.inventree.model.PaginatedBomItemList;
import com.w3asel.inventree.model.PaginatedBomItemSubstituteList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

public class TestBomApi extends TestApi {
    private BomApi api;

    @BeforeEach
    public void setup() {
        api = new BomApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
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
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertEquals("part", fields, actual.getPart());
        InventreeDemoDataset.assertEquals("sub_part", fields, actual.getSubPart());
        InventreeDemoDataset.assertEquals("quantity", fields, actual.getQuantity());
        InventreeDemoDataset.assertEquals("optional", fields, actual.getOptional());
        InventreeDemoDataset.assertEquals("consumable", fields, actual.getConsumable());
        InventreeDemoDataset.assertEquals("overage", fields, actual.getOverage());
        InventreeDemoDataset.assertEquals("reference", fields, actual.getReference());
        InventreeDemoDataset.assertEquals("note", fields, actual.getNote());
        InventreeDemoDataset.assertEquals("validated", fields, actual.getValidated());
        InventreeDemoDataset.assertEquals("inherited", fields, actual.getInherited());
        InventreeDemoDataset.assertEquals("allow_variants", fields, actual.getAllowVariants());

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
    public void bomList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.BOM_ITEM, null);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedBomItemList actual = api.bomList(limit, null, null, null, null, null, offset, null,
                null, null, null, null, null, null, null, null, null, null, null, null);
        Assertions.assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect total bom item count");
        List<BomItem> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        BomItem actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.BOM_ITEM, actualFirst.getPk()).get(0);
        assertBomItemEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"1", "375"})
    public void bomRetrieve(int pk) throws ApiException {
        BomItem actual = api.bomRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.BOM_ITEM, pk).get(0);
        assertBomItemEquals(expected, actual);

        // verify data not directly in demo dataset
        Double canBuild;
        switch (pk) {
            case 1:
                canBuild = 41.0;
                break;
            case 375:
                canBuild = 411.0;
                break;
            default:
                canBuild = null;
        }
        Assertions.assertEquals(canBuild, actual.getCanBuild(), "Incorrect canBuild");
    }

    private static void assertBomItemSubstituteEquals(JsonObject expected,
            BomItemSubstitute actual) {
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertEquals("bom_item", fields, actual.getBomItem());
        InventreeDemoDataset.assertEquals("part", fields, actual.getPart());

        // not directly available in demo dataset:
        // actual.getPartDetail();

        // not available through schema-mapped API:
        // metadata
    }

    @Test
    public void bomSubstituteList() throws ApiException {
        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.BOM_ITEM_SUBSTITUTE, null);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedBomItemSubstituteList actual =
                api.bomSubstituteList(limit, null, offset, null, null, null);
        Assertions.assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect total bom item count");
        List<BomItemSubstitute> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        BomItemSubstitute actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.BOM_ITEM_SUBSTITUTE, actualFirst.getPk()).get(0);
        assertBomItemSubstituteEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"1"})
    public void bomSubstituteRetrieve(int pk) throws ApiException {
        BomItemSubstitute actual = api.bomSubstituteRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.BOM_ITEM_SUBSTITUTE, pk).get(0);
        assertBomItemSubstituteEquals(expected, actual);
    }
}
