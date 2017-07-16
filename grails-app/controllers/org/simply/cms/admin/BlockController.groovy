package org.simply.cms.admin

import org.simply.cms.BlockService
import org.simply.cms.pages.Block
import org.simply.cms.pages.Page
import org.springframework.http.HttpStatus

class BlockController {
	static namespace = "admin"

	BlockService blockService

	def list(Long pageId) {
		List<Class> blocks = grailsApplication.config.simply.cms.blocks
	}

	def create(Long page, int index, String type) {
		Block block = blockService.createBlock(type)
		String template = blockService.getBlockTemplate(block.class, "form")
		render template: template, model: [block:block]
	}

	def edit(Long pageId, int index, String type) {
		Page page = Page.get(pageId)
		List body = page['body']
		Block block = body.get(index)
		String template = blockService.getBlockTemplate(block.class, "form")
		render template: template, model: [block:block, page:page]
	}


	def save(Page page, int index, String type) {
		if(page) {
			Block block = blockService.createBlock(type, params.block)
			blockService.saveBlock(block, page, "body", index)
			String template = blockService.getBlockTemplate(block.class, "display")
			render template: template, model: [block:block, page:page]
		} else {
			render status: 404
		}

	}

	def delete(Page page, Integer index, String type) {
		List body = page['body']
		if(body.remove(index)) {
			page.save(flush:true)
			render status: HttpStatus.NO_CONTENT.value()
		}
		else render status: 404, text: "Block Not found"
	}
}
