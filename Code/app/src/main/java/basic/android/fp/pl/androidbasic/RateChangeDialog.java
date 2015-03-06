package basic.android.fp.pl.androidbasic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import basic.android.fp.pl.androidbasic.model.Rate;

public class RateChangeDialog {

	public interface OnCurrencyChangedListener {

		void onRateChanged(Rate currency);
	}

	private Context context;
	private AlertDialog dialog;
	private Rate currencyRate;

	private EditText inputEditText;
	private Button positiveButton;

	private OnCurrencyChangedListener listener;

	public RateChangeDialog(Context context, OnCurrencyChangedListener listener, Rate currencyRate) {
		this.context = context;
		this.listener = listener;
		this.currencyRate = currencyRate;
	}

	public void show() {
		if (dialog == null) {
			dialog = buildDialog();
		}

		dialog.show();

		getPositiveButton();
		setTextInInputEditText();
	}

	private AlertDialog buildDialog() {
		inputEditText = createInputEditText();

		return new AlertDialog.Builder(context) //
				.setIcon(R.drawable.ic_launcher) //
				.setTitle(R.string.dialog_title) //
				.setMessage(R.string.dialog_message) //
				.setPositiveButton(R.string.ok, new PositiveOnClickListener()) //
				.setNegativeButton(R.string.cancel, new NullOnClickListener()) //
				.setView(inputEditText) //
				.create();
	}

	private EditText createInputEditText() {
		EditText input = new EditText(context);
		input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		input.addTextChangedListener(new EmptyInputValidator());

		return input;
	}

	private void getPositiveButton() {
		positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
	}

	private void setTextInInputEditText() {
		inputEditText.setText(String.valueOf(currencyRate.getRate()));
	}

	private class PositiveOnClickListener implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			changeExchangeRate();
		}
	}

	private void changeExchangeRate() {
		CharSequence inputText = inputEditText.getText();

		double rate = Double.parseDouble(inputText.toString());
		currencyRate.setRate(rate);
		listener.onRateChanged(currencyRate);
	}

	private class EmptyInputValidator implements TextWatcher {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			boolean positiveButtonEnable = s.toString().trim().length() > 0;
			positiveButton.setEnabled(positiveButtonEnable);
		}
	}

	private class NullOnClickListener implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
		}
	}
}