package com.w3asel.inventree.api;

import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Models;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedSalesOrderList;
import com.w3asel.inventree.model.SalesOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestOrderApi extends TestApi {
    private OrderApi api;

    private static final int limit = 100;

    @BeforeEach
    public void setup() {
        api = new OrderApi(apiClient);
    }

    @Test
    public void testPo() throws ApiException {
        // int limit = 1;

        int poPk = api.orderPoList(limit, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null).getResults().get(0).getPk();
        api.orderPoRetrieve(poPk);
    }

    @Test
    public void testPoExtraLine() throws ApiException {
        // int limit = 1;

        int poExtraPk =
                api.orderPoExtraLineList(limit, null, null, null, null).getResults().get(0).getPk();
        api.orderPoExtraLineRetrieve(poExtraPk);
    }

    @Test
    public void testPoLine() throws ApiException {
        // int limit = 1;

        int poLinePk = api.orderPoLineList(limit, null, null, null, null, null, null, null, null,
                null, null, null, null).getResults().get(0).getPk();
        api.orderPoLineRetrieve(poLinePk);
    }

    @Test
    public void testRo() throws ApiException {
        // int limit = 1;

        int roPk = api.orderRoList(limit, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null).getResults().get(0).getPk();
        api.orderRoRetrieve(roPk);
    }

    @Test
    public void testRoExtraLine() throws ApiException {
        // int limit = 1;

        int roExtraPk =
                api.orderRoExtraLineList(limit, null, null, null, null).getResults().get(0).getPk();
        api.orderRoExtraLineRetrieve(roExtraPk);
    }

    @Test
    public void testRoLine() throws ApiException {
        // int limit = 1;

        int roLinePk =
                api.orderRoLineList(limit, null, null, null, null, null, null, null, null, null)
                        .getResults().get(0).getPk();
        api.orderRoLineRetrieve(roLinePk);
    }

    @Test
    public void testSo() throws ApiException {
        SalesOrder actual = api.orderSoRetrieve(1);
        Assertions.assertNotNull(actual);

        // int limit = 1;

        int soPk = api.orderSoList(limit, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null).getResults().get(0).getPk();
        api.orderSoRetrieve(soPk);
    }

    @Test
    public void testSoExtraLine() throws ApiException {
        // int limit = 1;

        int soExtraPk =
                api.orderSoExtraLineList(limit, null, null, null, null).getResults().get(0).getPk();
        api.orderSoExtraLineRetrieve(soExtraPk);

        api.orderSoExtraLineList(limit, null, null, null, "Coupon");
    }

    @Test
    public void testSoLine() throws ApiException {
        // int limit = 1;

        int soLinePk = api.orderSoLineList(limit, null, null, null, null, null, null, null, null,
                null, null, null).getResults().get(0).getPk();
        api.orderSoLineRetrieve(soLinePk);
    }

    @Test
    public void testSoAllocation() throws ApiException {
        // int limit = 1;

        int soAllocationPk = api.orderSoAllocationList(limit, null, null, null, null, null, null,
                null, null, null, null, null).getResults().get(0).getPk();
        api.orderSoAllocationRetrieve(soAllocationPk);
    }

    @Test
    public void testSoShipment() throws ApiException {
        // int limit = 1;

        int soShipmentPk = api.orderSoShipmentList(limit, null, null, null, null, null, null)
                .getResults().get(0).getPk();
        api.orderSoShipmentRetrieve(soShipmentPk);
    }

    @Test
    public void orderSoList() throws ApiException {

        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Models.ORDER_SALES, null);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedSalesOrderList actual = api.orderSoList(limit, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, offset, null, null, null, null,
                null, null, null, null, null, null, null, null);
        Assertions.assertEquals(expectedList.size(), actual.getCount());
        List<SalesOrder> actualList = actual.getResults();
    }
}
