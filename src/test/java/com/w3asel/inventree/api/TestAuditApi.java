package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static com.w3asel.inventree.InventreeDemoDataset.assertNullableFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.AuditLogEntry;
import com.w3asel.inventree.model.PaginatedAuditLogEntryList;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestAuditApi extends TestApi {
    private AuditApi api;

    @BeforeEach
    void setup() {
        api = new AuditApi(apiClient);
    }

    private static String mergeContentType(JsonElement element) {
        if (element.isJsonNull()) {
            return null;
        }
        JsonArray array = element.getAsJsonArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            if (i > 0) {
                sb.append(".");
            }
            sb.append(array.get(i).getAsString());
        }
        return sb.toString();
    }

    private static void assertArrayEquals(String field, JsonArray expectedArray, List<Object> actualList) {
        assertEquals(expectedArray.size(), actualList.size(), field + ": Array size mismatch");
        for (int i = 0; i < expectedArray.size(); i++) {
            JsonElement expectedValue = expectedArray.get(i);
            Object actualValue = actualList.get(i);

            if (expectedValue.isJsonNull()) {
                assertNull(actualValue, field + ": Expected null value for index: " + i);
            } else if (expectedValue.isJsonPrimitive()) {
                if (actualValue instanceof Double) {
                    assertEquals(expectedValue.getAsDouble(), actualValue, field + ": Incorrect value for index: " + i);
                } else {
                    assertEquals(expectedValue.getAsString(), actualValue.toString(),
                            field + ": Incorrect value for index: " + i);
                }
            } else {
                fail(field + ": Unexpected JSON element type for index " + i + ": "
                        + expectedValue.getClass().getName());
            }
        }
    }

    private static void assertObjectEquals(String field, JsonElement expectedElement, Object actualObject,
            String... ignoreKeys) {
        if (expectedElement.isJsonNull()) {
            assertNull(actualObject, field + ": Expected null object");
            return;
        }

        JsonObject expected = expectedElement.getAsJsonObject();
        @SuppressWarnings("unchecked")
        Map<String, Object> actual = (Map<String, Object>) actualObject;

        // remove ignored keys and diff the rest
        List<String> ignoreKeysList = List.of(ignoreKeys);
        SortedSet<String> expectedKeys = new TreeSet<>(expected.keySet());
        expectedKeys.removeAll(ignoreKeysList);
        SortedSet<String> actualKeys = new TreeSet<>(actual.keySet());
        actualKeys.removeAll(ignoreKeysList);
        assertIterableEquals(expectedKeys, actualKeys, field + ": Object keys mismatch");

        // check actual values
        for (String key : expectedKeys) {
            JsonElement expectedValue = expected.get(key);
            Object actualValue = actual.get(key);

            if (expectedValue.isJsonNull()) {
                assertNull(actualValue, field + ": Expected null value for key: " + key);
            } else if (expectedValue.isJsonPrimitive()) {
                if (actualValue instanceof Double) {
                    assertEquals(expectedValue.getAsDouble(), actualValue, field + ": Incorrect value for key: " + key);
                } else {
                    assertEquals(expectedValue.getAsString(), actualValue.toString(),
                            field + ": Incorrect value for key: " + key);
                }
            } else if (expectedValue.isJsonObject()) {
                assertTrue(actualValue instanceof Map, field + ": Expected Map for key: " + key);
                @SuppressWarnings("unchecked")
                Map<String, Object> actualMap = (Map<String, Object>) actualValue;
                assertObjectEquals(field + ": " + key, expectedValue, actualMap);
            } else if (expectedValue.isJsonArray()) {
                assertTrue(actualValue instanceof List, field + ": Expected List for key: " + key);
                @SuppressWarnings("unchecked")
                List<Object> actualList = (List<Object>) actualValue;
                assertArrayEquals(field + ": " + key, expectedValue.getAsJsonArray(), actualList);
            } else {
                fail(field + ": Unexpected JSON element type for key " + key + ": "
                        + expectedValue.getClass().getName());
            }
        }
    }

    private static void assertAuditLogEntryEquals(JsonObject expected, AuditLogEntry actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("action", fields, actual.getAction());
        assertFieldEquals("actor_email", fields, actual.getActorDetail().getEmail());

        String expectedActorUsername =
                fields.get("actor").isJsonNull() ? null : fields.get("actor").getAsJsonArray().get(0).getAsString();
        assertEquals(expectedActorUsername, actual.getActorUsername(), "Incorrect actor username");

        JsonElement expectedAdditionalDataValue = fields.get("additional_data");
        if (!expectedAdditionalDataValue.isJsonNull()) {
            JsonObject expectedAdditionalData = expectedAdditionalDataValue.getAsJsonObject();
            @SuppressWarnings("unchecked")
            Map<String, Object> actualAdditionalData = (Map<String, Object>) actual.getAdditionalData();

            assertObjectEquals("additional_data", expectedAdditionalData, actualAdditionalData, "deltas");

            if (expectedAdditionalData.has("deltas")) {
                assertObjectEquals("deltas", expectedAdditionalData.get("deltas"), actual.getDeltas());

                // TODO: check for key mismatches
                JsonObject expectedDeltas = expectedAdditionalData.get("deltas").getAsJsonObject();
                @SuppressWarnings("unchecked")
                Map<String, Object> actualDeltas = (Map<String, Object>) actual.getDeltas();
                for (String entryKey : expectedDeltas.keySet()) {
                    JsonObject expectedEntryDeltas = expectedDeltas.get(entryKey).getAsJsonObject();
                    @SuppressWarnings("unchecked")
                    Map<String, Object> actualEntryDeltas = (Map<String, Object>) actualDeltas.get(entryKey);
                    for (String key : expectedEntryDeltas.keySet()) {
                        if (actualEntryDeltas.get(key) instanceof Double) {
                            assertEquals(expectedEntryDeltas.get(key).getAsDouble(), actualEntryDeltas.get(key),
                                    "Deltas mismatch on key: " + key);
                        } else {
                            assertEquals(expectedEntryDeltas.get(key).getAsString(), actualEntryDeltas.get(key),
                                    "Deltas mismatch on key: " + key);
                        }
                    }
                }
            } else {
                assertNull(actual.getDeltas(), "Expected null deltas");
            }

            // TODO: other types of additional data?

        } else {
            assertNull(actual.getAdditionalData(), "Expected null additional data");
            assertNull(actual.getDeltas(), "Expected null deltas");
        }

        assertObjectEquals("changes", fields.get("changes"), actual.getChanges());

        // TODO implement further when testing a value that populates either of these
        assertEquals(0, fields.get("changes_text").getAsString().length(), "Expected empty changes_text");
        assertEquals(0, actual.getChangesDetail().size(), "Expected empty changes_detail");

        assertNullableFieldEquals(String.class, "cid", fields, actual.getCid());

        assertNullableFieldEquals(Integer.class, "event_type", fields, actual.getEventType());

        String expectedContentType = mergeContentType(fields.get("content_type"));
        assertEquals(expectedContentType, actual.getModelType(), "Incorrect model type");

        assertNullableFieldEquals(String.class, "notes", fields, actual.getNotes());
        assertFieldEquals("object_pk", fields, actual.getObjectPk());
        assertFieldEquals("object_repr", fields, actual.getObjectRepr());

        String expectedParentContentType = mergeContentType(fields.get("parent_content_type"));
        if (expectedParentContentType == null) {
            assertNull(actual.getParentModelType(), "Expected null parent model type");
        } else {
            assertEquals(expectedParentContentType, actual.getParentModelType(), "Incorrect parent model type");
        }

        assertNullableFieldEquals(String.class, "parent_object_pk", fields, actual.getParentObjectPk());
        assertNullableFieldEquals(String.class, "remote_addr", fields, actual.getRemoteAddr());
        assertNullableFieldEquals(Integer.class, "remote_port", fields, actual.getRemotePort());
        assertFieldEquals("timestamp", fields, actual.getTimestamp());

        // not directly available in demo dataset:
        // actual.getActionLabel();
        // actual.getActor();
        // actual.getEventLabel();
        // actual.getModelLabel();
    }

    @Test
    void auditList_stockTracking() throws ApiException {
        String[] model = {"stock", "stockitem"};

        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.AUDIT_LOGENTRY, null);
        // cull non stock.stockitem entries
        expectedList.removeIf(l -> !InventreeDemoDataset.getFields(l).get("content_type").getAsJsonArray().get(1)
                .getAsString().equals(model[1]));
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;
        // exclude entries from today, probably from previous test cases
        LocalDate maxDate = LocalDate.now().minusDays(1);
        String modelType = model[0] + "." + model[1];

        PaginatedAuditLogEntryList actual =
                api.auditList(limit, null, null, null, null, null, maxDate, null, null, modelType, offset, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total audit log: stock tracking count");
        List<AuditLogEntry> actualList = actual.getResults();

        // deep equals on first value
        AuditLogEntry actualFirst;
        JsonObject expectedFirst = null;
        int firstFoundIndex = 0;
        do {
            actualFirst = actualList.get(firstFoundIndex);

            List<JsonObject> expectedSearch =
                    InventreeDemoDataset.getObjects(Model.AUDIT_LOGENTRY, actualFirst.getPk());
            if (!expectedSearch.isEmpty()) {
                expectedFirst = expectedSearch.get(0);
                break;
            }
            firstFoundIndex++;
        } while (firstFoundIndex < actualList.size());

        assertTrue(firstFoundIndex < actualList.size(),
                "Failed to find any expected audit log: stock tracking");
        assertAuditLogEntryEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"2611", "3916", "3915"})
    void auditRetrieve_stockTracking(int auditEntry) throws ApiException {
        AuditLogEntry actual = api.auditRetrieve(auditEntry);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.AUDIT_LOGENTRY, auditEntry).get(0);
        assertAuditLogEntryEquals(expected, actual);

        // verify data not directly in demo dataset
        String expectedActionLabel;
        String expectedEventLabel;
        String expectedModelLabel = "Stock Item";
        switch (auditEntry) {
            case 2611:
                expectedActionLabel = "update";
                expectedEventLabel = "Edited stock item";
                break;
            case 3916:
                expectedActionLabel = "create";
                expectedEventLabel = "Stock item created";
                break;
            case 3915:
                expectedActionLabel = "update";
                expectedEventLabel = "Shipped against Sales Order";
                break;
            default:
                expectedActionLabel = null;
                expectedEventLabel = null;
        }
        assertEquals(expectedActionLabel, actual.getActionLabel(), "Incorrect action label");
        assertEquals(expectedEventLabel, actual.getEventLabel(), "Incorrect label");
        assertEquals(expectedModelLabel, actual.getModelLabel(), "Incorrect model label");
    }
}
