package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestAccountPhoneApi extends TestApi {
    private AccountPhoneApi api;

    @BeforeEach
    void setup() {
        api = new AccountPhoneApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.allauthAccountPhonePost(null);
    }

    @Disabled("500: No attribute AuthenticationResponse")
    @Test
    void allauthAccountPhoneGet() throws ApiException {
        api.allauthAccountPhoneGet();
    }
}
