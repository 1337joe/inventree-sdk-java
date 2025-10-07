package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Config;
import com.w3asel.inventree.model.EmailMessage;
import com.w3asel.inventree.model.PaginatedEmailMessageList;
import com.w3asel.inventree.model.TestEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class TestAdminApi extends TestApi {
    private AdminApi api;

    @BeforeEach
    void setup() {
        api = new AdminApi(apiClient);
        // TODO why is this not the same format as elsewhere?
        apiClient.setOffsetDateTimeFormat(DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnn").withZone(ZoneOffset.UTC));
    }

    @Test
    void adminConfigList() throws ApiException {
        List<Config> actual = api.adminConfigList();
        assertFalse(actual.isEmpty(), "Expected list of configs");
    }

    @ParameterizedTest
    @CsvSource({"INVENTREE_LOG_LEVEL", "INVENTREE_FLAGS"})
    void adminConfigRetrieve(String key) throws ApiException {
        Config actual = api.adminConfigRetrieve(key);
        assertNotNull(actual, "Missing config response");
        assertEquals(key, actual.getKey(), "Incorrect key");
    }

    @Disabled("No way to create email to destroy")
    @Test
    void adminEmailBulkDestroy() throws ApiException {
        api.adminEmailBulkDestroy(null);
    }

    @Disabled("No way to create email to destroy")
    @Test
    void adminEmailDestroy() throws ApiException {
        api.adminEmailDestroy(null);
    }

    @Disabled("No data")
    @Test
    void adminEmailList() throws ApiException {
        // List<JsonObject> expectedList =
        // InventreeDemoDataset.getObjects(Model.TODO, null);
        // assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedEmailMessageList actual = api.adminEmailList(limit, offset, null, null);
        // assertEquals(expectedList.size(), actual.getCount(), "Incorrect email list count");
        List<EmailMessage> actualList = actual.getResults();

        // check items returned by key
        // List<Integer> expectedPks = expectedList.stream()
        // .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
        // .toList();
        // List<UUID> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        // assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        EmailMessage actualFirst = actualList.get(0);
        // JsonObject expectedFirst = InventreeDemoDataset
        // .getObjects(Model.TODO, actualFirst.getPk()).get(0);
        // assertManufacturerPartEquals(expectedFirst, actualFirst, false);
    }

    @Disabled("No demo data")
    @ParameterizedTest
    @CsvSource({"uuid"})
    void adminEmailRetrieve(UUID globalId) throws ApiException {
        api.adminEmailRetrieve(globalId);
    }

    @Test
    void adminEmailTestCreate() throws ApiException {
        TestEmail input = new TestEmail().email("test@test.test");

        ApiException thrown = assertThrows(ApiException.class,
                () -> api.adminEmailTestCreate(input), "Unconfigured email should fail");
        assertEquals(400, thrown.getCode(), "Expected HTTP 400 Bad Request");
        assertTrue(thrown.getMessage().contains("Email server not configured"),
                "Should contain Email server not configured: " + thrown.getMessage());
    }
}
