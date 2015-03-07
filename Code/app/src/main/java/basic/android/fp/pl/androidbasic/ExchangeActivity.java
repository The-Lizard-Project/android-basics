package basic.android.fp.pl.androidbasic;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;

import basic.android.fp.pl.androidbasic.model.ExchangeRate;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnTextChanged;

public class ExchangeActivity extends Activity {

	public static final String CURRENCY_BUNDLE_KEY = "CURRENCY_BUNDLE_KEY";

	@InjectView(R.id.newCurrency)
	protected TextView newCurrencyTextView;
	@InjectView(R.id.currency)
	protected TextView currencyTextView;

	private ExchangeRate exchangeRate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exchange);
		ButterKnife.inject(this);

		exchangeRate = (ExchangeRate) getIntent().getSerializableExtra(CURRENCY_BUNDLE_KEY);
		currencyTextView.setText("Aktualny kurs to:\t" + exchangeRate.getRate());
	}

	@OnTextChanged(value = R.id.currencyEditText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
	protected void onTextChange(Editable text) {
		if (isValid(text.toString())) {
			float value = Float.parseFloat(text.toString()) * exchangeRate.getRate();
			newCurrencyTextView.setText("To\t" + value + "\t" + exchangeRate.getCurrency());
		} else {
			newCurrencyTextView.setText(R.string.invalid);
		}
	}

	private boolean isValid(String text) {
		try {
			Float.parseFloat(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}