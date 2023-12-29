package com.currency_converter.currency_converter.REST;

import java.util.Map;

public class ExchangeRateResponse {

    private Map<String, Double> Data;

    public Map<String, Double> getData() {
        return Data;
    }

    public void setData(Map<String, Double> rates) {
        this.Data = rates;
    }

}
