# List Open [Files for Process](http://www.cyberciti.biz/faq/howto-linux-get-list-of-open-files/)

$ ps -aef | grep httpd


lsof -p 1879
lsof: WARNING: can't stat() fuse.gvfsd-fuse file system /run/user/1000/gvfs
      Output information may be incomplete.
COMMAND  PID  USER   FD   TYPE             DEVICE SIZE/OFF    NODE NAME
mysqld  1879 mysql  cwd    DIR                8,6     4096  143580 /var/lib/mysql


To list opne files for firefox process, enter:
$ ls -l /proc/7857/fd

dlm47 proc # find -name 'fd'

# Best [compression method](http://askubuntu.com/questions/236598/best-compression-method)
	 sudo apt-get install p7zip-full

# Zip [with password](http://www.techiestuffs.com/how-zip-unzip-files-via-commandline-linux/) 
	$ zip -P 123 hr.zip rdc-hr.md
	$ zip -e ../notes.zip Note1.md
	$ unzip -P 123 ../hr.zip

# VI [Better colors for syntax highlighting](http://vim.wikia.com/wiki/Better_colors_for_syntax_highlighting)
If you have a black background, use the following command to get a better color map for syntax highlighting:
	:set background=dark

# Join trimmed lines
	$ cat | sed -e 's/^ *//' -e 's/ *$//' | tr -d '\n'

# Gedit [Change case](https://help.gnome.org/users/gedit/stable/gedit-plugins-change-case.html.en)
	This plugin helps you to change the case of selected portions of text. You can use it to change text to be all lower case, all upper case, to invert the case, or apply title case. To enable this plugin, select gedit ▸ Preferences ▸ Plugins ▸ Change Case.
	Once the Change Case plugin is enabled, you can use it by completing the following steps:
	Highlight the portion of text that you want to change.
	Select gedit ▸ Change Case
	Choose your desired text-formatting option.

# Inner Join Set [comm -23] (http://www.computerhope.com/unix/ucomm.htm)
	-1
	suppress column 1 (lines unique to FILE1)
	-2
	suppress column 2 (lines unique to FILE2)
	-3
	suppress column 3 (lines that appear in both files)
	--check-order

# Why [declare?](http://www.thegeekstuff.com/2010/05/bash-variables/)
Using declare statement in bash, we can limit the value assigned to the variables. It restricts the properties of variables. Option in a declare statement is used to determine the type of a variable.


# In the shell, [what is “ 2>&1 ”?](http://stackoverflow.com/questions/818255/in-the-shell-what-is-21)
at first, 2>1 may look like a good way to redirect stderr to stdout. However, it will actually be interpreted as "redirect stderr to a file named 1". & indicates that what follows is a file descriptor and not a filename. So the construct becomes: 2>&1.

# Command [substitution](http://www.tutorialspoint.com/unix_commands/bash.htm)
Command substitution allows the output of a command to replace the command name. There are two forms:
	$(command) or ‘command‘

Bash performs the expansion by executing command and replacing the command substitution with the standard output of the command, with any trailing newlines deleted. Embedded newlines are not deleted, but they may be removed during word splitting. The command substitution $(cat file) can be replaced by the equivalent but faster $(< file).

When the old-style backquote form of substitution is used, backslash retains its literal meaning except when followed by $, ‘, or \. The first backquote not preceded by a backslash terminates the command substitution. When using the $(command) form, all characters between the parentheses make up the command; none are treated specially.

Command substitutions may be nested. To nest when using the backquoted form, escape the inner backquotes with backslashes.

If the substitution appears within double quotes, word splitting and pathname expansion are not performed on the results.

# Environment variable [$?](http://linuxcommand.org/wss0150.php)
First, you can examine the contents of the $? environment variable. $? will contain the exit status of the last command executed. You can see this work with the following:
	[me] $ true; echo $?
	0
	[me] $ false; echo $?
	1

# linux-client-[google-drive](http://www.unixmen.com/grive-unofficial-open-source-linux-client-google-drive/)
# declare a variable as [read only](http://tldp.org/LDP/abs/html/declareref.html)
To declare a variable as read only, use the following statement:
declare -r varname

To declare that a variable should accept only numeric values (integers), use the following statement:
declare -i varname

# rpm file list
	rpm -ql ruby-irb-1.8.5-24.el5
	/usr/bin/irb

# POM make the version snapshot
	$ find -iname 'pom.xml' -exec sed -i 's/2.1.47/2.1.47.1-SNAPSHOT/g' {} \;

# Use test  
  $ test -d .git && echo "Y" || echo "N"
  unless => "test -d $localdir/$reponame/.git",

# How to find [all zero byte files in directory](http://superuser.com/questions/575235/how-to-find-all-zero-byte-files-in-directory)
	find . -size 0
	or if you require a long listing append the -ls option

	find . -size 0 -ls

# Print second last column/field in AWK
> awk '{print $(NF-1)}'

#  Copy specific file type keeping the folder structure
* Find:
> $ find . -iname '*.iml' -exec cp --parents {} ~/tmp/cmf \;
* Rsync:
```
$ rsync -avrh --include '.classpath' --exclude '*' ~/projects/ ~/tmp/cp0/
sending incremental file list
created directory /home/lplin/tmp/cp0
./
sent 60 bytes  received 61 bytes  242.00 bytes/sec
total size is 0  speedup is 0.00
```
==> doesn't work

* Find again:
> find . -name '.classpath' -exec cp --parents \{\} ~/tmp/cp0/ \;
==> works !!!

* Cp + Find:
```
cp --parents . ~/var/prjs
find ~/tmp/cp0 -name '*' -exec cp --parents \{\} ~/projects/ \;

find . -name 'bin' -exec cp -rf --parents \{\} ~/tmp/bin0/ \;
```

# [Sync files between boxes](http://www.thegeekstuff.com/2011/01/rsync-exclude-files-and-folders/):
```
rsync -avz --progress --delete --exclude 'target' dlm47:/home/lplin/projects /home/lplin
```
# Change Primary Group of User
> $ sudo usermod -g users lplin

# apt-cache search keyword
* where the search keyword can be all or part of a package name or any words used in its description.
To get a list of ALL packages
> apt-cache search .

* Use Synaptic if you have X-forwarding enabled or are on a desktop

# Linux equivalent for on the command line [“start” and “open”](http://superuser.com/questions/38984/linux-equivalent-command-for-open-command-on-mac-windows)
> xdg-open is what you're looking for.

# Recursively in Bash[ change file extensions](http://stackoverflow.com/questions/21985492/recursively-change-file-extensions-in-bash)
> find . -name "*.txt" -exec rename 's/\.txt$/.md/' '{}' \;

# Mount SMB Share:
	smb://172.16.31.129/gdrive
	sudo mount -o username=lplin,password=8888 -t cifs //172.16.31.129/gdrive $HOME/win7-gdrive
	
# GNU less: [search while ignoring case sensitivity](http://unix.stackexchange.com/questions/116395/less-is-always-case-insensitive)
	You can set it from within less by typing -i 




