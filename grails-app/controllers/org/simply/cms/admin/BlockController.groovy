package org.simply.cms.admin

import org.simply.cms.BlockService
import org.simply.cms.pages.Block
import org.simply.cms.pages.Page

class BlockController {
	static namespace = "admin"

	BlockService blockService

	def list(Long pageId) {
		List<Class> blocks = grailsApplication.config.simply.cms.blocks
	}

	def create(Long page, int index, String blockType) {
		Block block = blockService.createBlock(blockType)
		String template = blockService.getBlockTemplate(block.class, "form")
		render template: template, model: [block:block]
	}

	def edit(Long pageId, int index, String type) {
		Page page = Page.get(pageId)
		List body = page['body']
		Block block = body.get(index)
		String template = blockService.getBlockTemplate(block.class, "form")
		render template: template, model: [block:block]
	}


	def save(Page page, int index, String blockType) {
		if(page) {
			Block block = blockService.createBlock(blockType, params.block)
			blockService.saveBlock(block, page, "body", index)
			String template = blockService.getBlockTemplate(block.class, "display")
			render template: template, model: [block:block]
		} else {
			render status: 404
		}

	}

	def delete(Page page, Integer index) {
		List body = page['body']
		if(body.remove(index)) {
			page.save(flush:true)
			render status: 200
		}
		else render status: 404, text: "Block Not found"
	}
}
