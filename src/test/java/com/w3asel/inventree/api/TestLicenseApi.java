package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.LicenseView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLicenseApi extends TestApi {
    private LicenseApi api;

    @BeforeEach
    void setup() {
        api = new LicenseApi(apiClient);
    }

    @Test
    void licenseRetrieve() throws ApiException {
        LicenseView actual = api.licenseRetrieve();
        assertNotNull(actual, "Missing licenses");
        assertNotNull(actual.getBackend(), "Null backend licenses");
        assertFalse(actual.getBackend().isEmpty(), "Missing backend licenses");
        assertNotNull(actual.getFrontend(), "Null frontend licenses");
        // empty when front-end is not built, such as in the CI environment
        // assertFalse(actual.getFrontend().isEmpty(), "Missing frontend licenses");
    }
}
