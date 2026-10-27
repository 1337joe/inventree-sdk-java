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
import com.w3asel.inventree.model.PartSerialNumber;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TestPricingApi extends TestApi {
    private PricingApi api;

    @BeforeEach
    void setup() {
        api = new PricingApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.pricingInternalPriceCreate(null);
        api.pricingInternalPriceDestroy(null);
        // api.pricingInternalPriceList(null, null, null, null, null);
        api.pricingInternalPricePartialUpdate(null, null);
        // api.pricingInternalPriceRetrieve(null);
        api.pricingInternalPriceUpdate(null, null);
        api.pricingPartPartialUpdate(null, null);
        api.pricingPartRetrieve(null);
        api.pricingPartUpdate(null, null);
        api.pricingSalePriceCreate(null);
        api.pricingSalePriceDestroy(null);
        api.pricingSalePriceList(null, null, null, null, null);
        api.pricingSalePricePartialUpdate(null, null);
        api.pricingSalePriceRetrieve(null);
        api.pricingSalePriceUpdate(null, null);
    }

    @Test
    void test() throws ApiException {
        int limit = 1000;
        api.pricingSalePriceList(limit, null, null, null, null);
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
    void pricingInternalPriceList() throws ApiException {
        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.PRICING_INTERNAL_PRICE_BREAK, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedPartInternalPriceList actual = api.pricingInternalPriceList(limit, offset, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect part internal price list count");
        List<PartInternalPrice> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .collect(Collectors.toList());
        List<Integer> actualPks =
                actualList.stream().map(c -> c.getPk()).sorted().collect(Collectors.toList());
        assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        PartInternalPrice actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.PRICING_INTERNAL_PRICE_BREAK, actualFirst.getPk()).get(0);
        assertPartInternalPriceEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void pricingInternalPriceRetrieve(int pk) throws ApiException {
        PartInternalPrice actual = api.pricingInternalPriceRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.PRICING_INTERNAL_PRICE_BREAK, pk).get(0);
        assertPartInternalPriceEquals(expected, actual);
    }
}
