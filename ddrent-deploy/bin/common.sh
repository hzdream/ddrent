checkProcess(){
   pid=`ps -ef|grep ${_moduleName}|grep -v 'grep'|awk '{print $2}'`
   if [ "X${pid}" != "X" ]; then
      return 0
   else
      return 1
   fi
}

printStartStatus(){
   checkProcess
   if [ $? -eq 0 ]; then
      echo "${_moduleName} start sucessful.      [OK]"
      return 0
   else
      echo "${_moduleName} start Failed.      [Failed]"
      return 1
   fi
}

startProcess(){
   echo $1
   checkProcess
   if [ $? -eq 0 ]; then
	  echo "${_moduleName} had started."
      return 0
   else
      ${start}
      echo "wait 3 seconds to start ..." && sleep 3 
	  printStartStatus
   fi       
}

stopProcess(){
   echo $1
   checkProcess
   if [ $? -eq 1 ]; then
      echo "${_moduleName} not running.      [WARNING]"
	  return 0;
   else
      pid=`ps -ef|grep ${_moduleName}|grep -v 'grep'|awk '{print $2}'`
      if [ "X${pid}" = "X" ]; then
          echo "${_moduleName} had stop.       [OK]"
      else
          kill $pid
		  echo "wait 2 seconds to stop ..." && sleep 2;
		  count=1;
		  while [[ $count -lt 5 ]]
		  do
			pid=`ps -ef|grep ${_moduleName}|grep -v 'grep'|awk '{print $2}'`
			if [ "X${pid}" = "X" ]; then
				echo "${_moduleName} stop sucessful.       [OK]"
				return 0;
			fi
			let "count++"
			echo "wait 2 seconds to stop ..."
			sleep 2
		  done
          echo "${_moduleName} stop faild, had wait 10 seconds, but still failed. please check yourself      [FAILED]"
		  return 1;
      fi
   fi
}

restartProcess(){
   stopProcess $1
   if [ $? -eq 0 ]; then
	  startProcess $2
      return 0
   fi
}

getProcessStatus(){
   checkProcess
   if [ $? -eq 0 ]; then
      echo "${_moduleName} is running."
      return 0
   else
      echo "${_moduleName} is not running."
      return 1
   fi
}

uninstall(){
   checkProcess
   if [ $? -eq 0 ]; then
      pid=`ps -ef|grep ${_moduleName}|grep -v 'grep'|awk '{print $2}'`
      if [ "X${pid}" != "X" ]; then
         kill $pid
      fi
   fi
   ${remove}
}

commandError(){
   echo ""
   echo "ERROR:UNKNOWN COMMAND:\"$_command\" "
   exit 1
}
