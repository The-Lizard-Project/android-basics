package basic.android.fp.pl.androidbasic.util;

import java.util.ArrayList;
import java.util.List;

import basic.android.fp.pl.androidbasic.model.ExchangeRate;
import basic.android.fp.pl.androidbasic.model.RatesList;

public class MockData {

	private static RatesList ratesList;

	public static RatesList getListOfRates() {
		if (ratesList == null) {
			List<ExchangeRate> rates = new ArrayList<>();
			rates.add(new ExchangeRate("Dollar", "Australia", 0.3431f));
			rates.add(new ExchangeRate("Lev", "Bulgaria", 0.4724f));
			rates.add(new ExchangeRate("Real", "Brazil", 0.7974f));
			rates.add(new ExchangeRate("Dollar", "Canada", 0.3326f));
			rates.add(new ExchangeRate("Franc", "Switzerland", 0.2584f));
			rates.add(new ExchangeRate("Yuan Renminbi", "China", 1.676f));
			rates.add(new ExchangeRate("Koruna", "Czech Republic", 6.6242f));
			rates.add(new ExchangeRate("Krone", "Denmark", 1.8007f));
			rates.add(new ExchangeRate("Pound", "United Kingdom", 0.1752f));
			rates.add(new ExchangeRate("Dollar", "Hong Kong", 2.0737f));
			rates.add(new ExchangeRate("Kuna", "Croatia", 1.85f));
			rates.add(new ExchangeRate("Forint", "Hungary", 73.764f));
			rates.add(new ExchangeRate("Rupiah", "Indonesia", 3469.59f));
			rates.add(new ExchangeRate("Shekel", "Israel", 1.0694f));
			rates.add(new ExchangeRate("Rupee", "India", 16.646f));
			rates.add(new ExchangeRate("Yen", "Japan", 32.152f));
			rates.add(new ExchangeRate("Won", "Korea (South)", 294.43f));
			rates.add(new ExchangeRate("Peso", "Mexico", 4.0236f));
			rates.add(new ExchangeRate("Ringgit", "Malaysia", 0.9763f));
			rates.add(new ExchangeRate("Krone", "Norway", 2.0644f));
			rates.add(new ExchangeRate("Dollar", "New Zealand", 0.357f));
			rates.add(new ExchangeRate("Peso", "Philippines", 11.8f));
			rates.add(new ExchangeRate("New Leu", "Romania", 1.0738f));
			rates.add(new ExchangeRate("Ruble", "Russia", 16.332f));
			rates.add(new ExchangeRate("Krona", "Sweden", 2.2258f));
			rates.add(new ExchangeRate("Dollar", "Singapore", 0.3661f));
			rates.add(new ExchangeRate("Baht", "Thailand", 8.6685f));
			rates.add(new ExchangeRate("Lira", "Turkey", 0.6924f));
			rates.add(new ExchangeRate("Rand", "South Africa", 3.1443f));
			rates.add(new ExchangeRate("Euro", "Euro Member", 0.2416f));
			ratesList = new RatesList("2015-03-07", new ExchangeRate("Zloty", "Poland", 0f), rates);
		}
		return ratesList;
	}
}