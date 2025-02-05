package com.w3asel.inventree.api;

import com.w3asel.inventree.client.ApiClient;
import com.w3asel.inventree.client.ServerConfiguration;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

public abstract class TestApi {
    private static final String INVENTREE_PROPERTIES = "inventree.properties";

    protected static ApiClient apiClient;

    @BeforeAll
    public static void setUp() {
        final InputStream is = TestApi.class.getClassLoader().getResourceAsStream(INVENTREE_PROPERTIES);
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (final IOException e) {
            System.err.printf("Unable to load %s", INVENTREE_PROPERTIES);
        }

        apiClient = new ApiClient();
        apiClient.setUsername(properties.getProperty("username"));
        apiClient.setPassword(properties.getProperty("password"));
        apiClient.setLenientOnJson(true);
        ServerConfiguration server =
                new ServerConfiguration(properties.getProperty("server_url", "http://localhost:8000"),
                        "",
                        Collections.EMPTY_MAP);
        apiClient.setServers(Collections.singletonList(server));
    }

}
