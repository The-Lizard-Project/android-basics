package basic.android.fp.pl.androidbasic.network.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import basic.android.fp.pl.androidbasic.util.Currency;

public class CurrencyTypeAdapter extends TypeAdapter<Currency> {

	@Override
	public void write(JsonWriter out, Currency value) throws IOException {
		if (value == null) {
			out.nullValue();
			return;
		}
		out.value(value.toString());
	}

	@Override
	public Currency read(JsonReader in) throws IOException {
		if (in.peek() == JsonToken.NULL) {
			in.nextNull();
			return null;
		}
		return Currency.valueOf(in.nextString());
	}
}
