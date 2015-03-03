#!/bin/bash
cd $HOME/gits/lplin/scm-test1
git pull origin master
rm -rf $HOME/tmp/rdc-`date +%a`/
mv $HOME/rdc/ $HOME/tmp/rdc-`date +%a`/
7z x jiras.7z -o$HOME/rdc
