package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.IconPackage;
import com.w3asel.inventree.model.PaginatedIconPackageList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestIconsApi extends TestApi {
    private IconsApi api;

    @BeforeEach
    public void setup() {
        api = new IconsApi(apiClient);
    }

    @Test
    public void iconsList() throws ApiException {
        int limit = 10;
        int offset = 0;

        PaginatedIconPackageList actual = api.iconsList(limit, offset);
        Assertions.assertTrue(actual.getCount() > 0, "Missing icons packages in response");
        List<IconPackage> actualList = actual.getResults();

        IconPackage actualFirst = actualList.get(0);
        Assertions.assertNotNull(actualFirst.getName(), "Name missing");
    }
}
