package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.HealthCheckStatus;
import com.w3asel.inventree.model.HealthCheckStatusStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSystemApi extends TestApi {
    private SystemApi api;

    @BeforeEach
    void setup() {
        api = new SystemApi(apiClient);
    }

    @Test
    void systemHealthRetrieve() throws ApiException {
        HealthCheckStatus actual = api.systemHealthRetrieve();
        assertEquals(HealthCheckStatusStatusEnum.OK, actual.getStatus(),
                "Expected OK status when up for testing.");
    }
}
