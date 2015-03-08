Server application requires Python 2.7 and Django 1.7 to work.

Installation guide can be found here:
http://docs.python-guide.org/en/latest/starting/install/win/
and Djangos guide here:
https://www.djangoproject.com/download/

And if you'll want to query the service from a device rather then an emulator you'll need to edit ./Backend/settings.py  and add your ip address to a ALLOWED_HOSTS array.

Now in order to start the server you only need to execut runserver.bat file from cmd.
