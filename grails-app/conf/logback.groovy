import ch.qos.logback.classic.filter.ThresholdFilter
import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

scan()

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        //'%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

boolean isDevelopmentMode = Environment.isDevelopmentMode()
def targetDir = BuildSettings.TARGET_DIR
if (isDevelopmentMode && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}


String appenderName = isDevelopmentMode ? 'STDOUT' : 'file'
String logsDir = System.properties.getProperty('catalina.base', '.') + "/logs"
String logFileName = logsDir + "/simply.log"
String errorLogFileName = logsDir + "/simply-errors.log"
String perfLogFileName = logsDir + "/simply-perf.log"
String perfAppender = isDevelopmentMode ? "STDOUT" : "perf"

println "logFile name: ${logFileName}"

if(!isDevelopmentMode) {
    appender("file", RollingFileAppender) {
        file = logFileName
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%date [%level] %-30logger{30} - %msg%n%exception{10}"
        }

        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${logFileName}.%d{yyyy-MM-dd}"
            maxHistory = 15
        }
    }

    appender("error", RollingFileAppender) {
        file = errorLogFileName
        append = true

        filter(ThresholdFilter) {
            level = "WARN"
        }

        encoder(PatternLayoutEncoder) {
            pattern = "%date [%level] %-30logger{30} - %msg%n%exception{10}"
        }

        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${errorLogFileName}.%d{yyyy-MM-dd}"
            maxHistory = 15
        }
    }

    appender("perf", FileAppender) {
        file = perfLogFileName
        append = true

        encoder(PatternLayoutEncoder) {
            pattern = "%date [%level] %-30logger{30} - %msg%n%exception{10}"
        }

    }
}


logger("perfLogger", DEBUG, [perfAppender], true)
logger("grails.app", DEBUG, [appenderName])
logger("org.simply", DEBUG, [appenderName])
logger("org.grails.plugins.databasemigration", INFO, [appenderName])
logger("liquibase", INFO, [appenderName])
logger("grails.plugins.crudify", INFO, [appenderName])

if(isDevelopmentMode) {
    root(WARN, ["STDOUT"])
} else {
    root(WARN, ["error"])
}

