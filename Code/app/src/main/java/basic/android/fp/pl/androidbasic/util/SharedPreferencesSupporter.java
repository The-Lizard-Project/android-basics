package basic.android.fp.pl.androidbasic.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import basic.android.fp.pl.androidbasic.model.ExchangeRate;

public class SharedPreferencesSupporter {

    private static final String CURRENCY_MAIN_KEY = SharedPreferencesSupporter.class.getName();
    private static final String CURRENCY = ".currency";
    private static final String COUNTRY = ".country";
    private static final String AVERAGE_RATE = ".averageRate";

    public static ExchangeRate loadCurrentRate(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        float averageRate = preferences.getFloat(CURRENCY_MAIN_KEY + AVERAGE_RATE, 3.73f);
        String currency = preferences.getString(CURRENCY_MAIN_KEY + CURRENCY, "Dollar");
        String country = preferences.getString(CURRENCY_MAIN_KEY + COUNTRY, "United States");
        return new ExchangeRate(currency, country, averageRate);
    }

    public static void saveCurrentRate(ExchangeRate exchangeRate, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(CURRENCY_MAIN_KEY + AVERAGE_RATE, exchangeRate.getRate());
        editor.putString(CURRENCY_MAIN_KEY + CURRENCY, exchangeRate.getCurrency());
        editor.putString(CURRENCY_MAIN_KEY + COUNTRY, exchangeRate.getCountry());
        editor.apply();
    }
}