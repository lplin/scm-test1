# Links
- [creating-a-directory](http://www.puppetcookbook.com/posts/creating-a-directory.html)
- [techniques](http://docs.puppetlabs.com/guides/techniques.html)

# Starting the Puppetmaster:
	$ sudo puppet master --mkusers
	$ sudo puppet agent --test --server=`hostname`
	err: Could not retrieve catalog from remote server: hostname not match with the server certificate
	warning: Not using cache on failed catalog
	err: Could not retrieve catalog; skipping run

# [Puppet tutorial](http://bitfieldconsulting.com/puppet-tutorial)
	$ apt-get install libopenssl-ruby rdoc libopenssl-ruby1.8 libreadline-ruby1.8 libruby1.8 rdoc1.8 ruby1.8
	$ wget http://puppetlabs.com/downloads/facter/facter-1.5.8.tar.gz
	$ wget http://puppetlabs.com/downloads/puppet/puppet-2.6.4.tar.gz
	$ tar xvzf facter-1.5.8.tar.gz
	$ cd facter-1.5.8
	$ ruby install.rb
	$ cd ..
	$ tar xvzf puppet-2.6.4.tar.gz
	$ cd puppet-2.6.4
	$ ruby install.rb
	$ puppetd --version

# Puppet Install
	$ wget ftp://fr2.rpmfind.net/linux/epel/5/x86_64/epel-release-5-4.noarch.rpm
	$ sudo rpm -Uvh epel-release-5-4.noarch.rpm
	$ sudo yum install puppet
	$ puppet apply manifests/site.pp --modulepath modules/
	notice: Scope(Class[Tomcat]): Installing Tomcat(apache-tomcat-6.0.35.tar.gz) in
	 /opt/tomcat].
	notice: /Stage[main]//Package[libtool]/ensure: created
	FACTER_somevar=2.1 puppetd --test
	 will expose $somevar=2.1 to your manifests



