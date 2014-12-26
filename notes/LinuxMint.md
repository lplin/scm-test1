# Install [Gedit 3](http://forums.linuxmint.com/viewtopic.php?f=47&t=117673)
	sudo apt-get purge gedit
	sudo apt-get purge gedit-common
	sudo apt-get install gedit-common/trusty
	sudo apt-get install gedit/trusty


# Firefox always warn me if closing multiple tab:
about:config and changing "browser.warnOnQuit" 


# Use [Rsyc](http://www.thegeekstuff.com/2011/01/rsync-exclude-files-and-folders)
	rsync -avz --progress --delete --exclude 'target' dlm47:/home/lplin/var/notes /home/lplin/var/notes

# gedit [diff](http://www.webupd8.org/2011/01/how-to-integrate-meld-into-gedit-for.html)

# SMB:
	smb://dlm47/root$/
	[root$]
	path = /
	create mask = 0755
	force user = root
	browsable = yes
	writable = yes

	* smb://dlm47/jiras
	# pdbedit -L -v
	# smbpasswd -a lplin
	New SMB password:
	Retype new SMB password:
	Added user lplin.

# File sharing [Network Block Device](http://www.tuxradar.com/answers/347)

# Linuxmint [tips and tricks](http://www.techsupportalert.com/content/tips-and-tricks-mint-after-installation-mint-13-cinnamon-edition.htm)

# Use [Bootmenu](http://www.howtogeek.com/howto/17787/clean-up-the-new-ubuntu-grub2-boot-menu/)

# Mail
- [PostfixBasicSetupHowto](https://help.ubuntu.com/community/PostfixBasicSetupHowto)
- [setup-mail-server-ubuntu-14-04](http://www.krizna.com/ubuntu/setup-mail-server-ubuntu-14-04/)
> sudo dpkg-reconfigure postfix
> sudo postconf -e 'home_mailbox = Maildir/'

# Use [sftp](http://www.krizna.com/ubuntu/setup-ftp-server-on-ubuntu-14-04-vsftpd/)

# Read [ext4 from Windows](http://www.ext2fsd.com/)

#	Login from lplin@localhost to rdcapp@localhost without password
	$ ssh-keygen -t rsa (no passphrase, enter all the way)
	$ ssh rdcapp@localhost mkdir -p .ssh
	$ cat ~/.ssh/id_rsa.pub | ssh rdcapp@localhost 'cat >> .ssh/authorized_keys'
	$ ssh rdcapp@localhost
	Welcome to Linux Mint 17 Qiana (GNU/Linux 3.13.0-24-generic x86_64)
	# adduser rdcapp (interactively)

# SSH
	$ sudo apt-get install openssh-server openssh-client

# VPN
	sudo apt-get install network-manager-vpnc-gnome
	sudo restart network-manager
	Host=173.252.138.4
	GroupName=RDC_RSA
	cisco compatible client (http://forums.linuxmint.com/viewtopic.php?f=47&t=151887)
	Description=RDC RSA Connection
	export GROUP_PASS=RDC_n3v3r2b3us3d_RDC
	dlm47: 10.10.12.11 

# Use [VNC](http://community.linuxmint.com/tutorial/view/1188)
	sudo apt-get install vnc4server
	==> Works by uncommenting two lines
	sudo apt-get install vncviewer
	==> copy / paste doesn't work
	Use RealVNC directly: http://www.realvnc.com/download/viewer/ 

	"s", "d" key issue: (https://bugs.launchpad.net/ubuntu/+source/vnc4/+bug/658723)
	Clear in dconf-editor: org.gnome.desktop.wm.keybindings for keys:
	maximize(Up), panel-main-menu(s), show-desktop(d), unmaximize (Down)

	Good xstartup: vncconfig not work issue fixed
	$ cat /home/lplin/.vnc/xstartup
	#!/bin/sh


	vncconfig -iconic &
	# Uncomment the following two lines for normal desktop:
	unset SESSION_MANAGER
	exec /etc/X11/xinit/xinitrc


	[ -x /etc/vnc/xstartup ] && exec /etc/vnc/xstartup
	[ -r $HOME/.Xresources ] && xrdb $HOME/.Xresources
	xsetroot -solid grey
	# vncconfig -iconic &
	x-terminal-emulator -geometry 80x24+10+10 -ls -title "$VNCDESKTOP Desktop" &
	x-window-manager &

	* Java Plugin: keyin the dir
	/usr/lib/mozilla/plugins $
	 libnpjp2.so -> /home/lplin/apps/jdk1.6.0_26/jre/lib/amd64/libnpjp2.so


# Use [VM](https://gist.github.com/magnetikonline/5274656)
	# Download (https://www.virtualbox.org/wiki/Linux_Downloads)
		sudo apt-get update
		sudo apt-get install virtualbox-4.3
	# Download part2.rar from: https://www.modern.ie/en-us/virtualization-tools#downloads
	# $ unrar e ../IE8.WinXP.For.LinuxVirtualBox.part2.rar
	# $ tar xf IE8\ -\ WinXP.ova
	-rw------- 1 lplin users 1109813248 Nov  4  2013 IE8 - WinXP-disk1.vmdk
	# VirtualBox
	# Select Use an existing virtual hard drive file, pick your downloaded and extracted *.vmdk image.
	# extend Windows expiration (http://www.sitepoint.com/virtual-machine-browser-testing-modern-ie/)
	rundll32.exe syssetup,SetupOobeBnk
	# settings / shared folders / the plus icon 
	# Network driver: http://downloadmirror.intel.com/18717/eng/PRO2K3XP_32.exe
	https://downloadcenter.intel.com/Detail_Desc.aspx?DwnldID=18717&lang=eng&ProdId=871
	# Clipboard: https://www.liberiangeek.net/2013/09/copy-paste-virtualbox-host-guest-machines/
	# IP: (http://askubuntu.com/questions/52147/how-can-i-access-apache-on-virtualbox-guest-from-host)
		Network tab > bridged networking > eth0
	# ping 10.10.12.4
	PING 10.10.12.4 (10.10.12.4) 56(84) bytes of data.
	64 bytes from 10.10.12.4: icmp_seq=1 ttl=128 time=0.358 ms

# Virtual Box [Activation](http://techblog.tv/virtualbox-clone-windows-activation/)
  http://www.guidgen.com/

# Use [Remote Desktop](http://searchvirtualdesktop.techtarget.com/tip/How-to-set-up-VirtualBox-remote-display-for-VM-access-anywhere)
Settings > Display > Remote Display
	$ rdesktop-vrdp -g 1500x800 10.10.12.4

# [using-a-physical-hard-drive-with-a-virtualbox-vm](http://www.serverwatch.com/server-tutorials/using-a-physical-hard-drive-with-a-virtualbox-vm.html)
	$ sudo VBoxManage internalcommands createrawvmdk -filename "/home/lplin/apps/WinVista.vmdk" -rawdisk /dev/sda2 
	RAW host disk access VMDK file /home/lplin/apps/WinVista.vmdk created successfully.
	sudo VBoxManage convertfromraw /dev/sda2 /home/lplin/apps/WinVista1.vdi --format VDI 

# IntelliJ:
	Orig: /home/lplin/apps/idea-IC-135.1289/bin/idea.sh
	To: /home/lplin/apps/idea-IC-139.659.2/bin/idea.sh

# Use [Grive](http://www.enqlu.com/2014/03/how-to-install-grive-google-drive-client-on-ubuntu-14-04-lts-or-linux-mint-17.html)
	sudo add-apt-repository ppa:thefanclub/grive-tools
	sudo apt-get update
	sudo apt-get install grive-tools

# Change Grive Home	
	/opt/thefanclub/grive-tools
	./grive-setup:48:googleDriveFolder="$userHome/Google Drive"	
	./grive-indicator:886:  googleDrive = "Google Drive"

# gnome-panel
	sudo apt-get install gnome-panel

# VNC-Server
	tar xvf ~/Downloads/VNC-5.1.0-Linux-x64-DEB.tar.gz 
	sudo dpkg -i VNC-Server-5.1.0-Linux-x64.deb 
	sudo apt-get update
	vncserver
	sudo vnclicense -add F6B74-D42MX-MGAE2-AJNAK-HY2AA

# Synergy
	$ sudo dpkg -i synergy-1.4.16-r1969-Linux-x86_64.deb 
	$ sudo apt-get update


