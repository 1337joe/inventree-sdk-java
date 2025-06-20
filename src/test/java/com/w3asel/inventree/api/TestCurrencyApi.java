package com.w3asel.inventree.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.CurrencyExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TestCurrencyApi extends TestApi {
    private CurrencyApi api;

    @BeforeEach
    void setup() {
        api = new CurrencyApi(apiClient);
    }

    @Test
    void testCurrencyCodeSerialization() throws ApiException {
        // comma-separated list
        new SettingsApi(apiClient).settingsGlobalRetrieve("CURRENCY_CODES").getValue();
        // needs to not break on CAD (which isn't in the default list)
        new CompanyApi(apiClient).companyPriceBreakList(100, null, null, null, null, null, null,
                null);
    }

    @Test
    void currencyExchangeRetrieve() throws ApiException {
        // TODO don't like having a custom time format here... (also nanosecond time parsing might
        // read wrong since only micros are provided)
        apiClient.setOffsetDateTimeFormat(DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnz").withZone(ZoneOffset.UTC));

        CurrencyExchange actual = api.currencyExchangeRetrieve();
        assertNotNull(actual);
    }

    @Disabled
    @Test
    void currencyRefreshCreate() throws ApiException {
        api.currencyRefreshCreate();
    }
}
