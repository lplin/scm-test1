# How To Calculate The MySQL Database Size [Ref](http://www.mkyong.com/mysql/how-to-calculate-the-mysql-database-size/)
	Here’s the SQL script to list out the entire databases size

	SELECT table_schema "Data Base Name", SUM( data_length + index_length) / 1024 / 1024 
	"Data Base Size in MB" FROM information_schema.TABLES GROUP BY table_schema ;

	Another SQL script to list out one database size, and each tables size in detail

	SELECT TABLE_NAME, table_rows, data_length, index_length, 
	round(((data_length + index_length) / 1024 / 1024),2) "Size in MB"
	FROM information_schema.TABLES WHERE table_schema = "schema_name";


# Remove the last comma
	SET @val='Переулок Крапивный,5,';
	SELECT IF(RIGHT(@val, 1)=',', LEFT(@val, CHAR_LENGTH(@val)-1), @val);

# String compare exact in query MySQL 
	SELECT CONCAT_WS('|', TRIM(AddressLine1), TRIM(AddressLine2)) FROM business_legacy -- TRIM not working in WHERE
	WHERE BusinessID='00000000000000445502' AND TRIM(AddressLine1)!=AddressLine1
	;

	SELECT 'A'='A'; -- 1
	SELECT 'A '='A'; -- 1
	SELECT '  A'='A'; -- 0
	SELECT 'A '= BINARY 'A'; -- 0

# Using Unix Timestamps in MySQL Mini-Course [Ref](http://www.epochconverter.com/programming/mysql-from-unixtime.php)
	SELECT UNIX_TIMESTAMP(NOW()) (now() is optional)

	SET @ts=UNIX_TIMESTAMP();
	SELECT @ts;
	SELECT @ts:=@ts+1, p.* FROM person p WHERE NAID='';

# Null, Empty string and CONCAT
	SET @v1=NULL;
	SET @v1='09/01/1988';
	SET @v1=''; -- Empty string is better treated.
	SELECT CONCAT_WS('|', 'ABC', IF(@v1='', @v1, DATE_FORMAT(STR_TO_DATE(@v1, '%m/%d/%Y'), '%Y%m%d')), 'xyz');

	SELECT CONCAT_WS(',', NULL, NULL); -- ''
	SELECT CONCAT_WS(',', '', ''); -- ','


# Only column name in WHERE
	SELECT * FROM business WHERE (NAID IS NULL OR BusinessID); -- Return many rows.

# GRANT 
	mysql> GRANT ALL ON c6.* TO 'rdcapp';
	(GRANT ALL ON c6.* TO 'rdcapp'@'localhost';) is too strict.

# Migrating Unicode Data From [MSSQL to MySQL](http://www.wolflabs.org/2012/08/09/migrating-unicode-data-from-mssql-to-mysql/)
	$ bcp "select '**BOL**', u.* from absynth.dbo.user u;" queryout "C:\tmp\user.csv.dump" -w -CRAW -t"**EOT**" -r"***EOL***" -S localhost -U "absynth" -P "peanutbutter"<br /> 
	$ iconv -f UTF-16LE -t UTF-8 < "C:\tmp\user.csv.dump" > "C:\tmp\user.csv" 
	$ del "C:\tmp\user.csv.dump" 
	$ mysql -h localhost -u absynth --password=cookies --execute="ALTER TABLE user DISABLE KEYS; LOAD DATA LOCAL INFILE 'C:\tmp\user.csv.dump' INTO TABLE user CHARACTER SET utf8 FIELDS TERMINATED BY '**EOT**' ENCLOSED BY '' ESCAPED BY '' LINES STARTING BY '**BOL****EOT**' TERMINATED BY '***EOL***'; ALTER TABLE user ENABLE KEYS;" --database=absynth 
	$ del "C:\tmp\user.csv"

# Check version:
	SELECT @@version; -- s1-dlapp07: '5.0.95', dlm47: 5.6.21 
	SHOW VARIABLES LIKE "%version%";

	LOAD DATA INFILE '/tmp/c6/Person_legacy.csv'
	INTO TABLE person_legacy
	FIELDS TERMINATED BY ','
	ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES;


# Import
	LOAD DATA INFILE '/tmp/c6/Person_legacy.csv'
	INTO TABLE person_legacy
	FIELDS TERMINATED BY ','
	ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 ROWS;
	LOAD DATA INFILE '/tmp/c6/Person_legacy.csv'
	INTO TABLE person_legacy
	FIELDS TERMINATED BY ','
	ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 ROWS;


# UTF8 [Character Set and Collation](http://dev.mysql.com/doc/refman/5.0/en/charset-applications.html)
	CREATE DATABASE mydb
		DEFAULT CHARACTER SET utf8
		DEFAULT COLLATE utf8_general_ci;

	utf8_general_ci is somewhat faster than utf8_unicode_ci, but less accurate (for sorting). The specific language utf8 encoding (such as utf8_swedish_ci) contain additional language rules that make them the most accurate to sort for those languages. Most of the time I use utf8_unicode_ci (I prefer accuracy to small performance improvements), unless I have a good reason to prefer a specific language. (http://stackoverflow.com/questions/367711/what-is-the-best-collation-to-use-for-mysql-with-php)

# Table info to SELECT
	cat | awk '{print $1}' | tr '\n' ', ' | sed -e 's/,/, /g' ==> tr still output ',' because of char based
	cat | awk '{print $1}' | sed -e 's/\n/, /g' ==> doesn't work because line by line, instead of char
	cat | awk '{print $1}' | tr '\n' '\t' | sed -e 's/\t/, /g' 

	BusinessID	varchar(255)
	NAID	varchar(255)
	LegalName	varchar(255)
	DBAName	varchar(255)
	AddressLine1	varchar(255)
	==>
	BusinessID, NAID, LegalName, DBAName, AddressLine1, AddressLine2, AddressLine3, AddressLine4, AddressLine5, AddressLine6, PostCode, Country, ApprovalSent, OwnershipType, BusinessType, Department,


# How to import a csv file into MySQL workbench? [Ref](http://stackoverflow.com/questions/11429827/how-to-import-a-csv-file-into-mysql-workbench)
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

