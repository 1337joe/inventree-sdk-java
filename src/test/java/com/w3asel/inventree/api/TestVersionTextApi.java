package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.VersionInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestVersionTextApi extends TestApi {
    private VersionTextApi api;

    @BeforeEach
    public void setup() {
        api = new VersionTextApi(apiClient);
    }

    @Test
    public void versionTextRetrieve() throws ApiException {
        int count = 10;
        List<VersionInformation> actual = api.versionTextList(null, count);
        Assertions.assertEquals(count, actual.size(), "Unexpected number of versions returned");

        VersionInformation latestActual = actual.get(actual.size() - 1);
        Assertions.assertNotNull(latestActual.getVersion(), "Version can't be null");
        Assertions.assertNotNull(latestActual.getDate(), "Date can't be null");
        // gh can be null
        Assertions.assertNotNull(latestActual.getText(), "Text can't be null");
        Assertions.assertFalse(latestActual.getText().isEmpty(), "Text can't be empty");
        Assertions.assertTrue(latestActual.getLatest(),
                "Last version should be latest: " + latestActual.getVersion());
    }
}
