package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.BulkRequest;
import com.w3asel.inventree.model.GenericStateClass;
import com.w3asel.inventree.model.PaginatedSalesOrderList;
import com.w3asel.inventree.model.PurchaseOrderLineItemReceive;
import com.w3asel.inventree.model.PurchaseOrderReceive;
import com.w3asel.inventree.model.SalesOrder;
import com.w3asel.inventree.model.StockItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestOrderApi extends TestApi {
    private OrderApi api;

    private static final int limit = 1000;

    @BeforeEach
    void setup() {
        api = new OrderApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.orderPoCancelCreate(null);
        api.orderPoCompleteCreate(null, null);
        api.orderPoCreate(null);
        api.orderPoDestroy(null);
        api.orderPoExtraLineCreate(null);
        api.orderPoExtraLineDestroy(null);
        api.orderPoExtraLineList(null, null, null, null, null, null);
        api.orderPoExtraLinePartialUpdate(null, null);
        api.orderPoExtraLineRetrieve(null);
        api.orderPoExtraLineUpdate(null, null);
        api.orderPoHoldCreate(null);
        api.orderPoIssueCreate(null);
        api.orderPoLineBulkDestroy(null);
        api.orderPoLineCreate(null);
        api.orderPoLineDestroy(null);
        api.orderPoLineList(null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null);
        api.orderPoLinePartialUpdate(null, null);
        api.orderPoLineRetrieve(null, null, null);
        api.orderPoLineUpdate(null, null);
        api.orderPoList(null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null);
        api.orderPoPartialUpdate(null, null);
        // api.orderPoReceiveCreate(null, null);
        api.orderPoRetrieve(null, null);
        // api.orderPoStatusRetrieve();
        api.orderPoUpdate(null, null);

        api.orderRoCancelCreate(null);
        api.orderRoCompleteCreate(null);
        api.orderRoCreate(null);
        api.orderRoDestroy(null);
        api.orderRoExtraLineCreate(null);
        api.orderRoExtraLineDestroy(null);
        api.orderRoExtraLineList(null, null, null, null, null, null);
        api.orderRoExtraLinePartialUpdate(null, null);
        api.orderRoExtraLineRetrieve(null);
        api.orderRoExtraLineUpdate(null, null);
        api.orderRoHoldCreate(null);
        api.orderRoIssueCreate(null);
        api.orderRoLineCreate(null);
        api.orderRoLineDestroy(null);
        api.orderRoLineList(null, null, null, null, null, null, null, null, null, null, null, null, null);
        api.orderRoLinePartialUpdate(null, null);
        api.orderRoLineRetrieve(null, null, null, null);
        api.orderRoLineStatusRetrieve();
        api.orderRoLineUpdate(null, null);
        api.orderRoList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        api.orderRoPartialUpdate(null, null);
        api.orderRoReceiveCreate(null, null);
        api.orderRoRetrieve(null, null);
        // api.orderRoStatusRetrieve();
        api.orderRoUpdate(null, null);

        api.orderSoAllocateCreate(null, null);
        api.orderSoAllocateSerialsCreate(null, null);
        api.orderSoAllocationBulkDestroy(null);
        api.orderSoAllocationBulkPartialUpdate(null);
        api.orderSoAllocationBulkUpdate(null);
        api.orderSoAllocationDestroy(null);
        api.orderSoAllocationList(null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null);
        api.orderSoAllocationPartialUpdate(null, null);
        api.orderSoAllocationRetrieve(null);
        api.orderSoAllocationUpdate(null, null);
        api.orderSoAutoAllocateCreate(null, null);
        api.orderSoCancelCreate(null);
        api.orderSoCompleteCreate(null, null);
        api.orderSoCreate(null);
        api.orderSoDestroy(null);
        api.orderSoExtraLineCreate(null);
        api.orderSoExtraLineDestroy(null);
        api.orderSoExtraLineList(null, null, null, null, null, null);
        api.orderSoExtraLinePartialUpdate(null, null);
        api.orderSoExtraLineRetrieve(null);
        api.orderSoExtraLineUpdate(null, null);
        api.orderSoHoldCreate(null);
        api.orderSoIssueCreate(null);
        api.orderSoLineCreate(null);
        api.orderSoLineDestroy(null);
        api.orderSoLineList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        api.orderSoLinePartialUpdate(null, null);
        api.orderSoLineRetrieve(null, null, null, null);
        api.orderSoLineUpdate(null, null);
        api.orderSoList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        api.orderSoPartialUpdate(null, null);
        api.orderSoRetrieve(null, null);
        api.orderSoShipmentCreate(null);
        api.orderSoShipmentDestroy(null);
        api.orderSoShipmentList(null, null, null, null, null, null, null, null, null, null, null);
        api.orderSoShipmentPartialUpdate(null, null);
        api.orderSoShipmentRetrieve(null);
        api.orderSoShipmentShipCreate(null, null);
        api.orderSoShipmentUpdate(null, null);
        // api.orderSoStatusRetrieve();
        api.orderSoUpdate(null, null);

        api.orderTransferOrderAllocateCreate(null, null);
        api.orderTransferOrderAllocateSerialsCreate(null, null);
        api.orderTransferOrderAllocationBulkPartialUpdate(null);
        api.orderTransferOrderAllocationBulkUpdate(null);
        api.orderTransferOrderAllocationDestroy(null);
        api.orderTransferOrderAllocationList(null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null);
        api.orderTransferOrderAllocationPartialUpdate(null, null);
        api.orderTransferOrderAllocationRetrieve(null);
        api.orderTransferOrderAllocationRetrieve(null);
        api.orderTransferOrderCancelCreate(null);
        api.orderTransferOrderCompleteCreate(null, null);
        api.orderTransferOrderCreate(null);
        api.orderTransferOrderDestroy(null);
        api.orderTransferOrderHoldCreate(null);
        api.orderTransferOrderIssueCreate(null);
        api.orderTransferOrderLineCreate(null);
        api.orderTransferOrderLineDestroy(null);
        api.orderTransferOrderLineList(null, null, null, null, null, null, null, null, null, null, null, null, null,
                null);
        api.orderTransferOrderLinePartialUpdate(null, null);
        api.orderTransferOrderLineRetrieve(null, null, null);
        api.orderTransferOrderLineUpdate(null, null);
        api.orderTransferOrderList(null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        api.orderTransferOrderPartialUpdate(null, null);
        api.orderTransferOrderRetrieve(null);
        api.orderTransferOrderUpdate(null, null);
    }

    @Test
    void testPo() throws ApiException {
        // int limit = 1;

        int poPk = api
                .orderPoList(limit, null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null)
                .getResults().get(0).getPk();
        api.orderPoRetrieve(poPk, null);

        // TODO verify results
    }

    @Test
    void orderPoExtraLineList() throws ApiException {
        // int limit = 1;

        int poExtraPk = api.orderPoExtraLineList(limit, null, null, null, null, null).getResults()
                .get(0).getPk();
        api.orderPoExtraLineRetrieve(poExtraPk);

        // TODO verify results
    }

    @Test
    void testPoLine() throws ApiException {
        // int limit = 1;

        int poLinePk = api.orderPoLineList(limit, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null).getResults().get(0).getPk();
        api.orderPoLineRetrieve(poLinePk, null, null);

        // TODO verify results
    }

    @Test
    void orderPoReceiveCreate() throws ApiException {
        int order = 16;
        int lineItem = 38;
        int location = 1;
        String[] serialNumbers = {"100", "101"};

        PurchaseOrderLineItemReceive lineReceive =
                new PurchaseOrderLineItemReceive().location(location);
        lineReceive.setLineItem(lineItem);
        lineReceive.setQuantity(BigDecimal.valueOf(serialNumbers.length));
        lineReceive.setSerialNumbers(String.join(",", serialNumbers));

        List<PurchaseOrderLineItemReceive> items = List.of(lineReceive);

        PurchaseOrderReceive receive = new PurchaseOrderReceive().items(items);

        // Note: This causes the received counter on the purchase order line to increase even after
        // the stock items are deleted
        List<StockItem> result = api.orderPoReceiveCreate(order, receive);
        try {
            assertEquals(serialNumbers.length, result.size(),
                    "Expected serialized items to be created");
        } finally {
            StockApi stockApi = new StockApi(apiClient);
            List<Integer> itemPks =
                    result.stream().map(StockItem::getPk).collect(Collectors.toList());
            stockApi.stockBulkDestroy(new BulkRequest().items(itemPks));
        }
    }

    @Test
    void orderPoStatusRetrieve() throws ApiException {
        GenericStateClass actual = api.orderPoStatusRetrieve();
        assertNotNull(actual, "Expected populated status");
        assertEquals("PurchaseOrderStatus", actual.getStatusClass(), "Incorrect status class");
    }

    @Test
    void testRo() throws ApiException {
        // int limit = 1;

        int roPk = api.orderRoList(limit, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null).getResults().get(0).getPk();
        api.orderRoRetrieve(roPk, null);

        // TODO verify results
    }

    @Test
    void testRoExtraLine() throws ApiException {
        // int limit = 1;

        int roExtraPk = api.orderRoExtraLineList(limit, null, null, null, null, null).getResults()
                .get(0).getPk();
        api.orderRoExtraLineRetrieve(roExtraPk);

        // TODO verify results
    }

    @Test
    void testRoLine() throws ApiException {
        // int limit = 1;

        int roLinePk = api.orderRoLineList(limit, null, null, null, null, null, null, null, null,
                null, null, null, null).getResults().get(0).getPk();
        api.orderRoLineRetrieve(roLinePk, null, null, null);

        // TODO verify results
    }

    @Test
    void orderRoStatusRetrieve() throws ApiException {
        GenericStateClass actual = api.orderRoStatusRetrieve();
        assertNotNull(actual, "Expected populated status");
        assertEquals("ReturnOrderStatus", actual.getStatusClass(), "Incorrect status class");
    }

    @Test
    void testSo() throws ApiException {
        SalesOrder actual = api.orderSoRetrieve(1, null);
        assertNotNull(actual);

        // int limit = 1;

        int soPk = api.orderSoList(limit, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null).getResults().get(0).getPk();
        api.orderSoRetrieve(soPk, null);

        // TODO verify results
    }

    @Test
    void testSoExtraLine() throws ApiException {
        // int limit = 1;

        int soExtraPk = api.orderSoExtraLineList(limit, null, null, null, null, null).getResults()
                .get(0).getPk();
        api.orderSoExtraLineRetrieve(soExtraPk);

        api.orderSoExtraLineList(limit, null, null, null, null, "Coupon");

        // TODO verify results
    }

    @Test
    void testSoLine() throws ApiException {
        // int limit = 1;

        int soLinePk = api.orderSoLineList(limit, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null).getResults().get(0).getPk();
        api.orderSoLineRetrieve(soLinePk, null, null, null);

        // TODO verify results
    }

    @Test
    void orderSoAllocation() throws ApiException {
        // int limit = 1;

        int soAllocationPk = api.orderSoAllocationList(limit, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null).getResults()
                .get(0).getPk();
        api.orderSoAllocationRetrieve(soAllocationPk);

        // TODO verify results
    }

    @Test
    void orderSoShipment() throws ApiException {
        // int limit = 1;

        int soShipmentPk =
                api.orderSoShipmentList(limit, null, null, null, null, null, null, null, null, null, null)
                        .getResults().get(0).getPk();
        api.orderSoShipmentRetrieve(soShipmentPk);

        // TODO verify results
    }

    @Test
    void orderSoList() throws ApiException {

        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.ORDER_SALES, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedSalesOrderList actual = api.orderSoList(limit, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, offset, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount());
        List<SalesOrder> actualList = actual.getResults();

        // TODO verify results
    }

    @Test
    void orderSoStatusRetrieve() throws ApiException {
        GenericStateClass actual = api.orderSoStatusRetrieve();
        assertNotNull(actual, "Expected populated status");
        assertEquals("SalesOrderStatus", actual.getStatusClass(), "Incorrect status class");
    }

    @Disabled("Missing sample data")
    @Test
    void testTransferOrderAllocation() throws ApiException {
        // int limit = 1;

        int toaPk = api
                .orderTransferOrderAllocationList(limit, null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null)
                .getResults().get(0).getPk();
        api.orderTransferOrderAllocationRetrieve(toaPk);

        // TODO verify results
    }

    @Disabled("Missing sample data")
    @Test
    void testTransferOrder() throws ApiException {
        // int limit = 1;

        int toPk = api
                .orderTransferOrderList(limit, null, null, null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                        null)
                .getResults().get(0).getPk();
        api.orderTransferOrderRetrieve(toPk);

        // TODO verify results
    }
}
