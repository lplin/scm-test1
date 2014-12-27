#OLD_PATH_PLUS=$GROOVY_HOME/bin:$JAVA_HOME/bin:$MVN_HOME/bin:$ACTIVEMQ_HOME
export SVN_EDITOR=vim
export GROUP_PASS=RDC_n3v3r2b3us3d_RDC
export JAVA_HOME=/home/lplin/apps/jdk1.6.0_45
#export JAVA_HOME=/home/lplin/apps/jdk1.7.0_71
#export JAVA_HOME=/home/lplin/apps/jdk1.8.0_20
#export M2_HOME=/usr/share/maven
#export MVN_HOME=/usr/share/maven
export MVN_HOME=/home/lplin/apps/apache-maven-3.1.1
#export MVN_HOME=/home/lplin/apps/apache-maven-3.2.3
#export MVN_HOME=/home/lplin/apps/apache-maven-2.2.1
export M2_HOME=$MVN_HOME
export M2_REPO=$HOME/.m2/repository
export ACTIVEMQ_HOME=/home/lplin/apps/apache-activemq-5.10.0
export ACTIVEMQ_BASE=/home/lplin/apps/apache-activemq-5.10.0
export ACTIVEMQ_CONF=/home/lplin/apps/apache-activemq-5.10.0/conf
export ACTIVEMQ_DATA=/home/lplin/apps/apache-activemq-5.10.0/data
export GROOVY_HOME=/home/lplin/apps/groovy-2.3.7
export JRE_LIB=/home/lplin/apps/jdk1.7.0_71/jre/lib/rt.jar
NEW_PATH_PLUS=$GROOVY_HOME/bin:$JAVA_HOME/bin:$MVN_HOME/bin:$ACTIVEMQ_HOME
if [[ $PATH != *${NEW_PATH_PLUS}* ]]; then
  export PATH=$NEW_PATH_PLUS:$PATH
#  echo "No NEW_PATH_PLUS"
else
  echo "NEW_PATH_PLUS exists"
fi
