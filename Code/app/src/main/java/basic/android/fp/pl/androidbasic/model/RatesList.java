package basic.android.fp.pl.androidbasic.model;

import java.util.Map;

import basic.android.fp.pl.androidbasic.util.Currency;

public class RatesList {

	private String date;
	private Currency base;
	private Map<String, Double> rates;

	public RatesList(String date, Currency base, Map<String, Double> rates) {
		this.date = date;
		this.base = base;
		this.rates = rates;
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

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
}
