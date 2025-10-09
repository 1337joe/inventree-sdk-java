package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedFailedTaskList;
import com.w3asel.inventree.model.PaginatedPendingTaskList;
import com.w3asel.inventree.model.PaginatedScheduledTaskList;
import com.w3asel.inventree.model.PendingTask;
import com.w3asel.inventree.model.ScheduledTask;
import com.w3asel.inventree.model.TaskOverview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestBackgroundTaskApi extends TestApi {
    private BackgroundTaskApi api;

    @BeforeEach
    void setup() {
        api = new BackgroundTaskApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.backgroundTaskFailedBulkDestroy(null);
        // api.backgroundTaskFailedList(null, null, null, null);
        api.backgroundTaskPendingBulkDestroy(null);
        // api.backgroundTaskPendingList(null, null);
        // api.backgroundTaskRetrieve();
        // api.backgroundTaskScheduledList(null, null, null, null);
    }

    @Test
    void backgroundTaskFailedList() throws ApiException {
        int limit = 10;
        int offset = 0;

        PaginatedFailedTaskList actual = api.backgroundTaskFailedList(limit, offset, null, null);
        assertEquals(0, actual.getCount(), "Incorrect total background task failed count");
        // expect no failures on test instance - nothing more to check
    }

    @Test
    void backgroundTaskPendingList() throws ApiException {
        int limit = 10;
        int offset = 0;

        PaginatedPendingTaskList actual = api.backgroundTaskPendingList(limit, offset);
        // assertEquals(expectedList.size(), actual.getCount(),
        // "Incorrect total background task pending count");
        List<PendingTask> actualList = actual.getResults();

        // TODO verify results
    }

    @Test
    void backgroundTaskRetrieve() throws ApiException {
        TaskOverview actual = api.backgroundTaskRetrieve();
        assertNotNull(actual, "Expected task overview response");
        assertEquals(0, actual.getFailedTasks(), "Unexpected background task failed count");
    }

    @Test
    void backgroundTaskScheduledList() throws ApiException {
        int limit = 10;
        int offset = 0;

        PaginatedScheduledTaskList actual =
                api.backgroundTaskScheduledList(limit, offset, null, null);
        // assertEquals(expectedList.size(), actual.getCount(),
        // "Incorrect total background task scheduled count");
        List<ScheduledTask> actualList = actual.getResults();

        // TODO verify results
    }
}
