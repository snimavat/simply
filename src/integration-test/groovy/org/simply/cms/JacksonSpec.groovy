package org.simply.cms

import com.fasterxml.jackson.databind.ObjectMapper
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
@Rollback
class JacksonSpec extends Specification {

	def "test jackson"() {
		setup:
		ObjectMapper mapper = new ObjectMapper()
		mapper.enableDefaultTyping()

		List l = []
		l << new Person(name: "test")
		l << new Student(name: "student", address: "address")

		when:
		String t = mapper.writeValueAsString(l)

		then:
		t != null

		when:
		List n = mapper.readValue(t, ArrayList)

		then:
		n.size() == 2
		n[0] instanceof Person
		n[1] instanceof Student
	}
}

class Person {
	String name
}

class Student extends Person {
	String address
}
