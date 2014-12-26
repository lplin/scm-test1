# Github project
	touch README.md
	git init
	git add README.md
	git commit -m "first commit"
	git remote add origin https://github.com/liping-lin/elevator.git
	git push -u origin master

	git remote add origin https://github.com/liping-lin/elevator.git
	git push -u origin master

# Unapply a stash
	$ git stash list
	stash@{0}: WIP on SUI-381: fd0935e [maven-release-plugin] prepare for next development iteration
	stash@{1}: WIP on SEARCH-108: 764ce50 [maven-release-plugin] prepare for next development iteration
	$ git stash show -p stash@{1} | git apply -R

# Reset
	git reset --hard HEAD (revert to a previous commit)
	git reset --hard <SOME-COMMIT>

# Install svn2git:
	$ sudo gem install svn2git
	/usr/local/lib/ruby/1.9.1/yaml.rb:56:in `<top (required)>':
	It seems your ruby installation is missing psych (for YAML output).
	To eliminate this warning, please install libyaml and reinstall your ruby.
	Successfully installed svn2git-2.2.2
	1 gem installed
	Installing ri documentation for svn2git-2.2.2...
	Installing RDoc documentation for svn2git-2.2.2...

# Rebase
	git checkout master
	git remote add upstream git://github.com/phatboyg/MassTransit.git
	git rebase upstream/master
	git push
	(From: http://stackoverflow.com/questions/7244321/how-to-update-github-forked-repository,
	http://stackoverflow.com/questions/12199723/github-rebase-upstream-master-with-origin-master  
	)

# Articles:
- http://www.vogella.com/articles/EGit/article.html 
- http://learn.github.com/p/intro.html 


# [Remote](http://learn.github.com/p/remotes.html)

# [Book](http://git-scm.com/book)
- https://github.com: pingnet888/ping816 (https://github.com/hmsonline)
- http://git-scm.com 

# http://git-scm.com/book/en/Getting-Started-Installing-Git:

	yum install curl-devel expat-devel gettext-devel \
	openssl-devel zlib-devel
	$ tar -zxf git-1.7.2.2.tar.gz
	$ cd git-1.7.2.2
	$ make prefix=/usr/local all
	$ sudo make prefix=/usr/local install

# http://www.ralfebert.de/tutorials/git,
	configure this globally for all your git projects:
	$ git config --global user.name "llin"
	$ git config --global llin@hmsonline.com

# Branch
	$ git branch -v
	$ git branch <name> <commit>.
	Switch:
	git checkout newfeature

# Tools: gitk/git gui/gitx

# Share (from: http://toroid.org/ams/git-central-repo-howto):
	$ git init --bare --shared shared.git
	[local-git]$ git push ssh://llin-lin3/home/llin/tries/git/shared.git '*:*'
	$ git clone ssh://llin-lin3/home/llin/tries/git/shared.git
	$ git push
	$ git pull

# push a new branch to the server
$ git push origin b1
[shared]$ git pull
...
 * [new branch]  	b1     	-> origin/b1
[shared]$ git checkout b1

# You can also use git remote to add nicknames for other repositories than origin
? $ git remote add t b1 rb1

# http://www.lordofthejars.com/2011/05/this-is-flash-of-pure-inspiration-mes-i.html 

