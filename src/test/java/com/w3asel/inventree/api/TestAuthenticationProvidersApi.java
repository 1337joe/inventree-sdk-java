package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAuthenticationProvidersApi extends TestApi {
    private AuthenticationProvidersApi api;

    @BeforeEach
    void setup() {
        api = new AuthenticationProvidersApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAuthProviderRedirectPost(null, null, null);
        api.allauthAuthProviderSignupGet();
        api.allauthAuthProviderSignupPost(null);
        api.allauthAuthProviderTokenPost(null);
    }

    @Test
    void allauthAuthProviderSignupGet() throws ApiException {
        ApiException thrown =
                assertThrows(ApiException.class, () -> api.allauthAuthProviderSignupGet(),
                        "Expected signupGet() to throw conflict when already authenticated");
        assertEquals(409, thrown.getCode(), "Expected HTTP 409 Conflict");
        assertTrue(thrown.getMessage().contains("Conflict"));
    }
}
