#!/bin/bash

WSSERVICE_HOME=$(cd `dirname $0`;cd ../;  pwd)
EXEC_JAR=$(ls ${WSSERVICE_HOME}/lib/*.jar)

#jdk1.7
#OPTS="-server -Xms600m -Xmx600m -Xmn400m -Xss256k -XX:PermSize=300m -XX:MaxPermSize=300m -XX:MaxDirectMemorySize=64m -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:TargetSurvivorRatio=90 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:CMSFullGCsBeforeCompaction=5 -XX:+UseCMSCompactAtFullCollection -XX:+UseCondCardMark -XX:+CMSParallelRemarkEnabled -XX:+CMSParallelInitialMarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80  -XX:CMSMaxAbortablePrecleanTime=5000 -XX:+CMSScavengeBeforeRemark -XX:+DisableExplicitGC -XX:+UseCompressedOops -XX:+TieredCompilation -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:+OptimizeStringConcat -XX:+UseCompressedStrings -XX:+UseStringCache -XX:+UseFastAccessorMethods -XX:+AlwaysPreTouch -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${WSSERVICE_HOME}/logs/heapdump.log"
#jdk1.8
OPTS="-server -Xms600m -Xmx600m -Xmn400m -Xss256k -XX:MetaspaceSize=300m -XX:MaxMetaspaceSize=300m -XX:MaxDirectMemorySize=64m -XX:+UseCompressedClassPointers -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:TargetSurvivorRatio=90 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+UseCondCardMark -XX:+CMSParallelRemarkEnabled -XX:+CMSParallelInitialMarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80  -XX:CMSMaxAbortablePrecleanTime=5000 -XX:+CMSScavengeBeforeRemark -XX:+DisableExplicitGC -XX:+UseCompressedOops -XX:+TieredCompilation -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:+OptimizeStringConcat -XX:+UseFastAccessorMethods -XX:+AlwaysPreTouch -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${WSSERVICE_HOME}/logs/heapdump.log"

echo -e "Starting the $WSSERVICE_HOME ...\c"
nohup java -Dspring.profiles.active=online -Dconfig.basedir=${WSSERVICE_HOME}/conf ${OPTS} -jar ${EXEC_JAR}>>${WSSERVICE_HOME}/logs/wsservice.log 2>&1 &
echo "OK!"