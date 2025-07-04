package com.w3asel.inventree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** Simple class to read and parse the demo dataset. */
public class InventreeDemoDataset {
    private static final String INVENTREE_DATA = "inventree_data.json";

    public static final String MODEL_KEY = "model";
    public static final String PRIMARY_KEY_KEY = "pk";
    public static final String FIELDS_KEY = "fields";

    private static List<JsonElement> rootElements;
    static {
        try (InputStream is =
                InventreeDemoDataset.class.getClassLoader().getResourceAsStream(INVENTREE_DATA)) {
            JsonElement root = JsonParser.parseReader(new JsonReader(new InputStreamReader(is)));
            JsonArray rootArray = root.getAsJsonArray();
            rootElements = rootArray.asList();
        } catch (final IOException e) {
            System.err.printf("Unable to load %s", INVENTREE_DATA);
        }
    }

    public static List<JsonObject> getObjects(Model model, Integer pk) {
        List<JsonObject> foundObjects = new ArrayList<>();
        for (JsonElement element : rootElements) {
            JsonObject object = element.getAsJsonObject();
            if (!model.key.equals(object.get(MODEL_KEY).getAsString())) {
                continue;
            }
            if (pk == null || pk == object.get(PRIMARY_KEY_KEY).getAsInt()) {
                foundObjects.add(object);
            }
        }
        return foundObjects;
    }

    public static JsonObject getFields(JsonObject object) {
        return object.get(FIELDS_KEY).getAsJsonObject();
    }

    /**
     * Verifies the equality of the selected field in the expected object and the provided value.
     *
     * @param <T>         The type of the actual value.
     * @param field       The name of the field to check.
     * @param expected    The object containing the expected value.
     * @param actualValue The actual test result value.
     */
    public static <T> void assertFieldEquals(String field, JsonObject expected, T actualValue) {
        assertNotNull(actualValue,
                "actualValue is null and cannot be used for type determination, use assertNullableEquals on field "
                        + field);

        @SuppressWarnings("unchecked")
        Class<T> type = (Class<T>) actualValue.getClass();

        assertNullableFieldEquals(type, field, expected, actualValue);
    }


    /**
     * Verifies the equality of the selected field in the expected object and the provided value
     * within the given non-negative {@code delta}.
     *
     * @param field       The name of the field to check.
     * @param expected    The object containing the expected value.
     * @param actualValue The actual test result value.
     * @param delta       The allowable difference between values to count as equal.
     */
    public static void assertFieldEquals(String field, JsonObject expected, Double actualValue,
            Double delta) {
        assertNullableFieldEquals(Double.class, field, expected, actualValue);
    }

    /**
     * Verifies the equality of the selected field in the expected object and the provided value.
     * Includes verifying that both are null.
     *
     * @param <T>         The type of the actual value.
     * @param type        The class of the actual value, specified to allow {@code actualValue} to
     *                    be null.
     * @param field       The name of the field to check.
     * @param expected    The object containing the expected value.
     * @param actualValue The actual test result value.
     */
    public static <T> void assertNullableFieldEquals(Class<T> type, String field,
            JsonObject expected, T actualValue) {
        assertFieldEquals(type, field, expected, actualValue, 0);
    }

    private static <T> void assertFieldEquals(Class<T> type, String field, JsonObject expected,
            T actualValue, double delta) {
        JsonElement fieldValue = expected.get(field);
        String message = "Incorrect " + field;

        if (fieldValue.isJsonNull()) {
            assertNull(actualValue, message);
        } else if (type == Boolean.class) {
            assertEquals(fieldValue.getAsBoolean(), actualValue, message);
        } else if (type == Double.class) {
            assertEquals(fieldValue.getAsDouble(), (Double) actualValue, delta, message);
        } else if (type == BigDecimal.class) {
            assertEquals(fieldValue.getAsDouble(), ((BigDecimal) actualValue).doubleValue(), delta,
                    message);
        } else if (type == Integer.class) {
            assertEquals(fieldValue.getAsInt(), actualValue, message);
        } else if (type == String.class) {
            assertEquals(fieldValue.getAsString(), actualValue, message);
        } else if (type == URI.class) {
            String uriString = fieldValue.getAsString();
            try {
                assertEquals(new URI(uriString), actualValue, message);
            } catch (URISyntaxException e) {
                fail("Unable to create URI from " + uriString);
            }
        } else if (Enum.class.isAssignableFrom(type)) {
            assertEquals(fieldValue.getAsString(), actualValue.toString(), message);
        } else if (type == Iterable.class) {
            assertIterableEquals(fieldValue.getAsJsonArray().asList().stream()
                    .map(JsonElement::getAsString).toList(), (Iterable<?>) actualValue, message);
        } else if (type == LocalDate.class) {
            assertEquals(LocalDate.from(DATE_FORMAT.parse(fieldValue.getAsString())), actualValue,
                    message);

        } else {
            fail("Unsupported type: " + type.getName());
        }
    }

    private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter DATETIME_FORMAT_MILLIS =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    private static DateTimeFormatter DATETIME_FORMAT_SECONDS =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

    public static OffsetDateTime parseOffsetDateTime(String field) {
        try {
            return OffsetDateTime.from(DATETIME_FORMAT_MILLIS.parse(field));
        } catch (Exception e) {
        }
        return OffsetDateTime.from(DATETIME_FORMAT_SECONDS.parse(field));
    }

    public enum Model {
        BOM_ITEM("part.bomitem"),
        BOM_ITEM_SUBSTITUTE("part.bomitemsubstitute"),
        BUILD("build.build"),
        PART_CATEGORY("part.partcategory"),
        PART_INTERNAL_PRICE_BREAK("part.partinternalpricebreak"),
        GLOBAL_SETTING("common.inventreesetting"),
        NOTIFICATION_SETTING("plugin.notificationusersetting"),
        USER_SETTING("common.inventreeusersetting"),
        COMPANY("company.company"),
        COMPANY_ADDRESS("company.address"),
        COMPANY_CONTACT("company.contact"),
        COMPANY_MANUFACTURER_PART("company.manufacturerpart"),
        COMPANY_SUPPLIER_PRICE_BREAK("company.supplierpricebreak"),
        COMPANY_SUPPLIER_PART("company.supplierpart"),
        CUSTOM_UNIT("common.customunit"),
        CUSTOM_USER_STATE("common.inventreecustomuserstatemodel"),
        ORDER_SALES("order.salesorder"),
        REPORT_LABEL_TEMPLATE("report.labeltemplate"),
        STOCK_ITEM("stock.stockitem"),
        STOCK_TRACKING("stock.stockitemtracking"),
        USER("auth.user");

        private final String key;

        private Model(String key) {
            this.key = key;
        }
    }

    @Test
    void getObjects_model() {
        List<JsonObject> actual = getObjects(Model.COMPANY_SUPPLIER_PRICE_BREAK, null);
        assertTrue(actual.size() > 1);
    }

    @Test
    void getObjects_model_pk() {
        int expected = 2;
        List<JsonObject> actual = getObjects(Model.COMPANY_SUPPLIER_PRICE_BREAK, expected);
        assertTrue(actual.size() == 1);
        JsonObject object = actual.get(0);
        assertEquals(expected, object.get(PRIMARY_KEY_KEY).getAsInt(), "Incorrect primary key");
    }
}
