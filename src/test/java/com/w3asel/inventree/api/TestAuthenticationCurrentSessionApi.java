package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAuthenticationCurrentSessionApi extends TestApi {
    private AuthenticationCurrentSessionApi api;

    @BeforeEach
    void setup() {
        api = new AuthenticationCurrentSessionApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAuthSessionDelete();
        api.allauthAuthSessionGet();
    }

    @Disabled("Unauthorized")
    @Test
    void allauthAuthSessionGet() throws ApiException {
        api.allauthAuthSessionGet();
    }
}
