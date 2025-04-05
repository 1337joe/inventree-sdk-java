package com.w3asel.inventree;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
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
    public static <T> void assertEquals(String field, JsonObject expected, T actualValue) {
        Assertions.assertNotNull(actualValue,
                "actualValue is null and cannot be used for type determination, use assertNullableEquals on field "
                        + field);

        @SuppressWarnings("unchecked")
        Class<T> type = (Class<T>) actualValue.getClass();

        assertNullableEquals(type, field, expected, actualValue);
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
    public static void assertEquals(String field, JsonObject expected, Double actualValue,
            Double delta) {
        assertNullableEquals(Double.class, field, expected, actualValue);
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
    public static <T> void assertNullableEquals(Class<T> type, String field, JsonObject expected,
            T actualValue) {
        assertEquals(type, field, expected, actualValue, 0);
    }

    private static <T> void assertEquals(Class<T> type, String field, JsonObject expected,
            T actualValue, double delta) {
        JsonElement fieldValue = expected.get(field);
        String message = "Incorrect " + field;

        if (fieldValue.isJsonNull()) {
            Assertions.assertNull(actualValue, message);
        } else if (type == Boolean.class) {
            Assertions.assertEquals(fieldValue.getAsBoolean(), actualValue, message);
        } else if (type == Double.class) {
            Assertions.assertEquals(fieldValue.getAsDouble(), (Double) actualValue, delta, message);
        } else if (type == Integer.class) {
            Assertions.assertEquals(fieldValue.getAsInt(), actualValue, message);
        } else if (type == String.class) {
            Assertions.assertEquals(fieldValue.getAsString(), actualValue, message);
        } else if (type == URI.class) {
            String uriString = fieldValue.getAsString();
            try {
                Assertions.assertEquals(new URI(uriString), actualValue, message);
            } catch (URISyntaxException e) {
                Assertions.fail("Unable to create URI from " + uriString);
            }
        } else {
            Assertions.fail("Unsupported type: " + type.getName());
        }
    }

    private static DateTimeFormatter DATETIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    public static OffsetDateTime parseOffsetDateTime(String field) {
        return OffsetDateTime.from(DATETIME_FORMAT.parse(field));
    }

    public enum Model {
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
        STOCK_TRACKING("stock.stockitemtracking");

        private final String key;

        private Model(String key) {
            this.key = key;
        }
    }

    @Test
    public void getObjects_model() {
        List<JsonObject> actual = getObjects(Model.COMPANY_SUPPLIER_PRICE_BREAK, null);
        Assertions.assertTrue(actual.size() > 1);
    }

    @Test
    public void getObjects_model_pk() {
        int expected = 2;
        List<JsonObject> actual = getObjects(Model.COMPANY_SUPPLIER_PRICE_BREAK, expected);
        Assertions.assertTrue(actual.size() == 1);
        JsonObject object = actual.get(0);
        Assertions.assertEquals(expected, object.get(PRIMARY_KEY_KEY).getAsInt(),
                "Incorrect primary key");
    }
}
