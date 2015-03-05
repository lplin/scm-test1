#!/bin/bash
cd $HOME/rdc/
rm $HOME/gits/lplin/scm-test1/jiras.7z
7z a -p $HOME/gits/lplin/scm-test1/jiras.7z *
cd $HOME/gits/lplin/scm-test1
git commit -am "Push at "`date +%Y-%m-%d.%H:%M:%S`
git push origin master
