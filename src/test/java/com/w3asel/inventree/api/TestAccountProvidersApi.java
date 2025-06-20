package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAccountProvidersApi extends TestApi {
    private AccountProvidersApi api;

    @BeforeEach
    void setup() {
        api = new AccountProvidersApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAccountProvidersDelete(null);
    }

    @Disabled("Unauthorized")
    @Test
    void allauthAccountProvidersGet() throws ApiException {
        api.allauthAccountProvidersGet();
    }
}
