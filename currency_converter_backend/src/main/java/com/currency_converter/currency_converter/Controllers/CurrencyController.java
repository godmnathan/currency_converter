package com.currency_converter.currency_converter.Controllers;

import com.currency_converter.currency_converter.Classes.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @GetMapping("/convert")
    @CrossOrigin
    public String convertCurrency(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam double amount) {

        return currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount);
    }
}
