package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.ActionPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestActionApi extends TestApi {
    private ActionApi api;

    @BeforeEach
    void setup() {
        api = new ActionApi(apiClient);
    }

    @Test
    void actionCreate_unmatched() {
        ActionPlugin input = new ActionPlugin().action("Unmatched Action");
        ApiException actual = assertThrows(ApiException.class, () -> api.actionCreate(input));
        assertEquals(404, actual.getCode(), "Incorrect error code");
    }
}
