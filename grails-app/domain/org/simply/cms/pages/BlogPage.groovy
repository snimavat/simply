package org.simply.cms.pages

import org.simply.cms.block.FlexiBlock
import org.hibernate.JsonUserType

class BlogPage extends Page {

	BlogCategory category
	String intro
	FlexiBlock body

	static mapping = {
		body type: JsonUserType, params: [clazz: FlexiBlock.name]
		intro type: "text"
	}

	static constraints = {
		body nullable: true
		intro nullable: true, blank: false, widget: "textarea"
		category nullable: true
	}

}

enum BlogCategory {
	JAVA("Gradle", 0),
	GRAILS("Grails", 1),
	GORM("Gorm", 2),
	GROOVY("Groovy", 3),
	GRADLE("Gradle", 4),
	RECIPES("Recipes", 5)

	long id
	String name

	BlogCategory(String name, id) {
		this.name = name
		this.id = id
	}


}
