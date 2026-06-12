package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static com.w3asel.inventree.InventreeDemoDataset.assertNullableFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedReportTemplateList;
import com.w3asel.inventree.model.ReportTemplate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestReportApi extends TestApi {
    private ReportApi api;

    @BeforeEach
    void setup() {
        api = new ReportApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.reportAssetCreate(null);
        api.reportAssetDestroy(null);
        api.reportAssetList(null, null);
        api.reportAssetPartialUpdate(null, null);
        api.reportAssetRetrieve(null);
        api.reportAssetUpdate(null, null);
        api.reportPrintCreate(null);
        api.reportSnippetCreate(null);
        api.reportSnippetDestroy(null);
        api.reportSnippetList(null, null);
        api.reportSnippetPartialUpdate(null, null);
        api.reportSnippetRetrieve(null);
        api.reportSnippetUpdate(null, null);
        api.reportTemplateCreate(null);
        api.reportTemplateDestroy(null);
        // api.reportTemplateList(null, null, null, null, null, null, null, null, null, null);
        api.reportTemplatePartialUpdate(null, null);
        // api.reportTemplateRetrieve(null);
        api.reportTemplateUpdate(null, null);
    }

    @Test
    void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.reportAssetList(limit, null);
        api.reportSnippetList(limit, null);

        // ReportAsset actual = api.reportAssetRetrieve(1);
        // assertNotNull(actual);
    }

    private void assertReportTemplateEquals(JsonObject expected, ReportTemplate actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertNullableFieldEquals(OffsetDateTime.class, "updated", fields, actual.getUpdated());
        assertFieldEquals("name", fields, actual.getName());
        assertFieldEquals("description", fields, actual.getDescription());
        assertFieldEquals("revision", fields, actual.getRevision());
        assertFieldEquals("attach_to_model", fields, actual.getAttachToModel());
        assertFieldEquals("filename_pattern", fields, actual.getFilenamePattern());
        assertFieldEquals("enabled", fields, actual.getEnabled());
        assertFieldEquals("model_type", fields, actual.getModelType());
        assertFieldEquals("filters", fields, actual.getFilters());
        assertFieldEquals("page_size", fields, actual.getPageSize());
        assertFieldEquals("landscape", fields, actual.getLandscape());
        assertFieldEquals("merge", fields, actual.getMerge());

        // template gets a prefix in the running system as compared to the demo data file
        String expectedTemplate = fields.get("template").getAsString();
        assertTrue(actual.getTemplate().toString().endsWith(expectedTemplate), "Incorrect template path");

        // not directly available in demo dataset:
        // actual.getUpdatedBy();
        // actual.getUpdatedByDetail();

        // not available through schema-mapped API:
        // metadata
    }

    @Test
    void reportTemplateList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.REPORT_TEMPLATE, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        PaginatedReportTemplateList actual =
                api.reportTemplateList(limit, null, null, null, null, null, null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total entry item count");
        List<ReportTemplate> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .collect(Collectors.toList());
        List<Integer> actualPks =
                actualList.stream().map(s -> s.getPk()).sorted().collect(Collectors.toList());
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        ReportTemplate actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.REPORT_TEMPLATE, actualFirst.getPk()).get(0);
        assertReportTemplateEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    void selectionRetrieve(int id) throws ApiException {
        ReportTemplate actual = api.reportTemplateRetrieve(id);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.REPORT_TEMPLATE, id).get(0);
        assertReportTemplateEquals(expected, actual);

        // verify data not directly in demo dataset
        Integer updatedBy;
        switch (id) {
            default:
                updatedBy = null;
        }

        assertEquals(updatedBy, actual.getUpdatedBy(), "Incorrect updated by");
    }
}
