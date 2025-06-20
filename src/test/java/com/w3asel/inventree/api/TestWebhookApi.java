package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestWebhookApi extends TestApi {
    private WebhookApi api;

    @BeforeEach
    void setup() {
        api = new WebhookApi(apiClient);
    }

    @Disabled("API endpoint not found")
    @Test
    void webhookCreate() throws ApiException {
        String endpoint = "test";
        api.webhookCreate(endpoint);
    }
}
