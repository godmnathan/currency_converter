import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-currency-converter',
  templateUrl: './currency-converter.component.html',
  styleUrls: ['./currency-converter.component.css']
})
export class CurrencyConverterComponent implements OnInit {

  currencyResponse: any;
  conversionForm: FormGroup;

  constructor(private http: HttpClient, private fb: FormBuilder) {
    this.conversionForm = this.fb.group({
      fromCurrency: ['USD', Validators.required],
      toCurrency: ['BRL', Validators.required],
      amount: [100, [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    // Fetch initial data on component initialization
    this.convertCurrency();
  }

  convertCurrency(): void {
    const { fromCurrency, toCurrency, amount } = this.conversionForm.value;

    let response = this.http.get(
      `http://localhost:8080/api/currency/convert?fromCurrency=${fromCurrency}&toCurrency=${toCurrency}&amount=${amount}`,
      { responseType: 'text' }
    );
    
    response.subscribe(
      (data) => this.currencyResponse = data,
      (error) => {
        this.currencyResponse = 'Error fetching data';
        console.error('Error fetching data: ', error);
      }
    );
  }
}
