package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAuthenticationWebAuthnLoginApi extends TestApi {
    private AuthenticationWebAuthnLoginApi api;

    @BeforeEach
    void setup() {
        api = new AuthenticationWebAuthnLoginApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAuthWebauthnAuthenticatePost(null);
        api.allauthAuthWebauthnLoginGet();
        api.allauthAuthWebauthnLoginPost(null);
        api.allauthAuthWebauthnReauthenticateGet();
        api.allauthAuthWebauthnReauthenticatePost(null);
    }

    @Disabled("API endpoint not found")
    @Test
    void allauthAuthWebauthnAuthenticateGet() throws ApiException {
        api.allauthAuthWebauthnAuthenticateGet();
    }
}
