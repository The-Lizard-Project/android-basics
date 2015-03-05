package basic.android.fp.pl.androidbasic.model;

import basic.android.fp.pl.androidbasic.util.Currency;

public class Rate {

	private final Currency currency;
	private final Double rate;

	public Rate(Currency currency, Double rate) {
		this.currency = currency;
		this.rate = rate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Double getRate() {
		return rate;
	}
}
