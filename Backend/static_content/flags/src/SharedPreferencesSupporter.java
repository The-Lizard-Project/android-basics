package basic.android.fp.pl.androidbasic.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import basic.android.fp.pl.androidbasic.model.ExchangeRate;

public class SharedPreferencesSupporter {

	private static final String CURRENCY_MAIN_KEY = SharedPreferencesSupporter.class.getName() + ".currency";
	private static final String NAME = ".name";
	private static final String AVERAGE_RATE = ".averageRate";

	public static ExchangeRate loadCurrentRate(Context context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		double averageRate = preferences.getFloat(CURRENCY_MAIN_KEY + AVERAGE_RATE, 1f);
		String name = preferences.getString(CURRENCY_MAIN_KEY + NAME, Currency.PLN.toString());
		return new ExchangeRate(Currency.valueOf(name), averageRate);
	}

	public static void saveCurrentRate(ExchangeRate exchangeRate, Context context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putFloat(CURRENCY_MAIN_KEY + AVERAGE_RATE, (float) exchangeRate.getRate().doubleValue());
		editor.putString(CURRENCY_MAIN_KEY + NAME, exchangeRate.getCurrency().toString());
		editor.apply();
	}
}