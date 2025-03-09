package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedStockItemList;
import com.w3asel.inventree.model.StockItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStockApi extends TestApi {
    private StockApi api;

    @BeforeEach
    public void setup() {
        api = new StockApi(apiClient);
    }

    @Test
    public void stockList() throws ApiException {
        int limit = 1000;
        int offset = 0;
        PaginatedStockItemList actual = api.stockList(limit, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, offset, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        Assertions.assertNotNull(actual);
    }

    @Test
    public void test() throws ApiException {
        StockItem actual = api.stockRetrieve(1223);
        Assertions.assertNotNull(actual);

        // api.list
    }

}
