package org.simply.cms

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.simply.cms.pages.FlexiBlock
import org.simply.cms.pages.BlogPage
import org.simply.cms.pages.Page
import spock.lang.Specification

/**
 * Verify FlexiBlock gets stored in json and can be reloaded.
 */

@Rollback
@Integration
class FlexiBlockSpec extends Specification {

	def "test save"() {
		given:
		BlogPage page = new BlogPage(slug: "test", parent: Page.ROOT, title: "test")
		FlexiBlock block = new FlexiBlock()
		block.blocks = []
		block.blocks << new Person(name: "person")
		block.blocks << new Student(name: "student")
		page.body = block

		when:
 		page.save(failOnError:true, flush:true)


		then:
		noExceptionThrown()

		when:
		BlogPage reloaded = BlogPage.get(page.id)

		then:
		reloaded != null
		reloaded.body != null
		reloaded.body.blocks.size() == block.blocks.size()
	}
}
