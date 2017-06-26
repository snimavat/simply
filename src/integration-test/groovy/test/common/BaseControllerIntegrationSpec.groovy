package test.common

import grails.util.GrailsWebMockUtil
import groovy.transform.CompileStatic
import org.grails.plugins.testing.GrailsMockHttpServletRequest
import org.grails.plugins.testing.GrailsMockHttpServletResponse
import org.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.request.RequestContextHolder
import spock.lang.Ignore

@CompileStatic
abstract class BaseControllerIntegrationSpec extends BaseIntegrationSpec {

	@Autowired
	WebApplicationContext ctx

	void setup() {
		MockHttpServletRequest request = new   GrailsMockHttpServletRequest(ctx.servletContext)
		MockHttpServletResponse response = new GrailsMockHttpServletResponse()
		GrailsWebMockUtil.bindMockWebRequest(ctx, request, response)
		currentRequestAttributes.setControllerName(controllerName)

	}

	@Ignore
	protected String getControllerName() { return null }

	@Ignore
	protected GrailsWebRequest getCurrentRequestAttributes() {
		return (GrailsWebRequest)RequestContextHolder.currentRequestAttributes()
	}

	void cleanup() {
		RequestContextHolder.resetRequestAttributes()
	}


	@Ignore
	def autowire(def controller) {
		ctx.autowireCapableBeanFactory.autowireBeanProperties(controller, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false)
		controller
	}
}
