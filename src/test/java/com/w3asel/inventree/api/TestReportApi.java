package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestReportApi extends TestApi {
    private ReportApi api;

    @BeforeEach
    public void setup() {
        api = new ReportApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.reportAssetList(limit, null);
        api.reportSnippetList(limit, null);
        api.reportTemplateList(limit, null, null, null, null, null, null);

        // ReportAsset actual = api.reportAssetRetrieve(1);
        // Assertions.assertNotNull(actual);
    }
}
