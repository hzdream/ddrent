# DDRENT documents
# 开始
    DDRENT 是一个个人分布式服务项目，运行环境说明：
- spring boot 2.0.5.RELEASE
- JDK 1.8 +
- mysql
    - tk.mybatis (国内还算比较热门的通用JPA工具[tk.mybatis]https://github.com/abel533/Mapper，便于开发)
- dubbo

# 结构如下：
![image](images/ddrent-deploy.png) [查看原图](https://raw.githubusercontent.com/hzdream/ddrent/master/images/ddrent-deploy.png)

    - |-- ddrent
          |-- ddrent-deploy
              |-- ddrent-web
                  |-- ddrent-core
                      |-- drent-api
                          |-- ddrent-common
                          
    api是预留的接口层，common基础公共包，core基础服务和dao层，web是Http Restful Api（没有前端HTML等），
    deploy 一键打包依赖层（用来发布打包的）

# 配置说明
```## spring profiles 
   spring.profiles.active=dev
   
   ##spring mvc config
   server.port = 8088
   server.session.timeout = 1800
   #server.servlet.context-path = /rent
   spring.mvc.static-path-pattern = /static/**
   spring.mvc.static-resorce-dir = /static
   spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
   spring.jackson.time-zone=GMT+8
   
   ###jdbc
   spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver
   spring.datasource.url = jdbc:mysql://localhost:3306/ddrent?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
   spring.datasource.username = root
   spring.datasource.password = 123abc
   mybatis.mapperLocations=classpath:mapper/*.xml
   
   ## mongo
   # single port
   #spring.data.mongodb.uri=mongodb://name:pass@localhost:27017/test
   # mutiy port
   #spring.data.mongodb.uri=mongodb://aifeng:aifeng@******
   
   ## log properties
   log.root = info,error,CONSOLE,DEBUG
   log.base = /usr/log/applog
   resources.server.port = 8081
   resources.server.host = 127.0.0.1
   
   #mapper.mappers=tk.mybatis.mapper.common.Mapper,tk.mybatis.mapper.common.Mapper2
   mapper.notEmpty=true
   logging.level.tk.mybatis.sample.mapper=TRACE
```
# 日志说明
    日志使用的是springboot 推荐的logbck
``` <configuration debug="false" scan="false" scanPeriod="300 seconds">
    
    	<springProperty scope="context" name="appname" source="appname" />
    	<property name="basedir" value="/usr/log/applog" />
    	
    	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    		<encoder>
    			<!-- <encrypt>true</encrypt>
    			<encryptKeys>cardno cardNumber bankacct id_no bind_mob</encryptKeys>
    			<partialEncryptKeys>cardno|64 cardNumber|64 bankacct|64 id_no|11 bind_mob|34</partialEncryptKeys> -->
    			<pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}][%-5level][%thread] %logger - %msg%n</pattern>  
    			<charset>UTF-8</charset>
    		</encoder>
    	</appender>
    	
    	<appender name="bizlogFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
    		<file>${basedir}/${appname}.log</file>
    		<append>true</append>
    		<encoder>
    			<!-- <encrypt>true</encrypt>
    			<encryptKeys>cardno cardNumber bankacct id_no bind_mob</encryptKeys>
    			<partialEncryptKeys>cardno|64 cardNumber|64 bankacct|64 id_no|11 bind_mob|34</partialEncryptKeys> -->
    			<pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}][%-5level][%thread] %logger - %msg%n</pattern>
    			<charset>UTF-8</charset>
    		</encoder>
    		<rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
    			<fileNamePattern>${basedir}/${appname}-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
    			<maxHistory>30</maxHistory>
    			<maxFileSize>150MB</maxFileSize>
    		</rollingPolicy>
    		<!-- <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
    			<level>INFO</level>
    		</filter> -->
    	</appender>
    	
    	<logger name="com.baidu.disconf" level="INFO" />  
    	
    	<!-- <logger name="org.apache.ibatis" level="DEBUG">
    		<appender-ref ref="STDOUT"/>
    	</logger>
    	<logger name="java.sql" level="debug">
    		<appender-ref ref="STDOUT"/>
    	</logger> -->
    	
    	<springProfile name="!online">
    		<logger name="org.springframework" level="INFO" />  
    		<root level="INFO">
    			<appender-ref ref="STDOUT" />
    		</root>
    	</springProfile>
    	
    	<springProfile name="online">
    		<logger name="org.springframework" level="INFO" />  
    		<root level="INFO">
    			<appender-ref ref="bizlogFile" />
    		</root>
    	</springProfile>
    </configuration>
```

# 打包说明
    环境类型有：DEVELOP、TEST、UAT、PRODUCT
    Maven 打包脚本（-P 后面加环境类型 可以-P DEVELOP | -P TEST | -P UAT | -P PRODUCT）： 
    `mvn clean package -Dmaven.test.skip=true -PDEVELOP` 或
    `mvn -pl ddrent-core -am clean package -Dmaven.test.skip=true -PDEVELOP`
**打包之后的结构如下，运行`sh bin/start.sh `项目就可以启动了**

![image](images/ddrent-deploy-package.png) ![image](images/ddrent-zip.jpg) ![image](images/ddrent-zip-bin.jpg)
