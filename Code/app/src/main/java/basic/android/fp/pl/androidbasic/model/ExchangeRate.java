package basic.android.fp.pl.androidbasic.model;

import java.io.Serializable;

public class ExchangeRate implements Serializable {

	private final String currency;
	private final String country;
	private Float rate;

	public ExchangeRate(String currency, String country, Float rate) {
		this.currency = currency;
		this.country = country;
		this.rate = rate;
	}

	public String getCurrency() {
		return currency;
	}

	public String getCountry() {
		return country;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}
}