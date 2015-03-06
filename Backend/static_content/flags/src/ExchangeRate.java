package basic.android.fp.pl.androidbasic.model;

import basic.android.fp.pl.androidbasic.util.Currency;

public class ExchangeRate {

	private final Currency currency;
	private Double rate;

	public ExchangeRate(Currency currency, Double rate) {
		this.currency = currency;
		this.rate = rate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}