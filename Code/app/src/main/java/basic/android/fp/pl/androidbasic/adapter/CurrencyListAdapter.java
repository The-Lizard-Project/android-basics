package basic.android.fp.pl.androidbasic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import basic.android.fp.pl.androidbasic.R;
import basic.android.fp.pl.androidbasic.model.ExchangeRate;
import basic.android.fp.pl.androidbasic.model.RatesList;
import basic.android.fp.pl.androidbasic.util.FlagAddress;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class CurrencyListAdapter extends BaseAdapter {

	private Context context;
	private List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();
	private final LayoutInflater inflater;

	public CurrencyListAdapter(Context context, RatesList ratesList) {
		this.context = context;
		exchangeRates = ratesList.getExchangeRates();
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return exchangeRates.size();
	}

	@Override
	public ExchangeRate getItem(int position) {
		return exchangeRates.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_currency_list, parent, false);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		ExchangeRate exchangeRate = getItem(position);
		vh.populate(exchangeRate);

		return convertView;
	}

	protected class ViewHolder {

		@InjectView(R.id.currencyName)
		protected TextView currencyName;
		@InjectView(R.id.averageRate)
		protected TextView averageRate;
		@InjectView(R.id.flag)
		protected ImageView flag;

		protected ViewHolder(View rootView) {
			ButterKnife.inject(this, rootView);
		}

		protected void populate(ExchangeRate exchangeRate) {
			currencyName.setText(exchangeRate.getCurrency().getCountry() + " " + exchangeRate.getCurrency().getCurrencyName());
			averageRate.setText(exchangeRate.getRate().toString());
			Picasso.with(context).load(FlagAddress.obtainAddress(exchangeRate.getCurrency())).placeholder(R.drawable.money).into(flag);
		}
	}
}