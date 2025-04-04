package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedIconPackageList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestIconsApi extends TestApi {
    private IconsApi api;

    @BeforeEach
    public void setup() {
        api = new IconsApi(apiClient);
    }

    @Disabled("Throws server error")
    @Test
    public void iconsList() throws ApiException {
        int limit = 10;
        int offset = 0;

        PaginatedIconPackageList actual = api.iconsList(limit, offset);
        Assertions.assertTrue(actual.getCount() > 0, "Missing icons packages in response");
        // List<IconPackage> actualList = actual.getResults();
        // TODO validate
    }
}
