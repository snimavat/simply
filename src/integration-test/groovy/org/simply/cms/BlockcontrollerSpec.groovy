package org.simply.cms

import org.simply.cms.admin.BlockController
import org.simply.cms.block.RichTextBlock
import org.simply.cms.pages.FlexiPage
import org.simply.cms.pages.Page
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import test.common.BaseControllerIntegrationSpec

@Integration
@Rollback
class BlockcontrollerSpec extends BaseControllerIntegrationSpec {
	String controllerName = "block"

	BlockController controller

	void setup() {
		controller = autowire(new BlockController())
	}

	Page createPage() {
		Page root = Page.first()
		assert root != null
		Page level1 = new FlexiPage(slug: "level1", parent: root, title: "test").save(failOnError:true, flush:true)
		return level1
	}

	def "test save block"() {
		given:
		FlexiPage page = createPage()
		assert page.id != null
		Map params = ["page.id":page.id, index:0, blockType:"RichTextBlock", block:[value:"test"]]
		controller.params.putAll(params)

		when:
		controller.save()

		then:
		noExceptionThrown()
		controller.response.status == 200

		FlexiPage updated = FlexiPage.get(page.id)
		updated.body != null
		updated.body.blocks.size() == 1
		updated.body.blocks[0] instanceof RichTextBlock
		updated.body.blocks[0].value == "test"
	}
}
