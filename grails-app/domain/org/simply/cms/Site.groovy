package org.simply.cms

import org.simply.cms.pages.Page


class Site {
	String name
	String hostname

	Page rootPage

	Boolean isDefault = false

	static transients = ['url']

	static constraints = {
		name nullable: false, blank: false
		hostname nullable: false, blank: false
		rootPage nullable: false
		isDefault nullable: false
	}

	static mapping = {
		cache true
	}

	public String getUrl() {
		return "http://${hostname}/"
	}
}
