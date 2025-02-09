package com.w3asel.inventree.api;

import com.w3asel.inventree.client.ApiException;
import com.w3asel.inventree.java.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCompanyApi extends TestApi {
    private CompanyApi api;

    @BeforeEach
    public void setup() {
        api = new CompanyApi(apiClient);
    }

    @Test
    public void companyRetrieve() throws ApiException {
        Company actual = api.companyRetrieve("7"); // TODO why not int?
        Assertions.assertNotNull(actual);
        Assertions.fail(actual.getAddress());
    }

    @Test
    public void test() throws ApiException {
//        Company actual = api.companyPartList("1");
    }
}
