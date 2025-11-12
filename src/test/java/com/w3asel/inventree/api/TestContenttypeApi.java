package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.ContentType;
import com.w3asel.inventree.model.PaginatedContentTypeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestContenttypeApi extends TestApi {
    private ContenttypeApi api;

    @BeforeEach
    void setup() {
        api = new ContenttypeApi(apiClient);
    }

    @Test
    void contenttypeList() throws ApiException {
        int limit = 5;
        PaginatedContentTypeList actual = api.contenttypeList(limit, null, null, null);
        assertTrue(actual.getCount() > 0, "Expected to receive models.");
        // TODO verify results if can be derived from something known
        // System.out.println(actual.getResults());
    }

    @Test
    void contenttypeRetrieveModel_notFound() throws ApiException {
        String model = "notfound";
        ApiException thrown = assertThrows(ApiException.class,
                () -> api.contenttypeRetrieveModel(model), "Unexpected model should not be found");
        assertEquals(404, thrown.getCode(), "Expected HTTP 404 Not Found");
    }

    @ParameterizedTest
    @CsvSource({"stockitem"})
    void contenttypeRetrieveModel_found(String model) throws ApiException {
        ContentType actual = api.contenttypeRetrieveModel(model);
        assertNotNull(actual);

        ContentType expected;
        switch (model) {
            case "stockitem":
                expected = new ContentType(actual.getPk(), "stock", "stockitem",
                        "Stock | Stock Item", false);
                break;
            default:
                fail("Unspecified expected value");
                expected = null;
                break;
        }

        assertEquals(expected, actual, "Incorrect ContentType by model");

        // also test retrieve with known id
        ContentType actual2 = api.contenttypeRetrieve(actual.getPk());
        assertEquals(expected, actual2, "Incorrect ContentType by id");
    }
}
