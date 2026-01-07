package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Attachment;
import com.w3asel.inventree.model.AttachmentModelTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.net.URI;

public class TestAttachmentApi extends TestApi {
    private AttachmentApi api;

    @BeforeEach
    void setup() {
        api = new AttachmentApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.attachmentBulkDestroy(null);
        // api.attachmentCreate(null);
        // api.attachmentDestroy(null);
        // api.attachmentList(null, null, null, null, null, null, null, null, null);
        api.attachmentPartialUpdate(null, null);
        api.attachmentRetrieve(null);
        api.attachmentUpdate(null, null);
    }

    @Disabled("No data")
    @Test
    void attachmentList() throws ApiException {
        int limit = 1000;
        api.attachmentList(limit, null, null, null, null, null, null, null, null);
    }

    @Test
    void attachmentCreateDestroy() throws ApiException {
        int initialCount =
                api.attachmentList(1, null, null, null, null, null, null, null, null).getCount();

        // set only required fields
        Attachment newItem = new Attachment().modelType(AttachmentModelTypeEnum.BUILD).modelId(0l);
        // file or link must be set as well
        newItem.setLink(URI.create("http://localhost:8000"));
        assertNull(newItem.getPk(), "Unsubmitted item should not have PK");

        Attachment actual = api.attachmentCreate(newItem);
        assertNotNull(actual.getPk(), "Created item should have PK");

        try {
            int createdCount = api.attachmentList(1, null, null, null, null, null, null, null, null)
                    .getCount();
            assertEquals(initialCount + 1, createdCount, "Count should have increased");

            Attachment retrieved = api.attachmentRetrieve(actual.getPk());
            assertEquals(actual.getPk(), retrieved.getPk(), "PKs don't match");
            assertEquals(actual.getModelType(), retrieved.getModelType(),
                    "Model types don't match");
            assertEquals(actual.getModelId(), retrieved.getModelId(), "Model IDs don't match");
            assertEquals(actual.getLink(), retrieved.getLink(), "Links don't match");
        } finally {
            api.attachmentDestroy(actual.getPk());
        }

        int deletedCount =
                api.attachmentList(1, null, null, null, null, null, null, null, null).getCount();
        assertEquals(initialCount, deletedCount, "Count should have reset");

        // verify item not found
        ApiException thrown = assertThrows(ApiException.class,
                () -> api.attachmentRetrieve(actual.getPk()), "Retrieve missing pk should error");
        assertEquals(404, thrown.getCode(), "Expected HTTP 404 Not Found");
        assertTrue(thrown.getMessage().contains("Not Found"),
                "Should contain Not Found: " + thrown.getMessage());
    }
}
