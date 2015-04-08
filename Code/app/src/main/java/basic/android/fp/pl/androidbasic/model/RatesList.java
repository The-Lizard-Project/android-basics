package basic.android.fp.pl.androidbasic.model;

import java.util.List;

public class RatesList {

	private final String date;
	private final ExchangeRate base;
	private final List<ExchangeRate> rates;

	public RatesList(String date, ExchangeRate base, List<ExchangeRate> exchangeRates) {
		this.date = date;
		this.base = base;
		this.rates = exchangeRates;
	}

	public List<ExchangeRate> getExchangeRates() {
		return rates;
	}
}