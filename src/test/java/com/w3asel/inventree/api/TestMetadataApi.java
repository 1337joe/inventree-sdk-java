package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestMetadataApi extends TestApi {
    private MetadataApi api;

    @BeforeEach
    void setup() {
        api = new MetadataApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.metadataPartialUpdate(null, null, null, null);
        api.metadataPkPartialUpdate(null, null, null);
        api.metadataPkRetrieve(null, null);
        api.metadataPkUpdate(null, null, null);
        api.metadataRetrieve(null, null, null);
        api.metadataUpdate(null, null, null, null);
    }
}
