package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestSessionsApi extends TestApi {
    private SessionsApi api;

    @BeforeEach
    void setup() {
        api = new SessionsApi(apiClient);
    }

    @Disabled("Unauthorized")
    @Test
    void allauthAuthSessionsGet() throws ApiException {
        api.allauthAuthSessionsGet();
    }
}
