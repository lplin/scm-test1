# GitHub lplin 
-	[scm-test1](https://github.com/lplin/scm-test1): liping.ln@gmail.com:8la
- [ext001](https://github.com/nature-tech/ext001): liping.lin.us@gmail.com:8la
-	[elevator](https://github.com/liping-lin/elevator): liping.lin.us@gmail.com:8la
```bash
	lplin@dlm47 ~/gits/lplin/scm-test1 $ git push origin master
	Username for 'https://github.com': lplin
	Password for 'https://lplin@github.com': 8l
	Everything up-to-date
```

# Without [username](http://stackoverflow.com/questions/6565357/git-push-requires-username-and-password)
	git remote set-url origin https://lplin@github.com/lplin/scm-test1.git

# [Stash](http://git-scm.com/book/en/Git-Tools-Stashing)
- To push a new stash onto your stack, run:	`$ git stash`
- Your working directory is clean: `$ git status`
- To see which stashes you’ve stored: `$ git stash list`
- If you want to apply one of the older stashes, you can specify it by naming it, like this: `$ git stash apply stash@{2}.`
- If you don’t specify a stash, Git assumes the most recent stash and tries to apply it: `$ git stash apply`

# Delete a Github tag
	$ git push --delete origin ozone-auth-
	To http://plgithub01.hmsonline.com/hudson/ozone-auth-devops.git
	 - [deleted]         ozone-auth-

# Pull tags
	git pull hms

# Push tags
	git push origin --tags

# Drop all changes:
	git checkout -- .

# Revert [back to up to a commit](http://stackoverflow.com/questions/1338728/how-to-delete-a-git-commit)
	git reset --hard 810bdf5a5946052cef98f03a7de4d8aa46cf9e8c
	git push origin master --force

# Revert all the commits:
	git reset --hard HEAD~1
	git push origin master --force

# Puppet
  exec { 'curl creds':
    path => '/bin/:/usr/bin/',
    command => "echo -e 'machine plgithub01.hmsonline.com\nlogin hudson\npassword 4codeCheckout' > ~/.netrc",
  }
 
	http://plgithub01.hmsonline.com/hms/expense-manager/raw/master/db/scripts/20120515000000_create_changelog.sql

# svn2git
svn2git http://svn.hmsonline.com/svn/platform/devops --notags --nobranch --authors ../ozone-authors.txt
*. $ svn log --quiet | grep -E "r[0-9]+ \| .+ \|" | awk '{print $3}' | sort | uniq > ../devops-authors.txt

# [init.pp](http://svn/svn/platform/devops/trunk/cirrus/puppet/modules/storm/manifests/init.pp)
	exec { "git clone http://github.com/nathanmarz/jzmq.git":
		path => "/usr/bin/:/bin/",
		cwd => "/tmp/",
		returns => ["0","128"],
		alias => "fetch jzmq",
		require => Package["git"],
	}

	machine llin-lin2.ws.hmsonline.com
	login hudson
	password 4codeCheckout

	Permission to hms/expense-manager.git denied to hudson.

# em-release-test
	[INFO] Executing: /bin/sh -c cd /vol/hudson_data/hudson.hudson/jobs/em-release-test/t1/expense-manager && git push http://plgithub01.hmsonline.com/llin/expense-manager.git master:master
	[ERROR] The git-push command failed.
	[ERROR] Command output:
	[ERROR] error: The requested URL returned error: 403 while accessing http://plgithub01.hmsonline.com/llin/expense-manager.git/info/refs?service=git-receive-pack
	[ERROR] fatal: HTTP request failed

	+ echo RSA
	RSA
	+ cat /opt/hudson/.ssh/id_rsa.pub
	ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEA4cvuMBT64QwW2YUyYhmUr6CMQBfI5QWYzFEdAPqLzddAmyJ4l4osR4GhLerRe12hAmhyJkoMlvReoI5hJ5CsaV06mUR2NULHtvQGq7f3YB1AH0zmSNxx242XXN4Kd6eLQCeqOa1lTzulQ70eSCjXePD48FovkerJEFO+ixJVpeeFQk/gkupedImjmvm9J1HSkXf60M0JpfOqTtEPme4FKvNzXMYIS0uryI9XkSPB+N8J89G/pvOmyx+ZTojDWvDvEJX/mUVIXinZuf+0f4Cf247LhDCYYzNhw/GR1KmBte4Hu0I1cpEzHi5wvFusuGoCaEvPhlHINmQQIq3KQo2qMQ== hudson@hmsonline.com
	+ echo DSA
	DSA
	+ cat /opt/hudson/.ssh/id_dsa.pub
	ssh-dss AAAAB3NzaC1kc3MAAACBAPWQleEooi1oRh094op1F3J17cq2loaJjZTbjFKLLwX09HLeUFn/PRNZ+tCVYhwVmHVGZrUkD5jlGPJBbqKYxD75yBLHxt8TgiEMCSTFUwyb7J0315MYcmZmpIw/SI0Bs/5I92R3wCQ2jOBiEEZ7TxiMMDd5HEzwxKc1jxGDUhbtAAAAFQD4WZcpmwnGTV3OeKovthF3rnJPqQAAAIBmg+UBiqbQ6ZvxSXEgBDnsJLhRrI9e09J9v+45orZFc0yYnx3l4uf1jk/5Pt0AIukQHHMZWhLhFt9x8zwTd+FV3ydOX6LOLWsTfj/C5iyeHRDWLXku8fdVggqDTHC0iliByR2MFBYcqrwyJ0W2D5VHIy1uq58WCPR2XOeoMQixGwAAAIEA6pJ5/9eyPnRAuE3vRPb2cod+D1dlyDv8ocYVXo7/5PGYCdL9WBlSOWBoXB6wdHDSTj4i24+Vom4x++ly9Y2wVplsBY8Zgm9/OAhYCwZwvIiyP0uJjXTwqwppT1gxLoEbMfkEeQ2R6pg/vLcXOgyvjtqt6TusgMTzV3GoltF9h7Q= hudson@pldevs01.hmsonline.com
	+ mkdir t2

# SCM: Not work
	http://plgithub01.hmsonline.com/hms/expense-manager.git
		  <scm>
		      <connection>scm:git:git@plgithub01.hmsonline.com:hms/expense-manager.git</connection>
		      <developerConnection>scm:git:git@plgithub01.hmsonline.com:hms/expense-manager.git</developerConnection>
		      <url>scm:git:git@plgithub01.hmsonline.com:hms/expense-manager.git</url>
		  </scm>
	or
		  <scm>
		      <connection>scm:git:http://plgithub01.hmsonline.com/hms/expense-manager.git</connection>
		      <developerConnection>scm:git:http://plgithub01.hmsonline.com/hms/expense-manager.git</developerConnection>
		      <url>scm:git:http://plgithub01.hmsonline.com/hms/expense-manager.git</url>
		  </scm>

# Working
	rm -rf /tmp/exmgr
	which git
	env
	export PATH=/home/llin/progs/git-1.8.1.3:/usr/maven/apache-maven-3.0.3/bin:/usr/java/jdk1.6.0_13/bin:/usr/kerberos/bin:/usr/local/bin:/bin:/usr/bin
	export TERM=xterm
	export LD_LIBRARY_PATH=/home/llin/lib
	which git
	git --version
	mvn -v
	cd /opt/hudson/.hudson/jobs/em-release-test
	rm -rf t1
	mkdir t1
	cd t1
	/usr/bin/git clone http://plgithub01.hmsonline.com/hms/expense-manager.git
	cd expense-manager
	mvn release:prepare release:perform
	cd ../..
	mkdir t1

	+ cat /opt/hudson/.netrc
	machine plgithub01.hmsonline.com
	login hudson
	password 4codeCheckout

# Source download [git-core](https://code.google.com/p/git-core/downloads/list), [git](https://github.com/git/git)
	git-1.7.4.1.tar
	http://pkgs.fedoraproject.org/repo/pkgs/git/git-1.7.4.1.tar.bz2/76898de4566d11c0d0eec7e99edc2b5c/

	./configure --with-curl=/usr/bin/curl
	./configure --with-curl=/usr/local/bin/curl

	USE_CURL_MULTI

# Release EM:
	rm -rf /tmp/exmgr
	which git
	env
	export PATH=/usr/maven/apache-maven-3.0.3/bin:/usr/java/jdk1.6.0_13/bin:/usr/kerberos/bin:/usr/local/bin:/bin:/usr/bin
	export TERM=xterm
	unset LD_LIBRARY_PATH
	mvn -v
	cd /opt/hudson/.hudson/jobs/em-release-test
	rm -rf t1
	mkdir t1
	cd t1
	/usr/bin/git clone http://plgithub01.hmsonline.com/llin/expense-manager.git
	cd expense-manager
	mvn release:prepare release:perform
	cd ../..
	mkdir t1

	git checkout master

	export LD_LIBRARY_PATH=/usr/lib/:/usr/lib64/

	LD_LIBRARY_PATH
	$LD_LIBRARY_PATH:/usr/lib/:/usr/lib64/

# Then Push 
	$ sudo rm /usr/local/bin/git*
	git push http://plgithub01.hmsonline.com/llin/expense-manager.git master:master

	git remote add origin http://plgithub01.hmsonline.com/llin/ozone.git
	git remote add llin http://plgithub01.hmsonline.com/llin/ozone-auth.git

	$ git remote add origin http://plgithub01.hmsonline.com/hms/ozone.git
	$ git push -u origin master
		svnuser => 'hudson',
		password => '4codeCheckout',

		
# SVN EM Devops
	export EM_VERSION=`/home/llin/tools/get-em-version.rb`
	svn copy http://svn.hmsonline.com/svn/platform/ozone/trunk http://svn.hmsonline.com/svn/expense-manager/tags/exmgr-parent-${EM_VERSION}/ozone -m "EXMGR-38: Ozone backup."
	svn delete http://svn.hmsonline.com/svn/expense-manager/tags/exmgr-parent-${EM_VERSION}/ozone/auth/internal -m "EXMGR-38: Remove EM unrelated Ozone directory: auth/internal."
	svn delete http://svn.hmsonline.com/svn/expense-manager/tags/exmgr-parent-${EM_VERSION}/ozone/service/cirrus -m "EXMGR-38: Remove EM unrelated Ozone directory: service/cirrus."
	svn delete http://svn.hmsonline.com/svn/expense-manager/tags/exmgr-parent-${EM_VERSION}/ozone/ui/cc -m "EXMGR-38: Remove EM unrelated Ozone directory: ui/cc."
	svn mkdir http://svn.hmsonline.com/svn/expense-manager/tags/exmgr-parent-${EM_VERSION}/devops -m "EXMGR-38: devops backup."
	svn copy http://svn.hmsonline.com/svn/platform/devops/trunk/share http://svn.hmsonline.com/svn/expense-manager/tags/exmgr-parent-${EM_VERSION}/devops/share -m "EXMGR-38: devops/share backup."
	svn copy http://svn.hmsonline.com/svn/platform/devops/trunk/ozone http://svn.hmsonline.com/svn/expense-manager/tags/exmgr-parent-${EM_VERSION}/devops/ozone -m "EXMGR-38: devops/ozone backup."
	svn copy http://svn.hmsonline.com/svn/platform/devops/trunk/expense-manager  http://svn.hmsonline.com/svn/expense-manager/tags/exmgr-parent-${EM_VERSION}/devops/expense-manager -m "EXMGR-38: devops/em backup"

# [To revert](http://stackoverflow.com/questions/4114095/git-revert-to-previous-commit-how)
	# This will destroy any local modifications
	# Don't do it if you have uncommitted work you want to keep
	git reset --hard 0d1d7fc32

# Distributed-Git [Distributed-Workflows](http://git-scm.com/book/en/Distributed-Git-Distributed-Workflows)

# To [avoid password](http://stackoverflow.com/questions/11021803/need-github-without-username-and-password)
	$ cat ~/.netrc
	machine plgithub01.hmsonline.com
	login llin
	password ***


# Clone
	$ git clone git@plgithub01.hmsonline.com:llin/ozone.git
	Cloning into 'ozone'...
	Permission denied (publickey).
	fatal: The remote end hung up unexpectedly

	==>

	$ git clone http://plgithub01.hmsonline.com/llin/ozone.git
	Cloning into 'ozone'...

	(gnome-ssh-askpass:14511): Gtk-WARNING **: cannot open display:
	error: unable to read askpass response from '/usr/libexec/openssh/gnome-ssh-askpass'
	Username for 'http://plgithub01.hmsonline.com': llin

	(gnome-ssh-askpass:14512): Gtk-WARNING **: cannot open display:
	error: unable to read askpass response from '/usr/libexec/openssh/gnome-ssh-askpass'
	Password for 'http://llin@plgithub01.hmsonline.com':
	remote: Counting objects: 30281, done.
	remote: Compressing objects: 100% (11602/11602), done.
	remote: Total 30281 (delta 19678), reused 28402 (delta 17803)
	Receiving objects: 100% (30281/30281), 38.25 MiB | 7.11 MiB/s, done.
	Resolving deltas: 100% (19678/19678), done.


# SVN ton git Try
	branches/dark-ui-theme-02.14.2013

	svn2git http://svn.hmsonline.com/svn/platform/ozone --notrunk --notags --branch branches/dark-ui-theme-02.14.2013 --authors ../ozone-authors.txt


	svn2git http://svn.hmsonline.com/svn/platform/ozone --notrunk  --notags  --authors ./authors.txt -v
	 szheng = Sam Zheng <szheng@healthmarketscience.com>

	export GEM_HOME=/usr/local/lib/ruby/gems/1.9.1
	export GEM_PATH=/usr/local/lib/ruby/gems/1.9.1

	[llin@llin-lin3 ozone]$ ruby -v
	ruby 1.9.3p0 (2011-10-30 revision 33570) [x86_64-linux]

	$ git --version
	git version 1.7.10-rc4


	492  git svn
		493  yum install git-svn
		494  sudo yum install git-svn
		495  git-svn 
		496  git-core
		497  $GEM_HOME
		498  ls -la $GEM_HOME
		499  vim ~/.bash_profile 
		500  which gem
		501  which rake
		502  sudo gem install svn2git
		503  yum install psych
		504  sudo yum install psych
		505  mkdir git
		506  cd git
		507  ll
		508  svn2git --help


	686  sudo yum install git --skip-broken

	sudo yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel

# [intro](http://learn.github.com/p/intro.html)

# ProGit [download](http://labs.kernelconcepts.de/downloads/books/Pro%20Git%20-%20Scott%20Chacon.pdf)

# Failed to connect to repository
	Command "/usr/bin/git ls-remote -h git@plgithub01.hmsonline.com:hms/ozone/auth/external.git HEAD" returned status code 128:
	git ls-remote -h git@plgithub01.hmsonline.com:hms/ozone

# [setup](http://learn.github.com/p/setup.html)

# Git [HowTo](http://lesseverything.com/blog/archives/2008/03/25/got-git-howto-git-and-github/)
	*.git clone [link to repo here]
	git@plgithub01.hmsonline.com:hms/ozone.git

	git clone http://plgithub01.hmsonline.com/hms/ozone/auth/external

	git clone http://plgithub01.hmsonline.com/hms/ozone

	*.
	git@plgithub01.hmsonline.com:hms/expense-manager.git
	git@plgithub01.hmsonline.com:hms/ozone.git

	*.
	$ git remote add origin http://plgithub01.hmsonline.com/hms/ozone.git
	$ git push -u origin master

	*. $ svn log --quiet | grep -E "r[0-9]+ \| .+ \|" | awk '{print $3}' | sort | uniq > ~/gits/ozone-authors.txt

	*.svn2git http://svn/svn/platform/ozone --notrunk --k --authors ../ozone-authors.txt --verbose

	*.
	git@plgithub01.hmsonline.com:hms/expense-manager.git

	git@plgithub01.hmsonline.com:hms/reactor.git


	*. sudo yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel

# EM Init
	touch README.md
	git init
	git add README.md
	git commit -m "EXMGR-200: Setup the project"
	git remote add hms http://plgithub01.hmsonline.com/hms/expense-manager.git
	git push -u hms master

	Push an existing repository from the command line

	git remote add origin http://plgithub01.hmsonline.com/hms/expense-manager.git
	git push -u origin master


- importing-[from-subversion](https://help.github.com/articles/importing-from-subversion)
- [svn2git](https://github.com/nirvdrum/svn2git#readme)
	$ svn2git http://svn/svn/expense-manager --nobranches --authors ../em-authors.txt --verbose


# Create a new repository on the command line
	touch README.md
	git init
	git add README.md
	git commit -m "first commit"
	git remote add origin http://plgithub01.hmsonline.com/llin/expense-manager.git
	git push -u origin master

	Push an existing repository from the command line

	git remote add origin http://plgithub01.hmsonline.com/llin/expense-manager.git
	git push -u origin master

	*. Processing Dependency: git = 1.7.4.1-1.el5 for package: git-svn
	git-svn 1.7.10.2


