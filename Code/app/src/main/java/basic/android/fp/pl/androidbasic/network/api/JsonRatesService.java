package basic.android.fp.pl.androidbasic.network.api;

import basic.android.fp.pl.androidbasic.model.RatesList;
import basic.android.fp.pl.androidbasic.util.Currency;
import retrofit.http.GET;
import retrofit.http.Query;

public interface JsonRatesService {

	@GET("/latest?base=USD")
	RatesList getCurrencyTable();

	@GET("/latest")
	RatesList getCurrencyTable(@Query("base") Currency currency);
}