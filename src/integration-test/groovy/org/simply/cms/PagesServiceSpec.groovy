package org.simply.cms

import org.simply.cms.pages.Page
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import grails.web.mapping.LinkGenerator
import spock.lang.Specification

@Integration
@Rollback
class PagesServiceSpec extends Specification {
	PagesService pagesService
	LinkGenerator grailsLinkGenerator

	def "test getUrlParts"() {
		setup:
		Page root = Page.first()
		assert root
		Page level1 = new Page(slug: "level1", parent: root, title: "test").save(failOnError:true)
		Page level2 = new Page(slug: "level2", parent: level1, title: "tes2").save(failOnError:true)
		Site site = new Site(hostname: "nimavat.me", name: "nimavat.me", rootPage: level1).save(failOnError:true)

		when:
		List result = pagesService.getUrlParts(level2)

		then:
		result != null

	}
}
