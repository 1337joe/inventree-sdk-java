package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.SupplierList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestSupplierApi extends TestApi {
    private SupplierApi api;

    @BeforeEach
    void setup() {
        api = new SupplierApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.supplierImportCreate(null);
        // api.supplierListList();
        api.supplierSearchList(null, null, null);
    }

    @Test
    void supplierListList() throws ApiException {
        List<SupplierList> actual = api.supplierListList();
        assertEquals(0, actual.size(), "Unexpected SupplierList contents");
    }
}
