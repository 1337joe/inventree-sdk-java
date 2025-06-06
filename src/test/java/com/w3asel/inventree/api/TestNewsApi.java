package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestNewsApi extends TestApi {
    private NewsApi api;

    @BeforeEach
    public void setup() {
        api = new NewsApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.newsList(limit, null, null, null);
    }
}
