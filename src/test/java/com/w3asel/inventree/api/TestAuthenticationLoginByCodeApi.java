package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAuthenticationLoginByCodeApi extends TestApi {
    private AuthenticationLoginByCodeApi api;

    @BeforeEach
    void setup() {
        api = new AuthenticationLoginByCodeApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAuthCodeConfirmPost(null);
        api.allauthAuthCodeRequestPost(null);
    }
}
