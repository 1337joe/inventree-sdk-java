package com.w3asel.inventree.api;

import com.w3asel.inventree.client.ApiException;
import com.w3asel.inventree.java.ReportAsset;
import org.junit.jupiter.api.Assertions;
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
        ReportAsset actual = api.reportAssetRetrieve(1);
        Assertions.assertNotNull(actual);
    }
}
