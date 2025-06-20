package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestSearchApi extends TestApi {
    private SearchApi api;

    @BeforeEach
    void setup() {
        api = new SearchApi(apiClient);
    }

    @Disabled
    @Test
    void searchCreate() throws ApiException {
        api.searchCreate(null);
    }
}
