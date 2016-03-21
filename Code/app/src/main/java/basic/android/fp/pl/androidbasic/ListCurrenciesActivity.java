package basic.android.fp.pl.androidbasic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import basic.android.fp.pl.androidbasic.adapter.CurrencyListAdapter;
import basic.android.fp.pl.androidbasic.model.ExchangeRate;
import basic.android.fp.pl.androidbasic.model.RatesList;
import basic.android.fp.pl.androidbasic.network.api.JsonRatesService;
import basic.android.fp.pl.androidbasic.util.SharedPreferencesSupporter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListCurrenciesActivity extends AppCompatActivity {

    @Bind(R.id.list)
    protected ListView currencyListView;
    private JsonRatesService service;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_currency);
        ButterKnife.bind(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.please_wait));

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(getString(R.string.webservice_url) + ":" + getString(R.string.webservice_port))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = restAdapter.create(JsonRatesService.class);

        loadData();
    }

    @OnItemClick(R.id.list)
    void onListItemClick(AdapterView<?> parent, int position) {
        CurrencyListAdapter currencyAdapter = (CurrencyListAdapter) parent.getAdapter();
        ExchangeRate exchangeRate = currencyAdapter.getItem(position);
        SharedPreferencesSupporter.saveCurrentRate(exchangeRate, this);
        Toast.makeText(this, "Currency saved to SharedPreferences", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.change_currency, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh || id == R.id.menu_refresh) {
            loadData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        dialog.show();

        service.getCurrencyTable().enqueue(new Callback<RatesList>() {
            @Override
            public void onResponse(Call<RatesList> call, Response<RatesList> response) {
                dialog.dismiss();
                currencyListView.setAdapter(new CurrencyListAdapter(ListCurrenciesActivity.this, response.body()));
            }

            @Override
            public void onFailure(Call<RatesList> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ListCurrenciesActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}