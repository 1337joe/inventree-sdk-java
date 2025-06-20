package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestDataOutputApi extends TestApi {
    private DataOutputApi api;

    @BeforeEach
    void setup() {
        api = new DataOutputApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.dataOutputBulkDestroy(null);
        // api.dataOutputList(null, null, null, null);
        api.dataOutputRetrieve(null);
    }

    @Disabled("No data")
    @Test
    void dataOutputList() throws ApiException {
        int limit = 1000;
        api.dataOutputList(limit, null, null, null);
    }
}
