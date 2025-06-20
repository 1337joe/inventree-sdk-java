package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestFlagsApi extends TestApi {
    private FlagsApi api;

    @BeforeEach
    void setup() {
        api = new FlagsApi(apiClient);
    }

    @Disabled("void response")
    @Test
    void flagsList() throws ApiException {
        int limit = 10;
        int offset = 0;

        api.flagsList(limit, offset);
    }

    @Disabled("void response")
    @ParameterizedTest
    @CsvSource({"key"})
    void flagsRetrieve(String key) throws ApiException {
        api.flagsRetrieve(key);
    }
}
