package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestContenttypeApi extends TestApi {
    private ContenttypeApi api;

    @BeforeEach
    public void setup() {
        api = new ContenttypeApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.contenttypeList(limit, null, null, null);
    }
}
