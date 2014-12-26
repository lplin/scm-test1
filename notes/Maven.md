# Missing maven-plugin-anno
- [maven-plugin-anno-1.2.4](http://repo.jfrog.org/artifactory/plugins-releases-local/org/jfrog/maven/annomojo/maven-plugin-anno/1.2.4/maven-plugin-anno-1.2.4.jar)

# Quick [Start](http://maven.apache.org/guides/getting-started/index.html)
	mvn archetype:generate -DgroupId=com.llin.interview -DartifactId=elevator -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

	mvn archetype:create -DgroupId=com.mycompany.app -DartifactId=my-app
	[WARNING] This goal is deprecated. Please use mvn archetype:generate instead

	mvn archetype:create \
		-DarchetypeGroupId=org.apache.maven.archetypes \
		-DgroupId=com.mycompany.app \
		-DartifactId=my-app

# Test
	mvn -Dtest=AppTest test
	mvn -Dtest=TestSquare,TestCi*le test

# Lifecycles
	mvn package
	default lifecycle phases executed.

* validate: validate the project is correct and all necessary information is available
* compile: compile the source code of the project
* test: test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed
* package: take the compiled code and package it in its distributable format, such as a JAR.
* integration-test: process and deploy the package if necessary into an environment where integration tests can be run
* verify: run any checks to verify the package is valid and meets quality criteria
* install: install the package into the local repository, for use as a dependency in other projects locally
* deploy: done in an integration or release environment, copies the final package to the remote repository for sharing with other developers and projects.

There are two other Maven lifecycles of note beyond the default list above. They are:
* clean: cleans up artifacts created by prior builds
* site: generates site documentation for this project

	mvn test-compile
	mvn eclipse:eclipse
	mvn process-resources
	mvn process-resources "-Dcommand.line.prop=hello again"

# [Dependency](http://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
The scope of the dependency - compile, runtime, test, system, and provided.

# Web App
	mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-webapp -DarchetypeArtifactId=maven-archetype-webapp

# Ignore Test
	maven.test.error.ignore: Set this to true to ignore errors during testing. Its use is NOT RECOMMENDED, but quite convenient on occasion.

# Get version
	$ xml_grep --text_only project/version pom.xml
	$ grep -A 1 -B 1 'artifactId>.*exmgr-parent' pom.xml | grep version
		<version>0.3.0-SNAPSHOT</version>

# Remove -SNAPSHOT in pom.xml
	perl -i -pe 's/2.0.0.1/2.0.0.1-SNAPSHOT/;' `find . -name pom.xml`


# Host key verification issue => kinit
	[INFO] [INFO] [deploy:deploy {execution: default-deploy}]
	[INFO] Uploading: scpexe://cvs.hmsonline.com/var/www/html/maven2/com/hmsonline/mdmutils/1.1.1.8/mdmutils-1.1.1.8.jar
	[INFO] [INFO] ------------------------------------------------------------------------
	[INFO] [ERROR] BUILD ERROR
	[INFO] [INFO] ------------------------------------------------------------------------
	[INFO] [INFO] Error deploying artifact: Error executing command for transfer
	[INFO]
	[INFO] Exit code 255 - Host key verification failed.

# [Deploy the site](http://intranet.hmsonline.com/confluence/display/SWDEV/Maven2Info)
	mvn site:site site:deploy


# Build failed in VNC: => Use putty
	[INFO] [site:attach-descriptor]
	[INFO] [install:install]
	[INFO] Installing /home/llin/projects/releases/auditrepo-1.0.2.4-branch/target/checkout/pom.xml to /home/llin/.m2/repository/com/hmsonline/auditrepo/auditrepo/1.0.2.7/auditrepo-1.0.2.7.pom
	[INFO] [deploy:deploy]
	Uploading: scpexe://cvs.hmsonline.com/var/www/html/maven2/com/hmsonline/auditrepo/auditrepo/1.0.2.7/auditrepo-1.0.2.7.pom
	[INFO] ------------------------------------------------------------------------
	[ERROR] BUILD ERROR
	[INFO] ------------------------------------------------------------------------
	[INFO] Error deploying artifact: Error executing command for transfer

	Exit code 255 - Permission denied (publickey,gssapi-with-mic,password).

# Multi module project
- [multi-module-projects](http://maven.apache.org/plugins/maven-eclipse-plugin/examples/multi-module-projects.html)
- [Sample from](http://maven.apache.org/plugins/maven-eclipse-plugin/examples/j2ee-simple.tar.gz)
- [create-multi-module-project](http://maven.apache.org/archetype/maven-archetype-plugin/examples/create-multi-module-project.html)
- <http://maven.apache.org/plugins/maven-eclipse-plugin/reactor.html>

# Moving from [Maven 1.x to Maven 2.x](http://intranet.hmsonline.com/confluence/display/SWDEV/Maven2Info#Maven2Info-Maven1.xvs.Maven2.x) ([guide-m1-m2](http://maven.apache.org/guides/mini/guide-m1-m2.html))
	mvn one:convert

# Maven 1
Modify project.properties to have:
	maven.repo.remote=http://plmproxy01/artifactory/libs-releases
	$ maven eclipse

# Func Tests
	[llin@llin-lin2 ra105x]$ mvn clean install -P func-tests

# [Wrong eclipse version](http://stackoverflow.com/questions/1397903/setting-project-for-eclipse-using-maven)
	mvn org.apache.maven.plugins:maven-eclipse-plugin:2.6:eclipse

	mvn dependency:tree
	*. Deploy:
	1) com.oracle:ojdbc14:jar:10.2.0.4.0

		Try downloading the file manually from the project website.

		Then, install it using the command:
			mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar -Dfile=/path/to/file

		Alternatively, if you host your own repository you can deploy the file there:
			mvn deploy:deploy-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar -Dfile=/path/to/file -Durl=[url] -DrepositoryId=[id]

		Path to dependency:
		  	1) com.hmsonline.bayermdm:bayermdm-functests:jar:1.1.2.1-SNAPSHOT
		  	2) com.oracle:ojdbc14:jar:10.2.0.4.0

# svn cannot tag pom.xml, already exists, work around:
	From:
	# mvn release:prepare
	=> fails
	# svn up -r head
	# mvn release:prepare -Dresume

# [Release](http://cvs.hmsonline.com/maven2/com/hmsonline/riteaid/02-riteaid-ear/1.0.1.3/)
	mvn release:prepare release:perform -X -Dusername=llin -Dpassword=****

	$ ssh cvs.hmsonline.com
	$ cd /var/www/html

	[INFO] 	Exit code 255 - Warning: Identity file /home/llin/.ssh/id_rsa not accessible: No such file or directory.
	[INFO] 	Host key verification failed.

	[llin@llin-lin2 .ssh]$ ssh-keygen -t rsa -C llin@hmsonline.com
	Generating public/private rsa key pair.
	Enter file in which to save the key (/home/llin/.ssh/id_rsa):
	/home/llin/.ssh/id_rsa already exists.
	Overwrite (y/n)? y
	Enter passphrase (empty for no passphrase):
	Enter same passphrase again:
	Your identification has been saved in /home/llin/.ssh/id_rsa.
	Your public key has been saved in /home/llin/.ssh/id_rsa.pub.
	The key fingerprint is:
	75:a3:d9:a0:50:7d:b1:29:91:c9:e0:b6:c4:34:dc:e4 llin@hmsonline.com

	http://maven.apache.org/maven-v4_0_0.xsd
	http://maven.apache.org/maven-v4_0_0.xsd
	[llin@llin-lin2 riteaid-mdm]$ grep -rni "javax/xml/transform" *

# Deploy ear
	mvn -DbuildName=test clean install ear-deployer:deploy-ear

	javadoc:javadoc

	??? mvn deploy:deploy-file -DgroupId=ant -DartifactId=ant-optional -Dversion=1.5.1 -Dpackaging=jar -Dfile=/home/llin/downloads/ant-optional-1.5.1.jar -Durl=http://build.hmsonline.com:9999/maven2Proxy/repository -DrepositoryId=hms-maven2-repos

# Dependency Reports
	mvn project-info-reports:dependencies -o
	[llin@llin-lin2 riteaid-mdm]$ MAVEN_OPTS=-Xmx512m mvn project-info-reports:dependencies



# Documentation system
	mvn archetype:create \
		-DarchetypeGroupId=org.apache.maven.archetypes \
		-DarchetypeArtifactId=maven-archetype-site \
		-DgroupId=com.mycompany.app \
		-DartifactId=my-app-site


# Web application
	mvn archetype:create \
		-DarchetypeGroupId=org.apache.maven.archetypes \
		-DarchetypeArtifactId=maven-archetype-webapp \
		-DgroupId=com.mycompany.app \
		-DartifactId=my-webapp

# Set Version
	This may be old news to some, but I just found this plugin, which will (among other things) set the version of your poms for you. I always hated having to manually do this (for instance, when a release fails and you need to revert your poms)..

	mvn versions:set -DnewVersion=1.0.1.0-SNAPSHOT

	WiThis may be old news to some, but I just found this plugin, which will (among other things) set the version of your poms for you. I always hated having to manually do this (for instance, when a release fails and you need to revert your poms)..

	mvn versions:set -DnewVersion=1.0.1.0-SNAPSHOT

	Will modify all your poms for you. It will also create backup copies of them first. You can get rid of these backups by doing

	mvn versions:commit  (this isn't 'svn commit', you still have to do that yourself)

	Or if you decide you don't like the new poms, you can revert to the backups by doing

	mvn versions:revert


	Theres a bunch of other features provided by this plugin that I didn't bother looking at, but I thought this was pretty handy.


	-Joshll modify all your poms for you. It will also create backup copies of them first. You can get rid of these backups by doing

	mvn versions:commit  (this isn't 'svn commit', you still have to do that yourself)

	Or if you decide you don't like the new poms, you can revert to the backups by doing

	mvn versions:revert


	Theres a bunch of other features provided by this plugin that I didn't bother looking at, but I thought this was pretty handy.


	-Josh


