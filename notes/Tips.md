# Sony VAIO vgnfw200 factory default: F10

# [Cron expression](http://docs.netkernel.org/book/view/book:mod:cron/doc:mod:cron:cronexpression)
| Field Name      | Allowed Values   | Allowed Special Characters |
|-----------------|------------------|----------------------------|
| Seconds         | 0-59             | , - * /                    |
| Minutes         | 0-59             | , - * /                    |
| Hours           | 0-23             | , - * /                    |
| Day-of-month    | 1-31             | , - * ? / L W C            |
| Month           | 1-12 or JAN-DEC  | , - * /                    |
| Day-of-Week     | 1-7 or SUN-SAT   | , - * ? / L C #            |
| Year (Optional) | empty, 1970-2099 | , - * /                    |	
	
	The '*' character is used to specify all values. For example, "*" in the minute field means "every minute".
	The '?' character is allowed for the day-of-month and day-of-week fields. It is used to specify 'no specific value'. This is useful when you need to specify something in one of the two fileds, but not the other. See the examples below for clarification.
	The '-' character is used to specify ranges For example "10-12" in the hour field means "the hours 10, 11 and 12".
	The ',' character is used to specify additional values. For example "MON,WED,FRI" in the day-of-week field means "the days Monday, Wednesday, and Friday".

# [Sticky mode](http://en.wikipedia.org/wiki/Filesystem_permissions#Changing_permission_behavior_with_setuid.2C_setgid.2C_and_sticky_bits)
On a directory, the sticky permission prevents users from renaming, moving or deleting contained files owned by users other than themselves, even if they have write permission to the directory. 	Only the directory owner and superuser are exempt from this.

# Without starting vncserver & vncviewer without a screen#:
	System / Preferences / Remote Desktop:
	[v]: Allow other users to view your desktop
		[v]: Allow other users to control your desktop
	[  ]: Ask you for confirmation
	[v]: Require the user to enter this password

# HOW TO FIX CISCO [VPN CLIENT ERROR 442 ON WINDOWS VISTA](http://help.lockergnome.com/vista/Cisco-VPN-Reason-442--ftopict37194.html)
	Press Windows and R key to open Run window, type in services.msc
	Stop the Cisco Systems … service
	Stop the Internet Connection Sharing (ICS) service
	Right click on ICS service and choose Properties. Then change Startup type to Disabled or Manual.
	Start Cisco Systems … service
	Done. Now create a VPN connection, error 442 no more !

# Smily: 
	:) or :-) ⇒ / ALT+074
	:| or :-| ⇒ / ALT+075
	:( or :-( ⇒ / ALT+076


