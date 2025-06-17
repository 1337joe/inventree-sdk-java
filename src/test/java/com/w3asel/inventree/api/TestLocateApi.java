package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestLocateApi extends TestApi {
    private LocateApi api;

    @BeforeEach
    public void setup() {
        api = new LocateApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.locateCreate(null);
    }
}
