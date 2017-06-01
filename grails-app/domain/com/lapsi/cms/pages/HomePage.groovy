package com.lapsi.cms.pages

/**
 * Created by sudhir on 01/06/17.
 */
class HomePage extends Page {
	static scaffold = [include:['slug', 'title', 'published'], exclude:['urlPath']]
}
