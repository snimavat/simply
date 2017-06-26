package org.simply.cms.block

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class FlexiBlock {
	List blocks

	Block put(int index, Block block) {
		if(blocks == null) blocks = []
		blocks[index] = block
		block
	}

	Block get(int index) {
		if(blocks == null) blocks = []
		return blocks[index]
	}

	Block remove(int index) {
		if(blocks != null && blocks.size() >= index) return blocks.remove(index)
		else return null
	}

}





