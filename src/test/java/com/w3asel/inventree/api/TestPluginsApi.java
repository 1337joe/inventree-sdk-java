package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedPluginConfigList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPluginsApi extends TestApi {
    private PluginsApi api;

    @BeforeEach
    public void setup() {
        api = new PluginsApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        PaginatedPluginConfigList actual =
                api.pluginsList(limit, null, null, null, null, null, null, null, null);
        api.pluginsSettingsListAll(limit, null, null, null);
        // api.pluginsSettingsList(null);
        // api.pluginsUiFeaturesList(null);
    }
}
