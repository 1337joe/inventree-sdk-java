package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestMachineApi extends TestApi {
    private MachineApi api;

    @BeforeEach
    void setup() {
        api = new MachineApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.machineCreate(null);
        api.machineDestroy(null);
        api.machineDriversList();
        api.machineList(null, null, null, null, null, null, null);
        api.machinePartialUpdate(null, null);
        api.machineRestartCreate(null);
        api.machineRetrieve(null);
        api.machineSettingsList(null);
        api.machineSettingsPartialUpdate(null, null, null, null);
        api.machineSettingsRetrieve(null, null, null);
        api.machineSettingsUpdate(null, null, null, null);
        api.machineStatusRetrieve();
        api.machineTypesList();
        api.machineUpdate(null, null);
    }

    @Test
    void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.machineDriversList();
        api.machineList(limit, null, null, null, null, null, null);
        api.machineTypesList();
        // api.machineSettingsList(null)
        api.machineStatusRetrieve();
    }
}
