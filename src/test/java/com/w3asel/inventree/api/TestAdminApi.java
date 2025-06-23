package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.EmailMessage;
import com.w3asel.inventree.model.PaginatedEmailMessageList;
import com.w3asel.inventree.model.TestEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import java.util.UUID;

public class TestAdminApi extends TestApi {
    private AdminApi api;

    @BeforeEach
    void setup() {
        api = new AdminApi(apiClient);
    }

    @Disabled("void response")
    @Test
    void adminConfigList() throws ApiException {
        api.adminConfigList();
    }

    @Disabled("void response")
    @ParameterizedTest
    @CsvSource({"key"})
    void adminConfigRetrieve(String key) throws ApiException {
        api.adminConfigRetrieve(key);
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
