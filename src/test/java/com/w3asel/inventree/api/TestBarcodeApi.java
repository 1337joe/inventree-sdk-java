package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestBarcodeApi extends TestApi {
    private BarcodeApi api;

    @BeforeEach
    public void setup() {
        api = new BarcodeApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.barcodeCreate(null);
        api.barcodeGenerateCreate(null);
        api.barcodeHistoryBulkDestroy(null);
        api.barcodeHistoryDestroy(null);
        // api.barcodeHistoryList(null, null, null, null, null, null);
        api.barcodeHistoryRetrieve(null);
        api.barcodeLinkCreate(null);
        api.barcodePoAllocateCreate(null);
        api.barcodePoReceiveCreate(null);
        api.barcodeSoAllocateCreate(null);
        api.barcodeUnlinkCreate(null);
    }

    @Disabled("No data")
    @Test
    public void barcodeHistoryList() throws ApiException {
        int limit = 1000;
        api.barcodeHistoryList(limit, null, null, null, null, null);
    }
}
