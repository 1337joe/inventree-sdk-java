package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.AllauthTokensRefreshGet200Response;
import com.w3asel.inventree.model.AllauthTokensRefreshGetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestTokensApi extends TestApi {
    private TokensApi api;

    @BeforeEach
    void setup() {
        api = new TokensApi(apiClient);
    }

    @Disabled("Need token to refresh")
    @Test
    void allauthoTkensRefreshGet() throws ApiException {
        String refreshToken = "unknown";
        AllauthTokensRefreshGetRequest request =
                new AllauthTokensRefreshGetRequest().refreshToken(refreshToken);
        AllauthTokensRefreshGet200Response actual = api.allauthTokensRefreshGet(request);
    }
}
