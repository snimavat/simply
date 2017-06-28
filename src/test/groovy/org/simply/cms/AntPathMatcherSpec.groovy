package org.simply.cms

import org.grails.io.support.AntPathMatcher
import spock.lang.Specification

class AntPathMatcherSpec extends Specification {
	AntPathMatcher matcher

	void setup() {
		matcher = new AntPathMatcher()
	}

	void "test match"() {
		given:
		String pattern = "category/{name}"

		expect:
		matcher.match(pattern, "category/test")
		matcher.match(pattern, "category/xxx")
		!matcher.match(pattern, "/category/test")
		!matcher.match(pattern, "/category/test/")

		when:
		Map<String, String> variables = matcher.extractUriTemplateVariables(pattern, "category/test")

		then:
		variables.name == "test"
	}
}
