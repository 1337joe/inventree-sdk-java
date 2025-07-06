package com.w3asel.inventree.api;

import static com.w3asel.inventree.InventreeDemoDataset.assertFieldEquals;
import static com.w3asel.inventree.InventreeDemoDataset.assertNullableFieldEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.ApiToken;
import com.w3asel.inventree.model.ExtendedUser;
import com.w3asel.inventree.model.GetAuthToken;
import com.w3asel.inventree.model.Group;
import com.w3asel.inventree.model.MeUser;
import com.w3asel.inventree.model.Owner;
import com.w3asel.inventree.model.PaginatedApiTokenList;
import com.w3asel.inventree.model.PaginatedUserCreateList;
import com.w3asel.inventree.model.Role;
import com.w3asel.inventree.model.UserCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import java.util.stream.Collectors;

public class TestUserApi extends TestApi {

    private UserApi api;

    @BeforeEach
    void setup() {
        api = new UserApi(apiClient);
    }

    @Disabled
    @Test
    void todo() throws ApiException {
        api.userCreate(null);
        api.userDestroy(null);
        api.userGroupCreate(null);
        api.userGroupDestroy(null);
        api.userGroupList(null, null, null, null);
        api.userGroupPartialUpdate(null, null);
        api.userGroupRetrieve(null);
        api.userGroupUpdate(null, null);
        // api.userList(null, null, null, null, null, null, null);
        api.userMeDestroy();
        api.userMePartialUpdate(null);
        // api.userMeRetrieve();
        api.userMeUpdate(null);
        api.userOwnerList(null, null);
        api.userOwnerRetrieve(null);
        api.userPartialUpdate(null, null);
        api.userProfilePartialUpdate(null);
        api.userProfileRetrieve();
        api.userProfileUpdate(null);
        // api.userRetrieve(null);
        api.userRolesRetrieve();
        api.userRulesetDestroy(null);
        api.userRulesetList(null, null, null, null, null, null);
        api.userRulesetPartialUpdate(null, null);
        api.userRulesetRetrieve(null);
        api.userRulesetUpdate(null, null);
        api.userSetPasswordPartialUpdate(null, null);
        api.userSetPasswordUpdate(null, null);
        // api.userTokenRetrieve(null);
        // api.userTokensCreate(null);
        // api.userTokensDestroy(null);
        // api.userTokensList(null, null, null, null, null, null);
        // api.userTokensRetrieve(null, null);
        api.userUpdate(null, null);
    }

    @Test
    void test() throws ApiException {
        // TODO verify results
        int limit = 1000;
        api.userGroupList(limit, null, null, null);
        api.userOwnerList(limit, null);
        api.userRulesetList(limit, null, null, null, null, null);
    }

    @Test
    void userGroupRetrieve_readers() throws ApiException {
        Group actual = api.userGroupRetrieve(1);
        assertNotNull(actual);
    }

    @Test
    void userMeRetrieve_admin() throws ApiException {
        MeUser actual = api.userMeRetrieve();
        assertNotNull(actual);
        assertEquals(1, actual.getPk(), "Incorrect pk returned");
        assertEquals("admin", actual.getUsername(), "Incorrect username returned");
        assertEquals("Adam", actual.getFirstName(), "Incorrect first name returned");
        assertEquals("Administrator", actual.getLastName(), "Incorrect last name returned");
        assertEquals("admin@demo.inventree.org", actual.getEmail(), "Incorrect email returned");
        assertEquals(0, actual.getGroups().size(), "Incorrect groups returned");
        assertTrue(actual.getIsStaff(), "Incorrect isStaff returned");
        assertTrue(actual.getIsSuperuser(), "Incorrect isSuperuser returned");
        assertTrue(actual.getIsActive(), "Incorrect isActive returned");
    }

    private static void assertExtendedUserEquals(JsonObject expected, ExtendedUser actual) {
        assertFieldEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected, actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        assertFieldEquals("is_superuser", fields, actual.getIsSuperuser());
        assertFieldEquals("username", fields, actual.getUsername());
        assertFieldEquals("first_name", fields, actual.getFirstName());
        assertFieldEquals("last_name", fields, actual.getLastName());
        assertFieldEquals("email", fields, actual.getEmail());
        assertFieldEquals("is_staff", fields, actual.getIsStaff());
        assertFieldEquals("is_active", fields, actual.getIsActive());

        List<Group> groups = actual.getGroups();
        List<String> actualGroupNames =
                groups.stream().map(Group::getName).collect(Collectors.toList());
        assertNullableFieldEquals(Iterable.class, "groups", fields, actualGroupNames);

        // not directly available in demo dataset:
        // actual.getProfile()

        // not directly available from server response:
        // "last_login"
        // "date_joined"
        // "user_permissions"
    }

    @Test
    void userList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.USER, null);
        assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedUserCreateList actual = api.userList(limit, null, null, null, offset, null, null);
        assertEquals(expectedList.size(), actual.getCount(), "Incorrect total user count");
        List<UserCreate> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // TODO validate - type is UserCreate, not ExtendedUser for some reason

        // deep equals on first value
        // UserCreate actualFirst = actualList.get(0);
        // JsonObject expectedFirst =
        // InventreeDemoDataset.getObjects(Model.USER, actualFirst.getPk()).get(0);
        // assertExtendedUserEquals(expectedFirst, actualFirst);
    }

    @Test
    void userOwnerRetrieve_readersGroup() throws ApiException {
        Owner actual = api.userOwnerRetrieve(1);
        assertNotNull(actual);
    }

    @ParameterizedTest
    @CsvSource({"1", "2"})
    void userRetrieve(int pk) throws ApiException {
        ExtendedUser actual = api.userRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.USER, pk).get(0);
        assertExtendedUserEquals(expected, actual);
    }

    @Test
    void userRolesRetrieve_root() throws ApiException {
        Role actual = api.userRolesRetrieve();
        assertNotNull(actual);
    }

    @ResourceLock("userTokens")
    @Test
    void userTokenRetrieve() throws ApiException {
        String tokenName = "test-token-retrieve";

        int limit = 10;
        int offset = 0;

        PaginatedApiTokenList tokenList;
        tokenList = api.userTokensList(limit, offset, null, false, null, null);
        assertEquals(0, tokenList.getCount(), "Expect initial state of no (unrevoked) user tokens");

        GetAuthToken actual = api.userTokenRetrieve(tokenName);
        assertEquals(tokenName, actual.getName(), "Incorrect name");
        // unchecked:
        // actual.getExpiry();
        // actual.getToken();

        tokenList = api.userTokensList(limit, offset, null, false, null, null);
        assertEquals(1, tokenList.getCount(), "Expect newly created token");

        api.userTokensDestroy(tokenList.getResults().get(0).getId());

        tokenList = api.userTokensList(limit, offset, null, false, null, null);
        assertEquals(0, tokenList.getCount(), "Expect token deleted");
    }

    @ResourceLock("userTokens")
    @Test
    void userTokenCreate() throws ApiException {
        String tokenName = "test-token-create";
        ApiToken tokenInput = new ApiToken().name(tokenName);

        int limit = 10;
        int offset = 0;

        PaginatedApiTokenList tokenList;
        tokenList = api.userTokensList(limit, offset, null, false, null, null);
        assertEquals(0, tokenList.getCount(), "Expect initial state of no (unrevoked) user tokens");

        ApiToken actual = api.userTokensCreate(tokenInput);
        assertTrue(actual.getActive(), "Incorrect active state");
        assertNull(actual.getLastSeen(), "Incorrect last seen");
        assertEquals(tokenName, actual.getName(), "Incorrect name");
        assertFalse(actual.getRevoked(), "Incorrect revoked state");
        assertEquals("admin", actual.getUserDetail().getUsername(), "Incorrect username");
        // unchecked:
        // actual.getCreated();
        // actual.getExpiry();
        // actual.getId();
        // actual.getInUse();
        // actual.getToken();
        // actual.getUser();

        tokenList = api.userTokensList(limit, offset, null, false, null, null);
        assertEquals(1, tokenList.getCount(), "Expect newly created token");
        assertEquals(actual.getId(), tokenList.getResults().get(0).getId(),
                "Expected created token");

        api.userTokensDestroy(actual.getId());

        ApiToken deleted = api.userTokensRetrieve(actual.getId(), false);
        assertTrue(deleted.getRevoked(), "Incorrect revoked");
    }
}
