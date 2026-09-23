package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static com.w3asel.inventree.InventreeDemoDataset.assertNullableFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Note;
import com.w3asel.inventree.model.Note.ModelTypeEnum;
import com.w3asel.inventree.model.NotesImage;
import com.w3asel.inventree.model.PaginatedNoteList;
import com.w3asel.inventree.model.PaginatedNotesImageList;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
//        api.noteImageList(null, null, null, null, null, null, null, null);
        // api.noteList(null, null, null, null, null, null, null, null);
        api.notePartialUpdate(null, null);
        // api.noteRetrieve(null);
        api.noteUpdate(null, null);
    }

    private static void assertNotesImageEquals(JsonObject expected, NotesImage actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("note", fields, actual.getNote());
        assertFieldEquals("date", fields, actual.getDate());

        String expectedImagePath = fields.get("image").getAsString();
        assertTrue(actual.getImage().getPath().endsWith(expectedImagePath), "Incorrect image path");

        // not directly available in demo dataset:
        // actual.getUser();
    }

    @Test
    void noteImageList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.NOTES_IMAGE, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedNotesImageList actual = api.noteImageList(limit, null, null, null, offset, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total notes image count");
        List<NotesImage> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .collect(Collectors.toList());
        List<Integer> actualPks =
                actualList.stream().map(c -> c.getPk()).sorted().collect(Collectors.toList());
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        NotesImage actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.NOTES_IMAGE, actualFirst.getPk()).get(0);
        assertNotesImageEquals(expectedFirst, actualFirst);
    }

    private static void assertNoteEquals(JsonObject expected, Note actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("content", fields, actual.getContent());
        assertFieldEquals("description", fields, actual.getDescription());
        assertFieldEquals("model_id", fields, actual.getModelId());
        assertFieldEquals("primary", fields, actual.getPrimary());
        assertFieldEquals("template", fields, actual.getTemplate());
        assertFieldEquals("title", fields, actual.getTitle());
        assertNullableFieldEquals(OffsetDateTime.class, "updated", fields, actual.getUpdated());
        assertNullableFieldEquals(Integer.class, "updated_by", fields, actual.getUpdatedBy());

        // not directly available in demo dataset:
        // actual.getModelType();
        // actual.getUpdatedByDetail();
    }

    @Test
    void noteList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.NOTE, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedNoteList actual = api.noteList(limit, null, null, offset, null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total note count");
        List<Note> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .collect(Collectors.toList());
        List<Integer> actualPks =
                actualList.stream().map(c -> c.getPk()).sorted().collect(Collectors.toList());
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // deep equals on first value
        Note actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.NOTE, actualFirst.getPk()).get(0);
        assertNoteEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"1", "5"})
    void noteRetrieve(int pk) throws ApiException {
        Note actual = api.noteRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.NOTE, pk).get(0);
        assertNoteEquals(expected, actual);

        ModelTypeEnum expectedModelType;
        switch (pk) {
            case 1:
                expectedModelType = ModelTypeEnum.BUILD_BUILD;
                break;
            case 5:
                expectedModelType = ModelTypeEnum.ORDER_PURCHASEORDER;
                break;
            default:
                expectedModelType = null;
                break;
        }
        assertEquals(expectedModelType, actual.getModelType(), "Incorrect model type");
    }

}
