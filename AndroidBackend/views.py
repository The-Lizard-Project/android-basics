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
        data['base'] = tmpData['base']
        mmr = tmpData['rates']
        rate_recs = []
        for key, value in mmr.iteritems():
            rate_recs.append({'currency': key, 'rate': value})
        data['rates'] = rate_recs
    except Exception as detail:
        data = {
            "date": "2015-03-05",
            "base": "PLN",
            "rates": [{
                          "currency": "USD",
                          "rate": 0.2674
                      }, {
                          "currency": "IDR",
                          "rate": 3469.59
                      }, {
                          "currency": "BGN",
                          "rate": 0.4724
                      }, {
                          "currency": "ILS",
                          "rate": 1.0694
                      }, {
                          "currency": "GBP",
                          "rate": 0.1752
                      }, {
                          "currency": "DKK",
                          "rate": 1.8007
                      }, {
                          "currency": "CAD",
                          "rate": 0.3326
                      }, {
                          "currency": "JPY",
                          "rate": 32.152
                      }, {
                          "currency": "HUF",
                          "rate": 73.764
                      }, {
                          "currency": "RON",
                          "rate": 1.0738
                      }, {
                          "currency": "MYR",
                          "rate": 0.9763
                      }, {
                          "currency": "SEK",
                          "rate": 2.2258
                      }, {
                          "currency": "SGD",
                          "rate": 0.3661
                      }, {
                          "currency": "HKD",
                          "rate": 2.0737
                      }, {
                          "currency": "AUD",
                          "rate": 0.3431
                      }, {
                          "currency": "CHF",
                          "rate": 0.2584
                      }, {
                          "currency": "KRW",
                          "rate": 294.43
                      }, {
                          "currency": "CNY",
                          "rate": 1.676
                      }, {
                          "currency": "TRY",
                          "rate": 0.6924
                      }, {
                          "currency": "HRK",
                          "rate": 1.85
                      }, {
                          "currency": "NZD",
                          "rate": 0.357
                      }, {
                          "currency": "THB",
                          "rate": 8.6685
                      }, {
                          "currency": "EUR",
                          "rate": 0.2416
                      }, {
                          "currency": "NOK",
                          "rate": 2.0644
                      }, {
                          "currency": "RUB",
                          "rate": 16.332
                      }, {
                          "currency": "INR",
                          "rate": 16.646
                      }, {
                          "currency": "MXN",
                          "rate": 4.0236
                      }, {
                          "currency": "CZK",
                          "rate": 6.6242
                      }, {
                          "currency": "BRL",
                          "rate": 0.7974
                      }, {
                          "currency": "PHP",
                          "rate": 11.8
                      }, {
                          "currency": "ZAR",
                          "rate": 3.1443
                      }
            ],
            "error": detail}

    return HttpResponse(json.dumps(data), content_type="application/json")
