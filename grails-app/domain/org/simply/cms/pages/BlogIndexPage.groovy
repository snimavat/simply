package org.simply.cms.pages

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

import javax.servlet.http.HttpServletRequest

@GrailsCompileStatic
class BlogIndexPage extends Page {

	String intro

	static mapping = {
		intro type: "text"
	}

	static constraints = {
		intro nullable: true, blank: false, widget: "textarea"
	}

	@Override
	@CompileStatic(TypeCheckingMode.SKIP)
	Map getContext(HttpServletRequest request, Map params) {
		Map context = super.getContext(request, params)
		int max = params.int("max") ?: 10
		int offset = params.int("offset") ?: 0

		PagedResultList<BlogPage> pages = childs.list(max: max, offset: offset) {
			eq "published", true
		}

		context['childs'] = pages
		context['totalCount'] = pages.totalCount

		return context
	}

}
