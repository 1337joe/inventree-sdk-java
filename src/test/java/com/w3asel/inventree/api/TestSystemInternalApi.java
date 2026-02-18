package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestSystemInternalApi extends TestApi {
    private SystemInternalApi api;

    @BeforeEach
    void setup() {
        api = new SystemInternalApi(apiClient);
    }

    @Test
    @Disabled("Not implemented")
    void systemHealthRetrieve() throws ApiException {
        api.systemInternalObservabilityEndCreate(null);
    }
}
