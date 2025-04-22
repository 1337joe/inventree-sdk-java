package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedNotificationMessageList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestNotificationsApi extends TestApi {
    private NotificationsApi api;

    @BeforeEach
    public void setup() {
        api = new NotificationsApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.notificationsBulkDestroy(null);
        api.notificationsDestroy(null);
        // api.notificationsList(null, null, null, null, null, null);
        api.notificationsPartialUpdate(null, null);
        api.notificationsReadallRetrieve();
        api.notificationsRetrieve(null);
        api.notificationsUpdate(null, null);
    }

    @Test
    public void notificationsList() throws ApiException {
        // TODO verify results
        int limit = 10;
        int offset = 0;

        PaginatedNotificationMessageList actual =
                api.notificationsList(limit, null, offset, null, null, null);
        // System.out.println(actual);
    }
}
