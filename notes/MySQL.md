# Enable [--skip-secure-auth](http://dev.mysql.com/doc/refman/5.0/en/server-options.html#option_mysqld_secure-auth) globally
	$ cat /etc/mysql/my.cnf

	[client]
	port		= 3306
	socket		= /var/run/mysqld/mysqld.sock
	secure-auth	= OFF

