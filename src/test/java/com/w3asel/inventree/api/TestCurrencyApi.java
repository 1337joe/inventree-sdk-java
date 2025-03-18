package com.w3asel.inventree.api;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.CurrencyExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TestCurrencyApi extends TestApi {
    private CurrencyApi api;

    @BeforeEach
    public void setup() {
        api = new CurrencyApi(apiClient);
    }

    @Test
    public void currencyExchangeRetrieve() throws ApiException {
        // TODO don't like having a custom time format here... (also nanosecond time parsing might
        // read wrong since only micros are provided)
        apiClient.setOffsetDateTimeFormat(DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnz").withZone(ZoneOffset.UTC));

        CurrencyExchange actual = api.currencyExchangeRetrieve();
        Assertions.assertNotNull(actual);
    }

    @Disabled
    @Test
    public void currencyRefreshCreate() throws ApiException {
        api.currencyRefreshCreate();
    }
}
