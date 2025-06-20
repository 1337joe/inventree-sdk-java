package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.VersionView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestVersionApi extends TestApi {
    private VersionApi api;

    @BeforeEach
    void setup() {
        api = new VersionApi(apiClient);
    }

    @Test
    void versionRetrieve() throws ApiException {
        VersionView actual = api.versionRetrieve();
        assertNotNull(actual);
        assertNotNull(actual.getVersion());
        assertNotNull(actual.getLinks());
    }
}
