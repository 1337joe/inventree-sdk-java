package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Attachment;
import com.w3asel.inventree.model.AttachmentModelTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.net.URI;

public class TestAttachmentApi extends TestApi {
    private AttachmentApi api;

    @BeforeEach
    public void setup() {
        api = new AttachmentApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.attachmentBulkDestroy(null);
        // api.attachmentCreate(null);
        // api.attachmentDestroy(null);
        // api.attachmentList(null, null, null, null, null, null, null, null, null);
        api.attachmentMetadataPartialUpdate(null, null);
        api.attachmentMetadataRetrieve(null);
        api.attachmentMetadataUpdate(null, null);
        api.attachmentPartialUpdate(null, null);
        api.attachmentRetrieve(null);
        api.attachmentUpdate(null, null);
    }

    @Disabled("No data")
    @Test
    public void attachmentList() throws ApiException {
        int limit = 1000;
        api.attachmentList(limit, null, null, null, null, null, null, null, null);
    }

    @Test
    public void attachmentCreateDestroy() throws ApiException {
        int initialCount =
                api.attachmentList(1, null, null, null, null, null, null, null, null).getCount();

        // set only required fields
        Attachment newItem = new Attachment().modelType(AttachmentModelTypeEnum.BUILD).modelId(0);
        // file or link must be set as well
        newItem.setLink(URI.create("http://localhost:8000"));
        Assertions.assertNull(newItem.getPk(), "Unsubmitted item should not have PK");

        Attachment actual = api.attachmentCreate(newItem);
        Assertions.assertNotNull(actual.getPk(), "Created item should have PK");

        try {
            int createdCount = api.attachmentList(1, null, null, null, null, null, null, null, null)
                    .getCount();
            Assertions.assertEquals(initialCount + 1, createdCount, "Count should have increased");

            Attachment retrieved = api.attachmentRetrieve(actual.getPk());
            Assertions.assertEquals(actual.getPk(), retrieved.getPk(), "PKs don't match");
            Assertions.assertEquals(actual.getModelType(), retrieved.getModelType(),
                    "Model types don't match");
            Assertions.assertEquals(actual.getModelId(), retrieved.getModelId(),
                    "Model IDs don't match");
            Assertions.assertEquals(actual.getLink(), retrieved.getLink(), "Links don't match");
        } finally {
            api.attachmentDestroy(actual.getPk());
        }

        int deletedCount =
                api.attachmentList(1, null, null, null, null, null, null, null, null).getCount();
        Assertions.assertEquals(initialCount, deletedCount, "Count should have reset");

        // verify item not found
        try {
            api.attachmentRetrieve(actual.getPk());
            Assertions.fail("Expected 404 Not Found");
        } catch (ApiException e) {
            Assertions.assertTrue(e.getMessage().contains("Not Found"),
                    "Invalid key should not have been found.");
        }
    }
}
