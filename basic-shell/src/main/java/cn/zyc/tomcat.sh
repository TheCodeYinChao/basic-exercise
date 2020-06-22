#!/bin/bash
export JAVA_HOME=
export	CATANA_HOME=
CATALINA_OUT=
TOMCAT_PID=`ps -ef | grep ${CATANA_HOME} | grep -v "grep" | awk '{print $2}'`

#脚本输入提示
usage(){
	echo "请输入 $0 {start|stop|restart|status}"
}

#关闭tomcat
stop(){
	if [ "${TOMCAT_PID}" == "" ]
		then
			echo -e "\e[1;32m ${TOMCAT_NAME} is not running. \e[0m"
	else
		${TOMCAT_HOME}/bin/shutdown.sh 
		if [ "${TOMCAT_PID}" == "" ]
			then
				echo -e "\e[1;32m ${TOMCAT_NAME} service ceased to succeed. \e[0m"
		else
			sleep 5
			kill -9 ${TOMCAT_PID}
			sleep 3
			echo -e "\e[1;32m ${TOMCAT_NAME} service ceased to succeed. \e[0m"
		fi
	fi
}

#开启tomcat
start(){
	if [ "$TOMCAT_PID" == "" ]
	then
		echo "tomcat [ $CATANA_HOME ]is start "
		$CATANA_HOME/bin/startup.sh
	else
		echo "tomcat [ $CATANA_HOME ] is running PID $TOMCAT_PID"
	fi
	tail -300f $CATALINA_OUT/catalina.out
}

status(){
	if [ "${TOMCAT_PID}" == "" ]
		then
		   echo -e "\e[1;32m ${TOMCAT_NAME} is not running. \e[0m"
	else
		echo -e "\e[1;32m ${TOMCAT_NAME} is running. \e[0m"
	fi 
}

#重新启动tomcat
restart(){
stop
start
}

case "$1" in
	stop)
		stop
	;;
	start)
		start
	;;
	restart)
		restart
	;;
	status)
		status
	;;
	*)
		usage
	;;
esac