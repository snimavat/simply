package org.simply.cms

import org.simply.cms.block.FlexiBlock
import grails.databinding.DataBinder
import grails.databinding.SimpleMapDataBindingSource
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import grails.web.servlet.mvc.GrailsParameterMap
import org.simply.cms.block.StringBlock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpServletRequest
import spock.lang.Ignore
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

@Integration
@Rollback
@Ignore
class BlockBindingSpec extends Specification {

	@Autowired
	DataBinder grailsWebDataBinder

	@Autowired
	BlockService blockService

	def "test list binding"() {
		Map dict = [
				"body.blocks[0].type": 'h1',
				"body.blocks[0].value": 'test',
				"body.blocks[1].type": 'h1',
				"body.blocks[1].value": 'test2',
				"body.blocks[2].type": 'code',
				"body.blocks[2].language": 'Java',
				"body.blocks[2].code": 'println'
		]

		when:
		FlexiBlock block = new FlexiBlock()
		HttpServletRequest request = new MockHttpServletRequest()
		request.setParameters(dict)
		GrailsParameterMap params = new GrailsParameterMap(request)
		blockService.bindFromParams(params.body as SimpleMapDataBindingSource, block)

		then:
		noExceptionThrown()
		block.blocks.size() == 3
		(block.blocks[0] instanceof StringBlock)
		block.blocks[0].value == "test"

	}

}

