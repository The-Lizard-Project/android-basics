package basic.android.fp.pl.androidbasic.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import basic.android.fp.pl.androidbasic.R;
import basic.android.fp.pl.androidbasic.model.ExchangeRate;

public class RateChangeDialogFragment extends DialogFragment {

	private static final String CURRENCY_BUNDLE_KEY = "CURRENCY_BUNDLE_KEY";

	private OnCurrencyChangedListener onCurrencyChangedListener;
	private ExchangeRate currencyRate;

	private EditText inputEditText;
	private Button positiveButton;

	public static RateChangeDialogFragment getInstance(ExchangeRate rate) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(CURRENCY_BUNDLE_KEY, rate);

		RateChangeDialogFragment fragment = new RateChangeDialogFragment();
		fragment.setArguments(bundle);

		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			onCurrencyChangedListener = (OnCurrencyChangedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnCurrencyChangedListener");
		}

		currencyRate = (ExchangeRate) getArguments().getSerializable(CURRENCY_BUNDLE_KEY);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putSerializable(CURRENCY_BUNDLE_KEY, currencyRate);
		super.onSaveInstanceState(outState);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		inputEditText = createInputEditText();

		return new AlertDialog.Builder(getActivity()) //
				.setIcon(R.drawable.ic_launcher) //
				.setTitle(R.string.dialog_title) //
				.setMessage(R.string.dialog_message) //
				.setPositiveButton(R.string.ok, new PositiveOnClickListener()) //
				.setNegativeButton(R.string.cancel, new NullOnClickListener()) //
				.setView(inputEditText) //
				.create();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setTextInInputEditText();
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getPositiveButton();
	}

	private EditText createInputEditText() {
		EditText input = new EditText(getActivity());
		input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		input.addTextChangedListener(new EmptyInputValidator());
		input.setId(R.id.action_refresh);
		return input;
	}

	private void getPositiveButton() {
		positiveButton = ((AlertDialog) getDialog()).getButton(Dialog.BUTTON_POSITIVE);
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
		onCurrencyChangedListener.onRateChanged(currencyRate);
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
			if (positiveButton == null) {
				getPositiveButton();
			}
			if (positiveButton != null) {
				positiveButton.setEnabled(positiveButtonEnable);
			}

			currencyRate.setRate(Double.parseDouble(inputEditText.getText().toString()));
		}
	}

	private class NullOnClickListener implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
		}
	}

	public interface OnCurrencyChangedListener {

		void onRateChanged(ExchangeRate currency);
	}
}