package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestConfigurationApi extends TestApi {
    private ConfigurationApi api;

    @BeforeEach
    public void setup() {
        api = new ConfigurationApi(apiClient);
    }

    @Disabled("field authentication_method not defined")
    @Test
    public void allauthConfigGet() throws ApiException {
        api.allauthConfigGet();
    }
}
