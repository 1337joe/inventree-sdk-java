package com.w3asel.inventree;

import com.w3asel.inventree.api.OrderApi;
import com.w3asel.inventree.invoker.ApiClient;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.invoker.ServerConfiguration;
import com.w3asel.inventree.model.PaginatedSalesOrderLineItemList;
import com.w3asel.inventree.model.PaginatedSalesOrderList;
import com.w3asel.inventree.model.SalesOrderLineItem;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/** A test class that demonstrates configuring and calling the API. */
public class TestClient {
    private static final String INVENTREE_PROPERTIES = "inventree.properties";

    private ApiClient initializeClient() {
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

        return client;
    }

    @Test
    public void fetchSalesOrder() throws ApiException {
        ApiClient client = initializeClient();

        // Configure date time format to match what's returned by InvenTree
        client.setOffsetDateTimeFormat(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneOffset.UTC));

        // Construct and call API
        OrderApi api = new OrderApi(client);

        // Fetch first sales order (batch of 1)
        int limit = 1;
        int offset = 0;
        PaginatedSalesOrderList salesOrders = api.orderSoList(limit, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, offset, null, null, null,
                null, null, null, null, null, null, null, null, null);
        System.out.printf("Fetched %d of %d sales orders%n", salesOrders.getResults().size(),
                salesOrders.getCount());

        // Fetch all line items for the sales order (multiple pages in batches of 1)
        int targetOrder = salesOrders.getResults().get(0).getPk();
        List<SalesOrderLineItem> lineItems = new ArrayList<>();
        try {
            PaginatedSalesOrderLineItemList lineItemPage;
            do {
                lineItemPage = api.orderSoLineList(limit, null, null, null, lineItems.size(),
                        targetOrder, null, null, null, null, null, null);
                lineItems.addAll(lineItemPage.getResults());
            } while (lineItemPage.getCount() > lineItems.size());
        } catch (ApiException e) {
            System.err.printf("Failed to query API for line items for sales order %d.%n",
                    targetOrder);
            e.printStackTrace();
        }
        System.out.printf("SO %d: Fetched %d line items%n", targetOrder, lineItems.size());
    }
}
