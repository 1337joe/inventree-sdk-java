package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAccountProvidersApi extends TestApi {
    private AccountProvidersApi api;

    @BeforeEach
    public void setup() {
        api = new AccountProvidersApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.allauthAccountProvidersDelete(null);
    }

    @Disabled("Unauthorized")
    @Test
    public void allauthAccountProvidersGet() throws ApiException {
        api.allauthAccountProvidersGet();
    }
}
