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

public class FancyCurrencyListAdapter extends BaseAdapter {

	private Context context;
	private List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();

	public FancyCurrencyListAdapter(Context context, RatesList currencyTable) {
		this.context = context;
		exchangeRates = currencyTable.getExchangeRates();
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
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_currency_list, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ExchangeRate exchangeRate = getItem(position);
		holder.populate(exchangeRate);
		return convertView;
	}

	private class ViewHolder {

		protected final TextView currencyName;
		protected final TextView averageRate;
		protected final ImageView flag;

		protected ViewHolder(View view) {
			currencyName = (TextView) view.findViewById(R.id.currencyName);
			averageRate = (TextView) view.findViewById(R.id.averageRate);
			flag = (ImageView) view.findViewById(R.id.flag);
		}

		protected void populate(ExchangeRate exchangeRate) {
			currencyName.setText(exchangeRate.getCurrency().getCountry() + " " + exchangeRate.getCurrency().getCurrencyName());
			averageRate.setText(exchangeRate.getRate().toString());
			Picasso.with(context).load(FlagAddress.obtainAddress(exchangeRate.getCurrency())).placeholder(R.drawable.money).into(flag);
		}
	}
}