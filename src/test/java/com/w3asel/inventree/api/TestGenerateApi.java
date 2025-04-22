package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.GenerateBatchCode;
import com.w3asel.inventree.model.GenerateSerialNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestGenerateApi extends TestApi {
    private GenerateApi api;

    @BeforeEach
    public void setup() {
        api = new GenerateApi(apiClient);
    }

    @Test
    public void generateBatchCodeCreate() throws ApiException {
        GenerateBatchCode newItem = new GenerateBatchCode();
        GenerateBatchCode result = api.generateBatchCodeCreate(newItem);
        Assertions.assertNotNull(result.getBatchCode());
    }

    @Disabled("Return expects serial, gets serial_number")
    @Test
    public void generateSerialNumberCreate() throws ApiException {
        GenerateSerialNumber newItem = new GenerateSerialNumber();
        GenerateSerialNumber result = api.generateSerialNumberCreate(newItem);
        Assertions.assertNotNull(result.getSerial());
    }
}
