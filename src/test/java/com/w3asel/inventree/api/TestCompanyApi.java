package com.w3asel.inventree.api;

import com.google.gson.JsonElement;
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
import java.util.Map;

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
        Assertions.assertEquals(expected.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt(),
                actual.getPk(), "Incorrect primary key");

        Map<String, JsonElement> fields = InventreeDemoDataset.getFields(expected);

        Assertions.assertEquals(fields.get("name").getAsString(), actual.getName(),
                "Incorrect name");
        Assertions.assertEquals(fields.get("description").getAsString(), actual.getDescription(),
                "Incorrect description");

        String websiteString = fields.get("website").getAsString();
        try {
            Assertions.assertEquals(new URI(websiteString), actual.getWebsite(),
                    "Incorrect website");
        } catch (URISyntaxException e) {
            Assertions.fail("Unable to create URI from " + websiteString);
        }

        Assertions.assertEquals(fields.get("phone").getAsString(), actual.getPhone(),
                "Incorrect phone");
        String emailString =
                fields.get("email").isJsonNull() ? null : fields.get("email").getAsString();
        Assertions.assertEquals(emailString, actual.getEmail(), "Incorrect email");
        Assertions.assertEquals(fields.get("contact").getAsString(), actual.getContact(),
                "Incorrect contact");

        String linkString = fields.get("link").getAsString();
        try {
            Assertions.assertEquals(new URI(linkString), actual.getLink(), "Incorrect link");
        } catch (URISyntaxException e) {
            Assertions.fail("Unable to create URI from " + linkString);
        }

        String mediaPrefix = "/media/";

        String imageString = mediaPrefix + fields.get("image").getAsString();
        try {
            Assertions.assertEquals(new URI(imageString), actual.getImage(), "Incorrect image");
        } catch (URISyntaxException e) {
            Assertions.fail("Unable to create URI from " + imageString);
        }
        Assertions.assertEquals(fields.get("active").getAsBoolean(), actual.getActive(),
                "Incorrect active state");
        Assertions.assertEquals(fields.get("is_customer").getAsBoolean(), actual.getIsCustomer(),
                "Incorrect customer state");
        Assertions.assertEquals(fields.get("is_supplier").getAsBoolean(), actual.getIsSupplier(),
                "Incorrect supplier state");
        Assertions.assertEquals(fields.get("is_manufacturer").getAsBoolean(),
                actual.getIsManufacturer(), "Incorrect manufacturer state");
        Assertions.assertEquals(fields.get("currency").getAsString(), actual.getCurrency(),
                "Incorrect currency");


        if (detail) {
            Assertions.assertEquals(fields.get("notes").getAsString(), actual.getNotes(),
                    "Incorrect notes");
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
        Assertions.assertEquals(expected.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt(),
                actual.getPk(), "Incorrect primary key");

        Map<String, JsonElement> fields = InventreeDemoDataset.getFields(expected);

        Assertions.assertEquals(fields.get("company").getAsInt(), actual.getCompany(),
                "Incorrect company reference");
        Assertions.assertEquals(fields.get("title").getAsString(), actual.getTitle(),
                "Incorrect title");
        Assertions.assertEquals(fields.get("primary").getAsBoolean(), actual.getPrimary(),
                "Expected to be primary address");
        Assertions.assertEquals(fields.get("line1").getAsString(), actual.getLine1(),
                "Incorrect line 1");
        Assertions.assertEquals(fields.get("line2").getAsString(), actual.getLine2(),
                "Incorrect line 2");
        Assertions.assertEquals(fields.get("postal_code").getAsString(), actual.getPostalCode(),
                "Incorrect postal code");
        Assertions.assertEquals(fields.get("postal_city").getAsString(), actual.getPostalCity(),
                "Incorrect postal city");
        Assertions.assertEquals(fields.get("province").getAsString(), actual.getProvince(),
                "Incorrect province");
        Assertions.assertEquals(fields.get("country").getAsString(), actual.getCountry(),
                "Incorrect country");
        Assertions.assertEquals(fields.get("shipping_notes").getAsString(),
                actual.getShippingNotes(), "Incorrect shipping notes");
        Assertions.assertEquals(fields.get("internal_shipping_notes").getAsString(),
                actual.getInternalShippingNotes(), "Incorrect internal shipping notes");

        String uriString = fields.get("link").getAsString();
        try {
            Assertions.assertEquals(new URI(uriString), actual.getLink(), "Incorrect link");
        } catch (URISyntaxException e) {
            Assertions.fail("Unable to create URI from " + uriString);
        }
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
            Assertions.assertTrue(e.getMessage().contains("No items match"),
                    "Invalid key should not have been found.");
        }
    }

    // TODO address update, address partial update

    private static void assertContactEquals(JsonObject expected, Contact actual) {
        Assertions.assertEquals(expected.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt(),
                actual.getPk(), "Incorrect primary key");

        Map<String, JsonElement> fields = InventreeDemoDataset.getFields(expected);

        Assertions.assertEquals(fields.get("company").getAsInt(), actual.getCompany(),
                "Incorrect company reference");
        Assertions.assertEquals(fields.get("name").getAsString(), actual.getName(),
                "Incorrect name");
        Assertions.assertEquals(fields.get("phone").getAsString(), actual.getPhone(),
                "Incorrect phone");
        Assertions.assertEquals(fields.get("email").getAsString(), actual.getEmail(),
                "Incorrect email");
        Assertions.assertEquals(fields.get("role").getAsString(), actual.getRole(),
                "Incorrect role");

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
            Assertions.assertTrue(e.getMessage().contains("No items match"),
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

            api.companyContactBulkDestroy(new BulkRequest().items(createdPks));

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

    // TODO contact update, contact partial update


    private static void assertSupplierPartEquals(JsonObject expected, SupplierPart actual,
            boolean detail) {
        Assertions.assertEquals(expected.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt(),
                actual.getPk(), "Incorrect primary key");

        Map<String, JsonElement> fields = InventreeDemoDataset.getFields(expected);

        Assertions.assertEquals(fields.get("barcode_hash").getAsString(), actual.getBarcodeHash(),
                "Incorrect barcode hash");

        OffsetDateTime expectedUpdated = fields.get("updated").isJsonNull() ? null
                : InventreeDemoDataset.parseOffsetDateTime(fields.get("updated").getAsString())
                        .truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertEquals(expectedUpdated, actual.getUpdated(), "Incorrect updated");

        Assertions.assertEquals(fields.get("part").getAsInt(), actual.getPart(),
                "Incorrect part reference");
        Assertions.assertEquals(fields.get("supplier").getAsInt(), actual.getSupplier(),
                "Incorrect supplier reference");
        Assertions.assertEquals(fields.get("SKU").getAsString(), actual.getSKU(), "Incorrect SKU");
        Assertions.assertEquals(fields.get("active").getAsBoolean(), actual.getActive(),
                "Incorrect active state");
        Assertions.assertEquals(fields.get("manufacturer_part").getAsInt(),
                actual.getManufacturerPart(), "Incorrect manufacturer part reference");

        Assertions.assertEquals(fields.get("description").getAsString(), actual.getDescription(),
                "Incorrect description");
        String noteString =
                fields.get("note").isJsonNull() ? null : fields.get("note").getAsString();
        Assertions.assertEquals(noteString, actual.getNote(), "Incorrect note");

        String linkString = fields.get("link").getAsString();
        try {
            Assertions.assertEquals(new URI(linkString), actual.getLink(), "Incorrect link");
        } catch (URISyntaxException e) {
            Assertions.fail("Unable to create URI from " + linkString);
        }

        String packagingString =
                fields.get("packaging").isJsonNull() ? null : fields.get("packaging").getAsString();
        Assertions.assertEquals(packagingString, actual.getPackaging(), "Incorrect packaging");
        Assertions.assertEquals(fields.get("pack_quantity").getAsString(), actual.getPackQuantity(),
                "Incorrect pack quantity");
        Assertions.assertEquals(fields.get("pack_quantity_native").getAsDouble(),
                actual.getPackQuantityNative(), "Incorrect pack quantity native");
        Assertions.assertEquals(fields.get("available").getAsDouble(), actual.getAvailable(),
                "Incorrect available quantity");
        OffsetDateTime expectedAvailabilityUpdated = InventreeDemoDataset
                .parseOffsetDateTime(fields.get("availability_updated").getAsString())
                .truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertEquals(expectedAvailabilityUpdated, actual.getAvailabilityUpdated(),
                "Incorrect availability updated");

        if (detail) {
            String expectedNotes =
                    fields.get("notes").isJsonNull() ? null : fields.get("notes").getAsString();
            Assertions.assertEquals(expectedNotes, actual.getNotes(), "Incorrect notes");
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
        // TODO string company
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
            Assertions.assertEquals("RR0510P-104-D", actual.getMPN(), "Incorrect MPN");
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
        Assertions.assertEquals(expected.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt(),
                actual.getPk(), "Incorrect primary key");

        Map<String, JsonElement> fields = InventreeDemoDataset.getFields(expected);

        Assertions.assertEquals(fields.get("part").getAsInt(), actual.getPart(),
                "Incorrect part reference");
        Assertions.assertEquals(fields.get("barcode_hash").getAsString(), actual.getBarcodeHash(),
                "Incorrect barcode hash");
        Assertions.assertEquals(fields.get("manufacturer").getAsInt(), actual.getManufacturer(),
                "Incorrect manufacturer reference");
        Assertions.assertEquals(fields.get("MPN").getAsString(), actual.getMPN(), "Incorrect MPN");

        String descriptionString = fields.get("description").isJsonNull() ? null
                : fields.get("description").getAsString();
        Assertions.assertEquals(descriptionString, actual.getDescription(),
                "Incorrect description");

        String linkString =
                fields.get("link").isJsonNull() ? null : fields.get("link").getAsString();
        try {
            Assertions.assertEquals(linkString == null ? null : new URI(linkString),
                    actual.getLink(), "Incorrect link");
        } catch (URISyntaxException e) {
            Assertions.fail("Unable to create URI from " + linkString);
        }

        if (detail) {
            String expectedNotes =
                    fields.get("notes").isJsonNull() ? null : fields.get("notes").getAsString();
            Assertions.assertEquals(expectedNotes, actual.getNotes(), "Incorrect notes");
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
        Assertions.assertEquals(expected.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt(),
                actual.getPk(), "Incorrect primary key");

        Map<String, JsonElement> fields = InventreeDemoDataset.getFields(expected);

        OffsetDateTime expectedUpdate =
                InventreeDemoDataset.parseOffsetDateTime(fields.get("updated").getAsString())
                        .truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertEquals(expectedUpdate, actual.getUpdated(), "Incorrect updated time");

        Assertions.assertEquals(fields.get("quantity").getAsDouble(), actual.getQuantity(),
                "Incorrect quantity");
        Assertions.assertEquals(fields.get("price_currency").getAsString(),
                actual.getPriceCurrency(), "Incorrect currency");
        Assertions.assertEquals(fields.get("price").getAsDouble(), actual.getPrice().doubleValue(),
                1e-10, "Incorrect price");
        Assertions.assertEquals(fields.get("part").getAsInt(), actual.getPart(), "Incorrect part");

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
