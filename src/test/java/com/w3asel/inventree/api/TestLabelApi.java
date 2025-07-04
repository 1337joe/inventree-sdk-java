package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.LabelTemplate;
import com.w3asel.inventree.model.PaginatedLabelTemplateList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class TestLabelApi extends TestApi {
    private LabelApi api;

    @BeforeEach
    void setup() {
        api = new LabelApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.labelPrintCreate(null);
        api.labelTemplateCreate(null);
        api.labelTemplateDestroy(null);
        // api.labelTemplateList(null, null, null, null, null, null);
        api.labelTemplateMetadataPartialUpdate(null, null);
        api.labelTemplateMetadataRetrieve(null);
        api.labelTemplateMetadataUpdate(null, null);
        api.labelTemplatePartialUpdate(null, null);
        // api.labelTemplateRetrieve(null);
        api.labelTemplateUpdate(null, null);
    }


    private static void assertLabelTemplateEquals(JsonObject expected, LabelTemplate actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("name", fields, actual.getName());
        assertFieldEquals("description", fields, actual.getDescription());
        assertFieldEquals("revision", fields, actual.getRevision());
        assertFieldEquals("attach_to_model", fields, actual.getAttachToModel());
        assertFieldEquals("filename_pattern", fields, actual.getFilenamePattern());
        assertFieldEquals("enabled", fields, actual.getEnabled());
        // TODO requires proper enum naming
        // assertEquals("model_type", fields, actual.getModelType());
        assertFieldEquals("filters", fields, actual.getFilters());

        String mediaPrefix = "/media/";
        String imageString = mediaPrefix + fields.get("template").getAsString();
        try {
            assertEquals(new URI(imageString), actual.getTemplate(), "Incorrect template");
        } catch (URISyntaxException e) {
            fail("Unable to create URI from " + imageString);
        }

        assertFieldEquals("width", fields, actual.getWidth());
        assertFieldEquals("height", fields, actual.getHeight());
    }

    @Test
    void labelTemplateList() throws ApiException {
        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.REPORT_LABEL_TEMPLATE, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedLabelTemplateList actual =
                api.labelTemplateList(limit, null, null, null, offset, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect label template count");
        List<LabelTemplate> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        LabelTemplate actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.REPORT_LABEL_TEMPLATE, actualFirst.getPk()).get(0);
        assertLabelTemplateEquals(expectedFirst, actualFirst);
    }


    @ParameterizedTest
    @CsvSource({"1", "6"})
    void stockTrackRetrieve(int template) throws ApiException {
        LabelTemplate actual = api.labelTemplateRetrieve(template);
        JsonObject expected =
                InventreeDemoDataset.getObjects(Model.REPORT_LABEL_TEMPLATE, template).get(0);
        assertLabelTemplateEquals(expected, actual);
    }
}
