package com.w3asel.inventree.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.SalesOrder;

public class TestOrderApi extends TestApi {
    private OrderApi api;

    @BeforeEach
    public void setup() {
        api = new OrderApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        SalesOrder actual = api.orderSoRetrieve(1);
        Assertions.assertNotNull(actual);
    }
}
