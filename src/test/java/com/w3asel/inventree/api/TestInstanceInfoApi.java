package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Config;
import com.w3asel.inventree.model.EmailMessage;
import com.w3asel.inventree.model.PaginatedEmailMessageList;
import com.w3asel.inventree.model.TestEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class TestInstanceInfoApi extends TestApi {
    private InstanceInfoApi api;

    @BeforeEach
    void setup() {
        api = new InstanceInfoApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.instanceInfoRetrieve(null, null);
    }
}
