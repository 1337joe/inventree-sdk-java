package com.w3asel.inventree.api;


import com.w3asel.inventree.client.ApiException;
import com.w3asel.inventree.java.ExtendedUser;
import com.w3asel.inventree.java.Group;
import com.w3asel.inventree.java.MeUser;
import com.w3asel.inventree.java.UserDetails;
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
    public void userMeRetrieve_root() throws ApiException {
        MeUser user = api.userMeRetrieve();
        Assertions.assertNotNull(user);
        Assertions.assertEquals("root", user.getUsername(), "Incorrect username returned");
        Assertions.assertEquals("", user.getFirstName(), "Incorrect first name returned");
        Assertions.assertEquals("", user.getLastName(), "Incorrect last name returned");
        Assertions.assertTrue(user.getEmail().startsWith("root@"), "Incorrect email returned");
        Assertions.assertEquals(9, user.getPk(), "Incorrect pk returned");
        Assertions.assertEquals(0, user.getGroups().size(), "Incorrect groups returned");
    }

    @Test
    public void userRetrieve_reader() throws ApiException {
        ExtendedUser user = api.userRetrieve("2"); // TODO why is this a string, regex only allows digits?
        Assertions.assertNotNull(user);
        Assertions.assertEquals(2, user.getPk(), "Incorrect pk returned");
        Assertions.assertEquals("reader", user.getUsername(), "Incorrect username returned");
        Assertions.assertEquals("Ronald", user.getFirstName(), "Incorrect first name returned");
        Assertions.assertEquals("Reader", user.getLastName(), "Incorrect last name returned");
        Assertions.assertEquals("", user.getEmail(), "Incorrect email returned");
        List<Group> groups = user.getGroups();
        List<String> actualGroupNames = groups.stream().map(Group::getName).collect(Collectors.toList());
        List<String> expectedGroups = Arrays.asList("readers");
        Assertions.assertIterableEquals(expectedGroups, actualGroupNames, "Incorrect group names returned");
        Assertions.assertFalse(user.getIsStaff(), "Incorrect isStaff returned");
        Assertions.assertFalse(user.getIsSuperuser(), "Incorrect isSuperuser returned");
        Assertions.assertTrue(user.getIsActive(), "Incorrect isActive returned");
    }

//    @Test
    public void userList_unfiltered() throws ApiException {
        api.userList(null, null, null, null, null, null, null);
    }
}
