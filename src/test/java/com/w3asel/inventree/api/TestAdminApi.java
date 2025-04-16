package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestAdminApi extends TestApi {
    private AdminApi api;

    @BeforeEach
    public void setup() {
        api = new AdminApi(apiClient);
    }

    @Disabled("void response")
    @Test
    public void adminConfigList() throws ApiException {
        api.adminConfigList(null, null);
    }

    @Disabled("void response")
    @ParameterizedTest
    @CsvSource({"key"})
    public void adminConfigRetrieve(String key) throws ApiException {
        api.adminConfigRetrieve(key);
    }
}
