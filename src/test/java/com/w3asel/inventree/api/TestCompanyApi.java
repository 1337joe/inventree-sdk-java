package com.w3asel.inventree.api;

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
import com.w3asel.inventree.model.ManufacturerPartParameter;
import com.w3asel.inventree.model.PaginatedAddressList;
import com.w3asel.inventree.model.PaginatedCompanyList;
import com.w3asel.inventree.model.PaginatedContactList;
import com.w3asel.inventree.model.PaginatedManufacturerPartList;
import com.w3asel.inventree.model.PaginatedManufacturerPartParameterList;
import com.w3asel.inventree.model.PaginatedSupplierPartList;
import com.w3asel.inventree.model.PaginatedSupplierPriceBreakList;
import com.w3asel.inventree.model.SupplierPart;
import com.w3asel.inventree.model.SupplierPriceBreak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TestCompanyApi extends TestApi {
    private CompanyApi api;

    @BeforeEach
    public void setup() {
        api = new CompanyApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
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
        api.companyPartManufacturerParameterBulkDestroy(null);
        api.companyPartManufacturerParameterCreate(null);
        api.companyPartManufacturerParameterDestroy(null);
        // api.companyPartManufacturerParameterList(null, null, null, null, null, null, null, null,
        // null, null);
        api.companyPartManufacturerParameterPartialUpdate(null, null);
        // api.companyPartManufacturerParameterRetrieve(null);
        api.companyPartManufacturerParameterUpdate(null, null);
        api.companyPartManufacturerPartialUpdate(null, null);
        // api.companyPartManufacturerRetrieve(null);
        api.companyPartManufacturerUpdate(null, null);
        api.companyPartMetadataPartialUpdate(null, null);
        api.companyPartMetadataRetrieve(null);
        api.companyPartMetadataUpdate(null, null);
        api.companyPartPartialUpdate(null, null);
        api.companyPartRetrieve(null);
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
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertEquals("name", fields, actual.getName());
        InventreeDemoDataset.assertEquals("description", fields, actual.getDescription());
        InventreeDemoDataset.assertEquals("website", fields, actual.getWebsite());
        InventreeDemoDataset.assertEquals("phone", fields, actual.getPhone());
        InventreeDemoDataset.assertNullableEquals(String.class, "email", fields, actual.getEmail());
        InventreeDemoDataset.assertEquals("contact", fields, actual.getContact());
        InventreeDemoDataset.assertEquals("link", fields, actual.getLink());

        String mediaPrefix = "/media/";

        String imageString = mediaPrefix + fields.get("image").getAsString();
        try {
            Assertions.assertEquals(new URI(imageString), actual.getImage(), "Incorrect image");
        } catch (URISyntaxException e) {
            Assertions.fail("Unable to create URI from " + imageString);
        }

        InventreeDemoDataset.assertEquals("active", fields, actual.getActive());
        InventreeDemoDataset.assertEquals("is_customer", fields, actual.getIsCustomer());
        InventreeDemoDataset.assertEquals("is_supplier", fields, actual.getIsSupplier());
        InventreeDemoDataset.assertEquals("is_manufacturer", fields, actual.getIsManufacturer());
        InventreeDemoDataset.assertEquals("currency", fields, actual.getCurrency());

        if (detail) {
            InventreeDemoDataset.assertEquals("notes", fields, actual.getNotes());
        } else {
            Assertions.assertNull(actual.getNotes(), "Expect null notes on non-detail calls");
        }

        // not directly available in demo dataset:
        // actual.getAddress()
        // actual.getAddressCount()
        // actual.getPrimaryAddress()
        // actual.getPartsManufactured()
        // actual.getPartsSupplied()
        // actual.getRemoteImage()
    }

    @Test
    public void companyList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.COMPANY, null);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;
        String ordering = "name";
        PaginatedCompanyList actual =
                api.companyList(limit, null, null, null, null, null, offset, ordering, null);
        Assertions.assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect company list count");
        List<Company> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        Company actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.COMPANY, actualFirst.getPk()).get(0);
        assertCompanyEquals(expectedFirst, actualFirst, false);
    }

    @ParameterizedTest
    @CsvSource({"1", "23"})
    public void companyRetrieve(int company) throws ApiException {
        JsonObject expected = InventreeDemoDataset.getObjects(Model.COMPANY, company).get(0);
        Company actual = api.companyRetrieve(company);
        assertCompanyEquals(expected, actual, true);

        // verify data not directly in demo dataset
        if (1 == company) {
            Assertions.assertEquals(
                    "04964 Cox View Suite 815, 94832, Wesleyport, Delaware, Bolivia",
                    actual.getAddress(), "Incorrect address");
            Assertions.assertEquals(2, actual.getAddressCount(), "Incorrect address count");

            JsonObject expectedAddress =
                    InventreeDemoDataset.getObjects(Model.COMPANY_ADDRESS, 54).get(0);
            assertAddressEquals(expectedAddress, actual.getPrimaryAddress());

            Assertions.assertEquals(0, actual.getPartsManufactured(),
                    "Incorrect parts manufactured");
            Assertions.assertEquals(200, actual.getPartsSupplied(), "Incorrect parts supplied");
            Assertions.assertEquals(null, actual.getRemoteImage(), "Incorrect remote image");

        } else if (23 == company) {
            Assertions.assertEquals(5, actual.getPartsManufactured(),
                    "Incorrect parts manufactured");
            Assertions.assertEquals(0, actual.getPartsSupplied(), "Incorrect parts supplied");
        }
    }

    private static void assertAddressEquals(JsonObject expected, Address actual) {
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertEquals("company", fields, actual.getCompany());
        InventreeDemoDataset.assertEquals("title", fields, actual.getTitle());
        InventreeDemoDataset.assertEquals("primary", fields, actual.getPrimary());
        InventreeDemoDataset.assertEquals("line1", fields, actual.getLine1());
        InventreeDemoDataset.assertEquals("line2", fields, actual.getLine2());
        InventreeDemoDataset.assertEquals("postal_code", fields, actual.getPostalCode());
        InventreeDemoDataset.assertEquals("postal_city", fields, actual.getPostalCity());
        InventreeDemoDataset.assertEquals("province", fields, actual.getProvince());
        InventreeDemoDataset.assertEquals("country", fields, actual.getCountry());
        InventreeDemoDataset.assertEquals("shipping_notes", fields, actual.getShippingNotes());
        InventreeDemoDataset.assertEquals("internal_shipping_notes", fields,
                actual.getInternalShippingNotes());
        InventreeDemoDataset.assertEquals("link", fields, actual.getLink());
    }

    @Test
    public void companyAddressList_1() throws ApiException {
        int company = 1;
        int limit = 5;
        int offset = 0;
        String ordering = "title";
        PaginatedAddressList actual =
                api.companyAddressList(limit, company, offset, ordering, null);
        Assertions.assertEquals(2, actual.getCount(), "Incorrect total count");
        List<Address> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = List.of(53, 54);
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertIterableEquals(expectedPks, actualPks, "Primary keys don't match");

        // deep equals on first value
        Address actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.COMPANY_ADDRESS, actualFirst.getPk()).get(0);
        assertAddressEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"54"})
    public void companyAddressRetrieve(int address) throws ApiException {
        Address actual = api.companyAddressRetrieve(address);
        JsonObject expected =
                InventreeDemoDataset.getObjects(Model.COMPANY_ADDRESS, address).get(0);
        assertAddressEquals(expected, actual);
    }

    @Test
    public void companyAddressDestroy_NotFound() {
        try {
            api.companyAddressDestroy(-1);
            Assertions.fail("Expected 404 Not Found");
        } catch (ApiException e) {
            Assertions.assertTrue(e.getMessage().contains("Not Found"),
                    "Invalid key should not have been found.");
        }
    }

    @Test
    public void companyAddressCreateDestroy() throws ApiException {
        int company = 1;

        int initialCount = api.companyAddressList(1, company, null, null, null).getCount();

        // set only required fields
        Address newAddress = new Address().company(company).title("Title");
        Assertions.assertNull(newAddress.getPk(), "Unsubmitted address should not have PK");

        Address actual = api.companyAddressCreate(newAddress);
        Assertions.assertNotNull(actual.getPk(), "Created address should have PK");

        try {
            int createdCount = api.companyAddressList(1, company, null, null, null).getCount();
            Assertions.assertEquals(initialCount + 1, createdCount,
                    "Address count should have increased");
        } finally {
            api.companyAddressDestroy(actual.getPk());
        }

        int deletedCount = api.companyAddressList(1, company, null, null, null).getCount();
        Assertions.assertEquals(initialCount, deletedCount, "Address count should have reset");

        try {
            api.companyAddressDestroy(actual.getPk());
            Assertions.fail("Expected 404 Not Found");
        } catch (ApiException e) {
            Assertions.assertTrue(e.getMessage().contains("Not Found"),
                    "Invalid key should not have been found.");
        }
    }

    @Test
    public void companyAddressBulkDelete_NotFound() {
        try {
            api.companyAddressBulkDestroy(new BulkRequest().items(List.of(-1, -2)));
            Assertions.fail("Expected 400 Bad Request");
        } catch (ApiException e) {
            Assertions.assertTrue(e.getMessage().contains("No items found"),
                    "Invalid key should not have been found.");
        }
    }

    private static void assertContactEquals(JsonObject expected, Contact actual) {
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertEquals("company", fields, actual.getCompany());
        InventreeDemoDataset.assertEquals("name", fields, actual.getName());
        InventreeDemoDataset.assertEquals("phone", fields, actual.getPhone());
        InventreeDemoDataset.assertEquals("email", fields, actual.getEmail());
        InventreeDemoDataset.assertEquals("role", fields, actual.getRole());

        // not directly available in demo dataset:
        // actual.getCompanyName()
    }

    @Test
    public void companyContactList_1() throws ApiException {
        int company = 1;

        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.COMPANY_CONTACT, null);
        expectedList.removeIf(
                jo -> InventreeDemoDataset.getFields(jo).get("company").getAsInt() != company);
        Assertions.assertTrue(expectedList.size() > 1, "Expected demo data");

        int limit = 5;
        int offset = 0;
        String ordering = "name";
        PaginatedContactList actual =
                api.companyContactList(limit, company, offset, ordering, null);
        Assertions.assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect contact list count");
        List<Contact> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertIterableEquals(expectedPks, actualPks, "Primary keys don't match");

        // deep equals on first value
        Contact actualFirst = actualList.get(0);
        JsonObject expectedFirst =
                InventreeDemoDataset.getObjects(Model.COMPANY_CONTACT, actualFirst.getPk()).get(0);
        assertContactEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest
    @CsvSource({"1"})
    public void companyContactRetrieve(int contact) throws ApiException {
        Contact actual = api.companyContactRetrieve(contact);
        JsonObject expected =
                InventreeDemoDataset.getObjects(Model.COMPANY_CONTACT, contact).get(0);
        assertContactEquals(expected, actual);

        // verify data not directly in demo dataset
        if (1 == contact) {
            Assertions.assertEquals("Arrow", actual.getCompanyName(), "Incorrect company name");
        }
    }

    @Test
    public void companyContactDestroy_NotFound() {
        try {
            api.companyContactDestroy(-1);
            Assertions.fail("Expected 404 Not Found");
        } catch (ApiException e) {
            Assertions.assertTrue(e.getMessage().contains("Not Found"),
                    "Invalid key should not have been found.");
        }
    }

    @Test
    public void companyContactCreateDestroy() throws ApiException {
        int company = 1;

        int initialCount = api.companyContactList(1, company, null, null, null).getCount();

        // set only required fields
        Contact newContact = new Contact().company(company).name("Name");
        Assertions.assertNull(newContact.getPk(), "Unsubmitted contact should not have PK");

        Contact actual = api.companyContactCreate(newContact);
        Assertions.assertNotNull(actual.getPk(), "Created contact should have PK");

        try {
            int createdCount = api.companyContactList(1, company, null, null, null).getCount();
            Assertions.assertEquals(initialCount + 1, createdCount,
                    "Company count should have increased");
        } finally {
            api.companyContactDestroy(actual.getPk());
        }

        int deletedCount = api.companyContactList(1, company, null, null, null).getCount();
        Assertions.assertEquals(initialCount, deletedCount, "Contact count should have reset");

        try {
            api.companyContactDestroy(actual.getPk());
            Assertions.fail("Expected 404 Not Found");
        } catch (ApiException e) {
            Assertions.assertTrue(e.getMessage().contains("Not Found"),
                    "Invalid key should not have been found.");
        }
    }

    @Test
    public void companyContactBulkDestroy_NotFound() {
        try {
            api.companyContactBulkDestroy(new BulkRequest().items(List.of(-1, -2)));
            Assertions.fail("Expected 400 Bad Request");
        } catch (ApiException e) {
            Assertions.assertTrue(e.getMessage().contains("No items found"),
                    "Invalid key should not have been found.");
        }
    }

    @Test
    public void companyContactCreateListDelete() throws ApiException {
        int company = 1;
        int targetCount = 3;

        int initialCount = api.companyContactList(1, company, null, null, null).getCount();

        List<Integer> createdPks = new ArrayList<>();
        try {
            for (int i = 0; i < targetCount; i++) {
                // set only required fields
                Contact newContact = new Contact().company(company).name("Name");

                Contact actual = api.companyContactCreate(newContact);
                Assertions.assertNotNull(actual.getPk(), "Created contact should have PK");
                createdPks.add(actual.getPk());
            }

            int createdCount = api.companyContactList(1, company, null, null, null).getCount();
            Assertions.assertEquals(initialCount + targetCount, createdCount,
                    "Company count should have increased");

            try {
                api.companyContactBulkDestroy(new BulkRequest().items(createdPks));
            } catch (ApiException e) {
                Assertions.assertTrue(
                        e.getMessage().contains("HTTP 204 had non-zero Content-Length"),
                        "Expected improper content (server sends wrong status code)");
            }

            int deletedCount = api.companyContactList(1, company, null, null, null).getCount();
            Assertions.assertEquals(initialCount, deletedCount, "Contact count should have reset");
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
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertEquals("barcode_hash", fields, actual.getBarcodeHash());

        OffsetDateTime expectedUpdated = fields.get("updated").isJsonNull() ? null
                : InventreeDemoDataset.parseOffsetDateTime(fields.get("updated").getAsString())
                        .truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertEquals(expectedUpdated, actual.getUpdated(), "Incorrect updated");

        InventreeDemoDataset.assertEquals("part", fields, actual.getPart());
        InventreeDemoDataset.assertEquals("supplier", fields, actual.getSupplier());
        InventreeDemoDataset.assertEquals("SKU", fields, actual.getSKU());
        InventreeDemoDataset.assertEquals("active", fields, actual.getActive());
        InventreeDemoDataset.assertEquals("manufacturer_part", fields,
                actual.getManufacturerPart());
        InventreeDemoDataset.assertEquals("description", fields, actual.getDescription());
        InventreeDemoDataset.assertNullableEquals(String.class, "note", fields, actual.getNote());
        InventreeDemoDataset.assertEquals("link", fields, actual.getLink());
        InventreeDemoDataset.assertNullableEquals(String.class, "packaging", fields,
                actual.getPackaging());
        InventreeDemoDataset.assertEquals("pack_quantity", fields, actual.getPackQuantity());
        InventreeDemoDataset.assertEquals("pack_quantity_native", fields,
                actual.getPackQuantityNative());
        InventreeDemoDataset.assertEquals("available", fields, actual.getAvailable());

        OffsetDateTime expectedAvailabilityUpdated = InventreeDemoDataset
                .parseOffsetDateTime(fields.get("availability_updated").getAsString())
                .truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertEquals(expectedAvailabilityUpdated, actual.getAvailabilityUpdated(),
                "Incorrect availability updated");

        if (detail) {
            InventreeDemoDataset.assertNullableEquals(String.class, "notes", fields,
                    actual.getNotes());
        } else {
            Assertions.assertNull(actual.getNotes(), "Expect null notes on non-detail calls");

            Assertions.assertNull(actual.getManufacturerDetail(),
                    "Expect null manufacturer detail on non-detail calls");
            Assertions.assertNull(actual.getManufacturerPartDetail(),
                    "Expect null manufacturer part detail on non-detail calls");
            Assertions.assertNull(actual.getPartDetail(),
                    "Expect null part detail on non-detail calls");
            // this is populated in list calls, others don't seem to be
            // Assertions.assertNull(actual.getSupplierDetail(),
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
    public void companyPartList_1() throws ApiException {
        int company = 1;

        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.COMPANY_SUPPLIER_PART, null);
        expectedList.removeIf(
                jo -> InventreeDemoDataset.getFields(jo).get("supplier").getAsInt() != company);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedSupplierPartList actual = api.companyPartList(limit, null, null, null, company,
                null, null, null, offset, null, null, null, null, null, null, null, null);
        Assertions.assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect part list count");
        List<SupplierPart> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        SupplierPart actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.COMPANY_SUPPLIER_PART, actualFirst.getPk()).get(0);
        assertSupplierPartEquals(expectedFirst, actualFirst, false);
    }

    @ParameterizedTest
    @CsvSource({"11"})
    public void companyPartRetrieve(int supplierPart) throws ApiException {
        SupplierPart actual = api.companyPartRetrieve(supplierPart);
        JsonObject expected =
                InventreeDemoDataset.getObjects(Model.COMPANY_SUPPLIER_PART, supplierPart).get(0);
        assertSupplierPartEquals(expected, actual, true);

        // verify data not directly in demo dataset
        if (11 == supplierPart) {
            Assertions.assertEquals(0, actual.getInStock(), "Incorrect in stock");
            Assertions.assertEquals(null, actual.getManufacturerDetail(),
                    "Incorrect manufacturer detail");
            Assertions.assertEquals(null, actual.getMPN(), "Incorrect MPN");
            Assertions.assertEquals(0, actual.getOnOrder(), "Incorrect on order");
            Assertions.assertEquals(null, actual.getPartDetail(), "Incorrect part detail");

            CompanyBrief actualSupplierDetail = actual.getSupplierDetail();
            JsonObject expectedSupplier =
                    InventreeDemoDataset.getObjects(Model.COMPANY, actual.getSupplier()).get(0);
            Assertions.assertEquals(
                    InventreeDemoDataset.getFields(expectedSupplier).get("name").getAsString(),
                    actualSupplierDetail.getName(), "Incorrect supplier detail name");

            Assertions.assertEquals(null, actual.getPrettyName(), "Incorrect pretty name");
            Assertions.assertEquals(0, actual.getTags().size(), "Incorrect tags");
        }
    }

    private static void assertManufacturerPartEquals(JsonObject expected, ManufacturerPart actual,
            boolean detail) {
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertEquals("part", fields, actual.getPart());
        InventreeDemoDataset.assertEquals("barcode_hash", fields, actual.getBarcodeHash());
        InventreeDemoDataset.assertEquals("manufacturer", fields, actual.getManufacturer());
        InventreeDemoDataset.assertEquals("MPN", fields, actual.getMPN());
        InventreeDemoDataset.assertNullableEquals(String.class, "description", fields,
                actual.getDescription());
        InventreeDemoDataset.assertNullableEquals(URI.class, "link", fields, actual.getLink());

        if (detail) {
            InventreeDemoDataset.assertNullableEquals(String.class, "notes", fields,
                    actual.getNotes());
        } else {
            Assertions.assertNull(actual.getNotes(), "Expect null notes on list calls");

            Assertions.assertNull(actual.getManufacturerDetail(),
                    "Expect null manufacturer detail on list calls");
            Assertions.assertNull(actual.getPartDetail(), "Expect null part detail on list calls");
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
    public void companyPartManufacturerList() throws ApiException {
        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.COMPANY_MANUFACTURER_PART, null);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedManufacturerPartList actual = api.companyPartManufacturerList(limit, null, null,
                null, offset, null, null, null, null, null, null);
        Assertions.assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect manufacturer list count");
        List<ManufacturerPart> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        ManufacturerPart actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.COMPANY_MANUFACTURER_PART, actualFirst.getPk()).get(0);
        assertManufacturerPartEquals(expectedFirst, actualFirst, false);
    }

    @ParameterizedTest
    @CsvSource({"1"})
    public void companyPartManufacturerRetrieve(int manufacturerPart) throws ApiException {
        ManufacturerPart actual = api.companyPartManufacturerRetrieve(manufacturerPart);
        JsonObject expected = InventreeDemoDataset
                .getObjects(Model.COMPANY_MANUFACTURER_PART, manufacturerPart).get(0);
        assertManufacturerPartEquals(expected, actual, true);
    }

    @Disabled("No data in database")
    @Test
    public void companyPartManufacturerParameterList() throws ApiException {
        int limit = 5;
        int offset = 0;
        PaginatedManufacturerPartParameterList actual = api.companyPartManufacturerParameterList(
                limit, null, null, null, offset, null, null, null, null, null);
        Assertions.assertEquals(0, actual.getCount());
        List<ManufacturerPartParameter> actualList = actual.getResults();
        // TODO validate, no results?
    }

    @Disabled("No demo data to find")
    @Test
    public void companyPartManufacturerParameterRetrieve() throws ApiException {
        api.companyPartManufacturerParameterRetrieve(1);
    }

    private static void assertSupplierPriceBreakEquals(JsonObject expected,
            SupplierPriceBreak actual, boolean detail) {
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        OffsetDateTime expectedUpdate =
                InventreeDemoDataset.parseOffsetDateTime(fields.get("updated").getAsString())
                        .truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertEquals(expectedUpdate, actual.getUpdated(), "Incorrect updated time");

        InventreeDemoDataset.assertEquals("quantity", fields, actual.getQuantity());
        InventreeDemoDataset.assertEquals("price_currency", fields, actual.getPriceCurrency());
        InventreeDemoDataset.assertEquals("price", fields, actual.getPrice().doubleValue());
        InventreeDemoDataset.assertEquals("part", fields, actual.getPart());

        if (!detail) {
            Assertions.assertNull(actual.getPartDetail(), "Expect null part detail on list calls");
            Assertions.assertNull(actual.getSupplierDetail(),
                    "Expect null supplier detail on list calls");
        }

        // not directly available in demo dataset
        // actual.getPartDetail()
        // actual.getSupplier()
        // actual.getSupplierDetail()
    }

    @Test
    public void companyPriceBreakList_177() throws ApiException {
        int company = 1;
        int part = 177;

        List<JsonObject> expectedList =
                InventreeDemoDataset.getObjects(Model.COMPANY_SUPPLIER_PRICE_BREAK, null);
        expectedList
                .removeIf(jo -> InventreeDemoDataset.getFields(jo).get("part").getAsInt() != part);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 5;
        int offset = 0;
        PaginatedSupplierPriceBreakList actual =
                api.companyPriceBreakList(limit, null, offset, null, part, null, null, null);
        Assertions.assertEquals(2, actual.getCount(), "Incorrect price break list count");
        List<SupplierPriceBreak> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertTrue(expectedPks.containsAll(actualPks), "Unexpected primary keys");

        // deep equals on first value
        SupplierPriceBreak actualFirst = actualList.get(0);
        JsonObject expectedFirst = InventreeDemoDataset
                .getObjects(Model.COMPANY_SUPPLIER_PRICE_BREAK, actualFirst.getPk()).get(0);
        assertSupplierPriceBreakEquals(expectedFirst, actualFirst, false);

        for (SupplierPriceBreak priceBreak : actualList) {
            Assertions.assertEquals(company, priceBreak.getSupplier(),
                    "Incorrect supplier reference");
        }
    }

    @ParameterizedTest
    @CsvSource({"2,1"})
    public void companyPriceBreakRetrieve(int supplierPriceBreak, int company) throws ApiException {
        SupplierPriceBreak actual = api.companyPriceBreakRetrieve(supplierPriceBreak);
        JsonObject expectedPriceBreak =
                InventreeDemoDataset.getObjects(Model.COMPANY_SUPPLIER_PRICE_BREAK, 2).get(0);
        assertSupplierPriceBreakEquals(expectedPriceBreak, actual, true);

        Assertions.assertEquals(company, actual.getSupplier(), "Incorrect supplier reference");
    }
}
