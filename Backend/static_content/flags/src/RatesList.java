package basic.android.fp.pl.androidbasic.model;

import java.util.List;

import basic.android.fp.pl.androidbasic.util.Currency;

public class RatesList {

	private String date;
	private Currency base;
	private List<ExchangeRate> rates;

	public RatesList(String date, Currency base, List<ExchangeRate> exchangeRates) {
		this.date = date;
		this.base = base;
		this.rates = exchangeRates;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Currency getBase() {
		return base;
	}

	public void setBase(Currency base) {
		this.base = base;
	}

	public List<ExchangeRate> getExchangeRates() {
		return rates;
	}

	public void setExchangeRates(List<ExchangeRate> exchangeRates) {
		this.rates = exchangeRates;
	}
}
