package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAuditApi extends TestApi {
    private AuditApi api;

    @BeforeEach
    void setup() {
        api = new AuditApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.auditList(null, null, null, null, null, null, null, null, null, null, null, null);
        api.auditRetrieve(null);
    }

    @Test
    void test() throws ApiException {
        int limit = 5;
        int offset = 0;
        api.auditList(limit, null, null, null, null, null, null, null, null, offset, null, null);
    }
}
