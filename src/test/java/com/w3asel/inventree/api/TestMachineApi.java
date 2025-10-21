package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.MachineType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;

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
        // api.machineTypesList();
        api.machineUpdate(null, null);
    }

    @Test
    void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.machineDriversList();
        api.machineList(limit, null, null, null, null, null, null);
        // api.machineSettingsList(null);
        api.machineStatusRetrieve();
    }

    @Test
    void machineTypesList() throws ApiException {
        List<MachineType> actual = api.machineTypesList();

        assertEquals(1, actual.size(), "Incorrect machine type count");

        MachineType actualLabelPrinter = actual.get(0);
        assertEquals("label-printer", actualLabelPrinter.getSlug(), "Incorrect slug");
        assertEquals("Label Printer", actualLabelPrinter.getName(), "Incorrect name");
        assertTrue(actualLabelPrinter.getIsBuiltin(), "Incorrect built-in");
    }
}
