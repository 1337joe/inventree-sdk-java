package com.w3asel.inventree.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.UserDetails;

public class TestAuthApi extends TestApi {

    private AuthApi api;

    @BeforeEach
    public void setup() {
        api = new AuthApi(apiClient);
    }

    @Test
    public void authUserRetrieve_admin() throws ApiException {
        UserDetails user = api.authUserRetrieve();
        Assertions.assertNotNull(user);
        Assertions.assertEquals("admin", user.getUsername(), "Incorrect username returned");
        Assertions.assertEquals("", user.getFirstName(), "Incorrect first name returned");
        Assertions.assertEquals("", user.getLastName(), "Incorrect last name returned");
        Assertions.assertTrue(user.getEmail().startsWith("root@"), "Incorrect email returned");
        Assertions.assertEquals(9, user.getPk(), "Incorrect pk returned");
    }
}
