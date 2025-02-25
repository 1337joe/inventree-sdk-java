package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiClient;
import com.w3asel.inventree.invoker.ServerConfiguration;
import org.junit.jupiter.api.BeforeAll;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Properties;

public abstract class TestApi {
    private static final String INVENTREE_PROPERTIES = "inventree.properties";

    protected static ApiClient apiClient;

    @BeforeAll
    public static void setUp() {
        Properties properties = new Properties();
        try (InputStream is =
                TestApi.class.getClassLoader().getResourceAsStream(INVENTREE_PROPERTIES)) {
            properties.load(is);
        } catch (final IOException e) {
            System.err.printf("Unable to load %s", INVENTREE_PROPERTIES);
        }

        apiClient = new ApiClient();
        apiClient.setUsername(properties.getProperty("username", "root"));
        apiClient.setPassword(properties.getProperty("password", "root"));
        apiClient.setLenientOnJson(true);
        ServerConfiguration server = new ServerConfiguration(
                properties.getProperty("server_url", "http://localhost:8000"), "",
                Collections.emptyMap());
        apiClient.setServers(Collections.singletonList(server));

        // apiClient.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        apiClient.setOffsetDateTimeFormat(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneOffset.UTC));
    }
}
