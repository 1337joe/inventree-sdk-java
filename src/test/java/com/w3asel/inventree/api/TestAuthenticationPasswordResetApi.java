package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAuthenticationPasswordResetApi extends TestApi {
    private AuthenticationPasswordResetApi api;

    @BeforeEach
    void setup() {
        api = new AuthenticationPasswordResetApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAuthPasswordRequestPost(null);
        api.allauthAuthPasswordResetGet(null);
        api.allauthAuthPasswordResetPost(null);
    }
}
