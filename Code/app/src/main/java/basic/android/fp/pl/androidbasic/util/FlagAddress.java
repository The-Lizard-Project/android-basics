package basic.android.fp.pl.androidbasic.util;

public class FlagAddress {

	public static String obtainAddress(Currency currency) {
		return "http://10.57.149.37:8087/" + currency.getCountry().toLowerCase().replace(" ", "") + ".png";
	}
}