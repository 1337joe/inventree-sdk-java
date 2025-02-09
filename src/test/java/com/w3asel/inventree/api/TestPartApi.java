package com.w3asel.inventree.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Part;

public class TestPartApi extends TestApi {
    private PartApi api;

    @BeforeEach
    public void setup() {
        api = new PartApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        Part actual = api.partRetrieve(1);
        Assertions.assertNotNull(actual);
    }
}
