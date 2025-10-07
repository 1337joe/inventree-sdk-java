package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Flag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

public class TestFlagsApi extends TestApi {
    private FlagsApi api;

    @BeforeEach
    void setup() {
        api = new FlagsApi(apiClient);
    }

    @Test
    void flagsList() throws ApiException {
        List<Flag> actual = api.flagsList();
        assertFalse(actual.isEmpty(), "Expected list of flags");
    }

    @ParameterizedTest
    @CsvSource({"EXPERIMENTAL"})
    void flagsRetrieve(String key) throws ApiException {
        Flag actual = api.flagsRetrieve(key);
        assertEquals(key, actual.getKey(), "Incorrect key");
    }
}
