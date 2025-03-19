package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.ExtendedUser;
import com.w3asel.inventree.model.Group;
import com.w3asel.inventree.model.MeUser;
import com.w3asel.inventree.model.Owner;
import com.w3asel.inventree.model.PaginatedUserCreateList;
import com.w3asel.inventree.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestUserApi extends TestApi {

    private UserApi api;

    @BeforeEach
    public void setup() {
        api = new UserApi(apiClient);
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

    @Test
    public void userRolesRetrieve_root() throws ApiException {
        Role actual = api.userRolesRetrieve();
        Assertions.assertNotNull(actual);
    }

    @Test
    public void userRetrieve_admin() throws ApiException {
        ExtendedUser actual = api.userRetrieve("1"); // TODO why is this a string, regex only allows
                                                     // digits?
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.getPk(), "Incorrect pk returned");
        Assertions.assertEquals("admin", actual.getUsername(), "Incorrect username returned");
        Assertions.assertEquals("", actual.getFirstName(), "Incorrect first name returned");
        Assertions.assertEquals("", actual.getLastName(), "Incorrect last name returned");
        Assertions.assertEquals("", actual.getEmail(), "Incorrect email returned");
        Assertions.assertEquals(0, actual.getGroups().size(), "Incorrect group count returned");
        Assertions.assertTrue(actual.getIsStaff(), "Incorrect isStaff returned");
        Assertions.assertTrue(actual.getIsSuperuser(), "Incorrect isSuperuser returned");
        Assertions.assertTrue(actual.getIsActive(), "Incorrect isActive returned");
    }

    @Test
    public void userRetrieve_reader() throws ApiException {
        ExtendedUser actual = api.userRetrieve("2"); // TODO why is this a string, regex only allows
                                                     // digits?
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.getPk(), "Incorrect pk returned");
        Assertions.assertEquals("reader", actual.getUsername(), "Incorrect username returned");
        Assertions.assertEquals("Ronald", actual.getFirstName(), "Incorrect first name returned");
        Assertions.assertEquals("Reader", actual.getLastName(), "Incorrect last name returned");
        Assertions.assertEquals("", actual.getEmail(), "Incorrect email returned");
        List<Group> groups = actual.getGroups();
        List<String> actualGroupNames =
                groups.stream().map(Group::getName).collect(Collectors.toList());
        List<String> expectedGroups = Arrays.asList("readers");
        Assertions.assertIterableEquals(expectedGroups, actualGroupNames,
                "Incorrect group names returned");
        Assertions.assertFalse(actual.getIsStaff(), "Incorrect isStaff returned");
        Assertions.assertFalse(actual.getIsSuperuser(), "Incorrect isSuperuser returned");
        Assertions.assertTrue(actual.getIsActive(), "Incorrect isActive returned");
    }

    @Test
    public void userList_paginated() throws ApiException {
        int limit = 5;
        int offset = 0;
        PaginatedUserCreateList actual = api.userList(limit, null, null, null, offset, null, null);
        Assertions.assertNotNull(actual);
    }

    @Test
    public void userGroupRetrieve_readers() throws ApiException {
        Group actual = api.userGroupRetrieve("1"); // TODO string
        Assertions.assertNotNull(actual);
    }

    @Test
    public void userOwnerRetrieve_readersGroup() throws ApiException {
        Owner actual = api.userOwnerRetrieve(1);
        Assertions.assertNotNull(actual);
    }

    @Test
    public void userTokenList() throws ApiException {
        int limit = 100;
        api.userTokensList(limit, null, null, null);
    }
}
