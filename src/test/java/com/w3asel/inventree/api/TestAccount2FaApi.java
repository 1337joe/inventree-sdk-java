package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAccount2FaApi extends TestApi {
    private Account2FaApi api;

    @BeforeEach
    void setup() {
        api = new Account2FaApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAccountAuthenticatorsGet();
        api.allauthAccountAuthenticatorsRecoveryCodesGet();
        api.allauthAccountAuthenticatorsRecoveryCodesPost();
        api.allauthAccountAuthenticatorsTotpDelete();
        api.allauthAccountAuthenticatorsTotpGet();
        api.allauthAccountAuthenticatorsTotpPost(null);
    }

    @Disabled("Unauthorized")
    @Test
    void allauthAccountAuthenticatorsGet() throws ApiException {
        api.allauthAccountAuthenticatorsGet();
    }
}
