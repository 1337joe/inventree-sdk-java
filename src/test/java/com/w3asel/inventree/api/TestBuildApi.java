package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Build;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBuildApi extends TestApi {
    private BuildApi api;

    @BeforeEach
    public void setup() {
        api = new BuildApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        Build actual = api.buildRetrieve(1);
        Assertions.assertNotNull(actual);

        int limit = 1000;
        api.buildList(limit, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null);
    }
}
