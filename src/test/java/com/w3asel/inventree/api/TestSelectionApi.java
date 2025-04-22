package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.PaginatedSelectionListList;
import com.w3asel.inventree.model.SelectionList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestSelectionApi extends TestApi {
    private SelectionApi api;

    @BeforeEach
    public void setup() {
        api = new SelectionApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
        api.selectionCreate(null);
        api.selectionDestroy(null);
        api.selectionEntryCreate(null, null);
        api.selectionEntryDestroy(null, null);
        api.selectionEntryList(null, null, null);
        api.selectionEntryPartialUpdate(null, null, null);
        api.selectionEntryRetrieve(null, null);
        api.selectionEntryUpdate(null, null, null);
        api.selectionList(null, null);
        api.selectionPartialUpdate(null, null);
        api.selectionRetrieve(null);
        api.selectionUpdate(null, null);
    }

    @Test
    public void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        PaginatedSelectionListList actual = api.selectionList(limit, null);
        for (SelectionList list : actual.getResults()) {
            api.selectionEntryList(list.getPk(), limit, null);
        }
    }
}
