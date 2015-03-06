from django.conf.urls import patterns, url
import settings

urlpatterns = patterns('',
                       # Examples:
                       # url(r'^$', 'Backend.views.home', name='home'),
                       # url(r'^blog/', include('blog.urls')),
                       # url(r'^admin/', include(admin.site.urls)),

                       url(r'^list/(?P<iso_code>[a-zA-Z]{3})$',
                           'AndroidBackend.views.list_of_exchange_rates'),
                       url(r'^flags/(?P<path>.*)$', 'django.views.static.serve', {
                           'document_root': settings.MEDIA_ROOT}))