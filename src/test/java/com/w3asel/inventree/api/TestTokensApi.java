package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.AllauthTokensRefreshPost200Response;
import com.w3asel.inventree.model.AllauthTokensRefreshPostRequest;
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
        AllauthTokensRefreshPostRequest request =
                new AllauthTokensRefreshPostRequest().refreshToken(refreshToken);
        AllauthTokensRefreshPost200Response actual = api.allauthTokensRefreshPost(request);
    }
}
