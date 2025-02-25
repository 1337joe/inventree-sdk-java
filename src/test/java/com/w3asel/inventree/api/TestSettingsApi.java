package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.GlobalSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSettingsApi extends TestApi {
    private SettingsApi api;

    @BeforeEach
    public void setup() {
        api = new SettingsApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        GlobalSettings actual = api.settingsGlobalRetrieve("INVENTREE_DEFAULT_CURRENCY");
        Assertions.assertNotNull(actual);
    }
}
