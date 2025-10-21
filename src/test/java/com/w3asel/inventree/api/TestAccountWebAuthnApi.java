package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAccountWebAuthnApi extends TestApi {
    private AccountWebAuthnApi api;

    @BeforeEach
    void setup() {
        api = new AccountWebAuthnApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAccountAuthenticatorsWebauthnDelete(null);
        api.allauthAccountAuthenticatorsWebauthnPost(null);
        api.allauthAccountAuthenticatorsWebauthnPut(null);
    }

    @Disabled("API endpoint not found")
    @Test
    void allauthAccountAuthenticatorsWebauthnGet() throws ApiException {
        api.allauthAccountAuthenticatorsWebauthnGet(null);
    }
}
