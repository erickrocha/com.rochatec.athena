#!/bin/sh
PATH_SOURCE=/home/epr/java/projects/com.rochatec.persistence
TARGET_SOURCE=/home/epr/java/projects/com.rochatec.athena/customer/com.rochatec.athena.pdv.service/lib

cd $PATH_SOURCE
mvn clean install
cd $PATH_SOURCE/target
cp com.rochatec.persistence-1.0-SNAPSHOT.jar $TARGET_SOURCE



