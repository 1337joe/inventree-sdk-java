package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.InstanceInfo;
import com.w3asel.inventree.model.Note.ModelTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestInstanceInfoApi extends TestApi {
    private InstanceInfoApi api;

    @BeforeEach
    void setup() {
        api = new InstanceInfoApi(apiClient);
    }

    @ParameterizedTest
    @CsvSource({"13, BUILD_BUILD"})
    void instanceInfoRetrieve(int modelId, ModelTypeEnum modelType) throws ApiException {
        InstanceInfo actual = api.instanceInfoRetrieve(modelId, modelType.toString());

        // TODO verify results
        actual.getNoteCount();
        actual.getAttachmentCount();
        actual.getParameterCount();
    }
}
