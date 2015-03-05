package basic.android.fp.pl.androidbasic.util;

import java.util.HashMap;
import java.util.Map;

import basic.android.fp.pl.androidbasic.model.RatesList;

public class MockData {

	static RatesList ratesList;

	public static RatesList getListOfRates() {
		if (ratesList == null) {
			Map<String, Double> rates = new HashMap<String, Double>();
			rates.put("AUD", 0.3431);
			rates.put("BGN", 0.4724);
			rates.put("BRL", 0.7974);
			rates.put("CAD", 0.3326);
			rates.put("CHF", 0.2584);
			rates.put("CNY", 1.676);
			rates.put("CZK", 6.6242);
			rates.put("DKK", 1.8007);
			rates.put("GBP", 0.1752);
			rates.put("HKD", 2.0737);
			rates.put("HRK", 1.85);
			rates.put("HUF", 73.764);
			rates.put("IDR", 3469.59);
			rates.put("ILS", 1.0694);
			rates.put("INR", 16.646);
			rates.put("JPY", 32.152);
			rates.put("KRW", 294.43);
			rates.put("MXN", 4.0236);
			rates.put("MYR", 0.9763);
			rates.put("NOK", 2.0644);
			rates.put("NZD", 0.357);
			rates.put("PHP", 11.8);
			rates.put("RON", 1.0738);
			rates.put("RUB", 16.332);
			rates.put("SEK", 2.2258);
			rates.put("SGD", 0.3661);
			rates.put("THB", 8.6685);
			rates.put("TRY", 0.6924);
			rates.put("USD", 0.2674);
			rates.put("ZAR", 3.1443);
			rates.put("EUR", 0.2416);
			ratesList = new RatesList("2015-03-05", Currency.PLN, rates);
		}
		return ratesList;
	}
}
