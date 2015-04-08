package basic.android.fp.pl.androidbasic.util;

import android.content.Context;

import basic.android.fp.pl.androidbasic.R;
import basic.android.fp.pl.androidbasic.model.ExchangeRate;

public class FlagAddressBuilder {

	public static String obtainAddress(Context context, ExchangeRate rate) {
		String ws_url = context.getString(R.string.webservice_url);
		String port = context.getString(R.string.static_webservice_port);
		return ws_url + ":" + port + "/" + rate.getCountry().toLowerCase().replace(" ", "") + ".png";
	}
}