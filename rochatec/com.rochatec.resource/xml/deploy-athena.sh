#!/bin/sh
PATH_SOURCE=/home/erickpr/java/projects/com.rochatec.athena
JBOSS_HOME=/home/erickpr/java/server/jboss-as-7.1.1.Final
cd $PATH_SOURCE/core
mvn clean install
cd $JBOSS_HOME/standalone/deployments/
rm *
cd $PATH_SOURCE/core/enterprise/target
mv *.ear $JBOSS_HOME/standalone/deployments/
cd $PATH_SOURCE/core/enterprise/target/com.athena.enterprise-1.0.0-SNAPSHOT
cp business*.jar $PATH_SOURCE/frontend/com.rochatec.athena.services/lib
cd $PATH_SOURCE/core/enterprise/target/com.athena.enterprise-1.0.0-SNAPSHOT/lib
cp model*.jar $PATH_SOURCE/frontend/com.rochatec.athena.services/lib
cd $PATH_SOURCE/rochatec/com.rochatec.resource/xml
cp menus.xml $JBOSS_HOME/standalone/configuration
cd $JBOSS_HOME/bin
./standalone.sh
