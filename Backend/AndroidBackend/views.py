from django.shortcuts import render
import json
from django.http import HttpResponse
import urllib2

# Create your views here.
def list_of_exchange_rates(request, iso_code):
    data = ""
    try:
        jsonRequest = urllib2.urlopen("http://api.fixer.io/latest?base=" + iso_code)
        content = jsonRequest.read()
        data = json.loads(content.decode("utf8"))
    except Exception:
        data = {"base": iso_code, "date": "2015-03-05",
                "rates": {"AUD": 0.3431, "BGN": 0.4724, "BRL": 0.7974, "CAD": 0.3326, "CHF": 0.2584,
                          "CNY": 1.676, "CZK": 6.6242, "DKK": 1.8007, "GBP": 0.1752, "HKD": 2.0737,
                          "HRK": 1.85, "HUF": 73.764, "IDR": 3469.59, "ILS": 1.0694, "INR": 16.646,
                          "JPY": 32.152, "KRW": 294.43, "MXN": 4.0236, "MYR": 0.9763, "NOK": 2.0644,
                          "NZD": 0.357, "PHP": 11.8, "RON": 1.0738, "RUB": 16.332, "SEK": 2.2258,
                          "SGD": 0.3661, "THB": 8.6685, "TRY": 0.6924, "USD": 0.2674, "ZAR": 3.1443,
                          "EUR": 0.2416},
                "error": "Returned default values due to an error"}

    return HttpResponse(json.dumps(data), content_type="application/json")
