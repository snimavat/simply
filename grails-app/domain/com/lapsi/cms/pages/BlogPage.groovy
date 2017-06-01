package com.lapsi.cms.pages

class BlogPage extends Page {

	static scaffold = [include:['slug', 'title', 'category', 'author', 'body', 'published'], exclude:['urlPath']]

	String category
	String author
	String body

	static constraints = {
		category nullable: true
		author nullable: true
		body nullable: false, widget:"textarea"
	}
}
