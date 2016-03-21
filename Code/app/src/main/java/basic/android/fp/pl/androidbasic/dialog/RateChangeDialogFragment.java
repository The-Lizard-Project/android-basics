package basic.android.fp.pl.androidbasic.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import basic.android.fp.pl.androidbasic.R;
import basic.android.fp.pl.androidbasic.model.ExchangeRate;

public class RateChangeDialogFragment extends DialogFragment {

    private static final String CURRENCY_BUNDLE_KEY = "CURRENCY_BUNDLE_KEY";

    private OnCurrencyChangedListener onCurrencyChangedListener;
    private ExchangeRate currencyRate;

    private EditText inputEditText;

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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inputEditText = createInputEditText();

        return new AlertDialog.Builder(getActivity()) //
                .setIcon(R.drawable.ic_launcher) //
                .setTitle(R.string.dialog_title) //
                .setMessage(R.string.dialog_message) //
                .setPositiveButton(R.string.ok, new PositiveOnClickListener()) //
                .setNegativeButton(R.string.cancel, null) //
                .setView(inputEditText) //
                .create();
    }

    private EditText createInputEditText() {
        EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        input.addTextChangedListener(new CurrencyTextWatcher());
        input.setText(String.valueOf(currencyRate.getRate()));
        return input;
    }

    private boolean isValid(String text) {
        try {
            Float.parseFloat(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private class PositiveOnClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            changeExchangeRate();
        }
    }

    private void changeExchangeRate() {
        if (isValid(inputEditText.getText().toString())) {
            onCurrencyChangedListener.onRateChanged(currencyRate);
        } else {
            Toast.makeText(getActivity(), R.string.invalid, Toast.LENGTH_SHORT).show();
        }
    }

    private class CurrencyTextWatcher implements TextWatcher {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (isValid(s.toString())) {
                currencyRate.setRate(Float.parseFloat(s.toString()));
            }
        }
    }

    public interface OnCurrencyChangedListener {

        void onRateChanged(ExchangeRate currency);
    }
}