package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestErrorReportApi extends TestApi {
    private ErrorReportApi api;

    @BeforeEach
    public void setup() {
        api = new ErrorReportApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.errorReportBulkDestroy(null);
        api.errorReportDestroy(null);
        // api.errorReportList(null, null, null, null);
        api.errorReportPartialUpdate(null, null);
        api.errorReportRetrieve(null);
        api.errorReportUpdate(null, null);
    }

    @Disabled("No data")
    @Test
    public void errorReportList() throws ApiException {
        int limit = 1000;
        api.errorReportList(limit, null, null, null);
    }
}
