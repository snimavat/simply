package org.simply.cms.pages


class HomePage extends Page {
	static scaffold = [include:['slug', 'title', 'published'], exclude:['urlPath']]
}
