# See [Debugger](http://www.agile-workers.com/web/en/2012/09/debugging-ruby-1-9-3-applications-in-eclipse/)
	http://www.aptana.com/downloads/start
	Aptana Studio 3: http://download.aptana.com/studio3/plugin/install 
	vim ~/.rvm/gems/ruby-1.9.3-p448/gems/ruby-debug-ide-0.4.18/lib/ruby-debug-ide/xml_printer.rb 

	Setup env variables in Eclipse
	GEM_HOME=/home/llin/.rvm/gems/ruby-1.9.3-p448
	GEM_PATH=/home/llin/.rvm/gems/ruby-1.9.3-p448:/home/llin/.rvm/gems/ruby-1.9.3-p448@global


# A [bug](https://bugs.eclipse.org/bugs/show_bug.cgi?id=345976)
	Eclipse Kepler (64bit), DLTK Ruby 5.0.0.201306060709, Ruby 1.9.3p392 32bit, Windows 7 64bit, debugger 1.6.0: Works
	Eclipse Kepler (64bit), DLTK Ruby 5.0.0.201306060709, Ruby 2.0.0p0   32bit, Windows 7 64bit, debugger 1.6.0: Works
	Eclipse Kepler (64bit), DLTK Ruby 5.0.0.201306060709, Ruby 2.0.0p195 64bit, Windows 7 64bit, debugger 1.6.0: Works

	(debugger means debugger gem)

	So I guess, this bug can be set to resolved/fixed :-)
	==> Ruby 2.0.0p195 doesn't work in Linux, 
	/home/llin/.rvm/rubies/ruby-2.0.0-p247/bin/ruby: symbol lookup error: /home/llin/.rvm/gems/ruby-2.0.0-p195/gems/debugger-1.6.0/lib/ruby_debug.so: undefined symbol: rb_vm_get_sourceline

	==> 1.9.3 works
	Install in Eclipse: DLTK R-5.0 - file:/home/llin/progs/t/dltk/dltk-core-I-201306060709/
				 DLTK Ruby R-5.0 - file:/home/llin/progs/t/dltk/dltk-ruby-I-201306060709/

# Steps: DLTK 2.0
	$ rvm install 1.8.7
	Expected:
		1.8:
		x /home/llin/.rvm/rubies/ruby-1.8.7-p374/lib/ruby/user-gems/1.8/gems
		v /home/llin/.rvm/rubies/ruby-1.8.7-p374/lib/ruby/gems/1.8/gems
	$ gem install 'ruby-debug'
	$ gem install 'ruby-debug-ide'

	$ cat eclipse-scm.sh 
	#!/usr/bin/env bash
	[[ -s "$HOME/.rvm/scripts/rvm" ]] && source "$HOME/.rvm/scripts/rvm" # Load RVM into a shell session *as a function*
	rvm use 1.8.7
	/home/llin/progs/eclipse-std-kepler/eclipse -data /home/llin/workspaces/scm

	*. rvm use 1.8.6 (if rvm default doesn't work)


# [debugging-eclipse-plug-ins](http://stackoverflow.com/questions/13324722/debugging-eclipse-plug-ins)
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

	*. http://dev.eclipse.org/mhonarc/lists/dltk-commits/msg06303.html
	./plugins/org.eclipse.dltk.ruby.launching_1.0.0.v20100524-0038.jar
	+<extension point="org.eclipse.dltk.core.loggingOptions">
	+   <loggingOption
	+   		category="Launching"
	+        id="catchOutput"
	+        name="Catch &amp; save output of the launched processes">
	+   </loggingOption>


	*. $ rvm default 1.9.3
