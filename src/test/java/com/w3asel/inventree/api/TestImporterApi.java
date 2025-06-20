package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestImporterApi extends TestApi {
    private ImporterApi api;

    @BeforeEach
    void setup() {
        api = new ImporterApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.importerColumnMappingList(null, null, null, null, null);
        api.importerColumnMappingPartialUpdate(null, null);
        api.importerColumnMappingRetrieve(null);
        api.importerColumnMappingUpdate(null, null);
        api.importerModelsList();
        api.importerRowBulkDestroy(null);
        api.importerRowDestroy(null);
        api.importerRowList(null, null, null, null, null, null, null);
        api.importerRowPartialUpdate(null, null);
        api.importerRowRetrieve(null);
        api.importerRowUpdate(null, null);
        api.importerSessionAcceptFieldsCreate(null);
        api.importerSessionAcceptRowsCreate(null, null);
        api.importerSessionBulkDestroy(null);
        api.importerSessionCreate(null);
        api.importerSessionDestroy(null);
        api.importerSessionList(null, null, null, null, null, null, null);
        api.importerSessionPartialUpdate(null, null);
        api.importerSessionRetrieve(null);
        api.importerSessionUpdate(null, null);
    }

    @Test
    void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.importerColumnMappingList(limit, null, null, null, null);
        api.importerModelsList();
        api.importerRowList(limit, null, null, null, null, null, null);
        api.importerSessionList(limit, null, null, null, null, null, null);
    }
}
