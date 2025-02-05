package com.w3asel.inventree;

import com.w3asel.inventree.api.AuthApi;
import com.w3asel.inventree.api.OrderApi;
import com.w3asel.inventree.api.UserApi;
import com.w3asel.inventree.client.ApiClient;
import com.w3asel.inventree.client.ApiException;
import com.w3asel.inventree.client.ServerConfiguration;
import com.w3asel.inventree.java.SalesOrder;
import com.w3asel.inventree.java.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

public class TestClient {
    private static final String INVENTREE_PROPERTIES = "inventree.properties";

    @Test
    public void test() {
        final InputStream is = TestClient.class.getClassLoader().getResourceAsStream(INVENTREE_PROPERTIES);
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (final IOException e) {
            System.err.printf("Unable to load %s", INVENTREE_PROPERTIES);
        }

        ApiClient client = new ApiClient();
        client.setUsername(properties.getProperty("username"));
        client.setPassword(properties.getProperty("password"));
        client.setLenientOnJson(true);
        ServerConfiguration server =
                new ServerConfiguration(properties.getProperty("server_url", "http://localhost:8000"),
                        "",
                        Collections.EMPTY_MAP);
        client.setServers(Collections.singletonList(server));

        UserApi userApi = new UserApi(client);
//        userApi.userMeRetrieve();

        OrderApi api = new OrderApi(client);

        try {
            SalesOrder result = api.orderSoRetrieve(11);
            Assertions.fail("" + result.getReference());

        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        Assertions.fail("" + api.getHostIndex());
    }
}
