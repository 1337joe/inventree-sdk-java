package com.w3asel.inventree;

import com.w3asel.inventree.api.OrderApi;
import com.w3asel.inventree.invoker.ApiClient;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.invoker.ServerConfiguration;
import com.w3asel.inventree.model.PaginatedSalesOrderList;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

public class TestClient {
    private static final String INVENTREE_PROPERTIES = "inventree.properties";

    @Test
    public void test() throws ApiException {
        // Load properties file
        final InputStream is =
                TestClient.class.getClassLoader().getResourceAsStream(INVENTREE_PROPERTIES);
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (final IOException e) {
            System.err.printf("Unable to load %s", INVENTREE_PROPERTIES);
        }

        // Construct invoker
        ApiClient client = new ApiClient();
        client.setUsername(properties.getProperty("username"));
        client.setPassword(properties.getProperty("password"));
        client.setLenientOnJson(true);
        ServerConfiguration server = new ServerConfiguration(
                properties.getProperty("server_url", "http://localhost:8000"), "",
                Collections.emptyMap());
        client.setServers(Collections.singletonList(server));

        // Construct and call API
        OrderApi api = new OrderApi(client);

        int limit = 5;
        PaginatedSalesOrderList salesOrders = api.orderSoList(limit, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null);
        System.out.println("Fetched " + salesOrders.getResults().size() + " of "
                + salesOrders.getCount() + " Sales Orders");

        // SalesOrder result = api.orderSoRetrieve(11);
        // System.out.println(result.getReference());
    }
}
