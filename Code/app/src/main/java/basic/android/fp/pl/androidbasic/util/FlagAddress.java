package basic.android.fp.pl.androidbasic.util;

public class FlagAddress {

	public static String obtainAddress(Currency currency) {
		return "http://192.168.0.11:8087/" + currency.getCountry().toLowerCase().replace(" ", "") + ".png";
	}
}