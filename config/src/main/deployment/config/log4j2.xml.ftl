<?xml version="1.0" encoding="UTF-8"?>
<Configuration xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:noNamespaceSchemaLocation="Log4j-config.xsd"
               status="info" strict="true" name="sample-jersey" verbose="false"
               packages="org.abhishek.webapp.jersey.util.logging" monitorInterval="30">
    <Appenders>
        <Appender type="Console" name="STDOUT">
            <Layout type="PatternLayout" pattern="[${.vars["current.environment.name"]}] [%5.5p] [%d{ISO8601}] [%20.20t] [%30.30c] %m%n"/>
        </Appender>
        <Appender type="File" name="File" fileName="${r"${sys:server.log.dir}/service.log"}">
            <Layout type="PatternLayout" pattern="[${.vars["current.environment.name"]}] [%5.5p] [%d{ISO8601}] [%20.20t] [%30.30c] %m%n"/>
        </Appender>
    </Appenders>

    <Loggers>
        <Logger name="org.springframework" level="@{logging.level.other}"/>

        <Root level="@{logging.level.default}">
        <#if .vars["service.build.environment"] != "local">
            <AppenderRef ref="File"/>
        <#else>
            <AppenderRef ref="STDOUT"/>
        </#if>
        </Root>
    </Loggers>
</Configuration>