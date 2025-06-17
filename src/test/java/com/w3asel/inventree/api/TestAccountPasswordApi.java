package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAccountPasswordApi extends TestApi {
    private AccountPasswordApi api;

    @BeforeEach
    public void setup() {
        api = new AccountPasswordApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.allauthAccountPasswordChangePost(null);
    }
}
