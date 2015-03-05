package basic.android.fp.pl.androidbasic.util;

public class FlagAddress {

	public static String obtainAddress(Currency currency) {
		return "http://www.currencysymbols.in/flags/" + currency.getCountry().toLowerCase().replace(" ", "") + ".png";
	}
}
