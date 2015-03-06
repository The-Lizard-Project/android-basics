from django.conf.urls import patterns, url

urlpatterns = patterns('',
                       # Examples:
                       # url(r'^$', 'Backend.views.home', name='home'),

                       url(r'^list/(?P<iso_code>[a-zA-Z]{3})$',
                           'AndroidBackend.views.list_of_exchange_rates'))