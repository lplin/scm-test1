# # Backup SVN changed files
$ svn st | awk '{ print $2}' | grep src | grep -v '/resources' | xargs -I f1 cp --parents f1 ~/tmp/tt

# SVN propedit
	svn propedit svn:ignore .
	When you run that command svn will open your text editor, and this is where you can define patterns or specific files to ignore. If you place a * in the property file,

	For only one file:
	svn propset svn:ignore .project .

	[llin@llin-lin2 db]$ svn log install/ra_rds/scripts/grant_perms_for_mdb_rds.sql | more

	[llin@llin-lin2 ra]$ svn diff --old=http://svn/svn/riteaid-mdm/tags/riteaid-mdm-1.0.1.5/ --new=.

# SVN Ignore
	$ svn propset svn:ignore "cc" /home/llin/projects/platform/ozone/trunk/ui/cc
		property 'svn:ignore' set on '/home/llin/projects/platform/ozone/trunk/ui/cc'

# Find out which branch and revision number the tagtags it's sufficient to do:
	$ svn log -v -q --stop-on-copy http://svn/svn/datastore/tags/datastore-1.1.7.3 | grep "   A"
	$ svn log -v -q --stop-on-copy http://svn/svn/datastore/tags/datastore-1.1.7.3 | grep "   A"
		 A /datastore/tags/datastore-1.1.7.3 (from /datastore/branches/ds_m1:80055)

# [JBoss 4.2.x Properties File Deployer](https://github.com/aaronwalker/JBoss-Properties-File-Deployer)
	git clone git://github.com/aaronwalker/JBoss-Properties-File-Deployer.git

# CVS
	set CVSROOT=:ext:llin@cvs.hmsonline.com:/home/cvs
	$ cvs co hms_delivery/bin/dp_guid_xwalk
	llin@cvs.hmsonline.com's password:
	U hms_delivery/bin/dp_guid_xwalk

# SVN cannot tag pom.xml, already exists, work around:
	From:
	# mvn release:prepare
	=> fails
	# svn up -r head
	# mvn release:prepare -Dresume

# POM file diff
	svn st -v pom.xml
	svn log pom.xml
	svn diff -r 63476 pom.xml
	svn cat -r 63655 pom.xml

# Branch
	1.svn copy http://svn/svn/riteaid-mdm/tags/riteaid-mdm-1.0.1.5 http://svn/svn/riteaid-mdm/branches/riteaid-mdm-1.0.1.5-PROD
	(from: http://research.mosuma.com/faq/howto/svn_branch_merge)
	2.check out the branch
	3.Batch modification of pom.xml
	perl -i -pe 's/1.0.1.5/1.0.1.5.1-SNAPSHOT/;' `find . -name pom.xml`
	4.modify the scm section of the root pom.xml, to make sure the url exists.
	<scm>
		<url>https://svn.hmsonline.com/svn/riteaid-mdm/branches/riteaid-mdm-1.0.3.0-branch</url>
		<connection>scm:svn:http://svn.hmsonline.com/svn/riteaid-mdm/branches/riteaid-mdm-1.0.3.0-branch</connection>
		<developerConnection>scm:svn:http://svn.hmsonline.com/svn/riteaid-mdm/branches/riteaid-mdm-1.0.3.0-branch</developerConnection>
	</scm>

# Remove Windows ^M:
	$ svn diff | less
	$ svn status | awk '/^M/ {print $2}'
	$ svn status | awk '/^M/ {print $2}' | xargs rm

	svn commit -m 'preparing for next release'

# copy specific files with the structure
	[llin@llin-lin2 riteaid-mdm]$ svn st | awk '{print $2}' | cpio -pavdu ../branches/ra1013

# List changes in a revision
	[llin@llin-lin2 riteaid-mdm]$ svn log -v -r72328
	------------------------------------------------------------------------
	r72328 | llin | 2010-05-20 10:49:48 -0400 (Thu, 20 May 2010) | 1 line
	Changed paths:
		 M /riteaid-mdm/trunk/ejb/loader-service/src/main/resources/com/hmsonline/riteaid/hmsdata/create_fn_on_fullname_at_address.sql
		 M /riteaid-mdm/trunk/ejb/loader-service/src/main/resources/com/hmsonline/riteaid/hmsdata/create_practs_addresses_for_riteaid
		 M /riteaid-mdm/trunk/ejb/loader-service/src/main/resources/com/hmsonline/riteaid/hmsdata/fn_on_fullname_address.sql
		 M /riteaid-mdm/trunk/ejb/loader-service/src/main/resources/com/hmsonline/riteaid/hmsdata/fn_on_fullname_dea.sql
		 M /riteaid-mdm/trunk/ejb/loader-service/src/main/resources/com/hmsonline/riteaid/hmsdata/fn_on_fullname_distance_identifer.sql
		 M /riteaid-mdm/trunk/ejb/loader-service/src/main/resources/com/hmsonline/riteaid/hmsdata/fp_on_npi.sql

	RAMDM-438: Comments and styles.
	------------------------------------------------------------------------



