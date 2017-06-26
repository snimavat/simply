package org.simply.cms.pages

import org.simply.cms.block.FlexiBlock
import org.hibernate.JsonUserType

class FlexiPage extends Page {

	String intro
	FlexiBlock body

	static mapping = {
		body type: JsonUserType, params: [clazz: FlexiBlock.name]
		intro type: "text"
	}

	static constraints = {
		body nullable: true
		intro nullable: true, blank: false, widget: "textarea"

	}

}
