package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.ProjectCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestProjectCodeApi extends TestApi {
    private ProjectCodeApi api;

    @BeforeEach
    void setup() {
        api = new ProjectCodeApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.projectCodeCreate(null);
        api.projectCodeDestroy(null);
        api.projectCodeMetadataPartialUpdate(null, null);
        api.projectCodeMetadataRetrieve(null);
        api.projectCodeMetadataUpdate(null, null);
        api.projectCodePartialUpdate(null, null);
        api.projectCodeRetrieve(null);
        api.projectCodeUpdate(null, null);
    }

    @Test
    void projectCodeList() throws ApiException {
        // TODO verify results
        api.projectCodeList(1000, null, null, null);

        ProjectCode actual = api.projectCodeRetrieve(1);
        assertNotNull(actual);
    }
}
