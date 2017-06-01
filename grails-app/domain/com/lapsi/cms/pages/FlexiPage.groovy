package com.lapsi.cms.pages

class FlexiPage extends Page {

	static scaffold = [include:['slug', 'title', 'body', 'published'], exclude:['urlPath']]

	String body

	static constraints = {
		body nullable: false, widget:"textarea", type:"text"
	}
}
