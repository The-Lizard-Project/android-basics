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
			rates.add(new ExchangeRate(Currency.AUD, 0.3431));
			rates.add(new ExchangeRate(Currency.BGN, 0.4724));
			rates.add(new ExchangeRate(Currency.BRL, 0.7974));
			rates.add(new ExchangeRate(Currency.CAD, 0.3326));
			rates.add(new ExchangeRate(Currency.CHF, 0.2584));
			rates.add(new ExchangeRate(Currency.CNY, 1.676));
			rates.add(new ExchangeRate(Currency.CZK, 6.6242));
			rates.add(new ExchangeRate(Currency.DKK, 1.8007));
			rates.add(new ExchangeRate(Currency.GBP, 0.1752));
			rates.add(new ExchangeRate(Currency.HKD, 2.0737));
			rates.add(new ExchangeRate(Currency.HRK, 1.85));
			rates.add(new ExchangeRate(Currency.HUF, 73.764));
			rates.add(new ExchangeRate(Currency.IDR, 3469.59));
			rates.add(new ExchangeRate(Currency.ILS, 1.0694));
			rates.add(new ExchangeRate(Currency.INR, 16.646));
			rates.add(new ExchangeRate(Currency.JPY, 32.152));
			rates.add(new ExchangeRate(Currency.KRW, 294.43));
			rates.add(new ExchangeRate(Currency.MXN, 4.0236));
			rates.add(new ExchangeRate(Currency.MYR, 0.9763));
			rates.add(new ExchangeRate(Currency.NOK, 2.0644));
			rates.add(new ExchangeRate(Currency.NZD, 0.357));
			rates.add(new ExchangeRate(Currency.PHP, 11.8));
			rates.add(new ExchangeRate(Currency.RON, 1.0738));
			rates.add(new ExchangeRate(Currency.RUB, 16.332));
			rates.add(new ExchangeRate(Currency.SEK, 2.2258));
			rates.add(new ExchangeRate(Currency.SGD, 0.3661));
			rates.add(new ExchangeRate(Currency.THB, 8.6685));
			rates.add(new ExchangeRate(Currency.TRY, 0.6924));
			rates.add(new ExchangeRate(Currency.USD, 0.2674));
			rates.add(new ExchangeRate(Currency.ZAR, 3.1443));
			rates.add(new ExchangeRate(Currency.EUR, 0.2416));
			ratesList = new RatesList("2015-03-07", Currency.PLN, rates);
		}
		return ratesList;
	}
}
