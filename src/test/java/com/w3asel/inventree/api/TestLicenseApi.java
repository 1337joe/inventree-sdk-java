package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.LicenseView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLicenseApi extends TestApi {
    private LicenseApi api;

    @BeforeEach
    public void setup() {
        api = new LicenseApi(apiClient);
    }

    @Test
    public void licenseRetrieve() throws ApiException {
        LicenseView actual = api.licenseRetrieve();
        Assertions.assertNotNull(actual, "Missing licenses");
        Assertions.assertNotNull(actual.getBackend(), "Null backend licenses");
        Assertions.assertFalse(actual.getBackend().isEmpty(), "Missing backend licenses");
        Assertions.assertNotNull(actual.getFrontend(), "Null frontend licenses");
        // empty when front-end is not built, such as in the CI environment
        // Assertions.assertFalse(actual.getFrontend().isEmpty(), "Missing frontend licenses");
    }
}
