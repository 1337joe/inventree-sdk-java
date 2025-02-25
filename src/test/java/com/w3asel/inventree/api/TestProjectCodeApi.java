package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.ProjectCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestProjectCodeApi extends TestApi {
    private ProjectCodeApi api;

    @BeforeEach
    public void setup() {
        api = new ProjectCodeApi(apiClient);
    }

    @Test
    public void test() throws ApiException {
        ProjectCode actual = api.projectCodeRetrieve(1);
        Assertions.assertNotNull(actual);
    }
}
