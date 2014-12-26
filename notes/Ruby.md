# Learn [octokit](http://www.rubydoc.info/github/pengwynn/octokit/frames)

# Ruby 1.8 Note
	For Ruby 1.8 you must require 'rubygems' before requiring any gems.

# [rubygems-basics](http://guides.rubygems.org/rubygems-basics/)
	$ gem which sinatra
	/home/llin/.rvm/gems/ruby-1.8.7-p374/gems/sinatra-1.2.9/lib/sinatra.rb

	$ gem env
	RubyGems Environment:
		- RUBYGEMS VERSION: 2.0.9

	*. list all versions of a gem available at remote site
		$ gem list sinatra --remote --all

	*. Uninstall:
		 $ gem uninstall sinatra -v '< 1.4.3'

	*. $ gem install sinatra -v 1.3.3

	*. rvm use 1.8.6 (if rvm default doesn't work)


# Plug-ins[debugging-eclipse](http://stackoverflow.com/questions/13324722/debugging-eclipse-plug-ins)
To remote debug your plug-in, first add debug arguments to your target Eclipse .ini file
	-vmargs
	-Xdebug
	-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=1044

before launching it.

Then open another Eclipse instance with a workspace containing your plug-in project. Open Run > Debug Configurations..., select Remote Java Application and create a new configuration.

As Project, browse and select your plug-in project. Also fill in your connection properties (host of target Eclipse and port 1044).


# Debug in Eclipse
	/home/llin/.rvm/rubies/ruby-2.0.0-p247/lib/ruby/gems
	/home/llin/.rvm/rubies/ruby-2.0.0-p247/lib/ruby/user-gems/1.8/gems
	/home/llin/.rvm/rubies/ruby-2.0.0-p247/lib/ruby/gems/1.8/gems

	/home/llin/.rvm/rubies/ruby-1.9.3-p448/lib/ruby/gems/1.8/gems

	/home/llin/.rvm/gems/ruby-1.9.3-p448/gems

	/home/llin/.rvm/gems/ruby-1.9.3-p448/gems

	1.8:
	x /home/llin/.rvm/rubies/ruby-1.8.7-p374/lib/ruby/user-gems/1.8/gems
	v /home/llin/.rvm/rubies/ruby-1.8.7-p374/lib/ruby/gems/1.8/gems

	http://www.agile-workers.com/web/en/2012/09/debugging-ruby-1-9-3-applications-in-eclipse/
	$ gem install debugger
	$ gem install ruby-debug-ide

	cmdLine	String[9]: [/home/llin/.rvm/rubies/ruby-1.8.7-p374/bin/ruby, -KU, -I/home/llin/workspaces/scm/git-graph, -r/tmp/dltk47199.tmp/scripts/sync.rb, -r, /tmp/dltk47200.tmp/debugger/FastRunner.rb, -I, /tmp/dltk47200.tmp/debugger, /home/llin/workspaces/scm/git-graph/abc.rb]

	/home/llin/.rvm/rubies/ruby-1.8.7-p374/bin/ruby -KU -I/home/llin/workspaces/scm/git-graph -r/tmp/dltk47199.tmp/scripts/sync.rb -r /tmp/dltk47200.tmp/debugger/FastRunner.rb -I /tmp/dltk47200.tmp/debugger /home/llin/workspaces/scm/git-graph/abc.rb


	cmdLineLabel:	"/home/llin/.rvm/rubies/ruby-1.8.7-p374/bin/ruby -KU -I/home/llin/workspaces/scm/git-graph -r/tmp/dltk47199.tmp/scripts/sync.rb -r /tmp/dltk47200.tmp/debugger/FastRunner.rb -I /tmp/dltk47200.tmp/debugger /home/llin/workspaces/scm/git-graph/abc.rb"

	processLabel	"/home/llin/.rvm/rubies/ruby-1.8.7-p374/bin/ruby (Sep 23, 2013, 1:42:46 PM)" (id=13359)	

	pwd:
	/home/llin/.rvm/rubies/ruby-1.9.3-p448/lib/ruby/gems
	ln -s /home/llin/.rvm/gems/ruby-1.9.3-p448 ./1.8

# Require [DLTK](http://dev.eclipse.org/mhonarc/lists/dltk-commits/msg06303.html)
	./plugins/org.eclipse.dltk.ruby.launching_1.0.0.v20100524-0038.jar
	+<extension point="org.eclipse.dltk.core.loggingOptions">
	+   <loggingOption
	+   		category="Launching"
	+        id="catchOutput"
	+        name="Catch &amp; save output of the launched processes">
	+   </loggingOption>


	*. $ rvm default 1.9.3

	*.
	require 'csv'
	h = {}
	f = File.open('/home/llin/tests/em/files/130919_160458-D.csv')
	2.times {
		first_line = f.readline
	}
	CSV.parse(f, {:headers => true}).each do |row|
		h[row[0]]=row[2]
	end
	puts h

	File.open("/home/llin/workspace/scm/dltk-ruby/plugin-jar-files.txt", "r").each_line do |line|
		puts "\t<classpathentry kind=\"lib\" path=\"#{line.rstrip}\"/>"
	end


# ERB binding
  x = 1
  ERB.new('the value of x is: <%= x %>').result(binding)

# Intro [sinatra](http://www.sinatrarb.com/intro.html)
	gem install sinatra

# Eclipse [Debugger](http://www.agile-workers.com/web/en/2012/09/debugging-ruby-1-9-3-applications-in-eclipse/)
- [aptana](http://www.aptana.com/downloads/start)
- [Aptana Studio 3](http://download.aptana.com/studio3/plugin/install)
	vim ~/.rvm/gems/ruby-1.9.3-p448/gems/ruby-debug-ide-0.4.18/lib/ruby-debug-ide/xml_printer.rb 

	Setup env variables in Eclipse
	GEM_HOME=/home/llin/.rvm/gems/ruby-1.9.3-p448
	GEM_PATH=/home/llin/.rvm/gems/ruby-1.9.3-p448:/home/llin/.rvm/gems/ruby-1.9.3-p448@global

# How to [get Sinatra to auto-reload](http://stackoverflow.com/questions/1247125/how-to-get-sinatra-to-auto-reload-the-file-after-each-change)
	$ sudo gem install shotgun
	$ shotgun myapp.rb

# How do I make my Sinatra app [reload on changes?](http://www.sinatrarb.com/faq.html)
	$ gem install rerun
	$ rerun 'ruby app.rb'

# Install [RVM](http://rvm.io/)
	curl -L https://get.rvm.io | bash -s stable

# Uninstall using RVM
	$ sudo rvm remove ruby-1.9.3-p0
	/home/llin/.rvm/src/ruby-1.9.3-p0 has already been removed.
	/home/llin/.rvm/rubies/ruby-1.9.3-p0 has already been removed.
	Removing ruby-1.9.3-p0 aliases...
	Removing ruby-1.9.3-p0 wrappers...
	Removing ruby-1.9.3-p0 environments...
	Removing ruby-1.9.3-p0 binaries...
	** Uninstall doesn't work

	*. less /home/llin/progs/ruby-1.9.3-p0/.installed.list
		 cat .installed.list | sudo xargs rm -rf
		 ==> Bad, it will remove
		/usr/local/bin/
		/usr/local/lib/
		/usr/local/lib/ruby
		/usr/local/share/ri/1.9.1/system/Fixnum/%3c%3d%3e-i.ri
		/usr/local/lib/ruby/1.9.1/rss/maker/0.9.rb
		/usr/local/share/man/man1/ri.1
		/usr/local/lib/ruby/gems/1.9.1/specifications/rdoc-3.9.4.gemspec

		And /usr/bin/ruby is still there.
		./lib/fileutils.rb:333:  #   FileUtils.ln_s '/usr/bin/ruby', '/usr/local/bin/ruby'
		./lib/fileutils.rb:944:  #   FileUtils.chmod 0755, '/usr/bin/ruby', :verbose => true
		./lib/fileutils.rb:949:  #   FileUtils.chmod "u=wrx,go=rx", '/usr/bin/ruby', :verbose => true
		./.ext/common/pathname.rb:230:  #   Pathname.new("/usr/bin/ruby").each_filename {|filename| ... }

# [].method(:include?).parameters

# [Module](http://ruby-doc.org/core-2.0/Module.html#M000480)
A Module is a collection of methods and constants. The methods in a module may be instance methods or module methods. : 

# [Kernel](http://ruby-doc.org/core-2.0/Kernel.html#M001391)
The Kernel module is included by class Object, so its methods are available in every Ruby object. 

# [Method](http://www.ruby-doc.org/core-2.0/Object.html#method-i-method)
	String.public_instance_methods
	's'.methods
	’s’.public_methods

# [sqlite3](http://www.sqlite.org/download.html)
	$ gem list --remote --all sqlite
	sqlitedll-3_6_5.zip


# [rubyonrails](http://ar.rubyonrails.org/)

# The Lunch App
# check out the lunch app
	svn co http://svn/svn/sandbox/trunk/rubyLunch
	# check out LongView web app
	svn co http://svn/svn/longview-web/trunk/

	# create a new rails web app called proj42
	rails proj42

	# run the server
	cd proj42
	script/server        # Linux
	ruby script\server   # Windows
	# view in browser at http://localhost:3000/

	# create new scaffolding.  
	id column of type integer is automatically created
		script/generate scaffold product name:string description:text

	# push db changes (migrations) into database
		rake db:migrate

	# restart the server
	# view product listing in browser at http://localhost:3000/products/

# default routing
	# URL: http://localhost:3000/products/create
	# Runs "create" method of app/controllers/product_controller.rb
	# Then renders app/views/product/create.html.erb

# version of rails
	rails --version

# list all gems installed, with their versions
	gem list

# install a gem
	gem install sqlite-ruby    # Linux: you need sudo

# update/upgrade an old version of the rake gem
	gem update rake

# list all the rake tasks available
	rake -T

# list all rake tasks that have to do with mysql
	rake -T mysql

# add a column to a table
	script/generate migration add_price_to_product price:decimal

# another example of adding a column to a table
	script/generate migration add_order_by_to_restaurant order_by:integer
	# this generates a new migration file in db/migrate/
	# edit the self.up and self.down methods to call add_column and remove_column, respectively


