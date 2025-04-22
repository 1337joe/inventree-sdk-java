package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestBackgroundTaskApi extends TestApi {
    private BackgroundTaskApi api;

    @BeforeEach
    public void setup() {
        api = new BackgroundTaskApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.backgroundTaskFailedBulkDestroy(null);
        // api.backgroundTaskFailedList(null, null, null, null);
        api.backgroundTaskPendingBulkDestroy(null);
        api.backgroundTaskPendingList(null, null);
        api.backgroundTaskRetrieve();
        api.backgroundTaskScheduledList(null, null, null, null);
    }

    @Disabled("No data")
    @Test
    public void backgroundTaskFailedList() throws ApiException {
        int limit = 1000;
        api.backgroundTaskFailedList(limit, null, null, null);
    }

    @Test
    public void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.backgroundTaskPendingList(limit, null).getCount();
        api.backgroundTaskScheduledList(limit, null, null, null).getCount();
    }
}
