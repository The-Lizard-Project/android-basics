package basic.android.fp.pl.androidbasic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import basic.android.fp.pl.androidbasic.adapter.FancyCurrencyListAdapter;
import basic.android.fp.pl.androidbasic.model.ExchangeRate;
import basic.android.fp.pl.androidbasic.model.RatesList;
import basic.android.fp.pl.androidbasic.network.adapter.CurrencyTypeAdapter;
import basic.android.fp.pl.androidbasic.network.api.JsonRatesService;
import basic.android.fp.pl.androidbasic.util.Currency;
import basic.android.fp.pl.androidbasic.util.SharedPreferencesSupporter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ListCurrenciesActivity extends Activity {

	public static final String BASE_CURRENCY = ListCurrenciesActivity.class.getCanonicalName().concat("_BASE_CURRENCY");
	@InjectView(R.id.list)
	protected ListView currencyListView;
	private JsonRatesService service;
	private Currency baseCurrency;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_currency);
		ButterKnife.inject(this);

		String currencyCode = getIntent().getStringExtra(BASE_CURRENCY);
		baseCurrency = Currency.valueOf(currencyCode == null ? "PLN" : currencyCode);

		Gson gson = new GsonBuilder().
				setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
				registerTypeAdapter(Currency.class, new CurrencyTypeAdapter()).
				create();

		RestAdapter restAdapter = new RestAdapter.Builder().
				setEndpoint(getString(R.string.webservice_url)).
				setConverter(new GsonConverter(gson)).
				build();

		service = restAdapter.create(JsonRatesService.class);

		loadData();
	}

	@OnItemClick(R.id.list)
	void onListItemClick(AdapterView<?> parent, int position) {
		FancyCurrencyListAdapter currencyAdapter = (FancyCurrencyListAdapter) parent.getAdapter();
		ExchangeRate exchangeRate = currencyAdapter.getItem(position);
		baseCurrency = exchangeRate.getCurrency();
		SharedPreferencesSupporter.saveCurrentRate(exchangeRate, ListCurrenciesActivity.this);
		Toast.makeText(this, "Currency saved to SharedPreferences", Toast.LENGTH_SHORT).show();
		loadData();
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

	private AsyncTask<Currency, Void, RatesList> loadData() {
		return new MyTask(this).execute(baseCurrency);
	}

	private class MyTask extends AsyncTask<Currency, Void, RatesList> {

		private final ProgressDialog dialog;

		public MyTask(Context context) {
			dialog = new ProgressDialog(context);
			dialog.setMessage(getString(R.string.please_wait));
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected RatesList doInBackground(Currency... params) {
			return service.getCurrencyTable(params[0]);
		}

		@Override
		protected void onPostExecute(RatesList currencies) {
			super.onPostExecute(currencies);
			dialog.dismiss();
			currencyListView.setAdapter(new FancyCurrencyListAdapter(ListCurrenciesActivity.this, currencies));
		}
	}
}