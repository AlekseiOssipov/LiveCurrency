package com.example.livecurrency;

public class Currency {
    private String currency;
    private String rate;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String toString() {
        return "Валюта: " + currency + " cегодня по курсу - " + rate;
    }
}
