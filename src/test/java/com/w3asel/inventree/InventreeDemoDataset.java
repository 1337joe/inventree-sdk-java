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
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static List<JsonObject> getObjects(Models model, Integer pk) {
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

    public static Map<String, JsonElement> getFields(JsonObject object) {
        return object.get(FIELDS_KEY).getAsJsonObject().asMap();
    }

    private static DateTimeFormatter DATETIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    public static OffsetDateTime parseOffsetDateTime(String field) {
        return OffsetDateTime.from(DATETIME_FORMAT.parse(field));
    }

    public enum Models {
        COMPANY("company.company"),
        COMPANY_ADDRESS("company.address"),
        COMPANY_CONTACT("company.contact"),
        COMPANY_MANUFACTURER_PART("company.manufacturerpart"),
        COMPANY_SUPPLIER_PRICE_BREAK("company.supplierpricebreak"),
        COMPANY_SUPPLIER_PART("company.supplierpart"),
        ORDER_SALES("order.salesorder");

        private final String key;

        private Models(String key) {
            this.key = key;
        }
    }

    @Test
    public void getObjects_model() {
        List<JsonObject> actual = getObjects(Models.COMPANY_SUPPLIER_PRICE_BREAK, null);
        Assertions.assertTrue(actual.size() > 1);
    }

    @Test
    public void getObjects_model_pk() {
        int expected = 2;
        List<JsonObject> actual = getObjects(Models.COMPANY_SUPPLIER_PRICE_BREAK, expected);
        Assertions.assertTrue(actual.size() == 1);
        JsonObject object = actual.get(0);
        Assertions.assertEquals(expected, object.get(PRIMARY_KEY_KEY).getAsInt(),
                "Incorrect primary key");
    }
}
