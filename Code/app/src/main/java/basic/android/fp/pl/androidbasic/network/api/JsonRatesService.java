package basic.android.fp.pl.androidbasic.network.api;

import basic.android.fp.pl.androidbasic.model.RatesList;
import retrofit.http.GET;

public interface JsonRatesService {

	@GET("/list/USD")
	RatesList getCurrencyTable();
}