package com.w3asel.inventree.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.w3asel.inventree.invoker.ApiException;
import com.w3asel.inventree.model.CurrencyExchange;

public class TestCurrencyApi extends TestApi {
    private CurrencyApi api;

    @BeforeEach
    public void setup() {
        api = new CurrencyApi(apiClient);
    }

    @Test
    public void currencyExchangeRetrieve() throws ApiException {
        CurrencyExchange actual = api.currencyExchangeRetrieve();
        Assertions.assertNotNull(actual);
    }
}
