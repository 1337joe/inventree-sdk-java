package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAuthenticationWebAuthnSignupApi extends TestApi {
    private AuthenticationWebAuthnSignupApi api;

    @BeforeEach
    void setup() {
        api = new AuthenticationWebAuthnSignupApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAuthWebauthnSignupPost(null);
        api.allauthAuthWebauthnSignupPut(null);
    }

    @Disabled("API endpoint not found")
    @Test
    void allauthAuthWebauthnSignupGet() throws ApiException {
        api.allauthAuthWebauthnSignupGet();
    }
}
