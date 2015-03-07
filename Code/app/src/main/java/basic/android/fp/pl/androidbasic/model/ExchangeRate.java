package basic.android.fp.pl.androidbasic.model;

import java.io.Serializable;

import basic.android.fp.pl.androidbasic.util.Currency;

public class ExchangeRate implements Serializable {

	private final Currency currency;
	private Float rate;

	public ExchangeRate(Currency currency, Float rate) {
		this.currency = currency;
		this.rate = rate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}
}