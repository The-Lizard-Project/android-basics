package basic.android.fp.pl.androidbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import basic.android.fp.pl.androidbasic.dialog.RateChangeDialogFragment;
import basic.android.fp.pl.androidbasic.model.ExchangeRate;
import basic.android.fp.pl.androidbasic.util.SharedPreferencesSupporter;

public class MainActivity extends AppCompatActivity implements RateChangeDialogFragment.OnCurrencyChangedListener {

    private TextView currentCurrency;
    private ExchangeRate currentExchangeRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentCurrency = (TextView) findViewById(R.id.currentCurrency);
        Button changeCurrencyButton = (Button) findViewById(R.id.listCurrenciesButton);
        Button changeCurrencyDialogButton = (Button) findViewById(R.id.rateChangeButton);
        Button calculateCurrencyButton = (Button) findViewById(R.id.calculateCurrency);

        changeCurrencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListCurrenciesActivity.class);
                startActivity(i);
            }
        });

        changeCurrencyDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateChangeDialogFragment.getInstance(currentExchangeRate).show(getSupportFragmentManager(), "tag");
            }
        });

        calculateCurrencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ExchangeActivity.class);
                i.putExtra(ExchangeActivity.CURRENCY_BUNDLE_KEY, currentExchangeRate);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentExchangeRate = SharedPreferencesSupporter.loadCurrentRate(this);
        currentCurrency.setText("Twoja waluta to: " + currentExchangeRate.getCountry() + " " + currentExchangeRate.getCurrency() + "\nKurs: " + currentExchangeRate.getRate());
    }

    @Override
    public void onRateChanged(ExchangeRate exchangeRate) {
        currentExchangeRate = exchangeRate;
        currentCurrency.setText("Twoja waluta to: " + exchangeRate.getCountry() + " " + exchangeRate.getCurrency() + "\nKurs: " + exchangeRate.getRate());
        SharedPreferencesSupporter.saveCurrentRate(exchangeRate, this);
    }
}