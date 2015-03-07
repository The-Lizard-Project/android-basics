package basic.android.fp.pl.androidbasic.util;

import java.util.ArrayList;
import java.util.List;

import basic.android.fp.pl.androidbasic.model.ExchangeRate;
import basic.android.fp.pl.androidbasic.model.RatesList;

public class MockData {

	static RatesList ratesList;

	public static RatesList getListOfRates() {
		if (ratesList == null) {
			List<ExchangeRate> rates = new ArrayList<ExchangeRate>();
			rates.add(new ExchangeRate(Currency.AUD, 0.3431f));
			rates.add(new ExchangeRate(Currency.BGN, 0.4724f));
			rates.add(new ExchangeRate(Currency.BRL, 0.7974f));
			rates.add(new ExchangeRate(Currency.CAD, 0.3326f));
			rates.add(new ExchangeRate(Currency.CHF, 0.2584f));
			rates.add(new ExchangeRate(Currency.CNY, 1.676f));
			rates.add(new ExchangeRate(Currency.CZK, 6.6242f));
			rates.add(new ExchangeRate(Currency.DKK, 1.8007f));
			rates.add(new ExchangeRate(Currency.GBP, 0.1752f));
			rates.add(new ExchangeRate(Currency.HKD, 2.0737f));
			rates.add(new ExchangeRate(Currency.HRK, 1.85f));
			rates.add(new ExchangeRate(Currency.HUF, 73.764f));
			rates.add(new ExchangeRate(Currency.IDR, 3469.59f));
			rates.add(new ExchangeRate(Currency.ILS, 1.0694f));
			rates.add(new ExchangeRate(Currency.INR, 16.646f));
			rates.add(new ExchangeRate(Currency.JPY, 32.152f));
			rates.add(new ExchangeRate(Currency.KRW, 294.43f));
			rates.add(new ExchangeRate(Currency.MXN, 4.0236f));
			rates.add(new ExchangeRate(Currency.MYR, 0.9763f));
			rates.add(new ExchangeRate(Currency.NOK, 2.0644f));
			rates.add(new ExchangeRate(Currency.NZD, 0.357f));
			rates.add(new ExchangeRate(Currency.PHP, 11.8f));
			rates.add(new ExchangeRate(Currency.RON, 1.0738f));
			rates.add(new ExchangeRate(Currency.RUB, 16.332f));
			rates.add(new ExchangeRate(Currency.SEK, 2.2258f));
			rates.add(new ExchangeRate(Currency.SGD, 0.3661f));
			rates.add(new ExchangeRate(Currency.THB, 8.6685f));
			rates.add(new ExchangeRate(Currency.TRY, 0.6924f));
			rates.add(new ExchangeRate(Currency.USD, 0.2674f));
			rates.add(new ExchangeRate(Currency.ZAR, 3.1443f));
			rates.add(new ExchangeRate(Currency.EUR, 0.2416f));
			ratesList = new RatesList("2015-03-07", Currency.PLN, rates);
		}
		return ratesList;
	}
}
