package basic.android.fp.pl.androidbasic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import basic.android.fp.pl.androidbasic.model.Rate;
import basic.android.fp.pl.androidbasic.util.Currency;
import basic.android.fp.pl.androidbasic.util.SharedPreferencesSupporter;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements RateChangeDialog.OnCurrencyChangedListener {

	private Button changeCurrencyButton;
	private Button button2;
	private TextView currentCurrency;
	private Rate currentRate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);

		currentCurrency = (TextView) findViewById(R.id.currentCurrency);
		changeCurrencyButton = (Button) findViewById(R.id.listCurrenciesButton);
		button2 = (Button) findViewById(R.id.rateChangeButton);

		changeCurrencyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), ListCurrenciesActivity.class);
				i.putExtra(ListCurrenciesActivity.BASE_CURRENCY, currentRate.getCurrency());
				startActivity(i);
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				new RateChangeDialog(MainActivity.this, MainActivity.this, currency).show();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		currentRate = SharedPreferencesSupporter.loadCurrentRate(this);
		final Currency currency = currentRate.getCurrency();
		currentCurrency.setText("Twoja waluta to: " + currency.getCountry() + " " + currency.getCurrencyName() + "\nKurs: " + currentRate.getRate());
	}

	@Override
	public void onRateChanged(Rate rate) {
		currentRate = rate;
		final Currency currency = rate.getCurrency();
		currentCurrency.setText("Twoja waluta to: " + currency.getCountry() + " " + currency.getCurrencyName() + "\nKurs: " + rate.getRate());
		SharedPreferencesSupporter.saveCurrentRate(rate, this);
	}
}