# How to import a csv file into MySQL workbench?
http://stackoverflow.com/questions/11429827/how-to-import-a-csv-file-into-mysql-workbench
LOAD DATA LOCAL INFILE '/path/to/your/csv/file/model.csv' INTO TABLE test.dummy FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';

http://www.mysqltutorial.org/import-csv-file-mysql-table/

CREATE TABLE discounts (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  expired_date DATE NOT NULL,
  amount DECIMAL(10,2) NULL,
  PRIMARY KEY (id)
);

cat | sed -e 's/\t/\n/g' 

cat th.txt | sed -e 's/\t/\n/g' 

$ cat header.txt | sed -e 's/\t/\n/g' | awk '{print "  " $1$2$3 " VARCHAR(255) NULL,"}'
CREATE TABLE t1 (
  BusinessID VARCHAR(255) NULL,
  NAID VARCHAR(255) NULL,
  Forename VARCHAR(255) NULL,
  Middlename VARCHAR(255) NULL,
  Surname VARCHAR(255) NULL,
  DoB VARCHAR(255) NULL,
  AddressLine1 VARCHAR(255) NULL,
  AddressLine2 VARCHAR(255) NULL,
  AddressLine3 VARCHAR(255) NULL,
  AddressLine4 VARCHAR(255) NULL,
  AddressLine5 VARCHAR(255) NULL,
  AddressLine6 VARCHAR(255) NULL,
  PostCode VARCHAR(255) NULL,
  Country VARCHAR(255) NULL,
  SequenceNumber VARCHAR(255) NULL,
  ParentSequenceNumber VARCHAR(255) NULL
)

LOAD DATA INFILE '/tmp/Person_legacy.csv' 
INTO TABLE t1 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;


# Vertabelo
java -cp ./mysql-connector-java-5.1.34-bin.jar:./reverse-engineering-1_6_3.jar com.vertabelo.reverse.Main -url 'jdbc:mysql://w-dlapp07.rdc.com:3306/case_framemf?sendStringParametersAsUnicode=false;socketTimeout=180;loginTimeout=20&allowMultiQueries=true' -user rdcapp -password 321-rdc -o cmf-dev.xml


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

