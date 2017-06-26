package org.simply.cms

import org.simply.cms.block.Block
import org.simply.cms.block.FlexiBlock
import org.simply.cms.pages.Page
import grails.compiler.GrailsCompileStatic
import grails.core.GrailsApplication
import grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import grails.web.databinding.GrailsWebDataBinder
import groovy.transform.CompileDynamic
import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

import static org.grails.io.support.GrailsResourceUtils.appendPiecesForUri

@GrailsCompileStatic
class BlockService {
	private static final String BLOCK_DIR = "/blocks"

	private List<Class> blocks

	@Autowired
	GrailsApplication grailsApplication

	@Autowired
	GrailsWebDataBinder grailsWebDataBinder

	@PostConstruct
	@CompileDynamic
	void init() {
		blocks = grailsApplication.config.simply.cms.blocks
	}


	@CompileDynamic
	List<Class> listAvailableBlocks() {
		return blocks
	}

	Class blockForName(String name) {
		return blocks.find({ it.simpleName == name })
	}

	Block createBlock(String type, Map params = [:]) {
		Class clazz = blockForName(type)
		Block blockInstance = clazz.newInstance()
		if (params) {
			grailsWebDataBinder.bind(blockInstance, params as SimpleMapDataBindingSource)
		}

		return blockInstance
	}

	@Transactional
	void saveBlock(Block block, Page page, String fieldName, int blockIndex) {
		FlexiBlock field = (FlexiBlock) page[fieldName]
		if (field == null) {
			field = new FlexiBlock()
			page[fieldName] = field
		}
		field.put(blockIndex, block)
		page.save()
	}


	String getBlockTemplate(Class clazz, String template) {
		String dir = StringUtils.uncapitalize(clazz.simpleName)
		String path = appendPiecesForUri(BLOCK_DIR, dir, template)
		return path
	}

}
