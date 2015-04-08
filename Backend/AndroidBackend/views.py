import json
from django.http import HttpResponse
import urllib2

# Create your views here.
def list_of_exchange_rates(request, iso_code):
    data = {}
    try:
        jsonRequest = urllib2.urlopen("http://api.fixer.io/latest?base=" + iso_code)
        content = jsonRequest.read()
        tmpData = json.loads(content.decode("utf8"))
        data['date'] = tmpData['date']
        data['base'] = {'currency': RateConverter.get_rate_name(tmpData['base']), 'country':  RateConverter.get_rate_country_name(tmpData['base'])}
        mmr = tmpData['rates']
        rate_recs = []
        for key, value in mmr.iteritems():
            rate_recs.append({'currency': RateConverter.get_rate_name(key), 'country':  RateConverter.get_rate_country_name(key), 'rate': value})
        data['rates'] = rate_recs
    except Exception as detail:
        data = {"date" : "2015-04-02",
			"base" : {
				"currency" : "Dollar",
				"country" : "United States"
			},
			"rates" : [{
					"currency" : "Rupiah",
					"rate" : 13001.44,
					"country" : "Indonesia"
				}, {
					"currency" : "Lev",
					"rate" : 1.8059,
					"country" : "Bulgaria"
				}, {
					"currency" : "Shekel",
					"rate" : 3.9485,
					"country" : "Israel"
				}, {
					"currency" : "Pound",
					"rate" : 0.6755,
					"country" : "United Kingdom"
				}, {
					"currency" : "Krone",
					"rate" : 6.8982,
					"country" : "Denmark"
				}, {
					"currency" : "Dollar",
					"rate" : 1.2644,
					"country" : "Canada"
				}, {
					"currency" : "Yen",
					"rate" : 119.66,
					"country" : "Japan"
				}, {
					"currency" : "Forint",
					"rate" : 276.62,
					"country" : "Hungary"
				}, {
					"currency" : "New Leu",
					"rate" : 4.0783,
					"country" : "Romania"
				}, {
					"currency" : "Ringgit",
					"rate" : 3.6602,
					"country" : "Malaysia"
				}, {
					"currency" : "Krona",
					"rate" : 8.6283,
					"country" : "Sweden"
				}, {
					"currency" : "Dollar",
					"rate" : 1.3591,
					"country" : "Singapore"
				}, {
					"currency" : "Dollar",
					"rate" : 7.7524,
					"country" : "Hong Kong"
				}, {
					"currency" : "Dollar",
					"rate" : 1.3244,
					"country" : "Australia"
				}, {
					"currency" : "Franc",
					"rate" : 0.9603,
					"country" : "Switzerland"
				}, {
					"currency" : "Won",
					"rate" : 1092.62,
					"country" : "Korea (South)"
				}, {
					"currency" : "Yuan Renminbi",
					"rate" : 6.1962,
					"country" : "China"
				}, {
					"currency" : "Lira",
					"rate" : 2.5988,
					"country" : "Turkey"
				}, {
					"currency" : "Kuna",
					"rate" : 7.0455,
					"country" : "Croatia"
				}, {
					"currency" : "Dollar",
					"rate" : 1.3388,
					"country" : "New Zealand"
				}, {
					"currency" : "Baht",
					"rate" : 32.49,
					"country" : "Thailand"
				}, {
					"currency" : "Euro",
					"rate" : 0.9234,
					"country" : "Euro Member"
				}, {
					"currency" : "Krone",
					"rate" : 7.9876,
					"country" : "Norway"
				}, {
					"currency" : "Ruble",
					"rate" : 57.09,
					"country" : "Russia"
				}, {
					"currency" : "Rupee",
					"rate" : 62.182,
					"country" : "India"
				}, {
					"currency" : "Peso",
					"rate" : 15.112,
					"country" : "Mexico"
				}, {
					"currency" : "Koruna",
					"rate" : 25.457,
					"country" : "Czech Republic"
				}, {
					"currency" : "Real",
					"rate" : 3.1678,
					"country" : "Brazil"
				}, {
					"currency" : "Zloty",
					"rate" : 3.7532,
					"country" : "Poland"
				}, {
					"currency" : "Peso",
					"rate" : 44.406,
					"country" : "Philippines"
				}, {
					"currency" : "Rand",
					"rate" : 11.999,
					"country" : "South Africa"
				}
			],
            "error": "Something went wrong, You got a predefined exchange rates"}

    return HttpResponse(json.dumps(data), content_type="application/json")
	
class CurrencyRate():
	currency = ''
	country = ''

	def __init__(self, currency, country):
		self.currency = currency
		self.country = country
	
class RateConverter():

	rates = {
			'IDR': CurrencyRate('Rupiah','Indonesia'),
			'BGN': CurrencyRate('Lev','Bulgaria'),
			'ILS': CurrencyRate('Shekel','Israel'),
			'GBP': CurrencyRate('Pound','United Kingdom'),
			'DKK': CurrencyRate('Krone','Denmark'),
			'CAD': CurrencyRate('Dollar','Canada'),
			'JPY': CurrencyRate('Yen','Japan'),
			'HUF': CurrencyRate('Forint','Hungary'),
			'RON': CurrencyRate('New Leu','Romania'),
			'MYR': CurrencyRate('Ringgit','Malaysia'),
			'SEK': CurrencyRate('Krona','Sweden'),
			'SGD': CurrencyRate('Dollar','Singapore'),
			'HKD': CurrencyRate('Dollar','Hong Kong'),
			'AUD': CurrencyRate('Dollar','Australia'),
			'CHF': CurrencyRate('Franc','Switzerland'),
			'KRW': CurrencyRate('Won','Korea (South)'),
			'CNY': CurrencyRate('Yuan Renminbi','China'),
			'TRY': CurrencyRate('Lira','Turkey'),
			'HRK': CurrencyRate('Kuna','Croatia'),
			'NZD': CurrencyRate('Dollar','New Zealand'),
			'THB': CurrencyRate('Baht','Thailand'),
			'EUR': CurrencyRate('Euro','Euro Member'),
			'NOK': CurrencyRate('Krone','Norway'),
			'RUB': CurrencyRate('Ruble','Russia'),
			'INR': CurrencyRate('Rupee','India'),
			'MXN': CurrencyRate('Peso','Mexico'),
			'CZK': CurrencyRate('Koruna','Czech Republic'),
			'BRL': CurrencyRate('Real','Brazil'),
			'PLN': CurrencyRate('Zloty','Poland'),
			'PHP': CurrencyRate('Peso','Philippines'),
			'ZAR': CurrencyRate('Rand','South Africa'),
			'USD': CurrencyRate('Dollar','United States')
		};
		
	@staticmethod
	def get_rate_name(rate):
		return RateConverter.rates.get(rate, CurrencyRate('','')).currency
		
	@staticmethod
	def get_rate_country_name(rate):
		return RateConverter.rates.get(rate, CurrencyRate('','')).country