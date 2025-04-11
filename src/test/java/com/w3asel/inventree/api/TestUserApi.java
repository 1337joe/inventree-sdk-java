package com.w3asel.inventree.api;

import com.google.gson.JsonObject;
import com.w3asel.inventree.InventreeDemoDataset;
import com.w3asel.inventree.InventreeDemoDataset.Model;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.ExtendedUser;
import com.w3asel.inventree.model.Group;
import com.w3asel.inventree.model.MeUser;
import com.w3asel.inventree.model.Owner;
import com.w3asel.inventree.model.PaginatedUserCreateList;
import com.w3asel.inventree.model.Role;
import com.w3asel.inventree.model.UserCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import java.util.stream.Collectors;

public class TestUserApi extends TestApi {

    private UserApi api;

    @BeforeEach
    public void setup() {
        api = new UserApi(apiClient);
    }

    @Disabled
    @Test
    public void todo() throws ApiException {
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
        // api.userRetrieve(null);
        api.userRolesRetrieve();
        api.userTokenRetrieve();
        api.userTokensDestroy();
        api.userTokensDestroy2(null);
        api.userTokensList(null, null);
        api.userTokensList2(null, null, null);
        api.userUpdate(null, null);
    }

    @Test
    public void userGroupRetrieve_readers() throws ApiException {
        Group actual = api.userGroupRetrieve(1);
        Assertions.assertNotNull(actual);
    }

    @Test
    public void userMeRetrieve_admin() throws ApiException {
        MeUser actual = api.userMeRetrieve();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.getPk(), "Incorrect pk returned");
        Assertions.assertEquals("admin", actual.getUsername(), "Incorrect username returned");
        Assertions.assertEquals("", actual.getFirstName(), "Incorrect first name returned");
        Assertions.assertEquals("", actual.getLastName(), "Incorrect last name returned");
        Assertions.assertEquals("", actual.getEmail(), "Incorrect email returned");
        Assertions.assertEquals(0, actual.getGroups().size(), "Incorrect groups returned");
        Assertions.assertTrue(actual.getIsStaff(), "Incorrect isStaff returned");
        Assertions.assertTrue(actual.getIsSuperuser(), "Incorrect isSuperuser returned");
        Assertions.assertTrue(actual.getIsActive(), "Incorrect isActive returned");
    }

    private static void assertExtendedUserEquals(JsonObject expected, ExtendedUser actual) {
        InventreeDemoDataset.assertEquals(InventreeDemoDataset.PRIMARY_KEY_KEY, expected,
                actual.getPk());

        JsonObject fields = InventreeDemoDataset.getFields(expected);

        InventreeDemoDataset.assertEquals("is_superuser", fields, actual.getIsSuperuser());
        InventreeDemoDataset.assertEquals("username", fields, actual.getUsername());
        InventreeDemoDataset.assertEquals("first_name", fields, actual.getFirstName());
        InventreeDemoDataset.assertEquals("last_name", fields, actual.getLastName());
        InventreeDemoDataset.assertEquals("email", fields, actual.getEmail());
        InventreeDemoDataset.assertEquals("is_staff", fields, actual.getIsStaff());
        InventreeDemoDataset.assertEquals("is_active", fields, actual.getIsActive());

        List<Group> groups = actual.getGroups();
        List<String> actualGroupNames =
                groups.stream().map(Group::getName).collect(Collectors.toList());
        InventreeDemoDataset.assertNullableEquals(Iterable.class, "groups", fields,
                actualGroupNames);

        // not directly available in demo dataset:
        // actual.getProfile()

        // not directly available from server response:
        // "last_login"
        // "date_joined"
        // "user_permissions"
    }

    @Test
    public void userList() throws ApiException {
        List<JsonObject> expectedList = InventreeDemoDataset.getObjects(Model.USER, null);
        Assertions.assertTrue(expectedList.size() > 0, "Expected demo data");

        int limit = 10;
        int offset = 0;

        PaginatedUserCreateList actual = api.userList(limit, null, null, null, offset, null, null);
        // add one for automatically created root@localhost user
        Assertions.assertEquals(expectedList.size(), actual.getCount(),
                "Incorrect total user count");
        List<UserCreate> actualList = actual.getResults();

        // check items returned by key
        List<Integer> expectedPks = expectedList.stream()
                .map(json -> json.get(InventreeDemoDataset.PRIMARY_KEY_KEY).getAsInt()).sorted()
                .toList();
        List<Integer> actualPks = actualList.stream().map(c -> c.getPk()).sorted().toList();
        Assertions.assertTrue(expectedPks.containsAll(actualPks), "Incorrect primary keys");

        // TODO validate - type is UserCreate, not ExtendedUser for some reason

        // deep equals on first value
        // UserCreate actualFirst = actualList.get(0);
        // JsonObject expectedFirst =
        // InventreeDemoDataset.getObjects(Model.USER, actualFirst.getPk()).get(0);
        // assertExtendedUserEquals(expectedFirst, actualFirst);
    }

    @Test
    public void userOwnerRetrieve_readersGroup() throws ApiException {
        Owner actual = api.userOwnerRetrieve(1);
        Assertions.assertNotNull(actual);
    }

    @ParameterizedTest
    @CsvSource({"1", "2"})
    public void userRetrieve(int pk) throws ApiException {
        ExtendedUser actual = api.userRetrieve(pk);
        JsonObject expected = InventreeDemoDataset.getObjects(Model.USER, pk).get(0);
        assertExtendedUserEquals(expected, actual);
    }

    @Test
    public void userRolesRetrieve_root() throws ApiException {
        Role actual = api.userRolesRetrieve();
        Assertions.assertNotNull(actual);
    }

    @Test
    public void userTokenRetrieve() throws ApiException {
        api.userTokenRetrieve();
    }
}
