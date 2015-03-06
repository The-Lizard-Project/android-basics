package basic.android.fp.pl.androidbasic.network.api;

import basic.android.fp.pl.androidbasic.model.RatesList;
import basic.android.fp.pl.androidbasic.util.Currency;
import retrofit.http.GET;
import retrofit.http.Path;

public interface JsonRatesService {

	@GET("/list/{base}")
	RatesList getCurrencyTable(@Path("base") Currency currency);
}