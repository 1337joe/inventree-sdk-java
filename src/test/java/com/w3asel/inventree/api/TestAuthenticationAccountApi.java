package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAuthenticationAccountApi extends TestApi {
    private AuthenticationAccountApi api;

    @BeforeEach
    public void setup() {
        api = new AuthenticationAccountApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.allauthAuthEmailVerifyGet(null);
        api.allauthAuthEmailVerifyPost(null);
        api.allauthAuthEmailVerifyResendPost();
        api.allauthAuthLoginPost(null);
        api.allauthAuthPhoneVerifyPost(null);
        api.allauthAuthPhoneVerifyResendPost();
        api.allauthAuthReauthenticatePost(null);
        api.allauthAuthSignupPost(null);
    }
}
