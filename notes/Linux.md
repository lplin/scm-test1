# column to comma separated:
$ awk -F'\t' '{print $4}' | tr '\n' '\t' | sed 's/\t/, /g'

# HTTP upload:
	curl -F file=@RIB_INX_RD9000T_20150226_100002.xml http://w-tlapp02:4000/atlas/gridProcess?firmNumber=RD9000T

# UTF16 to UTF8
lplin@dlm47 /tmp/c6 $ file Personsample.txt 
Personsample.txt: Little-endian UTF-16 Unicode text, with CRLF, CR line terminators
lplin@dlm47 /tmp/c6 $ iconv -f UTF-16LE -t UTF-8 < Person_legacy.txt > Person_legacy1.txt


# Determine whether a variable is already defined [Ref](http://mywiki.wooledge.org/BashFAQ/083)

[ "$var" ]
[[ $var ]]

if [[ -v var ]]; then echo "var is defined"; fi

# Replace new line ( \n ) with space or Tab
$ cat | tr '\t' '\n'
cat | sed -e 's/\t/\n/g' 

# Single quote strings
cat | awk '{print "'\''" $1 "'\''"}'
cat | awk '{print "\x27" $1 "\x27"}'

# Bash: [Check if array element exists](http://www.fvue.nl/wiki/Bash:_Check_if_array_element_exists)
function in_array() {
    local hay needle=$1
    shift
    for hay; do
        [[ $hay == $needle ]] && return 0
    done
    return 1
}
Tests:

$ aa=(foo bar "cee dee" eek "2 3" 34 4)
$ in_array bar "${aa[@]}" && echo yes || echo no
yes
$ in_array cee       "${aa[@]}" && echo yes || echo no
no

In Bash 4, you can use [associative arrays](http://superuser.com/questions/195598/test-if-element-is-in-array-in-bash):

 if condition - check if element is present in array
http://stackoverflow.com/questions/14366390/bash-if-condition-check-if-element-is-present-in-array
array=(word "two words" words)
[[ ${array[@]} =~ words ]] && echo "in" || echo "out"

if [[ ${array[@]} =~ words ]]


if [[ " ${arr[*]} " == *" d "* ]]; then
    echo "arr contains d"
fi


# Find files in some dirs
	find -type d -name '2015012*' | xargs -I{} find {} -iname '*RIP*.xml'
	==> better performance
	ls -d 2015012* | xargs -I{} find {} -iname '*FRX*.xml'


# Display binary file with ASCII
	$ hexdump -C t3.doc

# SSH login without password [...](http://www.linuxproblem.org/art_9.html)
	$ ssh-keygen -t rsa (Do not enter a passphrase, only one time for the host from)
	$ ssh b@B mkdir -p .ssh ( if no this dir)
	$ cat ~/.ssh/id_rsa.pub | ssh b@B 'cat >> .ssh/authorized_keys' 

	Test OK:
	$ cat ~/.ssh/id_rsa.pub | ssh s1-dlapp08 'cat >> .ssh/authorized_keys'
	$ cat ~/.ssh/id_rsa.pub | ssh s1-dlapp07 'cat >> .ssh/authorized_keys'
	

# copy file content to clipboard from the command line on Linux desktop: [...](http://ask.xmodulo.com/copy-file-content-clipboard-linux-desktop.html)
	$ cat input.txt | xclip -i

Once the file content is copied to clipboard, you can paste it into another window or application simply by clicking on mouse `middle-button`.

# find files that are bigger/smaller than x bytes? [...](http://superuser.com/questions/204564/how-can-i-find-files-that-are-bigger-smaller-than-x-bytes)
	find . -type f -size -4096c
	-size n[cwbkMG]
		  `b'    for 512-byte blocks (this is the default if no suffix  is
		  `c'    for bytes
		  `w'    for two-byte words
		  `k'    for Kilobytes       (units of 1024 bytes)
		  `M'    for Megabytes    (units of 1048576 bytes)
		  `G'    for Gigabytes (units of 1073741824 bytes)

# Replace string while without touching timestamp
find . -iname '*.xml' | while read f; do
	ts=`stat -c "%Y" $f`
 	sed -i 's/RD09000P/RD03000T/g' $f
 	touch -d @$ts $f
done


# Replace biz_unit_id in files:
$ find -iname '*.xml' -exec sed -i 's/RD09000P/RD03000T/g' {} \;

# Howto: [Linux Rename Multiple Files](http://www.cyberciti.biz/tips/renaming-multiple-files-at-a-shell-prompt.html)
	rename "s/RD09000P/RD03000T/g" *.xml

Do it [recursively](http://unix.stackexchange.com/questions/37355/recursively-rename-subdirectories-that-match-a-regex):

	lplin@dlm47 ~/rdc/jiras/CMF-725_EU/RD03000T/orig $ find . -iname '*.xml' -exec rename -n 's/RD09000P/RD03000T/g' {} ";"

If rename -n (-not really) displays what it wants to do, and it's all right for you, omit the -n and make it happen.



# Use grep to [show just filenames](http://stackoverflow.com/questions/6637882/how-can-i-use-grep-to-show-just-filenames-no-in-line-matches-on-linux)

There is a standard option grep -l (that is a lowercase L) which does this. You also do not need -H in this case.

# Backup SVN changed files
$ svn st | awk '{ print $2}' | grep src | grep -v '/resources' | xargs -I f1 cp --parents f1 ~/tmp/tt


# traceroute alternative
Suggestion ; use the mtr command , it's way better !  8) 
mtr linuxmint.com


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




