package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.GenerateBatchCode;
import com.w3asel.inventree.model.GenerateSerialNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    /**
     * Part number is required for a serial number to be generated but omitting it is still a valid
     * request.
     */
    @Test
    public void generateSerialNumberCreate_null() throws ApiException {
        GenerateSerialNumber newItem = new GenerateSerialNumber();
        GenerateSerialNumber result = api.generateSerialNumberCreate(newItem);
        Assertions.assertNull(result.getSerialNumber());
    }

    @ParameterizedTest
    @CsvSource({"1,", "1,2"})
    public void generateSerialNumberCreate(int part, Integer quantity) throws ApiException {
        GenerateSerialNumber newItem = new GenerateSerialNumber().part(part).quantity(quantity);
        GenerateSerialNumber result = api.generateSerialNumberCreate(newItem);
        Assertions.assertNotNull(result.getSerialNumber());

        String[] actualSplit = result.getSerialNumber().split(",");
        Assertions.assertEquals(quantity == null ? 1 : quantity, actualSplit.length);
    }
}
