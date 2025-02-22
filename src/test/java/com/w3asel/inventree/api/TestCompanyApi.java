package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class TestCompanyApi extends TestApi {
    private CompanyApi api;

    @BeforeEach
    public void setup() {
        api = new CompanyApi(apiClient);
    }

    @Test
    public void companyRetrieve() throws ApiException {
        Company actual = api.companyRetrieve("1"); // TODO why not int?
        Assertions.assertNotNull(actual, "Company retrieve failed");
        Assertions.assertEquals(1, actual.getPk(), "Incorrect primary key");
        Assertions.assertEquals("DigiKey", actual.getName(), "Incorrect name");
        Assertions.assertEquals(2, actual.getAddressCount(), "Incorrect address count");
        Assertions.assertEquals("04964 Cox View Suite 815, 94832, Wesleyport, Delaware, Bolivia", actual.getAddress(), "Incorrect address");
        Address actualAddress = actual.getPrimaryAddress();
        Assertions.assertNotNull(actualAddress, "Primary address is null");
        Assertions.assertEquals(54, actualAddress.getPk(), "Incorrect primary address primary key");
    }

    @Test
    public void companyAddressList_1() throws ApiException {
        api.companyAddressList(1, 100, 0, null, null);
        // TODO validate
    }

    @Test
    public void companyList_paginated() throws ApiException {
        int limit = 5;
        int offset = 0;
        PaginatedCompanyList actual = api.companyList(null, null, null, null, limit, null, offset, null, null);
        Assertions.assertEquals(36, actual.getCount());
        List<Company> actualList = actual.getResults();
        // TODO validate
    }

    @Test
    public void companyAddressRetrieve() throws ApiException, URISyntaxException {
        Address actual = api.companyAddressRetrieve(54); // TODO not int
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(54, actual.getPk(), "Incorrect primary key");
        Assertions.assertEquals(1, actual.getCompany(), "Incorrect company reference");
        Assertions.assertEquals("Secondary Address", actual.getTitle(), "Incorrect title");
        Assertions.assertEquals("04964 Cox View Suite 815", actual.getLine1(), "Incorrect line 1");
        Assertions.assertEquals("", actual.getLine2(), "Incorrect line 2");
        Assertions.assertEquals("94832", actual.getPostalCode(), "Incorrect postal code");
        Assertions.assertEquals("Wesleyport", actual.getPostalCity(), "Incorrect postal city");
        Assertions.assertEquals("Delaware", actual.getProvince(), "Incorrect province");
        Assertions.assertEquals("Bolivia", actual.getCountry(), "Incorrect country");
        Assertions.assertEquals("", actual.getShippingNotes(), "Incorrect shipping notes");
        Assertions.assertEquals("", actual.getInternalShippingNotes(), "Incorrect internal shipping notes");
        Assertions.assertEquals(new URI(""), actual.getLink(), "Incorrect link");
    }

    @Test
    public void companyContactList() throws ApiException {
        PaginatedContactList actual = api.companyContactList(1, 100, 0, null, null);
        // TODO validate
    }

    @Test
    public void companyPartList() throws ApiException {
        PaginatedSupplierPartList actual = api.companyPartList(null, null, null, "1", null, 100, null, null, 0, null, null, null, null, null, null, null, null);
        // TODO validate
    }

    @Test
    public void companyPartManufacturerList_paginated() throws ApiException {
        int limit = 5;
        int offset = 0;
        api.companyPartManufacturerList(null, limit, null, null, offset, null, null, null, null, null, null);
        // TODO validate
    }

    @Test
    public void companyPartManufacturerParameterList_paginated() throws ApiException {
        int limit = 5;
        int offset = 0;
        api.companyPartManufacturerParameterList(limit, null, null, null, offset, null, null, null, null, null);
        // TODO validate
    }

    @Test
    public void companyPriceBreakList_paginated() throws ApiException {
        int limit = 5;
        int offset = 0;
        api.companyPriceBreakList(null, limit, offset, null, null, null, null, null);
        // TODO validate
    }
}
