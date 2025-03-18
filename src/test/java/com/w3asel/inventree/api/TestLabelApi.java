package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.LabelTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLabelApi extends TestApi {
    private LabelApi api;

    @BeforeEach
    public void setup() {
        api = new LabelApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        LabelTemplate actual = api.labelTemplateRetrieve(1);
        Assertions.assertNotNull(actual);

        int limit = 100;
        api.labelTemplateList(limit, null, null, null, null, null);
    }
}
