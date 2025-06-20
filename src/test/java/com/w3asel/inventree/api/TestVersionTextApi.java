package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.VersionInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestVersionTextApi extends TestApi {
    private VersionTextApi api;

    @BeforeEach
    void setup() {
        api = new VersionTextApi(apiClient);
    }

    @Test
    void versionTextRetrieve() throws ApiException {
        int count = 10;
        List<VersionInformation> actual = api.versionTextList(null, count);
        assertEquals(count, actual.size(), "Unexpected number of versions returned");

        VersionInformation latestActual = actual.get(actual.size() - 1);
        assertNotNull(latestActual.getVersion(), "Version can't be null");
        assertNotNull(latestActual.getDate(), "Date can't be null");
        // gh can be null
        assertNotNull(latestActual.getText(), "Text can't be null");
        assertFalse(latestActual.getText().isEmpty(), "Text can't be empty");
        assertTrue(latestActual.getLatest(),
                "Last version should be latest: " + latestActual.getVersion());
    }
}
