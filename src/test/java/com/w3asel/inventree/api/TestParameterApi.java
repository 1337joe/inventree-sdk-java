package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestParameterApi extends TestApi {
    private ParameterApi api;

    @BeforeEach
    void setup() {
        api = new ParameterApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.parameterBulkDestroy(null);
        api.parameterCreate(null);
        api.parameterDestroy(null);
        api.parameterList(null, null, null, null, null, null, null, null, null);
        api.parameterMetadataPartialUpdate(null, null);
        api.parameterMetadataRetrieve(null);
        api.parameterMetadataUpdate(null, null);
        api.parameterPartialUpdate(null, null);
        api.parameterTemplateCreate(null);
        api.parameterTemplateDestroy(null);
        api.parameterTemplateList(null, null, null, null, null, null, null, null, null, null, null,
                null, null);
        api.parameterTemplateMetadataPartialUpdate(null, null);
        api.parameterTemplateMetadataRetrieve(null);
        api.parameterTemplateMetadataUpdate(null, null);
        api.parameterTemplatePartialUpdate(null, null);
        api.parameterTemplateRetrieve(null);
        api.parameterTemplateUpdate(null, null);
        api.parameterUpdate(null, null);
    }

    @Test
    void testList() throws ApiException {
        int limit = 1000;
        int offset = 0;

        api.parameterList(limit, null, null, null, offset, null, null, null, null);
        api.parameterTemplateList(limit, null, null, null, null, null, null, null, null, offset,
                null, null, null);
    }
}
