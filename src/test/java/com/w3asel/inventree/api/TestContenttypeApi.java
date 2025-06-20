package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestContenttypeApi extends TestApi {
    private ContenttypeApi api;

    @BeforeEach
    void setup() {
        api = new ContenttypeApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        // api.contenttypeList(null, null, null, null);
        api.contenttypeRetrieve(null);
        api.contenttypeRetrieveModel(null);
    }

    @Test
    void contenttypeList() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.contenttypeList(limit, null, null, null);
    }
}
