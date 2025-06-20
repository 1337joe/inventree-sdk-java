package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.ActionPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestActionApi extends TestApi {
    private ActionApi api;

    @BeforeEach
    void setup() {
        api = new ActionApi(apiClient);
    }

    @Disabled("response contains unmapped 'error' field")
    @Test
    void actionCreate() throws ApiException {
        ActionPlugin input = new ActionPlugin().action("Unmatched Action");
        ActionPlugin result = api.actionCreate(input);
    }
}
