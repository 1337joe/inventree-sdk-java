package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAccountEmailApi extends TestApi {
    private AccountEmailApi api;

    @BeforeEach
    void setup() {
        api = new AccountEmailApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAccountEmailDelete(null);
        api.allauthAccountEmailPatch(null);
        api.allauthAccountEmailPost(null);
        api.allauthAccountEmailPut(null);
    }

    @Disabled("Unauthorized")
    @Test
    void allauthAccountEmailGet() throws ApiException {
        api.allauthAccountEmailGet();
    }
}
