package basic.android.fp.pl.androidbasic.network.api;

import basic.android.fp.pl.androidbasic.model.RatesList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonRatesService {

    @GET("/list/USD")
    Call<RatesList> getCurrencyTable();
}