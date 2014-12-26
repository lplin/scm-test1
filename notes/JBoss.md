# Enable log from the command
	export log4jbean='jboss.system:service=Logging,type=Log4jService'
	export twiddle="/usr/local/jboss/bin/twiddle.sh"
	export logger="com.hmsonline.auditrepo.ejb.qc.QCRunner"
	export twiddle="./twiddle.sh"
	scp llin-lin3://home/llin/projects/tevamdm/trunk/bin/twiddle.sh .

	$ export JBOSS_HOME=/usr/local/jboss
	$twiddle invoke $log4jbean getLoggerLevel $logger
	$twiddle invoke $log4jbean setLoggerLevel $logger DEBUG
		getLogLevel)
		  	[ $# -ne 2 ] && usage
		  	logger=$2
		  	echo Log level for $logger is
		  	$twiddle invoke $log4jbean getLoggerLevel $logger
		  	;;

		setLogLevel)
		  	[ $# -ne 3 ] && usage
		  	logger=$2
		  	level=$3
		  	echo Setting $loggestorager to $level
		  	$twiddle invoke $log4jbean setLoggerLevel $logger $level
		  	;;

# JMS: 
	CREATE USER jms
	IDENTIFIED BY jms123
	DEFAULT TABLESPACE ramdm_workflow_data
	TEMPORARY TABLESPACE temp
	PROFILE DEFAULT
	QUOTA UNLIMITED ON ramdm_workflow_data
	QUOTA UNLIMITED ON ramdm_workflow_idx

	GRANT CREATE SESSION TO jms;
	GRANT CREATE TABLE TO jms;
	GRANT CREATE ANY SEQUENCE TO jms WITH ADMIN OPTION;

	Make sure in /usr/local/jboss/server/riteaid/deploy/jms/pm-service.xml
	BLOB_TYPE=BINARYSTREAM_BLOB

