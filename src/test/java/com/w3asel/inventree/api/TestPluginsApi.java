package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedPluginConfigList;
import com.w3asel.inventree.model.PaginatedPluginSettingList;
import com.w3asel.inventree.model.PluginConfig;
import com.w3asel.inventree.model.PluginRegistryStatus;
import com.w3asel.inventree.model.PluginSetting;
import com.w3asel.inventree.model.PluginUIFeature;
import com.w3asel.inventree.model.PluginUserSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class TestPluginsApi extends TestApi {
    private static final String BARCODE_KEY = "inventreebarcode";
    private static final String LABEL_KEY = "inventreelabel";
    private static final String EMAIL_KEY = "inventree-email-notification";

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
        // api.pluginsList(null, null, null, null, null, null, null, null, null, null);
        api.pluginsReloadCreate(null);
        // api.pluginsRetrieve(null);
        // api.pluginsSettingsList(null);
        // api.pluginsSettingsListAll(null, null, null, null);
        api.pluginsSettingsPartialUpdate(null, null, null);
        // api.pluginsSettingsRetrieve(null, null);
        api.pluginsSettingsUpdate(null, null, null);
        // api.pluginsStatusRetrieve();
        api.pluginsUiFeaturesList(null);
        api.pluginsUninstallPartialUpdate(null, null);
        api.pluginsUninstallUpdate(null, null);
        // api.pluginsUserSettingsList(null);
        api.pluginsUserSettingsPartialUpdate(null, null, null);
        // api.pluginsUserSettingsRetrieve(null, null);
        api.pluginsUserSettingsUpdate(null, null, null);
    }

    @Test
    void pluginsList() throws ApiException {
        List<String> expectedKeys = new ArrayList<>(List.of(BARCODE_KEY, LABEL_KEY, EMAIL_KEY));

        int limit = 100; // fetch all to ensure statically listed ones are covered
        int offset = 0;
        PaginatedPluginConfigList actual =
                api.pluginsList(limit, null, null, null, null, null, offset, null, null, null);
        assertTrue(actual.getCount() > 0, "Expected > 0 plugins");

        List<PluginConfig> actualResults = actual.getResults();
        for (PluginConfig actualPlugin : actualResults) {
            if (expectedKeys.remove(actualPlugin.getKey())) {
                assertTrue(actualPlugin.getActive(),
                        "Expect " + actualPlugin.getKey() + " to be active");

            }
        }
        assertEquals(0, expectedKeys.size(), "Failed to find plugin(s): " + expectedKeys);
    }

    private static void assertPluginConfigEquals(PluginConfig expected, PluginConfig actual) {
        String messagePrefix = expected.getKey() + ": ";
        assertEquals(expected.getPk(), actual.getPk(), messagePrefix + "Incorrect PK");
        assertEquals(expected.getKey(), actual.getKey(), messagePrefix + "Incorrect key");
        assertEquals(expected.getName(), actual.getName(), messagePrefix + "Incorrect name");
        assertEquals(expected.getPackageName(), actual.getPackageName(),
                messagePrefix + "Incorrect package name");
        assertEquals(expected.getActive(), actual.getActive(), messagePrefix + "Incorrect active");
        // skip meta: complex, subject to version change
        // skip mixins: complex
        assertEquals(expected.getIsBuiltin(), actual.getIsBuiltin(),
                messagePrefix + "Incorrect is builtin");
        assertEquals(expected.getIsSample(), actual.getIsSample(),
                messagePrefix + "Incorrect is sample");
        assertEquals(expected.getIsInstalled(), actual.getIsInstalled(),
                messagePrefix + "Incorrect is installed");
        assertEquals(expected.getIsPackage(), actual.getIsPackage(),
                messagePrefix + "Incorrect is package");
        assertEquals(expected.getIsMandatory(), actual.getIsMandatory(),
                messagePrefix + "Incorrect is mandatory");
    }

    private static Stream<Arguments> pluginsRetrieve() {
        List<PluginConfig> plugins = new ArrayList<>();
        plugins.add(new PluginConfig(1, BARCODE_KEY, null, null, true, false, true, false, true)
                .name("InvenTreeBarcode"));
        plugins.get(plugins.size() - 1).setActive(true);

        return plugins.stream().map(p -> Arguments.argumentSet(p.getKey(), p));
    }

    @ParameterizedTest
    @MethodSource
    void pluginsRetrieve(PluginConfig expected) throws ApiException {
        PluginConfig actual = api.pluginsRetrieve(expected.getKey());

        assertPluginConfigEquals(expected, actual);
    }

    private static void assertPluginSettingEquals(PluginSetting expected, PluginSetting actual) {
        String messagePrefix = expected.getPlugin() + "/" + expected.getKey() + ": ";
        // skip PK, doesn't seem to be a static value
        assertEquals(expected.getPlugin(), actual.getPlugin(), messagePrefix + "Incorrect plugin");
        assertEquals(expected.getKey(), actual.getKey(), messagePrefix + "Incorrect key");

        assertEquals(expected.getName(), actual.getName(), messagePrefix + "Incorrect name");
        assertEquals(expected.getDescription(), actual.getDescription(),
                messagePrefix + "Incorrect description");
        assertEquals(expected.getType(), actual.getType(), messagePrefix + "Incorrect type");
        assertEquals(expected.getChoices(), actual.getChoices(),
                messagePrefix + "Incorrect choices");
        assertEquals(expected.getModelName(), actual.getModelName(),
                messagePrefix + "Incorrect model name");
        assertEquals(expected.getModelFilters(), actual.getModelFilters(),
                messagePrefix + "Incorrect model filters");
        assertEquals(expected.getApiUrl(), actual.getApiUrl(), messagePrefix + "Incorrect api url");
        assertEquals(expected.getTyp(), actual.getTyp(), messagePrefix + "Incorrect typ");
        assertEquals(expected.getUnits(), actual.getUnits(), messagePrefix + "Incorrect units");
        assertEquals(expected.getRequired(), actual.getRequired(),
                messagePrefix + "Incorrect required");

        TestSettingsApi.assertSettingValueEquals(expected.getType(), expected.getValue(),
                actual.getValue(), messagePrefix);
    }

    private static Map<String, Map<String, PluginSetting>> getDefaultSettings() {

        String typ = "plugin";

        Map<String, Map<String, PluginSetting>> settings =
                Map.of(BARCODE_KEY, new HashMap<>(), LABEL_KEY, new HashMap<>());

        settings.get(BARCODE_KEY).put("INTERNAL_BARCODE_FORMAT", new PluginSetting(1,
                "INTERNAL_BARCODE_FORMAT", "Internal Barcode Format",
                "Select an internal barcode format", "string",
                List.of(Map.of("value", "json", "display_name", "JSON barcodes (human readable)"),
                        Map.of("value", "short", "display_name",
                                "Short barcodes (space optimized)")),
                null, null, null, typ, "", false, null, null, BARCODE_KEY).value("json"));
        settings.get(BARCODE_KEY).put("SHORT_BARCODE_PREFIX", new PluginSetting(2,
                "SHORT_BARCODE_PREFIX", "Short Barcode Prefix",
                "Customize the prefix used for short barcodes, may be useful for environments with multiple InvenTree instances",
                "string", Collections.emptyList(), null, null, null, typ, "", false, null, null,
                BARCODE_KEY).value("INV-"));

        settings.get(LABEL_KEY).put("DEBUG",
                new PluginSetting(3, "DEBUG", "Debug mode",
                        "Enable debug mode - returns raw HTML instead of PDF", "boolean",
                        Collections.emptyList(), null, null, null, typ, "", false, null, null,
                        LABEL_KEY).value("False"));

        return settings;
    }

    private static Stream<Arguments> pluginsSettingsList() {
        Map<String, Map<String, PluginSetting>> settings = getDefaultSettings();

        return settings.entrySet().stream()
                .map(e -> Arguments.argumentSet(e.getKey(), e.getKey(), e.getValue()));
    }

    @ParameterizedTest
    @MethodSource
    void pluginsSettingsList(String plugin, Map<String, PluginSetting> expected)
            throws ApiException {
        List<PluginSetting> actual = api.pluginsSettingsList(plugin);

        for (PluginSetting actualSetting : actual) {
            assertPluginSettingEquals(expected.get(actualSetting.getKey()), actualSetting);
        }
    }

    @Disabled("Returns 0 on workflow test")
    @Test
    void pluginsSettingsListAll() throws ApiException {
        Map<String, Map<String, PluginSetting>> expected = getDefaultSettings();

        int limit = 10;
        int offset = 0;
        PaginatedPluginSettingList actual = api.pluginsSettingsListAll(limit, offset, null, null);
        assertTrue(actual.getCount() > 0, "Expected > 0 plugin settings");

        for (PluginSetting actualSetting : actual.getResults()) {
            PluginSetting expectedSetting =
                    expected.getOrDefault(actualSetting.getPlugin(), Collections.emptyMap())
                            .get(actualSetting.getKey());
            if (expectedSetting == null) {
                continue;
            }

            assertPluginSettingEquals(expectedSetting, actualSetting);
        }
    }

    private static Stream<Arguments> pluginsSettingsRetrieve() {
        Map<String, Map<String, PluginSetting>> settings = getDefaultSettings();

        Map<String, PluginSetting> arguments = new HashMap<>();
        for (String plugin : settings.keySet()) {
            for (Entry<String, PluginSetting> settingEntry : settings.get(plugin).entrySet()) {
                arguments.put(plugin + ":" + settingEntry.getKey(), settingEntry.getValue());
            }
        }

        return arguments.entrySet().stream()
                .map(e -> Arguments.argumentSet(e.getKey(), e.getValue()));
    }

    @ParameterizedTest
    @MethodSource
    void pluginsSettingsRetrieve(PluginSetting expected) throws ApiException {
        PluginSetting actual = api.pluginsSettingsRetrieve(expected.getKey(), expected.getPlugin());

        assertPluginSettingEquals(expected, actual);
    }

    @Test
    void pluginsStatusRetrieve() throws ApiException {
        PluginRegistryStatus actual = api.pluginsStatusRetrieve();
        assertTrue(actual.getActivePlugins() >= 3, "Unexpected at least 3 active plugins");
        assertEquals(0, actual.getRegistryErrors().size(), "Expected no errors");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"dashboard", "panel", "template_editor", "template_preview", "navigation"})
    void pluginsUiFeaturesList(String feature) throws ApiException {
        List<PluginUIFeature> actual = api.pluginsUiFeaturesList(feature);
        // no results found, but the parameter list contains the valid feature types
        assertEquals(0, actual.size());
    }

    private static void assertPluginUserSettingEquals(PluginUserSetting expected,
            PluginUserSetting actual) {
        String messagePrefix = expected.getPlugin() + "/" + expected.getKey() + ": ";
        // skip PK, doesn't seem to be a static value
        assertEquals(expected.getPlugin(), actual.getPlugin(), messagePrefix + "Incorrect plugin");
        assertEquals(expected.getKey(), actual.getKey(), messagePrefix + "Incorrect key");
        assertEquals(expected.getUser(), actual.getUser(), messagePrefix + "Incorrect user");

        assertEquals(expected.getName(), actual.getName(), messagePrefix + "Incorrect name");
        assertEquals(expected.getDescription(), actual.getDescription(),
                messagePrefix + "Incorrect description");
        assertEquals(expected.getType(), actual.getType(), messagePrefix + "Incorrect type");
        assertEquals(expected.getChoices(), actual.getChoices(),
                messagePrefix + "Incorrect choices");
        assertEquals(expected.getModelName(), actual.getModelName(),
                messagePrefix + "Incorrect model name");
        assertEquals(expected.getModelFilters(), actual.getModelFilters(),
                messagePrefix + "Incorrect model filters");
        assertEquals(expected.getApiUrl(), actual.getApiUrl(), messagePrefix + "Incorrect api url");
        assertEquals(expected.getTyp(), actual.getTyp(), messagePrefix + "Incorrect typ");
        assertEquals(expected.getUnits(), actual.getUnits(), messagePrefix + "Incorrect units");
        assertEquals(expected.getRequired(), actual.getRequired(),
                messagePrefix + "Incorrect required");

        TestSettingsApi.assertSettingValueEquals(expected.getType(), expected.getValue(),
                actual.getValue(), messagePrefix);
    }

    private static Map<String, Map<String, PluginUserSetting>> getDefaultUserSettings() {
        int ADMIN_USER = 1;

        String typ = "plugin_user";

        Map<String, Map<String, PluginUserSetting>> settings = Map.of(EMAIL_KEY, new HashMap<>());

        settings.get(EMAIL_KEY).put("NOTIFY_BY_EMAIL",
                new PluginUserSetting(1, "NOTIFY_BY_EMAIL", "Allow email notifications",
                        "Allow email notifications to be sent to this user", "boolean",
                        Collections.emptyList(), null, null, null, typ, "", false, null, null,
                        EMAIL_KEY, ADMIN_USER).value("True"));

        return settings;
    }

    private static Stream<Arguments> pluginsUserSettingsList() {
        Map<String, Map<String, PluginUserSetting>> settings = getDefaultUserSettings();

        return settings.entrySet().stream()
                .map(e -> Arguments.argumentSet(e.getKey(), e.getKey(), e.getValue()));
    }

    @ParameterizedTest
    @MethodSource
    void pluginsUserSettingsList(String plugin, Map<String, PluginUserSetting> expected)
            throws ApiException {
        List<PluginUserSetting> actual = api.pluginsUserSettingsList(plugin);

        for (PluginUserSetting actualSetting : actual) {
            assertPluginUserSettingEquals(expected.get(actualSetting.getKey()), actualSetting);
        }
    }

    private static Stream<Arguments> pluginsUserSettingsRetrieve() {
        Map<String, Map<String, PluginUserSetting>> settings = getDefaultUserSettings();

        Map<String, PluginUserSetting> arguments = new HashMap<>();
        for (String plugin : settings.keySet()) {
            for (Entry<String, PluginUserSetting> settingEntry : settings.get(plugin).entrySet()) {
                arguments.put(plugin + ":" + settingEntry.getKey(), settingEntry.getValue());
            }
        }

        return arguments.entrySet().stream()
                .map(e -> Arguments.argumentSet(e.getKey(), e.getValue()));
    }

    @ParameterizedTest
    @MethodSource
    void pluginsUserSettingsRetrieve(PluginUserSetting expected) throws ApiException {
        PluginUserSetting actual =
                api.pluginsUserSettingsRetrieve(expected.getKey(), expected.getPlugin());

        assertPluginUserSettingEquals(expected, actual);
    }
}
