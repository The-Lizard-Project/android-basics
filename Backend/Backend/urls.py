from django.conf.urls import patterns, url

from django.contrib.staticfiles.storage import staticfiles_storage
from django.views.generic.base import RedirectView

urlpatterns = patterns('',

                       url(r'^list/(?P<iso_code>[a-zA-Z]{3})$',
                           'AndroidBackend.views.list_of_exchange_rates'),
			
			url(r'^favicon.ico$',RedirectView.as_view(url=staticfiles_storage.url('favicon.ico'),permanent=False),
					     name="favicon"
			)	
		)

	
