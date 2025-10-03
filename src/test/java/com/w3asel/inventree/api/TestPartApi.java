package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static com.w3asel.inventree.InventreeDemoDataset.assertNullableFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Category;
import com.w3asel.inventree.model.CategoryTree;
import com.w3asel.inventree.model.PaginatedCategoryList;
import com.w3asel.inventree.model.PaginatedCategoryTreeList;
import com.w3asel.inventree.model.PaginatedPartInternalPriceList;
import com.w3asel.inventree.model.Part;
import com.w3asel.inventree.model.PartBomValidate;
import com.w3asel.inventree.model.PartInternalPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDate;
import java.util.List;

public class TestPartApi extends TestApi {
    private PartApi api;

    @BeforeEach
    void setup() {
        api = new PartApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.partBomCopyCreate(null, null);
        api.partBomValidatePartialUpdate(null, null);
        // api.partBomValidateRetrieve(null);
        api.partBomValidateUpdate(null, null);
        api.partBulkPartialUpdate(null);
        api.partBulkUpdate(null);
        api.partCategoryBulkPartialUpdate(null);
        api.partCategoryBulkUpdate(null);
        api.partCategoryCreate(null);
        api.partCategoryDestroy(null);
        // api.partCategoryList(null, null, null, null, null, null, null, null, null, null, null,
        // null);
        api.partCategoryMetadataPartialUpdate(null, null);
        api.partCategoryMetadataRetrieve(null);
        api.partCategoryMetadataRetrieve(null);
        api.partCategoryParametersCreate(null);
        api.partCategoryParametersDestroy(null);
        api.partCategoryParametersList(null, null);
        api.partCategoryParametersMetadataPartialUpdate(null, null);
        api.partCategoryParametersMetadataRetrieve(null);
        api.partCategoryParametersMetadataUpdate(null, null);
        api.partCategoryParametersPartialUpdate(null, null);
        api.partCategoryParametersRetrieve(null);
        api.partCategoryParametersUpdate(null, null);
        api.partCategoryPartialUpdate(null, null);
        // api.partCategoryRetrieve(null);
        // api.partCategoryTreeList(null, null, null);
        api.partCategoryUpdate(null, null);
        api.partCreate(null);
        api.partDestroy(null);
        api.partInternalPriceCreate(null);
        api.partInternalPriceDestroy(null);
        // api.partInternalPriceList(null, null, null, null, null);
        api.partInternalPricePartialUpdate(null, null);
        // api.partInternalPriceRetrieve(null);
        api.partInternalPriceUpdate(null, null);
        api.partList(null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null);
        api.partMetadataPartialUpdate(null, null);
        api.partMetadataRetrieve(null);
        api.partMetadataUpdate(null, null);
        api.partParameterCreate(null);
        api.partParameterDestroy(null);
        api.partParameterList(null, null, null, null, null, null, null);
        api.partParameterMetadataPartialUpdate(null, null);
        api.partParameterMetadataRetrieve(null);
        api.partParameterMetadataUpdate(null, null);
        api.partParameterPartialUpdate(null, null);
        api.partParameterRetrieve(null);
        api.partParameterTemplateCreate(null);
        api.partParameterTemplateDestroy(null);
        api.partParameterTemplateList(null, null, null, null, null, null, null, null, null, null,
                null);
        api.partParameterTemplateMetadataPartialUpdate(null, null);
        api.partParameterTemplateMetadataRetrieve(null);
        api.partParameterTemplateMetadataUpdate(null, null);
        api.partParameterUpdate(null, null);
        api.partPartialUpdate(null, null);
        api.partPricingPartialUpdate(null, null);
        api.partPricingRetrieve(null);
        api.partPricingUpdate(null, null);
        api.partRelatedCreate(null);
        api.partRelatedDestroy(null);
        api.partRelatedList(null, null, null, null, null, null, null);
        api.partRelatedMetadataPartialUpdate(null, null);
        api.partRelatedMetadataRetrieve(null);
        api.partRelatedMetadataUpdate(null, null);
        api.partRelatedRetrieve(null);
        api.partRelatedUpdate(null, null);
        api.partRequirementsRetrieve(null);
        api.partRetrieve(null, null, null, null, null);
        api.partSalePriceCreate(null);
        api.partSalePriceDestroy(null);
        api.partSalePriceList(null, null, null, null, null);
        api.partSalePricePartialUpdate(null, null);
        api.partSalePriceRetrieve(null);
        api.partSalePriceUpdate(null, null);
        api.partSerialNumbersRetrieve(null);
        api.partStocktakeBulkDestroy(null);
        api.partStocktakeCreate(null);
        api.partStocktakeDestroy(null);
        api.partStocktakeList(null, null, null, null);
        api.partStocktakePartialUpdate(null, null);
        api.partStocktakeRetrieve(null);
        api.partStocktakeUpdate(null, null);
        api.partTestTemplateCreate(null);
        api.partTestTemplateDestroy(null);
        api.partTestTemplateList(null, null, null, null, null, null, null, null, null, null, null);
        api.partTestTemplateMetadataPartialUpdate(null, null);
        api.partTestTemplateMetadataRetrieve(null);
        api.partTestTemplateMetadataUpdate(null, null);
        api.partTestTemplatePartialUpdate(null, null);
        api.partTestTemplateRetrieve(null);
        api.partTestTemplateUpdate(null, null);
        api.partThumbsList(null, null, null);
        api.partThumbsPartialUpdate(null, null);
        api.partThumbsRetrieve(null);
        api.partThumbsUpdate(null, null);
        api.partUpdate(null, null);
    }

    @Test
    void test() throws ApiException {
        // TODO verify results
        Part actual = api.partRetrieve(1, null, null, null, null);
        assertNotNull(actual);

        int limit = 1000;
        api.partList(limit, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null);

        api.partParameterList(limit, null, null, null, null, null, null);

        api.partParameterTemplateList(limit, null, null, null, null, null, null, null, null, null,
                null);

        api.partRelatedList(limit, null, null, null, null, null, null);

        api.partSalePriceList(limit, null, null, null, null);

        api.partStocktakeList(limit, null, null, null);

        api.partTestTemplateList(limit, null, null, null, null, null, null, null, null, null, null);

        api.partThumbsList(limit, null, null);

        api.partCategoryParametersList(limit, null);
    }

    @ParameterizedTest
    @CsvSource({"911", "912"})
    void partBomValidateRetrieve(int pk) throws ApiException {
        PartBomValidate actual = api.partBomValidateRetrieve(pk);
        assertEquals(pk, actual.getPk(), "Incorrect PK");

        JsonObject expected = InventreeDemoDataset.getObjects(Model.PART, pk).get(0);
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("bom_validated", fields, actual.getBomValidated());
        assertFieldEquals("bom_checksum", fields, actual.getBomChecksum());
        assertNullableFieldEquals(LocalDate.class, "bom_checked_date", fields,
                actual.getBomCheckedDate());

        // verify data not directly in demo dataset
        if (911 == pk) {
            assertEquals(5, actual.getBomCheckedBy(), "Incorrect checked by");
            assertNotNull(actual.getBomCheckedByDetail(), "Missing checked by detail");
            assertFalse(actual.getValid(), "Incorrect valid");
        } else if (912 == pk) {
            assertNull(actual.getBomCheckedBy(), "Incorrect checked by");
            assertNull(actual.getBomCheckedByDetail(), "Missing checked by detail");
            assertFalse(actual.getValid(), "Incorrect valid");
        }
    }

    private void assertCategoryEquals(JsonObject expected, Category actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("name", fields, actual.getName());
        assertFieldEquals("description", fields, actual.getDescription());
        assertNullableFieldEquals(Integer.class, "parent", fields, actual.getParent());
        assertFieldEquals("pathstring", fields, actual.getPathstring());
        assertNullableFieldEquals(Integer.class, "default_location", fields,
                actual.getDefaultLocation());
        assertFieldEquals("structural", fields, actual.getStructural());
        assertNullableFieldEquals(String.class, "default_keywords", fields,
                actual.getDefaultKeywords());
        assertFieldEquals("_icon", fields, actual.getIcon());
        assertFieldEquals("level", fields, actual.getLevel());

        // in demo dataset but not directly available from class:
        // metadata
        // lft
        // rght
        // tree_id

        // not directly available in demo dataset
        // actual.getPath();
        // actual.getParentDefaultLocation();
        // actual.getPartCount();
        // actual.getStarred();
        // actual.getSubcategories();
    }

    @Test
    void partCategoryList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.PART_CATEGORY, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedCategoryList actual = api.partCategoryList(limit, null, null, null, null, offset,
                null, null, null, null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect part category list count");
        List<Category> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        Category actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.PART_CATEGORY, actualFirst.getPk()).get(0);
        assertCategoryEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"1,", "5,true"})
    void partCategoryRetrieve(int pk, Boolean pathDetail) throws ApiException {
        Category actual = api.partCategoryRetrieve(pk, pathDetail);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.PART_CATEGORY, pk).get(0);
        assertCategoryEquals(expected, actual);

        if (pathDetail == null || !pathDetail) {
            assertNull(actual.getPath(), "Expected unpopulated path detail");
        } else {
            assertNotNull(actual.getPath(), "Expected populated path detail");
        }

        // verify data not directly in demo dataset
        if (1 == pk) {
            assertEquals(null, actual.getParentDefaultLocation(),
                    "Incorrect parent default location");
            assertEquals(135, actual.getPartCount(), "Incorrect part count");
            assertFalse(actual.getStarred(), "Incorrect starred");
            assertEquals(12, actual.getSubcategories(), "Incorrect subcategories");
        } else if (5 == pk) {
            assertEquals(null, actual.getParentDefaultLocation(),
                    "Incorrect parent default location");
            assertEquals(48, actual.getPartCount(), "Incorrect part count");
            assertFalse(actual.getStarred(), "Incorrect starred");
            assertEquals(0, actual.getSubcategories(), "Incorrect subcategories");
        }
    }

    private void assertCategoryTreeEquals(JsonObject expected, CategoryTree actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("name", fields, actual.getName());
        assertNullableFieldEquals(Integer.class, "parent", fields, actual.getParent());
        assertFieldEquals("structural", fields, actual.getStructural());
        assertFieldEquals("_icon", fields, actual.getIcon());

        // not directly available in demo dataset
        // actual.getSubcategories();
    }

    @Test
    void partCategoryTreeList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.PART_CATEGORY, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedCategoryTreeList actual = api.partCategoryTreeList(limit, offset, null);
        assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect part category tree list count");
        List<CategoryTree> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        CategoryTree actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.PART_CATEGORY, actualFirst.getPk()).get(0);
        assertCategoryTreeEquals(expectedFirst, actualFirst);
    }

    private void assertPartInternalPriceEquals(JsonObject expected, PartInternalPrice actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("part", fields, actual.getPart());
        assertFieldEquals("price", fields, actual.getPrice());
        assertFieldEquals("price_currency", fields, actual.getPriceCurrency());
        assertFieldEquals("quantity", fields, actual.getQuantity());
    }

    @Test
    void partInternalPriceList() throws ApiException {
        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.PART_INTERNAL_PRICE_BREAK, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedPartInternalPriceList actual =
                api.partInternalPriceList(limit, offset, null, null, null);;
        assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect part internal price list count");
        List<PartInternalPrice> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        PartInternalPrice actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.PART_INTERNAL_PRICE_BREAK, actualFirst.getPk()).get(0);
        assertPartInternalPriceEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"1"})
    void partInternalPriceRetrieve(int pk) throws ApiException {
        PartInternalPrice actual = api.partInternalPriceRetrieve(pk);
        JsonObject expected =
                InventreeDemoDataset.getObjects(Model.PART_INTERNAL_PRICE_BREAK, pk).get(0);
        assertPartInternalPriceEquals(expected, actual);
    }
}
