package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Build;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestBuildApi extends TestApi {
    private BuildApi api;

    @BeforeEach
    void setup() {
        api = new BuildApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.buildAllocateCreate(null, null);
        api.buildAutoAllocateCreate(null, null);
        api.buildCancelCreate(null, null);
        api.buildCompleteCreate(null, null);
        api.buildCreate(null);
        api.buildCreateOutputCreate(null, null);
        api.buildDeleteOutputsCreate(null, null);
        api.buildDestroy(null);
        api.buildFinishCreate(null, null);
        api.buildHoldCreate(null);
        api.buildIssueCreate(null);
        api.buildItemBulkDestroy(null);
        api.buildItemCreate(null);
        api.buildItemDestroy(null);
        api.buildItemList(null, null, null, null, null, null, null, null, null, null, null);
        api.buildItemMetadataPartialUpdate(null, null);
        api.buildItemMetadataRetrieve(null);
        api.buildItemMetadataUpdate(null, null);
        api.buildItemPartialUpdate(null, null);
        api.buildItemRetrieve(null);
        api.buildItemUpdate(null, null);
        api.buildLineCreate(null);
        api.buildLineDestroy(null);
        api.buildLineList(null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null);
        api.buildLinePartialUpdate(null, null);
        api.buildLineRetrieve(null);
        api.buildLineUpdate(null, null);
        api.buildList(null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null);
        api.buildMetadataPartialUpdate(null, null);
        api.buildMetadataRetrieve(null);
        api.buildMetadataUpdate(null, null);
        api.buildPartialUpdate(null, null);
        api.buildRetrieve(null);
        api.buildScrapOutputsCreate(null, null);
        api.buildStatusRetrieve();
        api.buildUnallocateCreate(null, null);
        api.buildUpdate(null, null);
    }

    @Test
    void test() throws ApiException {
        // TODO verify results
        Build actual = api.buildRetrieve(1);
        assertNotNull(actual);

        int limit = 1000;
        api.buildList(limit, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null);

        api.buildItemList(limit, null, null, null, null, null, null, null, null, null, null);

        api.buildLineList(limit, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null);
    }
}
