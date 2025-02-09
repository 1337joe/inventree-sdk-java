package com.w3asel.inventree.api;

import com.w3asel.inventree.client.ApiException;
import com.w3asel.inventree.java.Build;
import com.w3asel.inventree.java.PaginatedStockItemList;
import com.w3asel.inventree.java.StockItem;
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
        PaginatedStockItemList actual = api.stockList("1",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        Assertions.assertNotNull(actual);
    }

    @Test
    public void test() throws ApiException {
        StockItem actual = api.stockRetrieve(1223);
        Assertions.assertNotNull(actual);
    }

}
