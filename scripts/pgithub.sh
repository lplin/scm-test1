#!/bin/bash
cd $HOME/rdc/
zip -re $HOME/gits/lplin/scm-test1/jiras.zip *
cd $HOME/gits/lplin/scm-test1
git commit -am "Push at "`date +%Y-%m-%d.%H:%M:%S`
git push origin master
