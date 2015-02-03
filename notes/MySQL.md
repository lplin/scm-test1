# MySQL Workbench [shows CONCAT results as BLOB](http://stackoverflow.com/questions/13634369/mysql-workbench-shows-results-as-blob)
Background: This problem occurs when the binary string values (BINARY/VARBINARY type) are returned in the results. The binary strings contain the zero bytes and for some reason, apparently security, have not been shown by default. More details about binary strings here.

Even in the reported example SELECT INET_NTOA(167773449), the function returns binary string. Check this for reference.

Solution: Since MySQL Workbench v5.2.22, it can be set through preferences whether to SHOW or HIDE such values.

In MySQL Workbench, go to: "Edit -> Preferences... -> SQL Queries" OR "Edit -> Preferences... -> SQL Editor" (depending upon what version of Workbench you have).
Check the option 'Treat BINARY/VARBINARY as nonbinary character string' to show the actual value.

Or [Cast](http://stackoverflow.com/questions/18840557/mysql-concatstring-longtext-results-in-hex-string)
Have you tried casting? Usually works pretty well for me. Example:

SELECT CONCAT("abc",CAST(t.LONGTEXT_VALUE AS CHAR),"cde") FROM mytable t


# Enable [--skip-secure-auth](http://dev.mysql.com/doc/refman/5.0/en/server-options.html#option_mysqld_secure-auth) globally
	$ cat /etc/mysql/my.cnf

	[client]
	port		= 3306
	socket		= /var/run/mysqld/mysqld.sock
	secure-auth	= OFF

