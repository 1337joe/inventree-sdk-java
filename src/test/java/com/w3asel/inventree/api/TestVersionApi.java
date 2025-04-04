package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.VersionView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestVersionApi extends TestApi {
    private VersionApi api;

    @BeforeEach
    public void setup() {
        api = new VersionApi(apiClient);
    }

    @Test
    public void versionRetrieve() throws ApiException {
        VersionView actual = api.versionRetrieve();
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getVersion());
        Assertions.assertNotNull(actual.getLinks());
    }
}
