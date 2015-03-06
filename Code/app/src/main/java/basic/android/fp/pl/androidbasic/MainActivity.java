package basic.android.fp.pl.androidbasic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import basic.android.fp.pl.androidbasic.dialog.RateChangeDialogFragment;
import basic.android.fp.pl.androidbasic.model.ExchangeRate;
import basic.android.fp.pl.androidbasic.util.Currency;
import basic.android.fp.pl.androidbasic.util.SharedPreferencesSupporter;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements RateChangeDialogFragment.OnCurrencyChangedListener {

	private TextView currentCurrency;
	private ExchangeRate currentExchangeRate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);

		currentCurrency = (TextView) findViewById(R.id.currentCurrency);
		Button changeCurrencyButton = (Button) findViewById(R.id.listCurrenciesButton);
		Button button2 = (Button) findViewById(R.id.rateChangeButton);

		changeCurrencyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), ListCurrenciesActivity.class);
				i.putExtra(ListCurrenciesActivity.BASE_CURRENCY, currentExchangeRate.getCurrency());
				startActivity(i);
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RateChangeDialogFragment.getInstance(currentExchangeRate).show(getFragmentManager(), "tag");
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		currentExchangeRate = SharedPreferencesSupporter.loadCurrentRate(this);
		final Currency currency = currentExchangeRate.getCurrency();
		currentCurrency.setText("Twoja waluta to: " + currency.getCountry() + " " + currency.getCurrencyName() + "\nKurs: " + currentExchangeRate.getRate());
	}

	@Override
	public void onRateChanged(ExchangeRate exchangeRate) {
		currentExchangeRate = exchangeRate;
		final Currency currency = exchangeRate.getCurrency();
		currentCurrency.setText("Twoja waluta to: " + currency.getCountry() + " " + currency.getCurrencyName() + "\nKurs: " + exchangeRate.getRate());
		SharedPreferencesSupporter.saveCurrentRate(exchangeRate, this);
	}
}