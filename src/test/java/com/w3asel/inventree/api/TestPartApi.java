package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Part;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPartApi extends TestApi {
    private PartApi api;

    @BeforeEach
    public void setup() {
        api = new PartApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        Part actual = api.partRetrieve(1);
        Assertions.assertNotNull(actual);

        int limit = 1000;
        api.partList(limit, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null);

        api.partCategoryList(limit, null, null, null, null, null, null, null, null, null, null,
                null);

        api.partCategoryTreeList(limit, null, null);


        api.partParameterList(limit, null, null, null, null, null);

        api.partParameterTemplateList(limit, null, null, null, null, null, null, null, null, null,
                null);

        api.partRelatedList(limit, null, null, null, null, null, null);

        api.partSalePriceList(limit, null, null, null, null);

        api.partStocktakeList(limit, null, null, null, null);

        api.partTestTemplateList(limit, null, null, null, null, null, null, null, null, null, null);

        api.partThumbsList(limit, null, null);
    }

    @Test
    public void partInternalPriceList() throws ApiException {
        int limit = 1;
        api.partInternalPriceList(limit, null, null, null, null);
    }
}
