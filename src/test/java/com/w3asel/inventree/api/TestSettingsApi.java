package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.GlobalSettings;
import com.w3asel.inventree.model.PaginatedGlobalSettingsList;
import com.w3asel.inventree.model.PaginatedUserSettingsList;
import com.w3asel.inventree.model.UserSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

public class TestSettingsApi extends TestApi {
    private SettingsApi api;

    @BeforeEach
    void setup() {
        api = new SettingsApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        // api.settingsGlobalList(null, null, null, null);
        api.settingsGlobalPartialUpdate(null, null);
        // api.settingsGlobalRetrieve(null);
        api.settingsGlobalUpdate(null, null);
        // api.settingsUserList(null, null, null, null);
        api.settingsUserPartialUpdate(null, null);
        api.settingsUserRetrieve(null);
        // api.settingsUserUpdate(null, null);
    }

    private JsonElement getExpectedValue(Model model, String name, String user) {
        List<JsonObject> loadedSettings = InventreeDemoDataset.getObjects(model, null);
        if (user != null) {
            loadedSettings.removeIf(jo -> !InventreeDemoDataset.getFields(jo).get("user")
                    .getAsString().contains(user));
        }
        JsonObject expectedSetting = loadedSettings.stream().filter(
                s -> InventreeDemoDataset.getFields(s).get("key").getAsString().equals(name))
                .findFirst().orElse(null);
        return expectedSetting == null ? null
                : InventreeDemoDataset.getFields(expectedSetting).get("value");
    }

    private void assertTypedEquals(String expectedType, JsonElement expectedValue,
            String actualType, String actualValue, String settingKey) {
        assertEquals(expectedType, actualType, settingKey + ": Incorrect type");

        switch (expectedType) {
            default:
            case "string":
                assertEquals(expectedValue.getAsString(), actualValue,
                        settingKey + ": Incorrect parsed value");
                break;
            case "boolean":
                assertEquals(expectedValue.getAsBoolean(), Boolean.parseBoolean(actualValue),
                        settingKey + ": Incorrect parsed value");
                break;
            case "integer":
                assertEquals(expectedValue.getAsInt(), Integer.parseInt(actualValue),
                        settingKey + ": Incorrect parsed value");
                break;
        }
    }

    /** Value equals check that allows case mismatch for booleans. */
    public static void assertSettingValueEquals(String type, String expectedValue,
            String actualValue, String messagePrefix) {
        if (type.equals("boolean") && expectedValue != null) {
            assertTrue(expectedValue.equalsIgnoreCase(actualValue),
                    messagePrefix + "Incorrect value ==> expected <" + expectedValue
                            + "> but was: <" + actualValue + ">");
        } else {
            assertEquals(expectedValue, actualValue, messagePrefix + "Incorrect value");
        }
    }

    @Test
    void settingsGlobalList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.GLOBAL_SETTING, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        String ordering = "pk";
        PaginatedGlobalSettingsList actual = api.settingsGlobalList(limit, offset, ordering, null);
        assertTrue(expectedList.size() <= actual.getCount(), "Incorrect global settings count");
        List<GlobalSettings> actualList = actual.getResults();

        // demo data is minimal, just verify that we find key/value pairs that match
        boolean foundNonNull = false;
        for (GlobalSettings actualSetting : actualList) {

            JsonElement expectedValue =
                    getExpectedValue(Model.GLOBAL_SETTING, actualSetting.getKey(), null);
            if (expectedValue != null) {
                foundNonNull = true;
                assertSettingValueEquals(actualSetting.getType(), expectedValue.getAsString(),
                        actualSetting.getValue(), actualSetting.getKey() + ": ");
            }
        }
        if (!foundNonNull) {
            List<String> nameList = actualList.stream().map(GlobalSettings::getKey).toList();
            fail("Found no demo-configured settings: " + nameList);
        }
    }

    @ParameterizedTest
    @CsvSource({"CURRENCY_CODES,string", "INVENTREE_DEFAULT_CURRENCY,string",
            "REPORT_LOG_ERRORS,boolean", "BARCODE_RESULTS_MAX_NUM,integer"})
    void settingsGlobalRetrieve(String targetSetting, String type) throws ApiException {
        JsonElement expectedValue = getExpectedValue(Model.GLOBAL_SETTING, targetSetting, null);

        GlobalSettings actual = api.settingsGlobalRetrieve(targetSetting);
        assertNotNull(actual);
        assertEquals(targetSetting, actual.getKey(), "Incorrect key");

        assertTypedEquals(type, expectedValue, actual.getType(), actual.getValue(), targetSetting);
    }

    @Test
    void settingsUserList() throws ApiException {
        String user = "admin";
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.USER_SETTING, null);
        // expect only settings for current user
        expectedList.removeIf(
                jo -> !InventreeDemoDataset.getFields(jo).get("user").getAsString().contains(user));
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;
        String ordering = "pk";
        PaginatedUserSettingsList actual = api.settingsUserList(limit, offset, ordering, null);
        assertTrue(expectedList.size() <= actual.getCount(), "Incorrect user settings count");
        List<UserSettings> actualList = actual.getResults();

        // demo data is minimal, just verify that we find key/value pairs that match
        boolean foundNonNull = false;
        for (UserSettings actualSetting : actualList) {
            JsonElement expectedValue =
                    getExpectedValue(Model.USER_SETTING, actualSetting.getKey(), user);
            if (expectedValue != null) {
                foundNonNull = true;
                assertSettingValueEquals(actualSetting.getType(), expectedValue.getAsString(),
                        actualSetting.getValue(), actualSetting.getKey() + ": ");
            }
        }
        if (!foundNonNull) {
            List<String> nameList = actualList.stream().map(UserSettings::getKey).toList();
            fail("Found no demo-configured user settings: " + nameList);
        }
    }

    @ParameterizedTest
    @CsvSource({"NOTIFICATION_ERROR_REPORT,boolean", "SEARCH_PREVIEW_RESULTS,integer"})
    void settingsUserRetrieve(String targetSetting, String type) throws ApiException {
        String user = "admin";
        JsonElement expectedValue = getExpectedValue(Model.USER_SETTING, targetSetting, user);

        UserSettings actual = api.settingsUserRetrieve(targetSetting);
        assertNotNull(actual);
        assertEquals(targetSetting, actual.getKey(), "Incorrect key");

        assertTypedEquals(type, expectedValue, actual.getType(), actual.getValue(), targetSetting);
    }
}
