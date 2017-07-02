package org.simply.cms.logging

import groovy.util.logging.Slf4j

@Slf4j(category = "perfLogger")
class PerfLogger {

	void debug(String msg) {
		log.debug(msg)
	}

	void info(String msg) {
		log.info(msg)
	}

	void warn(String msg) {
		log.warn(msg)
	}

	void error(String msg) {
		log.error(msg)
	}

	void error(String msg, Throwable t) {
		log.error(msg, t)
	}
}
