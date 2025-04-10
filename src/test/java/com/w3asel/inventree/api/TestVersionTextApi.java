package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedVersionInformationList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestVersionTextApi extends TestApi {
    private VersionTextApi api;

    @BeforeEach
    public void setup() {
        api = new VersionTextApi(apiClient);
    }

    @Disabled("API not paginated")
    @Test
    public void versionTextList() throws ApiException {
        int limit = 10;
        PaginatedVersionInformationList actual = api.versionTextList(limit, null);
        System.out.println(actual);
    }
}
