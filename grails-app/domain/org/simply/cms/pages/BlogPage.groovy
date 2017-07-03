package org.simply.cms.pages

class BlogPage extends Page implements Book {

	BlogCategory category
	String intro
	List<Block> body

	static embedded = ['body']

	static mapping = {
		//body type: JsonUserType, params: [clazz: FlexiBlock.name]
		intro type: "text"
	}

	static constraints = {
		body nullable: true
		intro nullable: false, blank: false, widget: "textarea"
		category nullable: true
		body widget:"flexiField"
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

	static BlogCategory findByName(String name) {
		return values().find({it.name == name})
	}



}
