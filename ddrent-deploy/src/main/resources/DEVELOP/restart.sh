#!/bin/bash

WSSERVICE_HOME=$(cd `dirname $0`;cd ../;  pwd)
_moduleName=${WSSERVICE_HOME}
. ${WSSERVICE_HOME}/bin/common.sh
start="${WSSERVICE_HOME}/bin/start.sh"
restartProcess