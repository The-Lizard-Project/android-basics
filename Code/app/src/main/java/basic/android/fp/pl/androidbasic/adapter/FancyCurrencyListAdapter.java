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
import java.util.Map;

import basic.android.fp.pl.androidbasic.R;
import basic.android.fp.pl.androidbasic.model.Rate;
import basic.android.fp.pl.androidbasic.model.RatesList;
import basic.android.fp.pl.androidbasic.util.Currency;
import basic.android.fp.pl.androidbasic.util.FlagAddress;

public class FancyCurrencyListAdapter extends BaseAdapter {

	private Context context;
	private List<Rate> rates;

	public FancyCurrencyListAdapter(Context context, RatesList currencyTable) {
		this.context = context;
		rates = new ArrayList<Rate>(currencyTable.getRates().size());
		for (Map.Entry<String, Double> entry : currencyTable.getRates().entrySet()) {
			rates.add(new Rate(Currency.valueOf(entry.getKey()), entry.getValue()));
		}
	}

	@Override
	public int getCount() {
		return rates.size();
	}

	@Override
	public Rate getItem(int position) {
		return rates.get(position);
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
		Rate rate = getItem(position);
		holder.populate(rate);
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

		protected void populate(Rate rate) {
			currencyName.setText(rate.getCurrency().getCountry() + " " + rate.getCurrency().getCurrencyName());
			averageRate.setText(rate.getRate().toString());
			Picasso.with(context).load(FlagAddress.obtainAddress(rate.getCurrency())).placeholder(R.drawable.money).into(flag);
		}
	}
}