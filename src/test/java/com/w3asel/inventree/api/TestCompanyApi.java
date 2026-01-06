package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static com.w3asel.inventree.InventreeDemoDataset.assertNullableFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.Address;
import com.w3asel.inventree.model.BulkRequest;
import com.w3asel.inventree.model.Company;
import com.w3asel.inventree.model.CompanyBrief;
import com.w3asel.inventree.model.Contact;
import com.w3asel.inventree.model.ManufacturerPart;
import com.w3asel.inventree.model.PaginatedAddressList;
import com.w3asel.inventree.model.PaginatedCompanyList;
import com.w3asel.inventree.model.PaginatedContactList;
import com.w3asel.inventree.model.PaginatedManufacturerPartList;
import com.w3asel.inventree.model.PaginatedSupplierPartList;
import com.w3asel.inventree.model.PaginatedSupplierPriceBreakList;
import com.w3asel.inventree.model.PartBrief;
import com.w3asel.inventree.model.SupplierPart;
import com.w3asel.inventree.model.SupplierPriceBreak;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TestCompanyApi extends TestApi {
    private CompanyApi api;

    @BeforeEach
    void setup() {
        api = new CompanyApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.companyAddressBulkDestroy(null);
        // api.companyAddressCreate(null);
        // api.companyAddressDestroy(null);
        // api.companyAddressList(null, null, null, null, null);
        api.companyAddressPartialUpdate(null, null);
        // api.companyAddressRetrieve(null);
        api.companyAddressUpdate(null, null);
        api.companyContactBulkDestroy(null);
        // api.companyContactCreate(null);
        // api.companyContactDestroy(null);
        // api.companyContactList(null, null, null, null, null);
        api.companyContactMetadataPartialUpdate(null, null);
        api.companyContactMetadataRetrieve(null);
        api.companyContactMetadataUpdate(null, null);
        api.companyContactPartialUpdate(null, null);
        // api.companyContactRetrieve(null);
        api.companyContactUpdate(null, null);
        api.companyCreate(null);
        api.companyDestroy(null);
        // api.companyList(null, null, null, null, null, null, null, null, null);
        api.companyMetadataPartialUpdate(null, null);
        api.companyMetadataRetrieve(null);
        api.companyMetadataUpdate(null, null);
        api.companyPartBulkDestroy(null);
        api.companyPartCreate(null);
        api.companyPartDestroy(null);
        api.companyPartialUpdate(null, null);
        // api.companyPartList(null, null, null, null, null, null, null, null, null, null, null,
        // null, null, null, null, null, null);
        api.companyPartManufacturerBulkDestroy(null);
        api.companyPartManufacturerCreate(null);
        api.companyPartManufacturerDestroy(null);
        // api.companyPartManufacturerList(null, null, null, null, null, null, null, null, null,
        // null, null);
        api.companyPartManufacturerMetadataPartialUpdate(null, null);
        api.companyPartManufacturerMetadataRetrieve(null);
        api.companyPartManufacturerMetadataUpdate(null, null);
        api.companyPartManufacturerPartialUpdate(null, null);
        // api.companyPartManufacturerRetrieve(null);
        api.companyPartManufacturerUpdate(null, null);
        api.companyPartMetadataPartialUpdate(null, null);
        api.companyPartMetadataRetrieve(null);
        api.companyPartMetadataUpdate(null, null);
        api.companyPartPartialUpdate(null, null);
        // api.companyPartRetrieve(null, null, null, null, null);
        api.companyPartUpdate(null, null);
        api.companyPriceBreakCreate(null);
        api.companyPriceBreakDestroy(null);
        // api.companyPriceBreakList(null, null, null, null, null, null, null, null);
        api.companyPriceBreakPartialUpdate(null, null);
        // api.companyPriceBreakRetrieve(null);
        api.companyPriceBreakUpdate(null, null);
        // api.companyRetrieve(null);
        api.companyUpdate(null, null);
    }

    private static void assertCompanyEquals(JsonObject expected, Company actual, boolean detail) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("name", fields, actual.getName());
        assertFieldEquals("description", fields, actual.getDescription());
        assertFieldEquals("website", fields, actual.getWebsite());
        assertFieldEquals("phone", fields, actual.getPhone());
        assertNullableFieldEquals(String.class, "email", fields, actual.getEmail());
        assertFieldEquals("contact", fields, actual.getContact());
        assertFieldEquals("link", fields, actual.getLink());

        String mediaPrefix = "/media/";

        String imageString = mediaPrefix + fields.get("image").getAsString();
        try {
            assertEquals(new URI(imageString), actual.getImage(), "Incorrect image");
        } catch (URISyntaxException e) {
            fail("Unable to create URI from " + imageString);
        }

        assertFieldEquals("active", fields, actual.getActive());
        assertFieldEquals("is_customer", fields, actual.getIsCustomer());
        assertFieldEquals("is_supplier", fields, actual.getIsSupplier());
        assertFieldEquals("is_manufacturer", fields, actual.getIsManufacturer());
        assertFieldEquals("currency", fields, actual.getCurrency());

        if (detail) {
            assertFieldEquals("notes", fields, actual.getNotes());
        } else {
            assertNull(actual.getNotes(), "Expect null notes on non-detail calls");
        }

        // not directly available in demo dataset:
        // actual.getAddress()
        // actual.getAddressCount()
        // actual.getPrimaryAddress()
        // actual.getPartsManufactured()
        // actual.getPartsSupplied()
        // actual.getRemoteImage()
        // actual.getTaxId()
    }

    @Test
    void companyList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.COMPANY, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;
        String ordering = "name";
        PaginatedCompanyList actual =
                api.companyList(limit, null, null, null, null, null, offset, ordering, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect company list count");
        List<Company> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        Company actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.COMPANY, actualFirst.getPk()).get(0);
        assertCompanyEquals(expectedFirst, actualFirst, false);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 23})
    void companyRetrieve(int company) throws ApiException {
        JsonObject expected = InventreeDemoDataset.getObjects(Model.COMPANY, company).get(0);
        Company actual = api.companyRetrieve(company);
        assertCompanyEquals(expected, actual, true);

        // verify data not directly in demo dataset
        if (1 == company) {
            assertEquals(0, actual.getPartsManufactured(), "Incorrect parts manufactured");
            assertEquals(200, actual.getPartsSupplied(), "Incorrect parts supplied");
            assertEquals(null, actual.getRemoteImage(), "Incorrect remote image");

        } else if (23 == company) {
            assertEquals(5, actual.getPartsManufactured(), "Incorrect parts manufactured");
            assertEquals(0, actual.getPartsSupplied(), "Incorrect parts supplied");
        }
    }

    private static void assertAddressEquals(JsonObject expected, Address actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("company", fields, actual.getCompany());
        assertFieldEquals("title", fields, actual.getTitle());
        assertFieldEquals("primary", fields, actual.getPrimary());
        assertFieldEquals("line1", fields, actual.getLine1());
        assertFieldEquals("line2", fields, actual.getLine2());
        assertFieldEquals("postal_code", fields, actual.getPostalCode());
        assertFieldEquals("postal_city", fields, actual.getPostalCity());
        assertFieldEquals("province", fields, actual.getProvince());
        assertFieldEquals("country", fields, actual.getCountry());
        assertFieldEquals("shipping_notes", fields, actual.getShippingNotes());
        assertFieldEquals("internal_shipping_notes", fields, actual.getInternalShippingNotes());
        assertFieldEquals("link", fields, actual.getLink());
    }

    @Test
    void companyAddressList_1() throws ApiException {
        int company = 1;
        int limit = 5;
        int offset = 0;
        String ordering = "title";
        PaginatedAddressList actual =
                api.companyAddressList(limit, company, offset, ordering, null);
        assertEquals(2, actual.getCount(), "Incorrect total count");
        List<Address> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = List.of(53, 54);
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertIterableEquals(expectedPks, actualPks, "Primary keys don't match");

        // deep equals on first value
        Address actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.COMPANY_ADDRESS, actualFirst.getPk()).get(0);
        assertAddressEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @ValueSource(ints = {54})
    void companyAddressRetrieve(int address) throws ApiException {
        Address actual = api.companyAddressRetrieve(address);
        JsonObject expected =
                InventreeDemoDataset.getObjects(Model.COMPANY_ADDRESS, address).get(0);
        assertAddressEquals(expected, actual);
    }

    @Test
    void companyAddressDestroy_NotFound() {
        ApiException thrown = assertThrows(ApiException.class, () -> api.companyAddressDestroy(-1),
                "Destroy missing pk should error");
        assertEquals(404, thrown.getCode(), "Expected HTTP 404 Not Found");
        assertTrue(thrown.getMessage().contains("Not Found"),
                "Should contain Not Found: " + thrown.getMessage());
    }

    @Test
    void companyAddressCreateDestroy() throws ApiException {
        int company = 1;

        int initialCount = api.companyAddressList(1, company, null, null, null).getCount();

        // set only required fields
        Address newAddress = new Address().company(company).title("Title");
        assertNull(newAddress.getPk(), "Unsubmitted address should not have PK");

        Address actual = api.companyAddressCreate(newAddress);
        assertNotNull(actual.getPk(), "Created address should have PK");

        try {
            int createdCount = api.companyAddressList(1, company, null, null, null).getCount();
            assertEquals(initialCount + 1, createdCount, "Address count should have increased");
        } finally {
            api.companyAddressDestroy(actual.getPk());
        }

        int deletedCount = api.companyAddressList(1, company, null, null, null).getCount();
        assertEquals(initialCount, deletedCount, "Address count should have reset");

        ApiException thrown = assertThrows(ApiException.class,
                () -> api.companyAddressDestroy(actual.getPk()), "Destroy missing pk should error");
        assertEquals(404, thrown.getCode(), "Expected HTTP 404 Not Found");
        assertTrue(thrown.getMessage().contains("Not Found"),
                "Should contain Not Found: " + thrown.getMessage());
    }

    @Test
    void companyAddressBulkDelete_NotFound() {
        ApiException thrown = assertThrows(ApiException.class,
                () -> api.companyAddressBulkDestroy(new BulkRequest().items(List.of(-1, -2))),
                "Destroy missing pks should error");
        assertEquals(400, thrown.getCode(), "Expected HTTP 400 Bad Request");
        assertTrue(thrown.getMessage().contains("No items match"),
                "Should contain No items match: " + thrown.getMessage());
    }

    private static void assertContactEquals(JsonObject expected, Contact actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("company", fields, actual.getCompany());
        assertFieldEquals("name", fields, actual.getName());
        assertFieldEquals("phone", fields, actual.getPhone());
        assertFieldEquals("email", fields, actual.getEmail());
        assertFieldEquals("role", fields, actual.getRole());

        // not directly available in demo dataset:
        // actual.getCompanyName()
    }

    @Test
    void companyContactList_1() throws ApiException {
        int company = 1;

        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.COMPANY_CONTACT, null);
        expectedList.removeIf(
                jo -> InventreeDemoDataset.getFields(jo).get("company").getAsInt() != company);
        assertTrue(expectedList.size() > 1, "Expected demo data");

        int limit = 5;
        int offset = 0;
        String ordering = "name";
        PaginatedContactList actual =
                api.companyContactList(limit, company, offset, ordering, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect contact list count");
        List<Contact> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertIterableEquals(expectedPks, actualPks, "Primary keys don't match");

        // deep equals on first value
        Contact actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.COMPANY_CONTACT, actualFirst.getPk()).get(0);
        assertContactEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void companyContactRetrieve(int contact) throws ApiException {
        Contact actual = api.companyContactRetrieve(contact);
        JsonObject expected =
                InventreeDemoDataset.getObjects(Model.COMPANY_CONTACT, contact).get(0);
        assertContactEquals(expected, actual);

        // verify data not directly in demo dataset
        if (1 == contact) {
            assertEquals("Arrow", actual.getCompanyName(), "Incorrect company name");
        }
    }

    @Test
    void companyContactDestroy_NotFound() {
        ApiException thrown = assertThrows(ApiException.class, () -> api.companyContactDestroy(-1),
                "Destroy missing pk should error");
        assertEquals(404, thrown.getCode(), "Expected HTTP 404 Not Found");
        assertTrue(thrown.getMessage().contains("Not Found"),
                "Should contain Not Found: " + thrown.getMessage());
    }

    @Test
    void companyContactCreateDestroy() throws ApiException {
        int company = 1;

        int initialCount = api.companyContactList(1, company, null, null, null).getCount();

        // set only required fields
        Contact newContact = new Contact().company(company).name("Name");
        assertNull(newContact.getPk(), "Unsubmitted contact should not have PK");

        Contact actual = api.companyContactCreate(newContact);
        assertNotNull(actual.getPk(), "Created contact should have PK");

        try {
            int createdCount = api.companyContactList(1, company, null, null, null).getCount();
            assertEquals(initialCount + 1, createdCount, "Company count should have increased");
        } finally {
            api.companyContactDestroy(actual.getPk());
        }

        int deletedCount = api.companyContactList(1, company, null, null, null).getCount();
        assertEquals(initialCount, deletedCount, "Contact count should have reset");

        ApiException thrown = assertThrows(ApiException.class,
                () -> api.companyContactDestroy(actual.getPk()), "Destroy missing pk should error");
        assertEquals(404, thrown.getCode(), "Expected HTTP 404 Not Found");
        assertTrue(thrown.getMessage().contains("Not Found"),
                "Should contain Not Found: " + thrown.getMessage());
    }

    @Test
    void companyContactBulkDestroy_NotFound() {
        ApiException thrown = assertThrows(ApiException.class,
                () -> api.companyContactBulkDestroy(new BulkRequest().items(List.of(-1, -2))),
                "Destroy missing pks should error");
        assertEquals(400, thrown.getCode(), "Expected HTTP 400 Bad Request");
        assertTrue(thrown.getMessage().contains("No items match"),
                "Should contain No items match: " + thrown.getMessage());
    }

    @Test
    void companyContactCreateListDelete() throws ApiException {
        int company = 1;
        int targetCount = 3;

        int initialCount = api.companyContactList(1, company, null, null, null).getCount();

        List<Integer> createdPks = new ArrayList<>();
        try {
            for (int i = 0; i < targetCount; i++) {
                // set only required fields
                Contact newContact = new Contact().company(company).name("Name");

                Contact actual = api.companyContactCreate(newContact);
                assertNotNull(actual.getPk(), "Created contact should have PK");
                createdPks.add(actual.getPk());
            }

            int createdCount = api.companyContactList(1, company, null, null, null).getCount();
            assertEquals(initialCount + targetCount, createdCount,
                    "Company count should have increased");

            api.companyContactBulkDestroy(new BulkRequest().items(createdPks));

            int deletedCount = api.companyContactList(1, company, null, null, null).getCount();
            assertEquals(initialCount, deletedCount, "Contact count should have reset");
        } finally {
            for (int pk : createdPks) {
                try {
                    api.companyContactDestroy(pk);
                } catch (ApiException e) {
                    // should throw 404 exceptions on success
                }
            }
        }
    }

    private static void assertSupplierPartEquals(JsonObject expected, SupplierPart actual,
            boolean detail) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("barcode_hash", fields, actual.getBarcodeHash());

        OffsetDateTime expectedUpdated = fields.get("updated").isJsonNull() ? null
                : InventreeDemoDataset.parseOffsetDateTime(fields.get("updated").getAsString())
                        .truncatedTo(ChronoUnit.MINUTES);
        assertEquals(expectedUpdated, actual.getUpdated(), "Incorrect updated");

        assertFieldEquals("part", fields, actual.getPart());
        assertFieldEquals("supplier", fields, actual.getSupplier());
        assertFieldEquals("SKU", fields, actual.getSKU());
        assertFieldEquals("active", fields, actual.getActive());
        assertFieldEquals("manufacturer_part", fields, actual.getManufacturerPart());
        assertFieldEquals("description", fields, actual.getDescription());
        assertNullableFieldEquals(String.class, "note", fields, actual.getNote());
        assertFieldEquals("link", fields, actual.getLink());
        assertNullableFieldEquals(String.class, "packaging", fields, actual.getPackaging());
        assertFieldEquals("pack_quantity", fields, actual.getPackQuantity());
        assertFieldEquals("pack_quantity_native", fields, actual.getPackQuantityNative());
        assertFieldEquals("available", fields, actual.getAvailable());

        OffsetDateTime expectedAvailabilityUpdated = InventreeDemoDataset
                .parseOffsetDateTime(fields.get("availability_updated").getAsString())
                .truncatedTo(ChronoUnit.MINUTES);
        assertEquals(expectedAvailabilityUpdated, actual.getAvailabilityUpdated(),
                "Incorrect availability updated");

        if (detail) {
            assertNullableFieldEquals(String.class, "notes", fields, actual.getNotes());
        } else {
            assertNull(actual.getNotes(), "Expect null notes on non-detail calls");

            assertNull(actual.getManufacturerDetail(),
                    "Expect null manufacturer detail on non-detail calls");
            assertNull(actual.getManufacturerPartDetail(),
                    "Expect null manufacturer part detail on non-detail calls");
            assertNull(actual.getPartDetail(), "Expect null part detail on non-detail calls");
            // this is populated in list calls, others don't seem to be
            // assertNull(actual.getSupplierDetail(),
            // "Expect null supplier detail on non-detail calls");
        }

        // in demo dataset but not directly available from class:
        // metadata
        // barcode_data
        // base_cost
        // multiple

        // not directly available in demo dataset:
        // actual.getInStock();
        // actual.getManufacturerDetail();
        // actual.getMPN();
        // actual.getOnOrder();
        // actual.getPartDetail();
        // actual.getPrettyName();
        // actual.getSupplierDetail();
        // actual.getTags();
    }

    @Test
    void companyPartList_1() throws ApiException {
        int company = 1;

        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.COMPANY_SUPPLIER_PART, null);
        expectedList.removeIf(
                jo -> InventreeDemoDataset.getFields(jo).get("supplier").getAsInt() != company);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        // TODO missing complex filter parameters on this query
        PaginatedSupplierPartList actual =
                api.companyPartList(limit, null, null, null, company, null, null, null, null, null,
                        offset, null, null, null, null, null, null, null, null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect part list count");
        List<SupplierPart> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        SupplierPart actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.COMPANY_SUPPLIER_PART, actualFirst.getPk()).get(0);
        assertSupplierPartEquals(expectedFirst, actualFirst, false);
    }

    @ParameterizedTest
    @CsvSource({"11,,,,", "11,false,true,false,true", "11,true,false,true,false"})
    void companyPartRetrieve(int supplierPart, Boolean manufacturerDetail, Boolean partDetail,
            Boolean pretty, Boolean supplierDetail) throws ApiException {
        Boolean manufacturerPartDetail = null;
        SupplierPart actual = api.companyPartRetrieve(supplierPart, manufacturerDetail,
                manufacturerPartDetail, partDetail, pretty, supplierDetail);
        JsonObject expected =
                InventreeDemoDataset.getObjects(Model.COMPANY_SUPPLIER_PART, supplierPart).get(0);
        assertSupplierPartEquals(expected, actual, true);

        // verify data not directly in demo dataset
        JsonObject expectedManufacturer = null;
        String expectedPrettyName = null;
        if (11 == supplierPart) {
            assertEquals(0, actual.getInStock(), "Incorrect in stock");
            assertEquals("RR0510P-104-D", actual.getMPN(), "Incorrect MPN");
            assertEquals(0, actual.getOnOrder(), "Incorrect on order");
            assertEquals(0, actual.getTags().size(), "Incorrect tags");

            expectedManufacturer = InventreeDemoDataset.getObjects(Model.COMPANY, 4).get(0);
            expectedPrettyName = "DigiKey | RR05P100KDTR-ND | Susumu | RR0510P-104-D";
        }

        JsonObject expectedPart =
                InventreeDemoDataset.getObjects(Model.PART, actual.getPart()).get(0);
        JsonObject expectedSupplier =
                InventreeDemoDataset.getObjects(Model.COMPANY, actual.getSupplier()).get(0);

        // verify optional flags are respected
        if (manufacturerDetail == null || !manufacturerDetail) {
            assertNull(actual.getManufacturerDetail(), "Expected unpopulated manufacturer detail");
        } else {
            CompanyBrief actualManufacturerDetail = actual.getManufacturerDetail();
            assertNotNull(actual.getManufacturerDetail(), "Expected populated manufacturer detail");
            assertEquals(
                    InventreeDemoDataset.getFields(expectedManufacturer).get("name").getAsString(),
                    actualManufacturerDetail.getName(), "Incorrect manufacturer detail name");
        }
        if (partDetail == null || !partDetail) {
            assertNull(actual.getPartDetail(), "Expected unpopulated part detail");
        } else {
            PartBrief actualPartDetail = actual.getPartDetail();
            assertNotNull(actualPartDetail, "Expected populated part detail");
            assertEquals(InventreeDemoDataset.getFields(expectedPart).get("name").getAsString(),
                    actualPartDetail.getName(), "Incorrect part detail name");
        }
        if (pretty == null || !pretty) {
            assertNull(actual.getPrettyName(), "Expected unpopulated pretty name");
        } else {
            assertEquals(expectedPrettyName, actual.getPrettyName(), "Incorrect pretty name");
        }

        if (supplierDetail == null || !supplierDetail) {
            assertNull(actual.getSupplierDetail(), "Expected unpopulated supplier detail");
        } else {
            CompanyBrief actualSupplierDetail = actual.getSupplierDetail();
            assertNotNull(actualSupplierDetail, "Expected populated supplier detail");
            assertEquals(InventreeDemoDataset.getFields(expectedSupplier).get("name").getAsString(),
                    actualSupplierDetail.getName(), "Incorrect supplier detail name");
        }
    }

    private static void assertManufacturerPartEquals(JsonObject expected, ManufacturerPart actual,
            boolean detail) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("part", fields, actual.getPart());
        assertFieldEquals("barcode_hash", fields, actual.getBarcodeHash());
        assertFieldEquals("manufacturer", fields, actual.getManufacturer());
        assertFieldEquals("MPN", fields, actual.getMPN());
        assertNullableFieldEquals(String.class, "description", fields, actual.getDescription());
        assertNullableFieldEquals(URI.class, "link", fields, actual.getLink());

        if (detail) {
            assertNullableFieldEquals(String.class, "notes", fields, actual.getNotes());
        } else {
            assertNull(actual.getNotes(), "Expect null notes on list calls");

            assertNull(actual.getManufacturerDetail(),
                    "Expect null manufacturer detail on list calls");
            assertNull(actual.getPartDetail(), "Expect null part detail on list calls");
        }

        // in demo dataset but not directly available from class:
        // metadata
        // barcode_data

        // not directly available in demo dataset
        // actual.getManufacturerDetail();
        // actual.getPartDetail();
        // actual.getPrettyName();
        // actual.getTags();
    }

    @Test
    void companyPartManufacturerList() throws ApiException {
        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.COMPANY_MANUFACTURER_PART, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedManufacturerPartList actual = api.companyPartManufacturerList(limit, null, null,
                null, null, offset, null, null, null, null, null, null, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect manufacturer list count");
        List<ManufacturerPart> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        ManufacturerPart actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.COMPANY_MANUFACTURER_PART, actualFirst.getPk()).get(0);
        assertManufacturerPartEquals(expectedFirst, actualFirst, false);
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void companyPartManufacturerRetrieve(int manufacturerPart) throws ApiException {
        ManufacturerPart actual = api.companyPartManufacturerRetrieve(manufacturerPart);
        JsonObject expected = InventreeDemoDataset
                .getObjects(Model.COMPANY_MANUFACTURER_PART, manufacturerPart).get(0);
        assertManufacturerPartEquals(expected, actual, true);
    }

    private static void assertSupplierPriceBreakEquals(JsonObject expected,
            SupplierPriceBreak actual, boolean detail) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        OffsetDateTime expectedUpdate =
                InventreeDemoDataset.parseOffsetDateTime(fields.get("updated").getAsString())
                        .truncatedTo(ChronoUnit.MINUTES);
        assertEquals(expectedUpdate, actual.getUpdated(), "Incorrect updated time");

        assertFieldEquals("quantity", fields, actual.getQuantity());
        assertFieldEquals("price_currency", fields, actual.getPriceCurrency());
        assertFieldEquals("price", fields, actual.getPrice().doubleValue());
        assertFieldEquals("part", fields, actual.getPart());

        if (!detail) {
            assertNull(actual.getPartDetail(), "Expect null part detail on list calls");
            assertNull(actual.getSupplierDetail(), "Expect null supplier detail on list calls");
        }

        // not directly available in demo dataset
        // actual.getPartDetail()
        // actual.getSupplier()
        // actual.getSupplierDetail()
    }

    @Test
    void companyPriceBreakList_177() throws ApiException {
        int company = 1;
        int part = 177;

        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.COMPANY_SUPPLIER_PRICE_BREAK, null);
        expectedList
                .removeIf(jo -> InventreeDemoDataset.getFields(jo).get("part").getAsInt() != part);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedSupplierPriceBreakList actual = api.companyPriceBreakList(limit, null, offset,
                null, part, null, null, null, null, null);
        assertEquals(2, actual.getCount(), "Incorrect price break list count");
        List<SupplierPriceBreak> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        SupplierPriceBreak actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.COMPANY_SUPPLIER_PRICE_BREAK, actualFirst.getPk()).get(0);
        assertSupplierPriceBreakEquals(expectedFirst, actualFirst, false);

        for (SupplierPriceBreak priceBreak : actualList) {
            assertEquals(company, priceBreak.getSupplier(), "Incorrect supplier reference");
        }
    }

    @ParameterizedTest
    @CsvSource({"2,1"})
    void companyPriceBreakRetrieve(int supplierPriceBreak, int company) throws ApiException {
        SupplierPriceBreak actual = api.companyPriceBreakRetrieve(supplierPriceBreak);
        JsonObject expectedPriceBreak =
                InventreeDemoDataset.getObjects(Model.COMPANY_SUPPLIER_PRICE_BREAK, 2).get(0);
        assertSupplierPriceBreakEquals(expectedPriceBreak, actual, true);

        assertEquals(company, actual.getSupplier(), "Incorrect supplier reference");
    }
}
