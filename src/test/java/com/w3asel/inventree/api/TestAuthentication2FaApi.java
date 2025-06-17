package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAuthentication2FaApi extends TestApi {
    private Authentication2FaApi api;

    @BeforeEach
    public void setup() {
        api = new Authentication2FaApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.allauthAuth2faAuthenticatePost(null);
        api.allauthAuth2faReauthenticatePost();
        api.allauthAuth2faTrustPost(null);
    }
}
