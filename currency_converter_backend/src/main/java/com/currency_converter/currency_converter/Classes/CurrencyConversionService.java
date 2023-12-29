package com.currency_converter.currency_converter.Classes;

import com.currency_converter.currency_converter.REST.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConversionService {

    @Value("${exchange.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;

    public CurrencyConversionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String convertCurrency(String fromCurrency, String toCurrency, double amount) {
        try {
            // Make the GET request to the external API
            ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(apiUrl, ExchangeRateResponse.class);

            // Extract exchange rates from the response
            ExchangeRateResponse exchangeRatesResponse = response.getBody();

            if (exchangeRatesResponse != null && exchangeRatesResponse.getData() != null) {
                // Check if the currencies are valid
                if (!exchangeRatesResponse.getData().containsKey(fromCurrency) || !exchangeRatesResponse.getData().containsKey(toCurrency)) {
                    return "Invalid currency code(s).";
                }

                // Convert the specified amount from one currency to another
                double exchangeRateFrom = exchangeRatesResponse.getData().get(fromCurrency);
                double exchangeRateTo = exchangeRatesResponse.getData().get(toCurrency);
                double convertedAmount = amount * (exchangeRateTo / exchangeRateFrom);

                return "Converted amount: " + convertedAmount + " " + toCurrency;
            } else {
                // Handle the case when the response or data is null
                return "Error retrieving exchange rates: Null response or data";
            }
        } catch (HttpClientErrorException e) {
            // Handle HTTP client errors (e.g., 404, 500)
            return "Error retrieving exchange rates HTTP client: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString();
        } catch (Exception e) {
            // Handle other exceptions
            return "Error retrieving exchange rates: " + e.getMessage();
        }
    }

    // Additional method for converting currency with parameters
    public String convertCurrencyWithParameters(String fromCurrency, String toCurrency, double amount) {
        // Call the existing method with parameters
        return convertCurrency(fromCurrency, toCurrency, amount);
    }
}
