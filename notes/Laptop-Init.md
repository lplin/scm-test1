# WIFI: 2LP5QN8GGX3GH9VJ
# Inv table name case insensitive issue
	CALL gen_all_day_ranks(2691);

	SELECT * FROM TWRNK
	WHERE feedId=2691
	-- WHERE sym='1101' AND dur=1
	ORDER BY feedId DESC;

	SELECT * FROM TWSE
	-- WHERE feedId=5995;
	WHERE sym='1101'
	ORDER BY feedId DESC;


	SELECT * FROM Feed ORDER BY Id DESC;


	*.
	SELECT * FROM feed ORDER BY id DESC;
	ALTER TABLE deal RENAME TO deal1;

	ALTER TABLE dividend RENAME TO Dividend;
	ALTER TABLE feed RENAME TO Feed;
	ALTER TABLE gretai RENAME TO GreTai;
	ALTER TABLE groupedwatch RENAME TO GroupedWatch;
	ALTER TABLE invest RENAME TO Invest;
	ALTER TABLE itmval RENAME TO ItmVal;
	ALTER TABLE twrnk RENAME TO TWRNK;
	ALTER TABLE twse RENAME TO TWSE;
	ALTER TABLE taggedinvest RENAME TO TaggedInvest;
	ALTER TABLE tx RENAME TO Tx;
	ALTER TABLE watch RENAME TO Watch;

	SELECT CONCAT('ALTER TABLE ', LOWER(t1.table_name), ' RENAME TO ', t1.table_name, ';')
	FROM information_schema.tables t1
	WHERE t1.table_schema='inv2' AND t1.table_type='BASE TABLE'
		AND SUBSTRING(BINARY t1.table_name, 1, 1) = UPPER(SUBSTRING(t1.table_name, 1, 1))
	;


	SELECT CONCAT('DROP TABLE ', table_name, ';') FROM information_schema.tables t1
	-- INNER JOIN information_schema.tables t2 ON LOWER(t2.table_name)=t1.table_name
	WHERE table_schema='inv2' AND table_type='BASE TABLE'
		AND SUBSTRING(BINARY t1.table_name, 1, 1) = UPPER(SUBSTRING(t1.table_name, 1, 1))
	;

	DROP TABLE Dividend;
	DROP TABLE Feed;
	DROP TABLE GreTai;
	DROP TABLE GroupedWatch;
	DROP TABLE Invest;
	DROP TABLE ItmVal;
	DROP TABLE TWRNK;
	DROP TABLE TWSE;
	DROP TABLE TaggedInvest;
	DROP TABLE Tx;
	DROP TABLE Watch;


	SELECT COLLATION(VERSION());

# copy.com: liping.lin.us@gmail.com: 8x8

# Enable [Java Applet](http://www.wikihow.com/Enable-Oracle-Java-in-Your-Web-Browsers-on-Ubuntu-Linux)
	$ sudo ln -s /home/lplin/apps/jdk1.6.0_45/jre/lib/amd64/libnpjp2.so 

# MySQL import
	CREATE USER 'sa'@'%' IDENTIFIED BY '88

	Move all functions before the first PROCEDURE.
	$ mysqldbimport --server=root:8888@localhost --import=both --bulk-insert ./inv2.sql

	GRANT ALL ON inv2.* TO 'sa'@'%';
	GRANT ALL ON inv2.* TO 'sa'@'localhost';

	comment out all accidentally run.

# VPN
	dlm47: 10.10.12.11 
	export GROUP_PASS=RDC_n3v3r2b3us3d_RDC

# [Burg](http://www.dedoimedo.com/computers/grub-2.html#mozTocId722095)
	Type: others
	set root='(hd0,1)'
	chainloader +1

# MySQL install
	$ sudo apt-get install mysql-workbench
	$ sudo apt-get install mysql-server
	$ sudo apt-get install mysql-utilities

# [mysqldbexport](http://utilsmysql.blogspot.com/2014/01/improved-performance-of-data.html)
	mysqldump -d -u root -p8888 --routines inv2 > c:\temp\inv2.sql
	mysqldbexport --server=root:8888@localhost --export=both --bulk-insert inv > 'c:\temp\inv.sql'
	mysqldbexport --server=root:8888@localhost --export=both --bulk-insert inv1 > 'c:\temp\inv1.sql'
	mysqldbexport --server=root:8888@localhost --export=both --bulk-insert inv2 > 'c:\temp\inv2.sql' (no drop, with procedures)
	 
	mysql -uroot -p8888 < /media/mint/669C99FF9C99C9C7/temp/inv.sql

	mysqldbimport --server=root:8888@1ocalhost --import=both --bulk-insert /media/mint/669C99FF9C99C9C7/temp/inv1.sql


	mysqldbimport --server=root:8888@1ocalhost --import=both --multiprocess=2 --bulk-insert world_export.sql
		
	mysql> show databases;
	+--------------------+
	| Database           |
	+--------------------+
	| information_schema |
	| inv                |
	| inv1               |
	| inv2               |
	| mysql              |
	| performance_schema |
	| sakila             |
	| test               |
	| world              |
	+--------------------+  

	http://forums.linuxmint.com/viewtopic.php?f=46&t=113309

