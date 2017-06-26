package org.simply.cms

import org.simply.cms.block.FlexiBlock
import org.simply.cms.pages.FlexiPage
import org.simply.cms.pages.Page
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

/**
 * Verify FlexiBlock gets stored in json and can be reloaded.
 */

@Rollback
@Integration
class FlexiBlockSpec extends Specification {

	def "test save"() {
		given:
		FlexiPage page = new FlexiPage(slug: "test", parent: Page.ROOT, title: "test")
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
		FlexiPage reloaded = FlexiPage.get(page.id)

		then:
		reloaded != null
		reloaded.body != null
		reloaded.body.blocks.size() == block.blocks.size()
	}
}
