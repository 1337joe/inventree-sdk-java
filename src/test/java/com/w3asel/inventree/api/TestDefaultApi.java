package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.InfoApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDefaultApi extends TestApi {
    private DefaultApi api;

    @BeforeEach
    public void setup() {
        api = new DefaultApi(apiClient);
    }

    @Test
    public void rootRetrieve() throws ApiException {
        // TODO verify results
        InfoApi actual = api.rootRetrieve();
    }
}
