package org.simply.cms.pages

import groovy.transform.CompileStatic

@CompileStatic
class RouteResult {
	Page page
	Map model
	String view

	public RouteResult(Page p, String v, Map model) {
		this.page = p
		this.view = v
		this.model = model
	}
}
