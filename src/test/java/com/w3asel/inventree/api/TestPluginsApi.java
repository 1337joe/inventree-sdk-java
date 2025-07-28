package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PluginSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

public class TestPluginsApi extends TestApi {
    private PluginsApi api;

    @BeforeEach
    void setup() {
        api = new PluginsApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.pluginsActivatePartialUpdate(null, null);
        api.pluginsActivateUpdate(null, null);
        api.pluginsAdminRetrieve(null);
        api.pluginsDestroy(null);
        api.pluginsInstallCreate(null);
        api.pluginsList(null, null, null, null, null, null, null, null, null, null);
        api.pluginsMetadataPartialUpdate(null, null);
        api.pluginsMetadataRetrieve(null);
        api.pluginsMetadataUpdate(null, null);
        api.pluginsReloadCreate(null);
        api.pluginsRetrieve(null);
        api.pluginsSettingsList(null);
        api.pluginsSettingsListAll(null, null, null, null);
        api.pluginsSettingsPartialUpdate(null, null, null);
        api.pluginsSettingsRetrieve(null, null);
        api.pluginsSettingsUpdate(null, null, null);
        api.pluginsStatusRetrieve();
        api.pluginsUiFeaturesList(null);
        api.pluginsUninstallPartialUpdate(null, null);
        api.pluginsUninstallUpdate(null, null);
        api.pluginsUserSettingsList(null);
        api.pluginsUserSettingsPartialUpdate(null, null, null);
        api.pluginsUserSettingsRetrieve(null, null);
        api.pluginsUserSettingsUpdate(null, null, null);
    }

    @Test
    void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.pluginsList(limit, null, null, null, null, null, null, null, null, null);
        List<PluginSetting> pluginSettings =
                api.pluginsSettingsListAll(limit, null, null, null).getResults();
        Optional<String> pluginName = pluginSettings.stream().map(s -> s.getPlugin()).findFirst();
        if (pluginName.isPresent()) {
            api.pluginsSettingsList(pluginName.get());
            api.pluginsUserSettingsList(pluginName.get());
        }
        // api.pluginsUiFeaturesList(null);
    }
}
