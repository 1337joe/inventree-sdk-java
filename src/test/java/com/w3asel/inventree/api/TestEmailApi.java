package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.GetSimpleLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestEmailApi extends TestApi {
    private EmailApi api;

    @BeforeEach
    void setup() {
        api = new EmailApi(apiClient);
    }

    @Disabled("Response: {status:ok}, not simple login object")
    @Test
    void emailGenerateCreate() throws ApiException {
        GetSimpleLogin input = new GetSimpleLogin().email("admin@demo.inventree.org");
        GetSimpleLogin actual = api.emailGenerateCreate(input);
    }
}
