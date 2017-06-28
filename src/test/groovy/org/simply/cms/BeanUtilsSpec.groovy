package org.simply.cms

import org.simply.cms.utils.BeanUtils
import org.springframework.web.servlet.ModelAndView
import spock.lang.Specification

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import java.lang.reflect.Method

class BeanUtilsSpec extends Specification {

	def "test find all methods by annotation"() {
		when:
		List<Method> methods = BeanUtils.findAllMethodsWithAnnotation(Test, Anno)

		then:
		2 == methods.size()

	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Anno {
	String value()
}

class Test {

	@Anno("test")
	ModelAndView one() {}

	@Anno("test")
	ModelAndView two() {}

	void three() {

	}

}