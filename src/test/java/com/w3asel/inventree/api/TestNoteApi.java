package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestNoteApi extends TestApi {
    private NoteApi api;

    @BeforeEach
    void setup() {
        api = new NoteApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.noteCreate(null);
        api.noteDestroy(null);
        api.noteImageCreate(null);
        api.noteImageList(null, null, null, null, null, null, null, null);
        api.noteList(null, null, null, null, null, null, null, null);
        api.notePartialUpdate(null, null);
        api.noteRetrieve(null);
        api.noteUpdate(null, null);
    }

    @Test
    void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.noteImageList(limit, null, null, null, null, null, null, null);
        api.noteList(limit, null, null, null, null, null, null, null);
    }
}
